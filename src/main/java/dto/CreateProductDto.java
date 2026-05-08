package com.ws101.Tan.EcommerceApi.dto;

import com.ws101.Tan.EcommerceApi.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateProductDto(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @Positive(message = "Price must be positive")
        double price,

        @NotBlank(message = "Category is required")
        String category,

        @Positive(message = "Stock quantity must be positive")
        int stockQuantity,

        String imageUrl

) {
}