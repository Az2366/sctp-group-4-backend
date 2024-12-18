package com.group4.backend.jwt.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group4.backend.jwt.dto.AuthUser;
import com.group4.backend.jwt.dto.RegUser;
import com.group4.backend.jwt.model.Role;
import com.group4.backend.jwt.model.User;
import com.group4.backend.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public User register(RegUser regUser) {
        User user = mapToUser(regUser);
        return userRepository.save(user);
    }

    public String auth(AuthUser authUser) {
        User user = (User) userDetailsService.loadUserByUsername(authUser.getUsername());
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword()));
        return jwtService.generateJwtToken(authUser.getUsername());
    }

    private User mapToUser(RegUser regUser) {
        return User.builder()
                .username(regUser.getUsername())
                .firstname(regUser.getFirstname())
                .lastname(regUser.getLastname())
                .password(passwordEncoder.encode(regUser.getPassword()))
                .roles(List.of(Role.USER.name()))
                .build();
    }
}
