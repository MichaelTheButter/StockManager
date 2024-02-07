package com.stockmanager.config.security.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record LoginRequestDto(
        @NotNull(message = "userName required.")
        @Size(max = 100, message = "userName must not exceed 100 characters.")
        String userName,
        @NotNull(message = "password required.")
        @Size(max = 50, message = "password must not exceed 50 characters.")
        String password
) {
}
