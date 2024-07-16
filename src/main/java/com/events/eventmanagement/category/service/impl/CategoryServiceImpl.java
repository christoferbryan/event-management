package com.events.eventmanagement.category.service.impl;

import com.events.eventmanagement.category.entity.Category;
import com.events.eventmanagement.category.repository.CategoryRepository;
import com.events.eventmanagement.category.service.CategoryService;
import com.events.eventmanagement.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

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
}
