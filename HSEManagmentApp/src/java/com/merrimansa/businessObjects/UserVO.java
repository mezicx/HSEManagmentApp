/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.entities.Role;
import com.merrimansa.entities.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Steve
 */
public class UserVO implements Serializable {

    private String Email;
    private String FirstName;
    private String LastName;
    private int UserId;
    private HashSet<Role> Roles;

    public UserVO() {
    }

    public UserVO(User user) {
        this.Email = user.getEmail();
        this.FirstName = user.getForename();
        this.LastName = user.getSurname();
        this.UserId = user.getUserId();
        this.Roles = new HashSet<>(user.getRoleCollection());
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

    public Set<Role> getRoles() {
        return Roles;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    

    /**
     * Checks if this user has a specified role
     *
     * @param role
     * @return Return true if this user has the role specified in role param
     */
    public boolean hasRole(String role) {
        
        for(Role r:this.Roles){
            if(r.getRole().equalsIgnoreCase(role))
                return true;
        }
        return false;
               
    }

    /**
     * Identifies the highest role that this user possesses
     *
     * @return
     */
    public String getHighestRole() {
        String HighRoll = "Undefined";
        Set<Role> roles = this.Roles;
        for (Role r : roles) {
            System.out.println(r.getRole() + " In Loop, String length " + r.getRole().length());

            if (r.getRole().equalsIgnoreCase("Administrator")) {
                System.out.println(r.getRole() + " has evaluated to true");
                HighRoll = r.getRole();
                break;
            }
            if (r.getRole().equalsIgnoreCase("HSEManager")) {
                System.out.println(r.getRole() + " has evaluated to true");
                HighRoll = r.getRole();
                break;
            }
            if (r.getRole().equalsIgnoreCase("Assessor")) {
                System.out.println(r.getRole() + " has evaluated to true");
                HighRoll = r.getRole();
                break;
            }
            if (r.getRole().equalsIgnoreCase("User")) {
                System.out.println(r.getRole() + " has evaluated to true");
                HighRoll = r.getRole();
                break;
            }

        }

        return HighRoll;
    }

}
