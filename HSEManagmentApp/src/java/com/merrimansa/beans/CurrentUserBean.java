/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import com.merrimansa.businessObjects.UserVO;
import com.merrimansa.entities.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value = "currentUserBean")
@SessionScoped
public class CurrentUserBean implements Serializable {

    /**
     * Creates a new instance of CurrentUserBean
     */
    
    private UserVO theUser;
    
    public CurrentUserBean() {
    }

    public UserVO getTheUser() {
        return theUser;
    }

    public void setTheUser(UserVO theUser) {
        this.theUser = theUser;
    }
    
    
    
    
    
}
