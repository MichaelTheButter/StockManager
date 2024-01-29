package com.stockmanager.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.stockmanager.config.security.dto.JwtResponse;
import com.stockmanager.config.security.dto.LoginRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final JwsProperties jwsProperties;
    private final Algorithm algorithm;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    public JwtService(JwsProperties jwsProperties,
                      AuthenticationManager authenticationManager,
                      CustomUserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwsProperties = jwsProperties;
        this.algorithm = Algorithm.HMAC256(jwsProperties.sharedKey());
    }

    public JwtResponse createAuthentication(LoginRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.userName(), loginRequest.password())
        );
        List<String> auth = findAuthoritiesByUserName(loginRequest.userName());
        User user = (User) authenticate.getPrincipal();
        String userName = user.getUsername();
        String token = createSignedJWT(userName, auth);
        return new JwtResponse(token);
    }

    private List<String> findAuthoritiesByUserName(String userName) {
        Collection<? extends GrantedAuthority> authorities = userDetailsService.loadUserByUsername(userName).getAuthorities();
        return authorities.stream()
                .map(Object::toString)
                .toList();
    }


    private String createSignedJWT(String username, List<String> authorities) {
        TokenClock clock = new TokenClock();
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(clock.now)
                .withExpiresAt(clock.expTime)
                .withClaim("authorities", authorities)
                .sign(algorithm);

    }
    private class TokenClock {
        private Date now;
        private Date expTime;
        TokenClock () {
            this.now = Date.from(LocalDateTime.now()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
            LocalDateTime nowPlusExpTime = LocalDateTime.now()
                    .plusSeconds(jwsProperties.expirationSecond());
            this.expTime = Date.from(nowPlusExpTime.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

}
