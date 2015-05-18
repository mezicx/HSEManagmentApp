/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.ejb.HazardFacade;
import com.merrimansa.ejb.HazardManagerFacade;
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
    
    private String[] subcats;
    
    private Categories cats;

    @Inject
    Conversation conversation;

    
    
    @Inject
    HazardManagerFacade HMF;

    /**
     * Creates a new instance of ManageHazardBean
     */
    public ManageHazardBean() {
    }

    @PostConstruct
    public void init() {
        
        System.out.println("Init called");
        
        cats = new Categories();

        if (conversation.isTransient()) {
            conversation.begin();
        }

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();

        if (params.get("AssessmentId") != null) {
            
            AssessmentId = Integer.parseInt(params.get("AssessmentId"));
        }

        if (params.containsKey("HazardId") && params.get("HazardId") != null) {

            theHazard = HMF.getHazardByAssessment(Integer.parseInt(params.get("HazardId")));
            subcats = cats.getCat().get(theHazard.getCategory());
          
        } else {
            theHazard = new Hazard();
        }

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
        if(theHazard.getCategory() != null && !theHazard.getCategory().equals("")){
            
            //if not null update subcategories
            subcats = HMF.getSubCategory(theHazard.getCategory());
            
            theHazard.setSubCategory(null);
           
        }else
        {
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

    public Asset[] getAssets() {
        
        System.out.println("Assets called");
        
        
        return  HMF.getPotentialAssests(AssessmentId).toArray(new Asset[0]);
    }
    
    public InjuredParty[] getInjuredParties(){
        
        return HMF.getInjuredPartyCollection().toArray(new InjuredParty[0]);
    }
    
    public void setInjuredParties(InjuredParty[] ip){
        Collection c = Arrays.asList(ip);
        theHazard.setInjuredPartyCollection(c);
    }
    
    public String getConversationId(){
        return conversation.getId(); 
    }

}
