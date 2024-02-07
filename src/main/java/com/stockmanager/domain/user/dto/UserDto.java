package com.stockmanager.domain.user.dto;

import com.stockmanager.config.enumValidator.constraints.ValueOfEnum;
import com.stockmanager.domain.user.userrole.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    @NotNull(message = "userName must not be null")
    @Size(max = 100, message = "password must not exceed 100 characters.")
    private String userName;
    @NotNull(message = "password must not be null")
    @Size(max = 50, message = "password must not exceed 50 characters.")
    private String password;
    @NotNull(message = "userRole must not be null")
    @ValueOfEnum(enumClass = UserRole.class, message = "userRole must be any of 'ADMIN'/'USER'/'VIEWER'")
    private String  userRole;
}
