package com.example.bagicsession.member.dto;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long id;
    private final String email;

    public MemberResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
