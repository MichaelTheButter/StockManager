package com.stockmanager.core.user;

import com.stockmanager.core.user.dto.UserCredentialsDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class UserCredentialsDtoMapper {
    private  UserRoleRepository userRoleRepository;

    public UserCredentialsDtoMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    static UserCredentialsDto map(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(userName, password, roles);
    }

}
