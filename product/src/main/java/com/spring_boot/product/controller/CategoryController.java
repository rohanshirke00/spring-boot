package com.spring_boot.product.controller;

import com.spring_boot.product.dto.CategoryDTO;
import com.spring_boot.product.exception.CategoryAlreadyExistsException;
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
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){

        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        // return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    // show single category
    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    // update category
    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(id, categoryDTO);
    }

    // delete category
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

}
