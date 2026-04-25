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

    /*
     HTTP Status Codes Used:
     200 OK -> successful retrieval/update
     201 Created -> successful creation
     204 No Content -> successful deletion
     400 Bad Request -> invalid request data
     404 Not Found -> requested product does not exist
     500 Internal Server Error -> unexpected server-side failure
    */

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET all products -> 200 OK / 500 Internal Server Error
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return ResponseEntity.ok(productService.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET product by ID -> 200 OK / 404 Not Found / 500 Internal Server Error
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);

            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // FILTER products -> 200 OK / 400 Bad Request
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        try {
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

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // CREATE product -> 201 Created / 400 Bad Request / 500 Internal Server Error
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            if (product.getName() == null || product.getName().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Product created = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT replace product -> 200 OK / 404 Not Found / 500 Internal Server Error
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        try {
            Product updated = productService.updateProduct(id, product);

            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PATCH partial update -> 200 OK / 404 Not Found / 500 Internal Server Error
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        try {
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

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE product -> 204 No Content / 404 Not Found / 500 Internal Server Error
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            boolean deleted = productService.deleteProduct(id);

            if (!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}