package com.example.member.security.service;


public interface ITokenService {

    String getToken(String username, String password);
}
