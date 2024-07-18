package com.events.eventmanagement.category.service.impl;

import com.events.eventmanagement.category.dto.CategoryRequestDto;
import com.events.eventmanagement.category.dto.CategoryResponseDto;
import com.events.eventmanagement.category.entity.Category;
import com.events.eventmanagement.category.repository.CategoryRepository;
import com.events.eventmanagement.category.service.CategoryService;
import com.events.eventmanagement.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryResponseDto::toDto).toList();
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category newCategory = categoryRequestDto.toEntity();

        return CategoryResponseDto.toDto(newCategory);
    }
}
