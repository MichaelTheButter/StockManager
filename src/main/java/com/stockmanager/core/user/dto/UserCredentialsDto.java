package com.stockmanager.core.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserCredentialsDto {
    private String userName;
    private String password;
    private Set<String> roles;
}
