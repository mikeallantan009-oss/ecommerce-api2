package com.ws101.Tan.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * OrderItem entity representing a product entry inside an order.
 * Many OrderItems belong to one Order.
 */

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}