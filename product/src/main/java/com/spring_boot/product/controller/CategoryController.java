package com.spring_boot.product.controller;

import com.spring_boot.product.dto.CategoryDTO;
import com.spring_boot.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    // get all categories
    @GetMapping
    public List<CategoryDTO> getCategories(){
        return categoryService.getCategories();
    }

    // create category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    // show single category

    // update category

    // delete category


}
