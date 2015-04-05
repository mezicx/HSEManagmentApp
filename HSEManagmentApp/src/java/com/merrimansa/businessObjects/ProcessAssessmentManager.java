/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import com.merrimansa.ejb.ProcessAssessmentFacade;
import com.merrimansa.ejb.ProcessFacade;
import java.util.List;
import javax.inject.Inject;
import com.merrimansa.entities.Process;
import com.merrimansa.entities.ProcessAssessment;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Steve
 */
public class ProcessAssessmentManager {

    @Inject
    ProcessFacade processFacade;

    @Inject
    private UserVO currentUser;

    @Inject
    private ProcessAssessmentFacade pAFacade;

    public ProcessAssessmentManager() {

    }
    /**
     * 
     * @return 
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
                        //Assign process name to VO
                        ReqVO.setProcessName(currentProcess.getProcessName());
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
