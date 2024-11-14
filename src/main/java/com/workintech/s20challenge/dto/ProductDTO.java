package com.workintech.s20challenge.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record ProductDTO(Long id, @NotBlank(message = "Product name cannot be blank") String name, @DecimalMin(value = "0.0", message = "Price must be greater than 0") BigDecimal price, String description, Long categoryId) {}

