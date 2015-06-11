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
public enum ControlMeasureValues {
    
    ELIM("Elimination"),
    SUB("Substitution"),
    ENG("Engineering Controls"),
    ADMIN("AdministrativeControls"),
    PPE("PersonnelProtectiveEquipment");
    
    private String displayName;
    
    ControlMeasureValues(String DisplayName){
        this.displayName=DisplayName;
    }
    
    public String getDisplayName(){
        return displayName;
    }
    
    @Override
    public String toString(){
        return displayName;
    }
}
