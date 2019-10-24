package com.pointcontrol.controllers;

import com.pointcontrol.entities.Roles;
import com.pointcontrol.services.RolesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolesController {
    
    @Autowired
    private RolesService rolesService;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/api/roles/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Roles> findAll() {
        return rolesService.findAll();
    }
}