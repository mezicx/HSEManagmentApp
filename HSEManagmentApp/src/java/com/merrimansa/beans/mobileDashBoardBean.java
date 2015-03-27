/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

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
    UserAuthBean authBean;
    
    public mobileDashBoardBean() {
        
    }
    
    public String getUserName(){
        return authBean.getUserName();
    }
    
    public String getRole(){
        return authBean.getRole();
    }
    
}
