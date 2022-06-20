package com.study.memo.controller;

import com.study.memo.controller.dto.CategoryDTO;
import com.study.memo.entity.Category;
import com.study.memo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public Category createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("")
    public Page<Category> getCategories(Pageable pageable, @RequestParam String keyword){
        return categoryService.getCategories(pageable, keyword);
    }
}
