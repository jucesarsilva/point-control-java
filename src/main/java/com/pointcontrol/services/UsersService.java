package com.pointcontrol.services;

import com.pointcontrol.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pointcontrol.repositories.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;
    
    public Boolean existsByLogin(String login) {
        return usersRepository.existsByLogin(login);
    }
    
    public Integer getLastCodUser() {
        Integer lastCodUser = usersRepository.getLastCodUser();
        return lastCodUser == null ? 1 : lastCodUser + 1;
    }
    
    public Page<Users> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }
    
    public Page<Users> findByName(String name, Pageable pageable) {
        return usersRepository.findByNameIgnoreCaseContaining(name, pageable);
    }
    
    public int visibility(Integer codUser, Boolean active) {
        return usersRepository.visibility(codUser, active);
    }
    
    public int updatePassword(Integer codUser, String password) {
        return usersRepository.updatePassword(codUser, password);
    }

    public Users save(Users user) {
        return usersRepository.save(user);
    }
}
