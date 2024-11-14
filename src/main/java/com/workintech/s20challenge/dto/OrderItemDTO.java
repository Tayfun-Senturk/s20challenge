package com.workintech.s20challenge.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record OrderItemDTO(Long id, @NotNull(message = "Order ID cannot be null") Long orderId, @NotNull(message = "Product ID cannot be null") Long productId, @Positive(message = "Quantity must be positive") int quantity) {}
