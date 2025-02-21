package com.example.bagicsession.memo.controller;

import com.example.bagicsession.common.consts.Const;
import com.example.bagicsession.memo.dto.*;
import com.example.bagicsession.memo.service.MemoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memos")
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public ResponseEntity<MemoSaveResponse> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemoSaveRequest dto) {
        return ResponseEntity.ok(memoService.save(memberId,dto));
    }

    @GetMapping
    public ResponseEntity<List<MemoResponse>> getAll() {
        return ResponseEntity.ok(memoService.findAll());
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<MemoResponse> getOne(@PathVariable Long memoId) {
        return ResponseEntity.ok(memoService.findById(memoId));
    }

    @PutMapping("/{memoId}")
    public ResponseEntity<MemoUpdateResponse> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long memoId,
            @RequestBody MemoUpdateRequest dto) {
        return ResponseEntity.ok(memoService.update(memberId, memoId, dto));
    }

    @DeleteMapping("/{memoId}")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long memoId) {
        memoService.deleteById(memberId, memoId);
    }
}
