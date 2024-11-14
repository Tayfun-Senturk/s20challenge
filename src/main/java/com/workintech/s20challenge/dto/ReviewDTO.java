package com.workintech.s20challenge.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewDTO(Long id, Long userId, Long productId, @NotNull @Min(value = 1, message = "Rating should not be less than 1") @Max(value = 5, message = "Rating should not be more than 5") Integer rating, @NotBlank(message = "Comment cannot be blank") String comment) {}

