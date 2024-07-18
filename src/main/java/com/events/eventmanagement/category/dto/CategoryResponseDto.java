package com.events.eventmanagement.category.dto;

import com.events.eventmanagement.category.entity.Category;
import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;

    public static CategoryResponseDto toDto(Category category){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());

        return categoryResponseDto;
    }
}
