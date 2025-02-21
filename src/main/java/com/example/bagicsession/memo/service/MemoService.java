package com.example.bagicsession.memo.service;

import com.example.bagicsession.member.entity.Member;
import com.example.bagicsession.member.repository.MemberRepository;
import com.example.bagicsession.memo.dto.*;
import com.example.bagicsession.memo.entity.Memo;
import com.example.bagicsession.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemoSaveResponse save(Long memberId, MemoSaveRequest dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 멤버는 존재하지 않습니다"));
        Memo memo = new Memo(dto.getContent(), member);
        Memo saveMemo = memoRepository.save(memo);

        return new MemoSaveResponse(
                saveMemo.getId(),
                saveMemo.getContent(),
                saveMemo.getMember().getId()
        );
    }

    @Transactional(readOnly = true)
    public List<MemoResponse> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponse> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            dtos.add(new MemoResponse(
                    memo.getId(),
                    memo.getContent()
            ));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponse findById(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalArgumentException("작성하고 찾으세연")
        );
        return new MemoResponse(
                memo.getId(),
                memo.getContent()
        );
    }

    @Transactional
    public MemoUpdateResponse update(Long memberId, Long memoId, MemoUpdateRequest dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 멤버는 존재하지 않습니다"));
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalArgumentException("작성하고 찾으세연")
        );

        if (!memo.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("작성자 외 수정이 불가합니다");
        }

        memo.update(dto.getContent());
        return new MemoUpdateResponse(memo.getId(), memo.getContent());
    }

    @Transactional
    public void deleteById(Long memberId, Long memoId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 멤버는 존재하지 않습니다"));
        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalArgumentException("작성하고 찾으세연")
        );

        if (!memo.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("작성자 외 삭제는 불가합니다");
        }
        memoRepository.deleteById(memoId);
    }
}
