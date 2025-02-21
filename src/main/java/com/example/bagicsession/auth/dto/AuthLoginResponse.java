package com.example.bagicsession.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponse {

    private final Long memberId;

    public AuthLoginResponse(Long memberId) {
        this.memberId = memberId;
    }
}
