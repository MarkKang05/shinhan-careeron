package com.study.memo.service;

import com.study.memo.controller.dto.MemoDTO;
import com.study.memo.entity.Category;
import com.study.memo.entity.Memo;
import com.study.memo.repository.CategoryRepository;
import com.study.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Memo createMemo(MemoDTO memoDTO) {
        Optional<Category> category = categoryRepository.findById(memoDTO.getCategoryId());
        if( category.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found");
        }

        Memo memo = Memo.builder()
                .category(category.get())
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build();

        return memoRepository.save(memo);
    }

    public Memo getMemoById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MemoId not found"));
    }

    public Page<Memo> getMemos(Pageable pageable, String keyword) {
        if(keyword == null ){
            return memoRepository.findAll(pageable);
        }else{
            return memoRepository.findByNameContains(pageable, keyword);
        }
    }

    public Memo updateMemoById(Long id, MemoDTO memoDTO) {
        Optional<Memo> memo = memoRepository.findById(id);
        if(memo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MemoId not found");
        }

        Optional<Category> category = categoryRepository.findById(memoDTO.getCategoryId());
        if( category.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found");
        }

        Memo buildMemo = Memo.builder()
                .id(id)
                .category(category.get())
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build();

        return memoRepository.save(buildMemo);

    }

    public void deleteMemoById(Long id) {
        memoRepository.deleteById(id);
    }
}
