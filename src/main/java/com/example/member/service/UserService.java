package com.example.member.service;

import com.example.member.model.User;
import com.example.member.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;

    @Override
    public User create(final User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> find(final String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(final String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(final String id) {
        repository.deleteById(id);
    }
}
