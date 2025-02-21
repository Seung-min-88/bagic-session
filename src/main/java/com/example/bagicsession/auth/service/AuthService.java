package com.example.bagicsession.auth.service;

import com.example.bagicsession.auth.dto.AuthLoginRequest;
import com.example.bagicsession.auth.dto.AuthLoginResponse;
import com.example.bagicsession.auth.dto.AuthSignupRequest;
import com.example.bagicsession.member.entity.Member;
import com.example.bagicsession.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequest dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }

    @Transactional
    public AuthLoginResponse login(AuthLoginRequest dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 이메일의 멤버가 존재하지 않습니다"));
        return new AuthLoginResponse(member.getId());
    }
}
