package com.stockmanager.config.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
class BearerTokenFilter extends HttpFilter {
    private final Logger logger = LoggerFactory.getLogger(BearerTokenFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final SecurityContextHolderStrategy securityContextHolderStrategy;
    private final AuthenticationFailureHandler failureHandler;
    private final JwsProperties jwsProperties;

    public BearerTokenFilter(JwsProperties jwsProperties) {
        this.jwsProperties = jwsProperties;
        this.failureHandler = new SimpleUrlAuthenticationFailureHandler();
        this.securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    }

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }
        try {
            DecodedJWT decodedJWT = verifyToken(authorizationHeader);
            String userName = decodedJWT.getSubject();
            List<String> authorities = decodedJWT.getClaim("authorities").asList(String.class);

        List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).toList();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userName, null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityContext securityContext = securityContextHolderStrategy.getContext();
        securityContext.setAuthentication(authentication);
        chain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            System.err.println(e.getMessage());
            failureHandler.onAuthenticationFailure(request, response, e);
        }
        }

        private DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwsProperties.sharedKey());
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        try {
            String compactJwt = token.substring(BEARER_PREFIX.length());
            return verifier.verify(compactJwt);
            } catch (IndexOutOfBoundsException | JWTVerificationException e) {
                throw new JWTVerificationException("Token verification failure");
        }
        }
}
