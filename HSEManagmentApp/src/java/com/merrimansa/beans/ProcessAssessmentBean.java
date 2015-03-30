/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.entities.ProcessAssessment;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value = "processAssessmentBean")
@Dependent
public class ProcessAssessmentBean {

    @Inject
    ProcessAssessmentFacade assessmentFacade;
    /**
     * Creates a new instance of ProcessAssessmentBean
     */
    public ProcessAssessmentBean() {
    }
    
    public List<ProcessAssessment> getAssessments(){
        return assessmentFacade.findAll();
    }
    
}
