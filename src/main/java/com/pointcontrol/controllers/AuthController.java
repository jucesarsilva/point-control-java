package com.pointcontrol.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointcontrol.DTOs.LoginForm;
import com.pointcontrol.DTOs.JwtResponse;
import com.pointcontrol.security.JwtProvider;
import com.pointcontrol.security.UsersPrinciple;
import java.util.ArrayList;
import java.util.List;
import com.pointcontrol.repositories.RolesRepository;
import com.pointcontrol.repositories.UsersRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private UsersPrinciple userPrincipal;
    
    private List<String> roles;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    RolesRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getLogin(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        
        if(authentication.isAuthenticated()) {
            
            userPrincipal = (UsersPrinciple) authentication.getPrincipal();
            
            roles = new ArrayList();
            
            userPrincipal.getAuthorities().stream().filter((auth) -> (!roles.contains(auth.getAuthority()))).forEachOrdered((auth) -> {
                roles.add(auth.getAuthority());
            });
        }
        
        JwtResponse resp = new JwtResponse(
            jwt,
            roles,
            userPrincipal.getCodUser(),
            userPrincipal.getName(),
            userPrincipal.getEmail()
        );
        return ResponseEntity.ok(resp);
    }
}