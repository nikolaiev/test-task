package com.example.member.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;


public interface ITokenAuthenticationService {
    Authentication authenticate(HttpServletRequest request);
}
