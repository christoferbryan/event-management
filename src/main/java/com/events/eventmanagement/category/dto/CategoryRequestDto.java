package com.events.eventmanagement.category.dto;


import com.events.eventmanagement.category.entity.Category;
import lombok.Data;

@Data
public class CategoryRequestDto {
    private String name;

    public Category toEntity(){
        Category category = new Category();
        category.setName(name);

        return category;
    }
}
