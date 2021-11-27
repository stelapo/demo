package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByNameIsContainingIgnoreCase(String name, Pageable pageable);

    Page<User> findBySurnameIsContainingIgnoreCase(String surname, Pageable pageable);

    Page<User> findByNameIsContainingIgnoreCaseAndSurnameIsContainingIgnoreCase(String name, String surname, Pageable pageable);
}
