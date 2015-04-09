/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.ejb;

import com.merrimansa.businessObjects.ProcessAssessmentManager;
import com.merrimansa.businessObjects.RequiredAssessmentVO;
import com.merrimansa.businessObjects.UserVO;
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
    
}
