/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.entities.Hazard;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.structures.InjuryType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
    private ProcessAssessmentFacade PAF;
    
    private int AssessmentId;
    /**
     * Creates a new instance of ManageAssessmentBean
     */
    public ManageAssessmentBean() {
    }
    
    @PostConstruct
    public void init(){
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String,String> params = externalContext.getRequestParameterMap();
        
         AssessmentId = Integer.parseInt(params.get("AssessmentId"));
    }
    
    /**
     * 
     * @return 
     */
    public ProcessAssessment getAssessment(){
       
        
        return PAF.find(AssessmentId);
 
    }
    /**
     * 
     * @return 
     */
    public List<Hazard> getHazards(){
        
        
        return (List)PAF.find(AssessmentId).getHazardCollection();
    }
    /**
     * 
     * @param hazard
     * @return 
     */
    public List<InjuredParty> getInjuredParties(Hazard hazard){
        return hazard.getInjuredPartyList();
    }
    
    public InjuryType[] getInjuryTypes(){
        return InjuryType.values();
    }
    
}
