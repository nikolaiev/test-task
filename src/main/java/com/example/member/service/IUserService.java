package com.example.member.service;


import com.example.member.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User create(User object);

    Optional<User> find(String id);

    Optional<User> findByUsername(String userName);

    List<User> findAll();

    void delete(String id);
}
