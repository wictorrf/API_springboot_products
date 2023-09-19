package com.example.products.repositories;

import com.example.products.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepositorie extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
