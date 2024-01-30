package com.stockmanager.config.security.dto;

public record LoginRequestDto(
        String userName,
        String password
) {
}
