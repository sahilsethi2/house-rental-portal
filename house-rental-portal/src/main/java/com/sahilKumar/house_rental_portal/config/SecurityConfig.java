package com.sahilKumar.house_rental_portal.config;

import com.sahilKumar.house_rental_portal.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // --- CORRECT AND UPDATED ORDER OF RULES ---
                        .requestMatchers("/api/admin/locations/public").permitAll()
                        // 1. Public URLs (Anyone can access)
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/properties/**").permitAll()

                        // 2. Role-specific URLs
                        // CUSTOMER rules
                        .requestMatchers(HttpMethod.POST, "/api/bookings").hasRole("CUSTOMER")
                        .requestMatchers("/api/bookings/my-bookings").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/inquiries").hasRole("CUSTOMER")

                        // OWNER rules
                        .requestMatchers(HttpMethod.POST, "/api/properties").hasRole("OWNER")
                        .requestMatchers("/api/bookings/owner/**", "/api/bookings/{bookingId}/confirm", "/api/bookings/{bookingId}/cancel").hasRole("OWNER")
                        .requestMatchers("/api/inquiries/owner/**", "/api/inquiries/{inquiryId}/accept", "/api/inquiries/{inquiryId}/reject").hasRole("OWNER")

                        // ADMIN rules
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // 3. The "catch-all" rule (MUST BE LAST)
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}