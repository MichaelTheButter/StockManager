package com.stockmanager.core.user;

import com.stockmanager.core.user.dto.UserCredentialsDto;
import com.stockmanager.core.user.dto.UserDto;
import com.stockmanager.core.user.userrole.UserRole;

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

    static UserDto mapToResponse(User user){
        return UserDto.builder()
                .userName(user.getUserName())
                .userRole(user.getUserRole().getDescription())
                .password("***")
                .build();
    }

    static User mapToCreate(UserDto requestDto) {
        User user = new User();
        user.setUserName(requestDto.getUserName());
        UserRole role = UserRole.valueOf(requestDto.getUserRole());
        user.setUserRole(role);
        return user;
    }



}
