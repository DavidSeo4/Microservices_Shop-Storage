package com.basic.SecurityJwt.SecurityJwt.controllers;

import com.basic.SecurityJwt.SecurityJwt.security.auth.AuthRequest;
import com.basic.SecurityJwt.SecurityJwt.security.auth.AuthResponse;
import com.basic.SecurityJwt.SecurityJwt.service.AuthenticationService;
import com.basic.SecurityJwt.SecurityJwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;
    private JwtService jwtService;


    public AuthController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid AuthRequest authRequest){
        AuthResponse jwtDto = authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }

    @GetMapping("/getAuthUsername/{token}")
    public String getUsername(@PathVariable String token){
        String tokenNoBearer = token.split(" ")[1];
        return jwtService.extractUsername(tokenNoBearer);
    }



}
