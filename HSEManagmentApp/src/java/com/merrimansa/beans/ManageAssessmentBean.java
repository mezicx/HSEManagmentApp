/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.entities.ProcessAssessment;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value = "manageAssessmentBean")
@Dependent
public class ManageAssessmentBean {

    @Inject
    ProcessAssessmentFacade PAF;
    /**
     * Creates a new instance of ManageAssessmentBean
     */
    public ManageAssessmentBean() {
    }
    
    public ProcessAssessment getAssessment(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String,String> params = externalContext.getRequestParameterMap();
        
	int AssessmentId = Integer.parseInt(params.get("AssessmentId"));
        
        return PAF.find(AssessmentId);
 
    }
    
}
