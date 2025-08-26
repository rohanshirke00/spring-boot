package com.spring_boot.product.service;

import com.spring_boot.product.dto.CategoryDTO;
import com.spring_boot.product.entity.Category;
import com.spring_boot.product.exception.CategoryAlreadyExistsException;
import com.spring_boot.product.exception.CategoryNotFoundException;
import com.spring_boot.product.mapper.CategoryMapper;
import com.spring_boot.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    // create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){

        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw  new CategoryAlreadyExistsException("Category " + categoryDTO.getName() + " already exists");
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    // get all categories
    public List<CategoryDTO> getCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
    }

    // get category by id
    public CategoryDTO getCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category id: " + id + " not found!"));
        return CategoryMapper.toCategoryDTO(category);
    }

    // update category
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category id: " + id + " not found!"));
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);

    }

    // delete category
    public String deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category id: " + id + " not found!"));
        categoryRepository.deleteById(id);
        return "Category with id: " + id + " has been deleted";
    }



}
