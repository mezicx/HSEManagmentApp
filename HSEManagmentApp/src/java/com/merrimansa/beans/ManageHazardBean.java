/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.ejb.HazardFacade;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.Hazard;
import com.merrimansa.structures.Category;
import com.merrimansa.structures.InjuryType;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Named(value = "manageHazardBean")
@Dependent
public class ManageHazardBean {
    
    private int AssessmentId;
    
    private Hazard theHazard;
    
    
    
    @Inject
    Conversation conversation;
    
    @Inject 
    HazardFacade HF;
    /**
     * Creates a new instance of ManageHazardBean
     */
    public ManageHazardBean() {
    }
    
    @PostConstruct 
    public void init(){
        
        if(conversation.isTransient()){
            conversation.begin();
        }
                
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();
        
        AssessmentId = Integer.parseInt(params.get("AssessmentId"));
        
        if(params.containsKey("HazardId")){
            theHazard = HF.find(Integer.parseInt(params.get("HazardId")));
        }else{
            theHazard = new Hazard();
        }
        
    }

    public int getAssessmentId() {
        return AssessmentId;
    }

    public void setAssessmentId(int AssessmentId) {
        this.AssessmentId = AssessmentId;
    }

    public Hazard getTheHazard() {
        return theHazard;
    }

    public void setTheHazard(Hazard theHazard) {
        this.theHazard = theHazard;
    }

    public void updateSubCategories(){
        
    }
    
    
    
    public Category[] getCategories(){
        return Category.values();
    }
    
    public InjuryType[] getInjuryTypes() {
        return InjuryType.values();
    }
    
    public String[] getSubCategory(){
        return Category.PHYSICAL.getSubCats();
    }
    
    public Asset[] getAssets(){
        return (Asset[])theHazard.getAssessmentId().getProcessId().getDepartmentId().getAssetCollection().toArray();
    }
    
}
