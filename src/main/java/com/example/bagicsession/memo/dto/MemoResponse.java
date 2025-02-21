package com.example.bagicsession.memo.dto;

import lombok.Getter;

@Getter
public class MemoResponse {

    private final Long id;
    private final String todo;

    public MemoResponse(Long id, String todo) {
        this.id = id;
        this.todo = todo;
    }
}
