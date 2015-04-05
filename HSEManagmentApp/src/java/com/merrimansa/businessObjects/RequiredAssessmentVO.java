/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

import java.util.Date;

/**
 *
 * @author Steve
 */
public class RequiredAssessmentVO {
    
    private String ProcessName;
    private Date LastAssessment;
    private int ProcessId;
    private UserVO Assessor;
    private boolean toBeAssigned;

    public RequiredAssessmentVO() {
    }
    
    
    public RequiredAssessmentVO(String ProcessName, Date LastAssessment, int ProcessId) {
        this.ProcessName = ProcessName;
        this.LastAssessment = LastAssessment;
        this.ProcessId = ProcessId;
        this.toBeAssigned = false;
        
    }

    public String getProcessName() {
        return ProcessName;
    }

    public void setProcessName(String ProcessName) {
        this.ProcessName = ProcessName;
    }

    public Date getLastAssessment() {
        return LastAssessment;
    }

    public void setLastAssessment(Date LastAssessment) {
        this.LastAssessment = LastAssessment;
    }

    public int getProcessId() {
        return ProcessId;
    }

    public void setProcessId(int ProcessId) {
        this.ProcessId = ProcessId;
    }

    public UserVO getAssessor() {
        return Assessor;
    }

    public void setAssessor(UserVO Assessor) {
        this.Assessor = Assessor;
    }

    public boolean isToBeAssigned() {
        return toBeAssigned;
    }

    public void setToBeAssigned(boolean toBeAssigned) {
        this.toBeAssigned = toBeAssigned;
    }
    
    
    
    
}
