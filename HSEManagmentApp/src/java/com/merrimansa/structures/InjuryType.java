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
   
    BURNS("C1 Burns"),
    CHEM_CONTAMINATION("C2 Chemical Contamination"),
    CONTACT_EQUIPOBJECTS("C3 Contact with Equipment or Objects"),
    CONTACT_MACHINARY("C4 Contact wit Machinary"),
    CONTACT_STATIONARYOBJECT("C5 Contact with Stationary Object"),
    FOREIGNBODY_EYE("C6 Foreign Body in Eye"),
    STRUCK_BY_EJECTED("C7 Struck by Ejected Objects or Materials"),
    KNIFE_INJURY("C8 Knife Injury"),
    MAN_HANDLING_LIFTING("C9 Strains Whilst Lifting"),
    OTHER("C10 Other"),
    SHARPS("C11 Sharps Injury"),
    SLIPS_TRIPS("C12 Slips, Trips and Falls"),
    STRUCK_MOVING_OBJECT("C13 Struck by Moving or Falling Object"),
    FALL("C14 Falls from Height"),
    SPRAIN_STRAIN("C15 Sprains or Strains non Lifitng"),
    ELECTRIC_SHOCK("C16 Electrical Shock"),
    OCUP_ILL_HEALTH("C17 Occupational Ill Health");
    
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
