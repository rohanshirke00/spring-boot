package com.spring_boot.product.controller;

import com.spring_boot.product.dto.CategoryDTO;
import com.spring_boot.product.exception.CategoryAlreadyExistsException;
import com.spring_boot.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(
        name="Category REST API",
        description = "CRUD Operations for Category REST API"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    // get all categories
    @Operation(
            summary = "Get all Categories",
            description = "This api is use to fetch all the categories along with their products"
    )
    @GetMapping
    public List<CategoryDTO> getCategories(){
        return categoryService.getCategories();
    }

    // create category
    @Operation(
            summary = "Create a new Category",
            description = "This api is use to create a new category"
    )
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){

        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        // return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    // show single category
    @Operation(
            summary = "Fetch a single Category",
            description = "This api is use to fetch category using categoryId"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    // update category
    @Operation(
            summary = "Update a Category",
            description = "This api is use to update category using categoryId"
    )
    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(id, categoryDTO);
    }

    // delete category
    @Operation(
            summary = "Delete a Category",
            description = "This api is use to delete a category using categoryId"
    )
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

}
