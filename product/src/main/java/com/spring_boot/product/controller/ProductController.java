package com.spring_boot.product.controller;


import com.spring_boot.product.dto.ProductDTO;
import com.spring_boot.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name="Product REST API",
        description = "CRUD Operations for Product REST API"
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    // get all products
    @Operation(
            summary = "Get all Products",
            description = "This api is use to fetch all the products"
    )
    @GetMapping
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }

    // create product
    @Operation(
            summary = "Create a new product",
            description = "This api is use to create a new product"
    )
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO),HttpStatus.CREATED);
    }

    // get single product
    @Operation(
            summary = "Fetch a Product",
            description = "This api is use to fetch a product using productId"
    )
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    // update product
    @Operation(
            summary = "Update a Product",
            description = "This api is use to update a product using productId"
    )
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }

    // delete product
    @Operation(
            summary = "Delete a Product",
            description = "This api is use to delete a product using productId"
    )
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
