package com.events.eventmanagement.category.service;

import com.events.eventmanagement.category.dto.CategoryRequestDto;
import com.events.eventmanagement.category.dto.CategoryResponseDto;
import com.events.eventmanagement.category.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
}
