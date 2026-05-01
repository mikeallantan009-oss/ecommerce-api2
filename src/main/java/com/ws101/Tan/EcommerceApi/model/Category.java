package com.ws101.Tan.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Category entity representing a product category.
 * One Category can have many Products.
 * This is mapped using a One-to-Many relationship.
 */

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    public boolean equalsIgnoreCase(String category) {
    }
}