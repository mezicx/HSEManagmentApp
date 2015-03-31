/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value = "assignAssessmentBean")
@Dependent
public class AssignAssessmentBean {

    /**
     * Creates a new instance of AssignAssessmentBean
     */
    public AssignAssessmentBean() {
    }
    
    
    public void sayMessage(){
         FacesContext context = FacesContext.getCurrentInstance();
         System.out.println("Growl go");
        context.addMessage(null, new FacesMessage("Successful",  "IT DOES WORK") );
    }
    
}
