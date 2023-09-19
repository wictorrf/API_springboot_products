package com.example.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.products.dtos.UserDto;
import com.example.products.models.user.User;
import com.example.products.repositories.UserRepositorie;

@Service
public class UserService {
    
    @Autowired
    private UserRepositorie userRepositorie;

    public User registerUser(UserDto data){
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.userRepositorie.save(newUser);
        return newUser;
    }
}
