package com.example.bagicsession.memo.dto;

import lombok.Getter;

@Getter
public class MemoSaveRequest {

    private String content;

    public MemoSaveRequest(String content) {
        this.content = content;
    }
}
