package com.ws101.Tan.EcommerceApi.repository;

import com.ws101.Tan.EcommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Product entity.
 * Provides CRUD operations and custom query methods.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds products by category name using method naming convention.
     *
     * Example:
     * Product -> category -> name
     */
    List<Product> findByCategory_Name(String name);

    /**
     * Finds products within a price range using JPQL.
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findProductsByPriceRange(Double min, Double max);
}