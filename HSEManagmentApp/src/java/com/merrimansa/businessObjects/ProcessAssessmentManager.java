/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.ejb.ProcessFacade;
import com.merrimansa.ejb.UserFacade;
import com.merrimansa.entities.Process;
import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.entities.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Steve
 */
public class ProcessAssessmentManager {

    @Inject
    ProcessFacade processFacade;

    @Inject
    private ProcessAssessmentFacade pAFacade;
    
    @Inject
    private UserFacade userFacade;

    public ProcessAssessmentManager() {

    }
    
    /**
     * 
     * @param currentUser 
     * @return  
     */
    public List<ProcessAssessment> getAssignedAssessments(UserVO currentUser){
        
        return pAFacade.getProcessAssessmentsByUser(
        userFacade.find(currentUser.getUserId()));
        
    }
    
   
    /**
     * Assigns a new assessment to the user identified in the parameter with the basic
     * information set in the parameter
     * 
     * @param reqAssess 
     */
    public void assignAssessment(RequiredAssessmentVO reqAssess){
        ProcessAssessment pa = new ProcessAssessment();
        
        pa.setApproved(false);
        pa.setAssessmentDate(new Date());
        pa.setSubmitted(false);
        System.out.println("Process ID is "+reqAssess.getProcessId());
        pa.setProcessId(processFacade.find(reqAssess.getProcessId()));
        pa.setTitle(reqAssess.getProcessName());
        pa.setUserId(new User(reqAssess.getAssessor().getUserId()));
        
        pAFacade.create(pa);
        
    }
    /**
     * Returns a list of assessments that will be older than 
     * 2years 7months during the current calendar year
     * 
     * @return A list of display objects
     */
    public List<RequiredAssessmentVO> getRequiredAssessments() {
        //Get date object for end of current year
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        
        Date CalYear = calendar.getTime();
        
        //Initisialise new empty Assessment List value object
        List<RequiredAssessmentVO> requiredAssessments = new ArrayList<RequiredAssessmentVO>();
        //Obtain list of all process entities
        List<Process> processes = processFacade.findAll();
        //Loop through process list and inspect assessments
        for (Process currentProcess : processes) {

            List<ProcessAssessment> assessments = (List) currentProcess.getProcessAssessmentCollection();
            //If no assessments exisit add process to required assessment List
            if (assessments == null || assessments.isEmpty()) {
                requiredAssessments.add(new RequiredAssessmentVO(currentProcess.getProcessName(),
                null, currentProcess.getProcessId()));
            }else{
                //create VO to add if required
                RequiredAssessmentVO ReqVO = new RequiredAssessmentVO();
                //loop through assesments if one assessment date is less than
                // 2 years 9 months old dont add it to required assessment list
                for(ProcessAssessment a:assessments){
                    Date AssessmentDate = a.getAssessmentDate();
                    System.out.println(CalYear.getTime() - AssessmentDate.getTime());
                    if(CalYear.getTime() - AssessmentDate.getTime() < 104068800000L){
                        ReqVO = null;
                        break;
                    }else{
                        //Assign process name & Id to VO
                        ReqVO.setProcessName(currentProcess.getProcessName());
                        ReqVO.setProcessId(currentProcess.getProcessId());
                        //compare current iteration date with assignred VO date and reaassign if date is later
                        if(ReqVO.getLastAssessment() == null){
                            ReqVO.setLastAssessment(AssessmentDate);
                        }else if(a.getAssessmentDate().after(ReqVO.getLastAssessment())){
                            ReqVO.setLastAssessment(a.getAssessmentDate());
                        }
                        
                    }
                }
                //if the VO hasnt been set null add it to the return list
                if(ReqVO != null){
                    requiredAssessments.add(ReqVO);
                }
            }
        }
        return requiredAssessments;

    }

    
    
    

}
