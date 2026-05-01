package com.ws101.Tan.EcommerceApi.repository;

import com.ws101.Tan.EcommerceApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}