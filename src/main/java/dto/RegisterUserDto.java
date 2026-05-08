package com.ws101.Tan.EcommerceApi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserDto(

        @NotBlank(message = "Username is required")
        @Size(min = 8, max = 20, message = "Username must be 8-20 characters")
        String username,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Role is required")
        String role

) {
}