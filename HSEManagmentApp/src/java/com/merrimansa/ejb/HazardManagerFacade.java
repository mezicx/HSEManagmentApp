/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.ejb;

import com.merrimansa.businessObjects.HazardManager;
import com.merrimansa.businessObjects.UserVO;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.Hazard;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.ProcessStep;
import com.merrimansa.entities.User;
import com.merrimansa.structures.Categories;
import com.merrimansa.structures.ControlMeasureValues;
import com.merrimansa.structures.InjuryType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */

@Stateless
public class HazardManagerFacade {
    
    
    @Inject
    private HazardManager HM;
    
    public Hazard getHazardByAssessment(int AssessmentId){
    
        return HM.getHazardByAssessment(AssessmentId);
        
    }
    
    public Categories getCategories(){
        
        return HM.getCategories();
    }
    
    public InjuryType[] getInjuryTypes() {
        
        return HM.getInjuryTypes();
    }
    
    public String[] getSubCategory(String Cat){
        return HM.getSubCategory(Cat);
    }
    
    public Collection<Asset> getPotentialAssests(int AssessmentId){
        return HM.getPotentialAssests(AssessmentId);
    }
    
     public Collection<InjuredParty> getInjuredPartyCollection(){
         return HM.getInjuredPartyCollection();
     }
     
     public Collection<ProcessStep> getProcessStepCollection(int AssessmentId){
         return HM.getProcessSteps(AssessmentId);
     }
     
     public ControlMeasureValues[] getControlMeasures(){
        return HM.getControlMeasures();
    }
     
     public void saveHazard(Hazard theHazard){
        System.out.println("Save called in manager facade");
         
         HM.saveHazard(theHazard);
     }
     
     public List<UserVO> getUsers(){
         return HM.getUsers();
     }
     
     public User getUser(int Id){
         return HM.getUser(Id);
     }
}
