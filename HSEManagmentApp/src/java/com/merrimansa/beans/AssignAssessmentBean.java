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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author Steve
 */
@Named(value = "assignAssessmentBean")
@ConversationScoped
public class AssignAssessmentBean implements Serializable {

    /**
     * Creates a new instance of AssignAssessmentBean
     *
     *
     */
    @Inject
    private ProcessAssessmentManagerFacade PAMF;
    @Inject
    private UserManagerFacade UMF;
    @Inject
    private Conversation conversation;

    private int assignedUser;
    private List<RequiredAssessmentVO> processes;

    public AssignAssessmentBean() {
    }

    @PostConstruct
    public void init() {
        conversation.begin();
        processes = PAMF.getRequiredAssessments();
    }

    public List<RequiredAssessmentVO> getRequiredAssessments() {
        return processes;
    }

    public Map<String, Integer> getAssessorList() {
        Map<String, Integer> userMap = new HashMap();
        for (UserVO u : UMF.getActiveUsers("Assessor")) {
            userMap.put(u.getFullName(), u.getUserId());
        }

        return userMap;
    }

       
    public void assignAssessments(){
        FacesContext context = FacesContext.getCurrentInstance();
        //Create temp list to hold assessments to be assigned
        List<RequiredAssessmentVO> assignmentList = new ArrayList();
        //Create user VO for assessor
        UserVO assessor = UMF.getUserVoById(getAssignedUser());
        //Search for assigned assessments
        for(RequiredAssessmentVO ra:getRequiredAssessments()){
            System.out.println("The process assignment is "+ra.isToBeAssigned()+" for "+ra.getProcessName());
            if(ra.isToBeAssigned()){
                ra.setAssessor(assessor);
                assignmentList.add(ra);
            }
        }
        //Assign Each Assessment
        for(RequiredAssessmentVO r:assignmentList){
            PAMF.assignAssessment(r);
            context.addMessage(null, new FacesMessage("Successful", r.getProcessName() +" Assessment assigned to "+assessor.getFullName()));
        }
        
         conversation.end();
        
    }

    
    public int getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(int assignedUser) {

        System.out.println("It has been set");
        this.assignedUser = assignedUser;
    }

}
