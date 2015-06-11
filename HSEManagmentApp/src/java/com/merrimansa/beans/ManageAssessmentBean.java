/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.businessObjects.UserVO;
import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.ejb.ProcessAssessmentManagerFacade;
import com.merrimansa.ejb.UserManagerFacade;
import com.merrimansa.entities.Hazard;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.structures.InjuryType;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value = "manageAssessmentBean")
@ConversationScoped
public class ManageAssessmentBean implements Serializable {

    @Inject
    private ProcessAssessmentManagerFacade PAMF;

    @Inject
    private UserManagerFacade UMF;
    
    @Inject
    CurrentUserBean theUser;

    private int AssessmentId;
    @Inject
    private Conversation conversation;

    private ProcessAssessment theAssessment;
    private int UserId;

    /**
     * Creates a new instance of ManageAssessmentBean
     */
    public ManageAssessmentBean() {
    }

    @PostConstruct
    public void init() {
        conversation.begin();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();

        AssessmentId = Integer.parseInt(params.get("AssessmentId"));
        theAssessment = PAMF.findAssessment(AssessmentId);

    }

    public void updateAssessmentDetails() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            PAMF.updateAssessment(theAssessment);
            context.addMessage(null, new FacesMessage("Successful", "Assessment " + theAssessment.getAssessmentId() + " Sucessfully Updated"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Error", "Problem updating Assessment " + theAssessment.getAssessmentId()));
        }

        conversation.end();

    }
    
    public void submitAssessment() throws IOException{
       FacesContext context = FacesContext.getCurrentInstance();
       ExternalContext externalContext = context.getExternalContext();

        
        
        if(theUser.getTheUser().hasRole("HSEManager") || theUser.getTheUser().hasRole("Administrator") ){
            
            theAssessment.setApproved(true);
        }
        theAssessment.setSubmitted(true);
        
        try{
            PAMF.updateAssessment(theAssessment);
            context.addMessage(null, new FacesMessage("Successful", "Assessment " + theAssessment.getAssessmentId() + " Sucessfully Submitted"));
            externalContext.redirect("../mobileDashBoard.xhtml");
        }catch(Exception e){
            context.addMessage(null, new FacesMessage("Error", "Problem Submitting Assessment " + theAssessment.getAssessmentId()));
        }
    }
    
     public Map<String, Integer> getAssessorList() {
        Map<String, Integer> userMap = new HashMap();
        for (UserVO u : UMF.getActiveUsers()) {
            userMap.put(u.getFullName(), u.getUserId());
        }

        return userMap;
    }
    
    public void addTeamMember(){
         FacesContext context = FacesContext.getCurrentInstance();
         
          try {
            PAMF.addTeamMember(UserId, theAssessment.getAssessmentId());
            context.addMessage(null, new FacesMessage("Success","New member added to assessment team"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Error", "Problem adding new team member"));
        }

        conversation.end();
         
         
         
    }

    /**
     * Returns the Hazard collection as a list
     *
     * @return
     */
    public List<Hazard> getHazards() {

        return (List) theAssessment.getHazardCollection();
        
        

    }

    /**
     * Returns the injured party as a list
     *
     * @param hazard
     * @return
     */
    public List<InjuredParty> getInjuredParties(Hazard hazard) {
        return hazard.getInjuredPartyList();
    }

    

   

    public InjuryType[] getInjuryTypes() {
        return InjuryType.values();
    }

    public ProcessAssessment getTheAssessment() {
        return theAssessment;
    }

    public void setTheAssessment(ProcessAssessment theAssessment) {
        this.theAssessment = theAssessment;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }
    

    

}
