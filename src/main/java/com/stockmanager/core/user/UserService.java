package com.stockmanager.core.user;

import com.stockmanager.core.user.dto.UserCredentialsDto;
import com.stockmanager.core.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Optional<UserCredentialsDto> findCredentialsByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .map(UserCredentialsDtoMapper::map);
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = UserCredentialsDtoMapper.mapToCreate(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return UserCredentialsDtoMapper.mapToResponse(savedUser);

    }

}
