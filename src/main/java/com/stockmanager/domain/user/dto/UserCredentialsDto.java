package com.stockmanager.domain.user.dto;

import com.stockmanager.domain.user.userrole.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCredentialsDto {
    private String userName;
    private String password;
    private UserRole role;
}
