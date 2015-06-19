/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.ejb.ProcessAssessmentManagerFacade;
import com.merrimansa.entities.ProcessAssessment;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value = "approveAssessmentBean")
public class ApproveAssessmentBean {
    
    @Inject
    ProcessAssessmentManagerFacade PAMF; 
    
    ProcessAssessment theAssessment;
    
    @PostConstruct
    public void init(){
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        
        if (params.get("AssessmentId") != null) {

            theAssessment = getProcessAssessment(Integer.parseInt(params.get("AssessmentId")));
        }
    }

    public ProcessAssessment getTheAssessment() {
        return theAssessment;
    }

    public void setTheAssessment(ProcessAssessment theAssessment) {
        this.theAssessment = theAssessment;
    }
    
    
    
    
    public ProcessAssessment getProcessAssessment(int id){
       return PAMF.findAssessment(id);
    }
    
}


