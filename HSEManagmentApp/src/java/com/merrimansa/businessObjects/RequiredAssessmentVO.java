/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.businessObjects;

/**
 *
 * @author Steve
 */
public class RequiredAssessmentVO {
    
    private String ProcessName;
    private String LastAssessment;
    private int ProcessId;
    private UserVO Assessor;

    public RequiredAssessmentVO() {
    }
    
    
    public RequiredAssessmentVO(String ProcessName, String LastAssessment, int ProcessId, UserVO Assessor) {
        this.ProcessName = ProcessName;
        this.LastAssessment = LastAssessment;
        this.ProcessId = ProcessId;
        this.Assessor = Assessor;
    }

    public String getProcessName() {
        return ProcessName;
    }

    public void setProcessName(String ProcessName) {
        this.ProcessName = ProcessName;
    }

    public String getLastAssessment() {
        return LastAssessment;
    }

    public void setLastAssessment(String LastAssessment) {
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
    
    
    
}
