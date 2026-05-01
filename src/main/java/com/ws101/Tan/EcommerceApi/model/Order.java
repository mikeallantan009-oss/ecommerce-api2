package com.ws101.Tan.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Order entity representing a customer order.
 * One Order can contain multiple OrderItems.
 */

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}