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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;

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
    private DataTable reqAssessments;

    public AssignAssessmentBean() {
    }

    @PostConstruct
    public void init() {
        conversation.begin();
    }

    public List<RequiredAssessmentVO> getRequiredAssessments() {
        return PAMF.getRequiredAssessments();
    }

    public Map<String, Integer> getAssessorList() {
        Map<String, Integer> userMap = new HashMap();
        for (UserVO u : UMF.getActiveUsers("Assessor")) {
            userMap.put(u.getFullName(), u.getUserId());
        }

        return userMap;
    }

    public void sayMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("Growl go");
        UserVO vo =(UserVO)reqAssessments.getRowData("0");
        context.addMessage(null, new FacesMessage("Successful", "The Assessor is " + this.getAssignedUser()+ " "+vo.getFullName()));
        conversation.end();
    }

    public DataTable getReqAssessments() {
        return reqAssessments;
    }

    public void setReqAssessments(DataTable reqAssessments) {
        this.reqAssessments = reqAssessments;
    }

    public int getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(int assignedUser) {

        System.out.println("It has been set");
        this.assignedUser = assignedUser;
    }

}
