package com.ws101.Tan.EcommerceApi.controller;

import com.ws101.Tan.EcommerceApi.model.Product;
import com.ws101.Tan.EcommerceApi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(product);
    }

    // FILTER products
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        List<Product> result = switch (filterType.toLowerCase()) {
            case "category" -> productService.filterByCategory(filterValue);
            case "name" -> productService.filterByName(filterValue);
            case "price" -> {
                String[] range = filterValue.split("-");
                yield productService.filterByPrice(
                        Double.parseDouble(range[0]),
                        Double.parseDouble(range[1])
                );
            }
            default -> List.of();
        };

        return ResponseEntity.ok(result);
    }

    // CREATE product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT (replace full product)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        Product updated = productService.updateProduct(id, product);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updated);
    }

    // PATCH (partial update)
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        Product existing = productService.getProductById(id);

        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (product.getName() != null) existing.setName(product.getName());
        if (product.getDescription() != null) existing.setDescription(product.getDescription());
        if (product.getPrice() != 0) existing.setPrice(product.getPrice());
        if (product.getCategory() != null) existing.setCategory(product.getCategory());
        if (product.getStockQuantity() != 0) existing.setStockQuantity(product.getStockQuantity());
        if (product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        return ResponseEntity.ok(existing);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        boolean deleted = productService.deleteProduct(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}