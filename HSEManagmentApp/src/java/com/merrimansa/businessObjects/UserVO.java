/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import java.io.Serializable;
import java.util.Set;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Steve
 */
@SessionScoped
public class UserVO implements Serializable {
    
    private String Email;
    private String FirstName;
    private String LastName;
    private int UserId;
    private Set<String> Roles;

    public UserVO() {
    }
    
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public Set<String> getRoles() {
        return Roles;
    }

    public void setRoles(Set<String> Roles) {
        this.Roles = Roles;
    }
    
    
    
}
