/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.ejb.DepartmentFacade;
import com.merrimansa.ejb.ProcessFacade;
import com.merrimansa.ejb.SiteFacade;
import com.merrimansa.entities.Department;
import com.merrimansa.entities.Site;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.merrimansa.entities.Process;



/**
 *
 * @author Steve
 */

public class ProcessManager {
    
    @Inject
    SiteFacade SF;
    
    @Inject
    DepartmentFacade DF;
    
    @Inject
    ProcessFacade PF;
    
    public List<Site>getSites(){
        return SF.findAll();
    }
    
    public List<Department>getDepartments(Site site){
        
        List<Department> theDeps = new ArrayList();
        
        for(Department d: DF.findAll()){
            if(d.getSiteId().equals(site)){
                theDeps.add(d);
            }
        }
        return theDeps;
    }
    
    public List<Process>getProcesses(Department department){
        List<Process> theProcess = new ArrayList();
        
        for(Process p: PF.findAll()){
            if(p.getDepartmentId().equals(department)){
                theProcess.add(p);
            }
        }
        return theProcess;
    }
}
