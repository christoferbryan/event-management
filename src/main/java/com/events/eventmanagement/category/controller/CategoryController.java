package com.events.eventmanagement.category.controller;

import com.events.eventmanagement.category.dto.CategoryRequestDto;
import com.events.eventmanagement.category.service.CategoryService;
import com.events.eventmanagement.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        return Response.successResponse("Category with id " + id + " retrieved successfully", categoryService.getCategoryById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(){
        return Response.successResponse("All categories retrieved successfully", categoryService.getAllCategories());
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        return Response.successResponse("Category created successfully", categoryService.createCategory(categoryRequestDto));
    }
}
