/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.beans.converters.ImageProcessor;
import com.merrimansa.businessObjects.UserVO;
import com.merrimansa.ejb.HazardManagerFacade;
import com.merrimansa.ejb.PrecontrolAssessmentFacade;
import com.merrimansa.entities.Action;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.ControlMeasure;
import com.merrimansa.entities.Hazard;
import com.merrimansa.entities.Image;
import com.merrimansa.entities.InjuredParty;
import com.merrimansa.entities.PostcontrolAssessment;
import com.merrimansa.structures.Categories;

import com.merrimansa.structures.InjuryType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.merrimansa.entities.PrecontrolAssessment;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.entities.ProcessStep;
import com.merrimansa.entities.User;
import com.merrimansa.structures.AssessmentCalculator;
import com.merrimansa.structures.ControlMeasureValues;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import javax.faces.application.FacesMessage;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Steve
 */
@Named(value = "manageHazardBean")
@ConversationScoped
public class ManageHazardBean implements Serializable {

    private int AssessmentId;

    private Hazard theHazard;

    private ControlMeasure theControlMeasure;

    private ControlMeasure selectedControlMeasure;

    private Action theAction;

    private AssessmentCalculator AssessmentCalc;

    private String[] subcats;

    private Categories cats;

    private final String emptyString = "";

    @Inject
    private Conversation conversation;

    @Inject
    HazardManagerFacade HMF;

    private int theUserId;

    private UploadedFile theFile;

    /**
     * Creates a new instance of ManageHazardBean
     */
    public ManageHazardBean() {
    }

    @PostConstruct
    public void init() {

        //System.out.println("Init called");

        cats = new Categories();
        AssessmentCalc = new AssessmentCalculator();

        
        conversation.begin();
        System.out.println("Conversation Id " + this.getConversationId());
        

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = externalContext.getRequestParameterMap();

        //Get Assessment Id from Param Map if present
        if (params.get("AssessmentId") != null) {

            AssessmentId = Integer.parseInt(params.get("AssessmentId"));
        }
        //Set theControlMeasure to an empty control measure entity
        theControlMeasure = new ControlMeasure();

        //Set theAction to empty Action entity
        theAction = new Action();
        //theAction.setUserId(new User());
        //Get Hazard to be updated or create new one if none exists
        if (params.containsKey("HazardId") && params.get("HazardId") != null) {

            theHazard = HMF.getHazardByAssessment(Integer.parseInt(params.get("HazardId")));

            subcats = cats.getCat().get(theHazard.getCategory());

            theControlMeasure.setHazardId(theHazard);

        } else {
            theHazard = new Hazard();
            theHazard.setAssessmentId(new ProcessAssessment(AssessmentId));

        }
        //add a new precontrol & postcontrol assessment if one does not exisit
        if (theHazard.getPrecontrolAssessment() == null) {
            theHazard.setPrecontrolAssessment(new PrecontrolAssessment(1));
            theHazard.getPrecontrolAssessment().setHazardId(theHazard);
        }
        if (theHazard.getPostcontrolAssessment() == null) {
            theHazard.setPostcontrolAssessment(new PostcontrolAssessment(1));
            theHazard.getPostcontrolAssessment().setHazardId(theHazard);
        }

    }

    public UploadedFile getTheFile() {
        return theFile;
    }

    public void setTheFile(UploadedFile theFile) {
        this.theFile = theFile;
        System.out.println("The file name is: " + theFile.getFileName());
    }

    public AssessmentCalculator getAssesmentCalc() {
        return AssessmentCalc;
    }

    public void setAssesmentCalc(AssessmentCalculator AssesmentCalc) {
        this.AssessmentCalc = AssesmentCalc;
    }

    public Action getTheAction() {
        return theAction;
    }

    public void setTheAction(Action theAction) {
        this.theAction = theAction;
    }

    public List<InjuredParty> getInjuredParties() {
        return (List) HMF.getInjuredPartyCollection();
    }

    public int getAssessmentId() {
        return AssessmentId;
    }

    public void setAssessmentId(int AssessmentId) {
        this.AssessmentId = AssessmentId;
    }

    public ControlMeasure getTheControlMeasure() {
        return theControlMeasure;
    }

    public void setTheControlMeasure(ControlMeasure theControlMeasure) {
        this.theControlMeasure = theControlMeasure;
    }

    public Hazard getTheHazard() {
        return theHazard;
    }

    public void setTheHazard(Hazard theHazard) {
        this.theHazard = theHazard;
    }

    public int getTheUserId() {
        return theUserId;
    }

    public void setTheUserId(int theUserId) {
        this.theUserId = theUserId;
    }

    public boolean postControlRenderCheck() {
        Boolean render = false;
        if (theHazard.getControlMeasureCollection() != null
                && !theHazard.getControlMeasureCollection().isEmpty()) {

            render = true;
        }

        return render;
    }

