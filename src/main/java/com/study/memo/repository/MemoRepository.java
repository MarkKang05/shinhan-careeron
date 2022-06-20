package com.study.memo.repository;

import com.study.memo.entity.Category;
import com.study.memo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    Optional<Memo> findByName(String name);
    Page<Memo> findByNameContains(Pageable pageable, String keyword);
}
