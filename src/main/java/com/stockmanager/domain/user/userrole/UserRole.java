package com.stockmanager.domain.user.userrole;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ADMIN"), USER("USER"), VIEWER("VIEWER");

    private String description;
}
