package com.study.memo.service;

import com.study.memo.controller.dto.CategoryDTO;
import com.study.memo.entity.Category;
import com.study.memo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(CategoryDTO categoryDTO){
        Optional<Category> findOne = categoryRepository.findByName(categoryDTO.getName());
        if(findOne.isPresent()){ // 데이터가 이미 존재하면 Exception을 발생시키고 종료
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이름입니다.");
        }
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        category = categoryRepository.save(category);

        return category;
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found"));
    }

    public Page<Category> getCategories(Pageable pageable, String keyword) {
        if( keyword == null) {
            return categoryRepository.findAll(pageable);
        }else{
            return categoryRepository.findByNameContains(pageable, keyword);
        }
    }

    public Category updateCategoryById(Long id, CategoryDTO categoryDTO){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found");
        }

        Category buildCategory = Category.builder()
                .id(id)
                .name(categoryDTO.getName())
                .build();

        buildCategory = categoryRepository.save(buildCategory);

        return buildCategory;

    }

    public void deleteCategoryByid(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CategoryId not found");
        }
        categoryRepository.deleteById(id);
    }
}
