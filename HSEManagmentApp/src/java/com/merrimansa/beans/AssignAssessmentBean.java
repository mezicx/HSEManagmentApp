/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import com.merrimansa.businessObjects.RequiredAssessmentVO;
import com.merrimansa.ejb.ProcessAssessmentManagerFacade;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Steve
 */
@Named(value = "assignAssessmentBean")
@Dependent
public class AssignAssessmentBean {

    /**
     * Creates a new instance of AssignAssessmentBean
     * 
     * 
     */
    
    @Inject
    private ProcessAssessmentManagerFacade PAMF;
    private DataTable reqAssessments;
    
    public AssignAssessmentBean() {
    }
    
    public List<RequiredAssessmentVO> getRequiredAssessments(){
        return PAMF.getRequiredAssessments();
    }
    
    
    public void sayMessage(){
         FacesContext context = FacesContext.getCurrentInstance();
         System.out.println("Growl go");
        context.addMessage(null, new FacesMessage("Successful",  "IT DOES WORK") );
    }

    public DataTable getReqAssessments() {
        return reqAssessments;
    }

    public void setReqAssessments(DataTable reqAssessments) {
        this.reqAssessments = reqAssessments;
    }
    
    
    
}
