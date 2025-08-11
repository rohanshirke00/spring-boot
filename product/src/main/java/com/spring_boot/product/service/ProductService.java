package com.spring_boot.product.service;

import com.spring_boot.product.dto.ProductDTO;
import com.spring_boot.product.entity.Category;
import com.spring_boot.product.entity.Product;
import com.spring_boot.product.mapper.ProductMapper;
import com.spring_boot.product.repository.CategoryRepository;
import com.spring_boot.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    // create a product
    public ProductDTO createProduct(ProductDTO productDTO){
        // from payload we get name, description, price, categoryId
        // now check whether category exist or not using categoryId

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found!"));

        // DTO -> Entity
        Product product = ProductMapper.toProductEntity(productDTO, category);

        // save the database
        product = productRepository.save(product);

        // Entity -> DTO
        return ProductMapper.toProductDTO(product);

    }


    // get all products

    // get a product by id

    // update a product

    // delete a product

}
