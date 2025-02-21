package com.example.bagicsession.member.service;

import com.example.bagicsession.member.dto.MemberResponse;
import com.example.bagicsession.member.dto.MemberSaveRequest;
import com.example.bagicsession.member.dto.MemberSaveResponse;
import com.example.bagicsession.member.dto.MemberUpdateRequest;
import com.example.bagicsession.member.entity.Member;
import com.example.bagicsession.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public List<MemberResponse> findAll() {
        List<Member> members = memberRepository.findAll();

//        List<MemberResponse> dtos = new ArrayList<>();
//        for (Member member : members) {
//            dtos.add(new MemberResponse(
//                    member.getId(),
//                    member.getEmail()
//            ));
//        }
//        return dtos;

        return members.stream().map(
                member -> new MemberResponse(
                        member.getId(), member.getEmail())).toList();
    }

    @Transactional
    public MemberResponse findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 멤버는 존재하지 않습니다"));
        return new MemberResponse(member.getId(), member.getEmail());
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequest dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 멤버는 존재하지 않습니다"));
        member.update(dto.getEmail());
    }

    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
