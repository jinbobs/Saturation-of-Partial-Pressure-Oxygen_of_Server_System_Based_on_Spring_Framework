package com.jins.jin.repository;

import com.jins.jin.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}