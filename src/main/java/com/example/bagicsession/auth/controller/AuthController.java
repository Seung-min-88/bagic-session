package com.example.bagicsession.auth.controller;

import com.example.bagicsession.auth.dto.AuthLoginRequest;
import com.example.bagicsession.auth.dto.AuthLoginResponse;
import com.example.bagicsession.auth.dto.AuthSignupRequest;
import com.example.bagicsession.auth.service.AuthService;
import com.example.bagicsession.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequest dto) {
        authService.signup(dto);
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthLoginRequest dto, HttpServletRequest request) {
        AuthLoginResponse result = authService.login(dto);

        // 세션을 생성을 해야함
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId());
        // 이건 서비스가 인즐하지 말고 컨트롤러가 해야하는게 맞다
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
