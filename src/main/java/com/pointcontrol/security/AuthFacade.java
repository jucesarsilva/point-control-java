package com.pointcontrol.security;

import com.pointcontrol.enums.RoleName;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {
    
    private final SimpleGrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString());
    private final SimpleGrantedAuthority ROLE_USER = new SimpleGrantedAuthority(RoleName.ROLE_USER.toString());
    private UsersPrinciple user;
 
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public Boolean isAdminProfile() {
       return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(ROLE_ADMIN);
    }
    
    public Boolean isUserProfile() {
       return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(ROLE_USER);
    }
    
    public Object getPrincipal() {
       return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    public Integer getCodUser() {
        user = (UsersPrinciple) getPrincipal();
        return user.getCodUser();
    }
    
    public String getLogin() {
        user = (UsersPrinciple) getPrincipal();
        return user.getUsername();
    }
    
    public Collection<? extends GrantedAuthority> getRoles() {
        user = (UsersPrinciple) getPrincipal();
        return user.getAuthorities();
    }
}
