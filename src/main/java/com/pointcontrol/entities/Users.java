package com.pointcontrol.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity()
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @Column(name = "codUser")
    @NotNull
    private Integer codUser;
    
    @NotBlank
    @Size(max = 200)
    @Column(name = "email")
    private String email;
    
    @NotBlank
    @Size(max = 20)
    @Column(name = "login")
    private String login;
    
    @NotBlank
    @Size(min = 6, max = 100)
    @Column(name = "password")
    private String password;
    
    @NotBlank
    @Size(max = 200)
    @Column(name = "name")
    private String name;

    @Column(name = "active") 
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "userRoles",
        joinColumns = @JoinColumn(name = "codUser"), 
    	inverseJoinColumns = @JoinColumn(name = "codRole")
    )
    private Set<Roles> roles = new HashSet<>();

    public Users() {}

    public Integer getCodUser() {
        return codUser;
    }

    public void setCodUser(Integer codUser) {
        this.codUser = codUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "codUser=" + codUser + ", email=" + email + ", login=" + login + ", password=" + password + ", name=" + name + ", active=" + active + ", roles=" + roles + '}';
    }
}