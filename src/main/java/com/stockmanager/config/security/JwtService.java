package com.stockmanager.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
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

    private static final int EXP_TIME_SEC = 60 * 60;
    private final Algorithm algorithm;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    public JwtService(@Value("${jws.sharedKey}") String sharedKey,
                      AuthenticationManager authenticationManager,
                      CustomUserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.algorithm = Algorithm.HMAC256(sharedKey);
        this.userDetailsService = userDetailsService;
    }

    String createSignedJWT(String username, List<String> authorities) {
        LocalDateTime nowPlus1Hour = LocalDateTime.now().plusSeconds(EXP_TIME_SEC);
        Date expirationDate = Date.from(nowPlus1Hour
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                .withExpiresAt(expirationDate)
                .withClaim("authorities", authorities)
                .sign(algorithm);

    }


    public JWTResponse createAuthentication(LoginRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.userName, loginRequest.password)
        );
        Collection<? extends GrantedAuthority> authorities = userDetailsService.loadUserByUsername(loginRequest.userName).getAuthorities();
        List<String> auth = authorities.stream()
                .map(Object::toString)
                .toList();
        User user = (User) authenticate.getPrincipal();
        String userName = user.getUsername();
        String token = createSignedJWT(userName, auth);
        return new JWTResponse(token);
    }

    public record JWTResponse(String token) {}
    public record LoginRequestDto(String userName, String password) {}
}
