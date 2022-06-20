package com.study.memo.controller;

import com.study.memo.controller.dto.MemoDTO;
import com.study.memo.entity.Memo;
import com.study.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

   private final MemoService memoService;

   @PostMapping("")
   public Memo createMemo(@RequestBody MemoDTO memoDTO){
       return memoService.createMemo(memoDTO);
   }

    @GetMapping("/{id}")
    public Memo getMemoById(@PathVariable Long id){
        return memoService.getMemoById(id);
    }

    @GetMapping("")
    public Page<Memo> getMemos(Pageable pageable, @RequestParam String keyword){
        return memoService.getMemos(pageable, keyword);
    }

    @PutMapping("/{id}")
    public Memo updateMemoById(@PathVariable Long id, @RequestBody MemoDTO memoDTO){
        return memoService.updateMemoById(id, memoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMemoById(@PathVariable Long id){
       memoService.deleteMemoById(id);
    }

}
