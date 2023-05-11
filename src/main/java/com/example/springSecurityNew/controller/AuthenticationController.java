package com.example.springSecurityNew.controller;


import com.example.springSecurityNew.auth.AuthenticationRequest;
import com.example.springSecurityNew.auth.AuthenticationResponse;
import com.example.springSecurityNew.auth.RegisterRequest;
import com.example.springSecurityNew.entity.TestEntity;
import com.example.springSecurityNew.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<TestEntity> register(@RequestBody RegisterRequest request) {
            return ResponseEntity.ok(authenticationService.register(request) );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(authenticationService.authenticateRequest(request));
    }
}
