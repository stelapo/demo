package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUser(User user);

    User updateUser(User userId);

    User findUser(Long userId);

    void deleteUser(Long userId);

    Page<User> findAll(Pageable pageable);

    Page<User> findByNameAndSurname(String name, String surname, Pageable pageable);
}
