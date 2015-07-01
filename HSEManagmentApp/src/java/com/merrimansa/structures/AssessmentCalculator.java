/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.structures;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Steve
 */
public class AssessmentCalculator {
    
    private Map<String,Integer> likelihood;
    private Map<String,Integer> Severity;
    
    private int sev,like;
    
    public AssessmentCalculator(){
        
        likelihood = new LinkedHashMap();
        Severity = new LinkedHashMap();
        
        Severity.put("Trivial Accident", 1);
        Severity.put("Minor Accident", 2);
        Severity.put("Medical Treatment Accident", 3);
        Severity.put("Lost Time Accident", 4);
        Severity.put("Single Fatality", 5);
        Severity.put("Multiple Fatality", 6);
        
        likelihood.put("Practically Impossible", 1);
        likelihood.put("Unlikely", 2);
        likelihood.put("Possible", 3);
        likelihood.put("Likely", 4);
        likelihood.put("Very Likely", 5);
        likelihood.put("Inevitable", 6);
        
    }

    public int getSev() {
        return sev;
    }

    public void setSev(int sev) {
        this.sev = sev;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

   
    
    

    public Map<String, Integer> getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(Map<String, Integer> likelihood) {
        this.likelihood = likelihood;
    }

    public Map<String, Integer> getSeverity() {
        return Severity;
    }

    public void setSeverity(Map<String, Integer> Severity) {
        this.Severity = Severity;
    }
    
    public String getResult(int riskRating){
        
        String result = "High Risk";
        
        if(riskRating > 0 && riskRating <=6){
            result = "Low Risk";
        }else if(riskRating >6 && riskRating <=12){
            result = "Medium Risk";
        }
        
        return result;
        
        
    }
}
