package com.sahilKumar.house_rental_portal.service;

import com.sahilKumar.house_rental_portal.dto.SignUpRequest;
import com.sahilKumar.house_rental_portal.models.User;

import com.sahilKumar.house_rental_portal.dto.LoginRequest;
import com.sahilKumar.house_rental_portal.dto.SignUpRequest;
import com.sahilKumar.house_rental_portal.models.User;

public interface AuthService {
    User registerUser(SignUpRequest signUpRequest);
    String loginUser(LoginRequest loginRequest);
}