package com.ws101.Tan.EcommerceApi.service;

import com.ws101.Tan.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    /*
     * ProductService uses in-memory storage (ArrayList)
     * instead of a database.
     * Data is temporary and will reset when the application restarts.
     */

    // In-memory storage using ArrayList (no database)
    private final List<Product> products = new ArrayList<>();

    // ID counter for unique product IDs (no auto-increment DB)
    private Long idCounter = 11L;

    // Constructor to initialize sample data
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

    // Get all products
    public List<Product> getAllProducts() {
        return products;
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create product (with ID generation)
    public Product createProduct(Product product) {
        product.setId(idCounter++);
        products.add(product);
        return product;
    }

    // Update product
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

    // Delete product
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    // Filter by category
    public List<Product> filterByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Filter by name
    public List<Product> filterByName(String name) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Filter by price range
    public List<Product> filterByPrice(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}