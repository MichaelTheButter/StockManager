package com.stockmanager.domain.user;

import com.stockmanager.domain.user.dto.UserCredentialsDto;
import com.stockmanager.domain.user.dto.UserDto;
import com.stockmanager.infrastructure.controllers.exceptionhandling.UniqueConstraintException;
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
        checkIfUsernameAlreadyUsed(userDto.getUserName());
        User user = UserCredentialsDtoMapper.mapToCreate(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return UserCredentialsDtoMapper.mapToResponse(savedUser);
    }

    private void checkIfUsernameAlreadyUsed(String userName) {
        if(userRepository.existsByUserName(userName)) {
            throw new UniqueConstraintException("username already exist");
        }
    }


}
