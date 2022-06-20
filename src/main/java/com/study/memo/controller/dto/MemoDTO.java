package com.study.memo.controller.dto;

import com.study.memo.entity.Category;
import lombok.Data;

import javax.persistence.*;

@Data
public class MemoDTO {

    private Long categoryId;
    private String name;
    private String content;

}
