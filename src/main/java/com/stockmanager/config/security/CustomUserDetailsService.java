package com.stockmanager.config.security;

import com.stockmanager.core.user.UserService;
import com.stockmanager.core.user.dto.UserCredentialsDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private final UserService userService;
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findCredentialsByUserName(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }

    private UserDetails createUserDetails(UserCredentialsDto credentials) {
        return User.builder()
                .username(credentials.getUserName())
                .password(credentials.getPassword())
                .roles(credentials.getRoles().toArray(String[]::new))
                .build();
    }
}
