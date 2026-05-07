package com.ws101.Tan.EcommerceApi.repository;

import com.ws101.Tan.EcommerceApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}