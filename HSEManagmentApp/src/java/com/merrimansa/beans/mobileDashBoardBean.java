/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.ejb.ProcessAssessmentManagerFacade;
import com.merrimansa.entities.ProcessAssessment;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    @Inject
    ProcessAssessmentManagerFacade PAMF;
    
    public mobileDashBoardBean() {
        
    }
    
    public String getUserName(){
        return currentUser.getTheUser().getEmail();
    }
    
    public List<ProcessAssessment> getUsersAssessments(){
        return PAMF.getAssignedAssessments(currentUser.getTheUser());
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
