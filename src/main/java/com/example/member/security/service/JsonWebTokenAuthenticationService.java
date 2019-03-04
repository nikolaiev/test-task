package com.example.member.security.service;

import com.example.member.model.User;
import com.example.member.dto.UserAuthentication;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class JsonWebTokenAuthenticationService implements ITokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "x-auth-token";
    private final UserDetailsService userDetailsService;
    @Value("${security.token.secret.key}")
    private String secretKey;


    @Override
    public Authentication authenticate(final HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        final Jws<Claims> tokenData = parseToken(token);
        return Optional.ofNullable(tokenData)
                .map(this::getUserFromToken)
                .map(UserAuthentication::new)
                .orElse(null);
    }

    private Jws<Claims> parseToken(final String token) {
        if (token != null) {
            try {
                return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
                    | SignatureException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private User getUserFromToken(final Jws<Claims> tokenData) {
        try {
            return (User) userDetailsService
                    .loadUserByUsername(tokenData.getBody().get("username").toString());
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User "
                    + tokenData.getBody().get("username").toString() + " not found");
        }
    }
}
