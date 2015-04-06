/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Named(value = "mobileDashBoardBean")
@Dependent
public class mobileDashBoardBean {

    /**
     * Creates a new instance of mobileDashBoardBean
     * 
     * 
     */
    
    @Inject
    CurrentUserBean currentUser;
    
    public mobileDashBoardBean() {
        
    }
    
    public String getUserName(){
        return currentUser.getTheUser().getEmail();
    }
    
    
    
    /**
     * Check if a component should be rendered user
     * @param ReqRole
     * @return boolean true if it should be rendered
     */
    public boolean renderChecker(String ReqRole){
        
        return currentUser.getTheUser().hasRole(ReqRole);
    }
    
    
            
    
}
