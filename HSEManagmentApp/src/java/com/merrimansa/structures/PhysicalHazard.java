/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.structures;

/**
 *
 * @author Steve
 */
public enum PhysicalHazard {
    
    PRESSUREVESSEL("Pressure Vessel"),
    NOISEVIBRATION("Noise & Vibration"),
    RADIATION("Radiation"),
    TEMPEXTREMES("Temperature Extremes");
    
     private String displayName;
    
    PhysicalHazard(String DisplayName){
        this.displayName=DisplayName;
    }
    
     public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