    /**
     * Used to update sub categories selection box
     */
    public void updateSubCategories() {
        //Check category is not null
        //System.out.println("Ajax has been called");

        if (theHazard.getCategory() != null && !theHazard.getCategory().equals("")) {

            //if not null update subcategories
            //subcats = HMF.getSubCategory(theHazard.getCategory());
            System.out.println(theHazard.getCategory());
            subcats = cats.getCat().get(theHazard.getCategory());
            theHazard.setSubCategory(null);

        } else {
            subcats = new String[0];
        }
        //System.out.println("Ajax call completed");
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

    public ControlMeasure getSelectedControlMeasure() {
        return selectedControlMeasure;
    }

    public void setSelectedControlMeasure(ControlMeasure selectedControlMeasure) {
        System.out.println("selectedControl Measure set");
        this.selectedControlMeasure = selectedControlMeasure;
    }

    public List<Asset> getAssets() {

        return (List) HMF.getPotentialAssests(AssessmentId);
    }

    public List<ProcessStep> getProcessSteps() {
        return (List) HMF.getProcessStepCollection(AssessmentId);
    }

    public ControlMeasureValues[] getControlMeasureValues() {
        return HMF.getControlMeasures();
    }

    public List<UserVO> getUsers() {
        return HMF.getUsers();
    }

    public Image getImage() {

        Image im = new Image();
        im.setImageData(emptyString);
        if (theHazard.getImageCollection() != null) {
            if (!theHazard.getImageCollection().isEmpty()) {
                for (Image img : theHazard.getImageCollection()) {
                    im = img;
                }
            }
        }

        return im;
    }

    public String getConversationId() {
        return conversation.getId();
    }

    public String getEmptyString() {
        return emptyString;
    }

    /**
     * Adds Action to the selected control measure
     */
    public void addAction() {
        
        System.out.println("Add Action Called");
        FacesContext context = FacesContext.getCurrentInstance();
        //if no action collection exists for current hazard create new one
        if (selectedControlMeasure.getActionCollection() == null) {
            Collection<Action> c = new ArrayList();
            selectedControlMeasure.setActionCollection(c);
        }
        //Set User Id and Control Measure Id in placeholder action
        theAction.setUserId(HMF.getUser(theUserId));
        theAction.setControlId(selectedControlMeasure);
        //Add place holder action to control measure action collection
        selectedControlMeasure.getActionCollection().add(theAction);
        System.out.println("Action " + theAction.getControlId().getControlId() + " ");
        //Create new empty place holder Action object 
        theAction = new Action();
        theAction.setUserId(HMF.getUser(theUserId));
        theAction.setControlId(selectedControlMeasure);
        //Add new message showing success
        System.out.println("Action Added Sucessfully");
        context.addMessage(null, new FacesMessage("Success", "Action added"));

    }

    /**
     * Used to add a control measure to the hazard
     */
    public void addControlMeasure() {
        //Check if Hazard is new
        System.out.println("Conversation Id" + this.getConversationId());
        if (theHazard.getControlMeasureCollection() != null) {
            theHazard.getControlMeasureCollection().add(theControlMeasure);
            System.out.println("Control Measure Collection Already exisited");
        } else {
            theControlMeasure.setHazardId(theHazard);
            Collection<ControlMeasure> c;
            c = new ArrayList<>();
            c.add(theControlMeasure);
            try {
                theHazard.setControlMeasureCollection(c);

            } catch (Exception e) {
                System.out.println("Problem adding collection");
            }
            //System.out.println(theHazard.getControlMeasureCollection().toString());
        }

        theControlMeasure = new ControlMeasure();
        theControlMeasure.setHazardId(theHazard);

    }

    public void saveHazard() throws IOException {

        System.out.println("Save Called");

        if (this.theFile != null && !theFile.getFileName().equalsIgnoreCase("")) {
            try {
                ImageProcessor ip = new ImageProcessor();

                Image i = new Image();
                i.setImageData(ip.processImage(theFile));
                i.setHazardId(theHazard);
                i.setImageName(theFile.getFileName());
                
                
                if (theHazard.getImageCollection()!=null) {

                    theHazard.getImageCollection().add(i);
                } else {
                    Collection<Image> c = new ArrayList();
                    c.add(i);
                    theHazard.setImageCollection(c);
                }
                System.out.println("Image added sucessfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //for(ControlMeasure c : theHazard.getControlMeasureCollection()){
        //  System.out.println("Category "+ c.getControlCategory()+" Hazard Id " +c.getHazardId().getHazardId());
        //}
        //PCAF.create(theHazard.getPrecontrolAssessment());
        HMF.saveHazard(theHazard);

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        externalContext.redirect("./mobileManageAssessment.xhtml?AssessmentId=" + getAssessmentId());

        conversation.end();

        System.out.println("Conversation end is called");
    }

}
