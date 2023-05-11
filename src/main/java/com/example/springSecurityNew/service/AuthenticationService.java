package com.example.springSecurityNew.service;


import com.example.springSecurityNew.auth.AuthenticationRequest;
import com.example.springSecurityNew.auth.AuthenticationResponse;
import com.example.springSecurityNew.auth.RegisterRequest;
import com.example.springSecurityNew.entity.Role;
import com.example.springSecurityNew.entity.TestEntity;
import com.example.springSecurityNew.repository.TestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TestRepo repo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public TestEntity register(RegisterRequest request) {

        var user= TestEntity.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repo.save(user);
        return user;
    }

    public AuthenticationResponse authenticateRequest(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user= repo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
