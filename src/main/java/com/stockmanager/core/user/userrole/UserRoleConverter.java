package com.stockmanager.core.user.userrole;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String>{
    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null) return null;
        return userRole.getDescription();
    }

    @Override
    public UserRole convertToEntityAttribute(String description) {
        return Stream.of(UserRole.values())
                .filter(userRole -> userRole.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
