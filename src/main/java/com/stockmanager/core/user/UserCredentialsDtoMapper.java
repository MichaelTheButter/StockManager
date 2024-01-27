package com.stockmanager.core.user;

import com.stockmanager.core.user.dto.UserCredentialsDto;

class UserCredentialsDtoMapper {

    static UserCredentialsDto map(User user) {
        return UserCredentialsDto.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(user.getUserRole())
                .build();
    }
    static User map(UserCredentialsDto credentialsDto) {
        User user = new User();
        user.setUserName(credentialsDto.getUserName());
        user.setPassword(credentialsDto.getPassword());
        user.setUserRole(credentialsDto.getRole());
        return user;
    }

}
