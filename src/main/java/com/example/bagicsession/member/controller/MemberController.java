package com.example.bagicsession.member.controller;

import com.example.bagicsession.common.consts.Const;
import com.example.bagicsession.member.dto.MemberResponse;
import com.example.bagicsession.member.dto.MemberSaveRequest;
import com.example.bagicsession.member.dto.MemberSaveResponse;
import com.example.bagicsession.member.dto.MemberUpdateRequest;
import com.example.bagicsession.member.entity.Member;
import com.example.bagicsession.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getOne(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    @PutMapping
    public void update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequest dto) {

        memberService.update(memberId, dto);
    }

    @DeleteMapping
    public void delete(@SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId) {
        memberService.deleteById(memberId);
    }
}
