package com.stockmanager.core.user.dto;

import com.stockmanager.core.user.userrole.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCredentialsDto {
    private String userName;
    private String password;
    private UserRole role;
}
