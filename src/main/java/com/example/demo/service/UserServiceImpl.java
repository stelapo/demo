package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (user.getUserId() != null)
            user.setUserId(null);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.findById(user.getUserId())
                .map(
                        userToUpdate -> userRepository.save(user)
                )
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with id " + user.getUserId())
                )
        ;
    }

    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with id " + userId)
                )
                ;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .map(
                        user -> {
                            userRepository.delete(user);
                            return "OK";
                        }
                )
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with id " + userId)
                )
        ;
    }
}