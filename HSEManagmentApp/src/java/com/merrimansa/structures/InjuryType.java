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
public enum InjuryType {
   
    BURNS("Burns"),
    CHEM_CONTAMINATION("Chemical Contamination"),
    CONTACT_EQUIPOBJECTS("Contact with Equipment or Objects"),
    CONTACT_MACHINARY("Contact wit Machinary"),
    CONTACT_STATIONARYOBJECT("Contact with Stationary Object"),
    FOREIGNBODY_EYE("Foreign Body in Eye"),
    STRUCK_BY_EJECTED("Struck by Ejected Objects or Materials"),
    KNIFE_INJURY("Knife Injury"),
    MAN_HANDLING_LIFTING("Strains Whilst Lifting"),
    OTHER("Other"),
    SHARPS("Sharps Injury"),
    SLIPS_TRIPS("Slips, Trips and Falls"),
    STRUCK_MOVING_OBJECT("Struck by Moving or Falling Object"),
    FALL("Falls from Height"),
    SPRAIN_STRAIN("Sprains or Strains non Lifitng"),
    ELECTRIC_SHOCK("Electrical Shock"),
    OCUP_ILL_HEALTH("Occupational Ill Health");
    
    private String displayName;
    
    InjuryType(String DisplayName){
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
