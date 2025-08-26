package com.spring_boot.product.repository;

import com.spring_boot.product.dto.CategoryDTO;
import com.spring_boot.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

}
