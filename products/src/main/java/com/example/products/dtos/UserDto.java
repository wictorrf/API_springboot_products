package com.example.products.dtos;

import com.example.products.models.user.UserRole;

public record UserDto( String login, String password, UserRole role) {
    
}
