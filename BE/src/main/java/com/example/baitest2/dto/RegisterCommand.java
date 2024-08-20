package com.example.baitest2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterCommand(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Email is required")
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format") String email,
        @NotBlank(message = "Password is required")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",
                message = "Password must be 8-20 characters and contain at least one uppercase letter, one lowercase letter, one number, and one special character") String password
) {}

