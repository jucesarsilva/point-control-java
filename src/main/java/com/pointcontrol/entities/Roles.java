package com.pointcontrol.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity()
@Table(name = "roles")
public class Roles implements Serializable {

    @Id
    @Column(name="codRole")
    @NotNull
    private Integer codRole;

    @Column(name="role", length = 20)
    @NotBlank
    private String role;
    
    @Column(name="label", length = 50)
    private String label;

    @PostLoad
    protected void repair(){
        if(role != null) {
            role = role.trim();
        }
    }
    
    public Roles() {
    }

    public Integer getCodRole() {
        return codRole;
    }

    public void setCodRole(Integer codRole) {
        this.codRole = codRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Role{" + "codRole=" + codRole + ", role=" + role + ", label=" + label + '}';
    }
}