/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.ejb.HazardFacade;
import com.merrimansa.ejb.InjuredPartyFacade;
import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.ejb.ProcessFacade;
import com.merrimansa.ejb.UserFacade;
import com.merrimansa.ejb.UserManagerFacade;
import com.merrimansa.entities.Hazard;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.structures.Categories;
import com.merrimansa.structures.InjuryType;
import javax.inject.Inject;
import com.merrimansa.entities.Process;
import com.merrimansa.entities.ProcessStep;
import com.merrimansa.entities.User;
import com.merrimansa.structures.ControlMeasureValues;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Steve
 */
public class HazardManager {
    
    @Inject
    private HazardFacade HF;
    
    @Inject
    private InjuredPartyFacade IPF;
    
    @Inject
    private ProcessFacade PF;
    
    @Inject 
    private ProcessAssessmentFacade PAF;
    
    @Inject UserManagerFacade UMF;
    
    @Inject UserFacade UF;
    
    
    
    private Categories cats;
    private ControlMeasureValues controlMeasure;
    
    public HazardManager(){
        
        cats = new Categories();
    }
    
    public Hazard getHazardByAssessment(int AssessmentId){
        
       return HF.find(AssessmentId);
        
    }
    
    public Categories getCategories(){
        
        return cats;
        
    }
    
    public InjuryType[] getInjuryTypes() {
        return InjuryType.values();
    }
    
    public String[] getSubCategory(String Cat){
        return cats.getCat().get(Cat);
    }
    
    public Collection<ProcessStep> getProcessSteps(int AssessmentId){
        ProcessAssessment PA = PAF.find(AssessmentId);
        return PA.getProcessId().getProcessStepCollection();
    }
    
    public Collection<Asset> getPotentialAssests(int AssessmentId){
        ProcessAssessment PA = PAF.find(AssessmentId);
        int ProcessId = PA.getProcessId().getProcessId();
        Process P = PF.find(ProcessId);
            
        
        
        return P.getDepartmentId().getAssetCollection();
                        
    }
    
    public Collection<InjuredParty> getInjuredPartyCollection(){
        return IPF.findAll();
    }
    
    public void saveHazard(Hazard theHazard){
        
        
        if(theHazard.getHazardId() == null){
            HF.create(theHazard);
        }else
        {
            System.out.println("Save called in manager");
            // System.out.println(theHazard.getHazardId());
            HF.edit(theHazard);
        }
    }
    
    public ControlMeasureValues[] getControlMeasures(){
        return ControlMeasureValues.values();
    }
    
    public List<UserVO> getUsers(){
        return UMF.getActiveUsers();
    }
    
    public void deleteHazard(Hazard theHazard){
        
    }
    
    public User getUser(int Id){
        return UF.find(Id);
    }
    
    
    
    
    
}
