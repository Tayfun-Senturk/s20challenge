package com.workintech.s20challenge.dto;


import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderDTO(Long id, @NotNull(message = "User ID cannot be null") Long userId, List<Long> productIds, String orderStatus) {}

