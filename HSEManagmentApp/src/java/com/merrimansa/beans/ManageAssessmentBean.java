/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.entities.Hazard;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.structures.InjuryType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ProcessAssessmentFacade PAF;

    private int AssessmentId;
    @Inject
    private Conversation conversation;

    ProcessAssessment theAssessment;

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
        theAssessment = PAF.find(AssessmentId);

    }

    public void updateAssessmentDetails() {

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            PAF.edit(theAssessment);
            context.addMessage(null, new FacesMessage("Successful", "Assessment " + theAssessment.getAssessmentId() + " Sucessfully Updated"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Error", "Problem updating Assessment " + theAssessment.getAssessmentId()));
        }

        conversation.end();

    }

    /**
     *
     * @return
     */
    public List<Hazard> getHazards() {

        return (List) theAssessment.getHazardCollection();

    }

    /**
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
    
    public void redirectToHazard(){
        
    }

}
