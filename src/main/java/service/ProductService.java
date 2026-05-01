package com.ws101.Tan.EcommerceApi.service;

import com.ws101.Tan.EcommerceApi.model.Product;
import com.ws101.Tan.EcommerceApi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for product-related operations.
 *
 * Provides business logic for managing, filtering, and retrieving products.
 * Uses Spring Data JPA repository instead of in-memory storage.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor-based dependency injection
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products.
     *
     * @return list of all products from database
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the product ID
     * @return the matching Product or null if not found
     */
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    /**
     * Creates a new product.
     *
     * @param product the product to add
     * @return the created product
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id the product ID to update
     * @param updatedProduct new product data
     * @return updated Product or null if not found
     */
    public Product updateProduct(Long id, Product updatedProduct) {

        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product existing = existingProduct.get();

            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setCategory(updatedProduct.getCategory());
            existing.setStockQuantity(updatedProduct.getStockQuantity());
            existing.setImageUrl(updatedProduct.getImageUrl());

            return productRepository.save(existing);
        }

        return null;
    }

    /**
     * Deletes a product by ID.
     *
     * @param id product ID to remove
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteProduct(Long id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }

    /**
     * Filters products by category.
     *
     * @param category product category
     * @return list of matching products
     */
    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategory_Name(category);
    }

    /**
     * Filters products by price range.
     *
     * @param min minimum price
     * @param max maximum price
     * @return list of products within range
     */
    public List<Product> filterByPrice(double min, double max) {
        return productRepository.findProductsByPriceRange(min, max);
    }
}