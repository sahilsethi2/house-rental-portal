package com.sahilKumar.house_rental_portal.service.impl;

import com.sahilKumar.house_rental_portal.dto.SignUpRequest;
import com.sahilKumar.house_rental_portal.models.User;
import com.sahilKumar.house_rental_portal.models.enums.Role;
import com.sahilKumar.house_rental_portal.repository.UserRepository;
import com.sahilKumar.house_rental_portal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahilKumar.house_rental_portal.dto.LoginRequest;
import com.sahilKumar.house_rental_portal.dto.SignUpRequest;
import com.sahilKumar.house_rental_portal.models.User;
import com.sahilKumar.house_rental_portal.models.enums.Role;
import com.sahilKumar.house_rental_portal.repository.UserRepository;
import com.sahilKumar.house_rental_portal.service.AuthService;
import com.sahilKumar.house_rental_portal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User registerUser(SignUpRequest signUpRequest) {
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists!");
        }
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.valueOf(signUpRequest.getRole().toUpperCase()));
        return userRepository.save(user);
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        final UserDetails userDetails = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        return jwtUtil.generateToken(userDetails);
    }
}