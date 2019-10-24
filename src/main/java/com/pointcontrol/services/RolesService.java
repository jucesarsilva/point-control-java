package com.pointcontrol.services;

import com.pointcontrol.entities.Roles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pointcontrol.repositories.RolesRepository;

@Service
public class RolesService {
    
    @Autowired
    private RolesRepository rolesRepository;
    
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }
    
    public Integer getCodRole(String role) {
        return rolesRepository.getCodRole(role);
    }
}
