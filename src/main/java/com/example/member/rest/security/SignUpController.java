package com.example.member.rest.security;

import com.example.member.dto.Login;
import com.example.member.model.Authority;
import com.example.member.model.User;
import com.example.member.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/api/signup")
@AllArgsConstructor
public class SignUpController {

    private final UserService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody Login dto) {
        final User user = service.create(User.builder()
                .password(dto.getPassword())
                .username(dto.getUsername())
                .authorities(Collections.singletonList(Authority.ROLE_USER))
                .build());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
