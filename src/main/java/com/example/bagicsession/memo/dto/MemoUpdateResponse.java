package com.example.bagicsession.memo.dto;

import lombok.Getter;

@Getter
public class MemoUpdateResponse {

    private Long id;
    private String content;

    public MemoUpdateResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
