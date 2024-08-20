package com.example.baitest2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginCommand(
        @NotBlank @JsonProperty("username_or_email") String usernameOrEmail,
        @NotBlank String password
) {}
