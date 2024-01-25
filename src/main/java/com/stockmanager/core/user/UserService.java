package com.stockmanager.core.user;

import com.stockmanager.core.user.dto.UserCredentialsDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserCredentialsDto> findCredentialsByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .map(UserCredentialsDtoMapper::map);
    }

}
