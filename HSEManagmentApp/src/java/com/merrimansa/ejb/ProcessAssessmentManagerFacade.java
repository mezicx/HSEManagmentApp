/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.ejb;

import com.merrimansa.businessObjects.ProcessAssessmentManager;
import com.merrimansa.businessObjects.RequiredAssessmentVO;
import com.merrimansa.businessObjects.UserVO;
import com.merrimansa.entities.ProcessAssessment;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Stateless
public class ProcessAssessmentManagerFacade {
    
    
    
            
    @Inject 
    ProcessAssessmentManager PSM;
    
    public List<RequiredAssessmentVO>getRequiredAssessments(){
        return PSM.getRequiredAssessments();
    }
    
    public void assignAssessment(RequiredAssessmentVO reqAssess){
        PSM.assignAssessment(reqAssess);
    }
    
    public List<ProcessAssessment> getAssignedAssessments(UserVO currentUser){
        return PSM.getAssignedAssessments(currentUser);
    }
    
    public ProcessAssessment findAssessment(int AssessmentId){
        return PSM.findAssessment(AssessmentId);
    }
    public void updateAssessment(ProcessAssessment assessment){
        PSM.updateAssessment(assessment);
    }
    
    public void addTeamMember(int UserID, int AssessmentId){
        PSM.addTeamMember(UserID, AssessmentId);
    }
    
    public List<ProcessAssessment> getSubmittedAssessments(){
        return PSM.getSubmittedAssessments();
    }
    
    
    
    public void setApproved(int assessmentId){
        PSM.setApproved(assessmentId);
    }
}
