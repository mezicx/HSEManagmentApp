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
import com.merrimansa.entities.Hazard;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.structures.Categories;
import com.merrimansa.structures.InjuryType;
import javax.inject.Inject;
import com.merrimansa.entities.Process;
import java.util.Collection;

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
    
    
    
    private Categories cats;
    
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
    
    public Collection<Asset> getPotentialAssests(int AssessmentId){
        ProcessAssessment PA = PAF.find(AssessmentId);
        int ProcessId = PA.getProcessId().getProcessId();
        Process P = PF.find(ProcessId);
        
        
        
        
        return P.getDepartmentId().getAssetCollection();
                        
    }
    
    public Collection<InjuredParty> getInjuredPartyCollection(){
        return IPF.findAll();
    }
    
    
    
    
}
