package com.workintech.s20challenge.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(Long id, @NotBlank(message = "Category name cannot be blank") String name, String description) {}

