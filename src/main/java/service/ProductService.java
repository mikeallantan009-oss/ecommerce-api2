package com.ws101.Tan.EcommerceApi.service;

import com.ws101.Tan.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for product-related operations.
 *
 * Provides business logic for managing, filtering, and retrieving products.
 * Uses in-memory storage (List<Product>) instead of a database.
 */
@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    /**
     * Initializes the product list with sample data.
     */
    public ProductService() {
        products.add(new Product(1L, "Laptop", "Gaming laptop", 75000, "Electronics", 10, null));
        products.add(new Product(2L, "Phone", "Smartphone", 30000, "Electronics", 20, null));
        products.add(new Product(3L, "Headphones", "Wireless headphones", 2500, "Accessories", 50, null));
        products.add(new Product(4L, "Keyboard", "Mechanical keyboard", 3500, "Accessories", 30, null));
        products.add(new Product(5L, "Mouse", "Gaming mouse", 1500, "Accessories", 40, null));
        products.add(new Product(6L, "Monitor", "24-inch monitor", 9000, "Electronics", 15, null));
        products.add(new Product(7L, "Chair", "Office chair", 5000, "Furniture", 12, null));
        products.add(new Product(8L, "Table", "Study table", 7000, "Furniture", 8, null));
        products.add(new Product(9L, "Notebook", "College notebook", 50, "Stationery", 200, null));
        products.add(new Product(10L, "Pen", "Ballpoint pen", 10, "Stationery", 500, null));
    }

    /**
     * Retrieves all products.
     *
     * @return list of all products in memory
     */
    public List<Product> getAllProducts() {
        return products;
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the product ID
     * @return the matching Product or null if not found
     */
    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Creates a new product.
     *
     * @param product the product to add
     * @return the created product
     */
    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    /**
     * Updates an existing product by ID.
     *
     * @param id the product ID to update
     * @param updatedProduct new product data
     * @return updated Product or null if not found
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setCategory(updatedProduct.getCategory());
            existing.setStockQuantity(updatedProduct.getStockQuantity());
            existing.setImageUrl(updatedProduct.getImageUrl());
        }

        return existing;
    }

    /**
     * Deletes a product by ID.
     *
     * @param id product ID to remove
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    /**
     * Filters products by category.
     *
     * @param category product category
     * @return list of matching products
     */
    public List<Product> filterByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Filters products by name keyword.
     *
     * @param name search keyword
     * @return list of matching products
     */
    public List<Product> filterByName(String name) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filters products by price range.
     *
     * @param min minimum price
     * @param max maximum price
     * @return list of products within range
     */
    public List<Product> filterByPrice(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}