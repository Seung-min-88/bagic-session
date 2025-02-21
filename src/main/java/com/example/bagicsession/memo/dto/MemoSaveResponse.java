package com.example.bagicsession.memo.dto;

import com.example.bagicsession.member.entity.Member;
import lombok.Getter;

@Getter
public class MemoSaveResponse {

    private final Long id;
    private final String content;
    private final Long memberId;

    public MemoSaveResponse(Long id, String content, Long memberId) {
        this.id = id;
        this.content = content;
        this.memberId =  memberId;
    }
}
