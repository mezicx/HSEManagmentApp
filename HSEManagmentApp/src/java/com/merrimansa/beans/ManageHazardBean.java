/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.ejb.HazardFacade;
import com.merrimansa.ejb.HazardManagerFacade;
import com.merrimansa.ejb.PrecontrolAssessmentFacade;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.Hazard;
import com.merrimansa.structures.Categories;
import com.merrimansa.structures.Category;
import com.merrimansa.structures.InjuryType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.PrecontrolAssessment;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.entities.ProcessStep;
import com.merrimansa.structures.AssessmentCalculator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
@Named(value = "manageHazardBean")
@ConversationScoped
public class ManageHazardBean implements Serializable {

    private int AssessmentId;

    private Hazard theHazard;

    private AssessmentCalculator AssessmentCalc;
    
    
    private String[] subcats;

    private Categories cats;

    

    @Inject
    private Conversation conversation;

    @Inject
    HazardManagerFacade HMF;
    
    @Inject
    PrecontrolAssessmentFacade PCAF;

    /**
     * Creates a new instance of ManageHazardBean
     */
    public ManageHazardBean() {
    }

    @PostConstruct
    public void init() {

        System.out.println("Init called");

        cats = new Categories();
        AssessmentCalc = new AssessmentCalculator();

       // if (conversation.isTransient()) {
        //   System.out.println("Conversation is transient");
        conversation.begin();
        System.out.println("Conversation Id " + this.getConversationId());
        //}

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();

        if (params.get("AssessmentId") != null) {

            AssessmentId = Integer.parseInt(params.get("AssessmentId"));
        }

        if (params.containsKey("HazardId") && params.get("HazardId") != null) {

            theHazard = HMF.getHazardByAssessment(Integer.parseInt(params.get("HazardId")));
            
            subcats = cats.getCat().get(theHazard.getCategory());

        } else {
            theHazard = new Hazard(1);
            theHazard.setAssessmentId(new ProcessAssessment(AssessmentId));
            
        }
        
        if(theHazard.getPrecontrolAssessment() == null){
            theHazard.setPrecontrolAssessment(new PrecontrolAssessment(1));
            theHazard.getPrecontrolAssessment().setHazardId(theHazard);
        }

    }

    public AssessmentCalculator getAssesmentCalc() {
        return AssessmentCalc;
    }

    public void setAssesmentCalc(AssessmentCalculator AssesmentCalc) {
        this.AssessmentCalc = AssesmentCalc;
    }
    
    

    public int getAssessmentId() {
        return AssessmentId;
    }

    public void setAssessmentId(int AssessmentId) {
        this.AssessmentId = AssessmentId;
    }

    

    public Hazard getTheHazard() {
        return theHazard;
    }

    public void setTheHazard(Hazard theHazard) {
        this.theHazard = theHazard;
    }

    public void updateSubCategories() {
        //Check category is not null
        if (theHazard.getCategory() != null && !theHazard.getCategory().equals("")) {

            //if not null update subcategories
            subcats = HMF.getSubCategory(theHazard.getCategory());

            theHazard.setSubCategory(null);

        } else {
            subcats = new String[0];
        }

    }

    public String[] getCategories() {

        return HMF.getCategories().getCat().keySet().toArray(new String[0]);
    }

    public InjuryType[] getInjuryTypes() {
        return HMF.getInjuryTypes();
    }

    public String[] getSubCategory() {

        return subcats;
    }

    public List<Asset> getAssets() {

        return (List) HMF.getPotentialAssests(AssessmentId);
    }

    public List<ProcessStep>getProcessSteps(){
        return (List)HMF.getProcessStepCollection(AssessmentId);
    }

    public String getConversationId() {
        return conversation.getId();
    }

    public void saveHazard() throws IOException {

        System.out.println("Routine?"+theHazard.getRoutine());
        
        
        //PCAF.create(theHazard.getPrecontrolAssessment());
        
        HMF.saveHazard(theHazard);

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        externalContext.redirect("./mobileManageAssessment.xhtml?AssessmentId=" + getAssessmentId());

        conversation.end();

        System.out.println("Conversation end is called");
    }

}
