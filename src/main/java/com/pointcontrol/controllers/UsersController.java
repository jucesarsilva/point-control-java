package com.pointcontrol.controllers;

import com.pointcontrol.entities.Users;
import com.pointcontrol.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/api/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Users> findAll(
        @PageableDefault Pageable pageable
    ) {
        return usersService.findAll(pageable);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/api/users/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Users> findByNome(
        @PathVariable("name") String name, 
        @PageableDefault Pageable pageable
    ) {
        return usersService.findByName(name, pageable);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/api/users/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerUser(
        @RequestBody Users user
    ) {
        if(usersService.existsByLogin(user.getLogin()) != null) {
            return new ResponseEntity<>("Login já existe!", HttpStatus.BAD_REQUEST);
        } 
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCodUser(usersService.getLastCodUser());
        usersService.save(user);
        return ResponseEntity.ok().body("Usuário registrado com sucesso!");
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/api/users/visibility/{codUser}/{active}")
    @PreAuthorize("hasRole('ADMIN')")
    public Integer visibility(
        @PathVariable("codUser") Integer codUser,
        @PathVariable("active") Boolean active
    ) {
        return usersService.visibility(codUser, active);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/api/users/updatePassword")
    @PreAuthorize("hasRole('ADMIN')")
    public Integer updatePassword(
        @RequestParam("codUser") Integer codUser, 
        @RequestParam("password") String password
    ) {
        return usersService.updatePassword(codUser, passwordEncoder.encode(password));
    }
}