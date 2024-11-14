package com.workintech.s20challenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(Long id, @NotBlank(message = "Name cannot be blank") String name, @Email(message = "Email should be valid") @NotBlank(message = "Email cannot be blank") String email, @NotBlank(message = "Password cannot be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password) {}


