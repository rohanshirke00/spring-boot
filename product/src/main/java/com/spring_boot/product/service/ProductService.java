package com.spring_boot.product.service;

import com.spring_boot.product.dto.ProductDTO;
import com.spring_boot.product.entity.Category;
import com.spring_boot.product.entity.Product;
import com.spring_boot.product.exception.CategoryNotFoundException;
import com.spring_boot.product.exception.ProductNotFoundException;
import com.spring_boot.product.mapper.ProductMapper;
import com.spring_boot.product.repository.CategoryRepository;
import com.spring_boot.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    // create a product
    public ProductDTO createProduct(ProductDTO productDTO){
        // from payload we get name, description, price, categoryId
        // now check whether category exist or not using categoryId

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("Category id: " + productDTO.getCategoryId() + " not found!"));

        // DTO -> Entity
        Product product = ProductMapper.toProductEntity(productDTO, category);

        // save the database
        product = productRepository.save(product);

        // Entity -> DTO
        return ProductMapper.toProductDTO(product);

    }


    // get all products
    public List<ProductDTO> getProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    // get a product by id
    public ProductDTO getProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product id: " + id + " not found!"));
        return ProductMapper.toProductDTO(product);
    }

    // update a product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product id: " + id + " not found!"));
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("Category id: " + productDTO.getCategoryId() + " not found!"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);

        return ProductMapper.toProductDTO(product);
    }

    // delete a product
    public String deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product id: " + id + " not found!"));
        productRepository.deleteById(id);
        return "Product with id: " + id + " has been deleted";
    }

}
