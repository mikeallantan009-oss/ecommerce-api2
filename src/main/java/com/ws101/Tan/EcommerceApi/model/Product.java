package com.ws101.Tan.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required and must not be empty")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be a positive number")
    private Double price;

    /**
     * Product entity representing items available in the e-commerce system.
     * Many Products belong to one Category.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @Min(value = 0, message = "Stock quantity must be non-negative")
    private int stockQuantity;

    private String imageUrl;

    public Product(long id, String phone, String smartphone, int i, String electronics, int stockQuantity, String imageUrl) {
    }

    public void setCategory(@NotBlank(message = "Category is required") String category) {
    }

    public void setCategory(Category category) {
    }
}