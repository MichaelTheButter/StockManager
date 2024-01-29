package com.stockmanager.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "jws")
public record JwsProperties(
        String sharedKey,
        int expirationSecond
) {}
