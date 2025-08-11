package com.spring_boot.product.service;

import com.spring_boot.product.dto.CategoryDTO;
import com.spring_boot.product.entity.Category;
import com.spring_boot.product.mapper.CategoryMapper;
import com.spring_boot.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    // create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    // get all categories

    // get category by id

    // update category

    // delete category



}
