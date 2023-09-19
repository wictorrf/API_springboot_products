package com.example.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.products.dtos.AuthenticationDto;
import com.example.products.dtos.LoginResponseDto;
import com.example.products.dtos.UserDto;
import com.example.products.models.user.User;
import com.example.products.repositories.UserRepositorie;
import com.example.products.services.TokenService;
import com.example.products.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepositorie userRepositorie;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserDto data){
        if(this.userRepositorie.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }
        this.userService.registerUser(data);
        return ResponseEntity.ok().build();
    }
}
