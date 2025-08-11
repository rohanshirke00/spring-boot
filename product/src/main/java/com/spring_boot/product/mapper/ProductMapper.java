package com.spring_boot.product.mapper;

import com.spring_boot.product.dto.ProductDTO;
import com.spring_boot.product.entity.Category;
import com.spring_boot.product.entity.Product;

public class ProductMapper {

    // entity to DTO
    public static ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryId(product.getCategory().getId());

        return productDTO;

    }

    // DTO to entity
    public static Product toProductEntity(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        return product;

    }

}
