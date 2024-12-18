package com.group4.backend.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.backend.jwt.dto.AuthUser;
import com.group4.backend.jwt.dto.RegUser;
import com.group4.backend.jwt.model.User;
import com.group4.backend.jwt.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegUser regUser) {
        return ResponseEntity.ok(userService.register(regUser));
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody AuthUser authUser) {
        return ResponseEntity.ok(userService.auth(authUser));
    }
}
