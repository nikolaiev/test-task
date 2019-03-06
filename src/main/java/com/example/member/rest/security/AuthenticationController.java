package com.example.member.rest.security;

import com.example.member.dto.LoginDto;
import com.example.member.dto.Token;
import com.example.member.security.service.ITokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final ITokenService tokenService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody LoginDto dto) {
        final String token = tokenService.getToken(dto.getUsername(), dto.getPassword());
        if (token != null) {
            final Token response = new Token();
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }
}
