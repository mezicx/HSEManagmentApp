/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import com.merrimansa.businessObjects.RequiredAssessmentVO;
import com.merrimansa.businessObjects.UserVO;
import com.merrimansa.ejb.ProcessAssessmentManagerFacade;
import com.merrimansa.ejb.UserManagerFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Inject
    private UserManagerFacade UMF;
    
    private String assignedUser;
    private DataTable reqAssessments;
    
    public AssignAssessmentBean() {
    }
    
    public List<RequiredAssessmentVO> getRequiredAssessments(){
        return PAMF.getRequiredAssessments();
    }
    
    public Map<String,String>getAssessorList(){
       Map<String,String> userMap = new HashMap();
        for(UserVO u:UMF.getActiveUsers("Assessor")){
            userMap.put(u.getFullName(), Integer.toString(u.getUserId()));
        }
        
        return userMap;
    }
    
    
    public void sayMessage(){
         FacesContext context = FacesContext.getCurrentInstance();
         System.out.println("Growl go");
        context.addMessage(null, new FacesMessage("Successful",  "The Assessor is "+this.getAssignedUser()) );
    }

    public DataTable getReqAssessments() {
        return reqAssessments;
    }

    public void setReqAssessments(DataTable reqAssessments) {
        this.reqAssessments = reqAssessments;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }
    
    
    
}
