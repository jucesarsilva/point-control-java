package com.pointcontrol.security;

import com.pointcontrol.security.UsersPrinciple;
import com.pointcontrol.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pointcontrol.repositories.UsersRepository;

@Service
public class UsersServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    	
        Users user = userRepository.findByLogin(login)
            .orElseThrow(() -> 
            new UsernameNotFoundException("User Not Found with -> username or email : " + login)
        );

        return UsersPrinciple.build(user);
    }
}