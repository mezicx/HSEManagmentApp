/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.ejb;

import com.merrimansa.businessObjects.ProcessManager;
import com.merrimansa.entities.Department;
import com.merrimansa.entities.Site;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
public class ProcessManagerFacade {
    
    @Inject
    ProcessManager PM;
    
    public List<Site>getSites(){
        return PM.getSites();
    }
    
    public List<Department>getDepartments(Site site){
        return PM.getDepartments(site);
    }
}
