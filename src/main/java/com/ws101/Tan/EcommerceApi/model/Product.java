package com.ws101.Tan.EcommerceApi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    @NotBlank(message = "Product name is required and must not be empty")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be a positive number")
    private Double price;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 0, message = "Stock quantity must be non-negative")
    private int stockQuantity;

    private String imageUrl;

    public Product(long id, String phone, String smartphone, int i, String electronics, int stockQuantity, String imageUrl) {
    }
}