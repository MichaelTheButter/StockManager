package com.stockmanager.config.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.util.List;

public class BearerTokenFilter extends HttpFilter {
    private final Logger logger = LoggerFactory.getLogger(BearerTokenFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
    private final String sharedKey = "wsc7e4lk-a23a-4vdb-brgc-33d4g0c58261";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }
        try {
        String compactJwt = authorizationHeader.substring(BEARER_PREFIX.length());
        Algorithm algorithm = Algorithm.HMAC256(sharedKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT decodedJWT = verifier.verify(compactJwt);
        String userName = decodedJWT.getSubject();
        List<String> authorities = decodedJWT.getClaim("authorities").asList(String.class);
        System.out.println(userName + authorities);
        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).toList();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userName, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContext securityContext = securityContextHolderStrategy.getContext();
        securityContext.setAuthentication(authentication);
        System.out.println(securityContext.getAuthentication());
        chain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            System.out.println(e.getMessage());
            failureHandler.onAuthenticationFailure(request, response, e);
        }
        }
}
