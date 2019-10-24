package com.pointcontrol.DTOs;

import java.util.ArrayList;
import java.util.List;

public class JwtResponse {
    private String name;
    private String email;
    private String token;
    private String type = "Bearer";
    private List<String> roles = new ArrayList();
    private Integer codUser;

    public JwtResponse(
        String accessToken, 
        List<String> roles, 
        Integer codUser,
        String name,
        String email
    ) {
        this.name = name;
        this.email = email;
        this.token = accessToken;
        this.roles = roles;
        this.codUser = codUser;
    }
    
    public Integer getCodUser() {
        return codUser;
    }

    public void setCodUser(Integer codUser) {
        this.codUser = codUser;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}