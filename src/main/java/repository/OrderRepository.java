package com.ws101.Tan.EcommerceApi.repository;

import com.ws101.Tan.EcommerceApi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}