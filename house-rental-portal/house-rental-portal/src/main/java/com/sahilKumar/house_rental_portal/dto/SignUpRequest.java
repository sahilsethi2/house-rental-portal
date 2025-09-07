package com.sahilKumar.house_rental_portal.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String role; // Can be "ROLE_OWNER" or "ROLE_CUSTOMER"
}