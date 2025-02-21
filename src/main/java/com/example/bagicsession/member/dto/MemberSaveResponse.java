package com.example.bagicsession.member.dto;

import lombok.Getter;

@Getter
public class MemberSaveResponse {

    private final Long id;
    private final String email;

    public MemberSaveResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
