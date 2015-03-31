/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.ejb.ProcessFacade;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
public class ProcessAssessmentManager {
    
    @Inject
    ProcessFacade processFacade;

    public ProcessAssessmentManager() {
        
    }
    
    public List getRequiredAssessments(){
        return null;
    }
    
    
}
