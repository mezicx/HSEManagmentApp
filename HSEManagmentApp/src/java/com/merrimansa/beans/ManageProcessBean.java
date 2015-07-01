/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.ejb.ProcessManagerFacade;
import com.merrimansa.entities.Department;
import com.merrimansa.entities.Site;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named("manageProcessBean")
@ConversationScoped
public class ManageProcessBean implements Serializable {
    
    @Inject
    ProcessManagerFacade PMF;
    
    private Site theSite;
    private List<Department> theDepartments;
    
    @PostConstruct
    public void init(){
        theDepartments = new ArrayList();
    }
    
    /**
     * Used to update Area selection box when site is changed
     */
    public void updateUpdateDepartments(){
        theDepartments = PMF.getDepartments(theSite);
    }
    
    public List<Site> getSites(){
        return PMF.getSites();
    }

    public Site getTheSite() {
        return theSite;
    }

    public void setTheSite(Site theSite) {
        this.theSite = theSite;
    }

    public List<Department> getTheDepartments() {
        return theDepartments;
    }

    public void setTheDepartments(List<Department> theDepartments) {
        this.theDepartments = theDepartments;
    }
    
    
    
    
    
    
}
