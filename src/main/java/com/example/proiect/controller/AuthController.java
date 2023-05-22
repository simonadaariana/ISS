package com.example.proiect.controller;

import com.example.proiect.dbo.AuthResponse;
import com.example.proiect.dbo.LoginRequest;
import com.example.proiect.dbo.RegisterRequest;
import com.example.proiect.dbo.RegisterResponse;
import com.example.proiect.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping()
public class AuthController {
    private final UserService userService;

    @PostMapping(path = "/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping(path = "/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = userService.signup(registerRequest);
        return response;
    }

    @GetMapping(path = "/logout")
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
