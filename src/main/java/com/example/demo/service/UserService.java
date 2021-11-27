package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User createUser(User user);

    User updateUser(User userId);

    User findUser(Long userId);

    void deleteUser(Long userId);
}
