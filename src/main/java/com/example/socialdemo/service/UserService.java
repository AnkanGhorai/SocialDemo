package com.example.socialdemo.service;

import com.example.socialdemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User save(User user);
    Optional<User> findById(int id);
    void deleteById(int id);
}
