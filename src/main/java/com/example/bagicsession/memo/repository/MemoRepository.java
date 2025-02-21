package com.example.bagicsession.memo.repository;

import com.example.bagicsession.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
