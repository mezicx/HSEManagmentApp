/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.structures;

import java.util.Map;

/**
 *
 * @author Steve
 */
public enum Category {
    
    PHYSICAL("Physical Hazards"),
    ERGONOMICS("Ergonomics"),
    FIRE("Fire"),
    CHEMBIO("Chemical/Biological"),
    WORKEQUIP("Work Equipment"),
    CONTRACTENGINEER("Contractors & Engineering"),
    NEIGHBOURS("Neighbours"),
    BULIDINGPREM("Buildings & Premises"),
    HUMANFACTORS("Human Factors");
    
    private String displayName;
    private static final String[] Phy = {"Pressure Vessels","Noise & Vibration", "Radiation","Temperature Extremes"};
    private static final String[] Erg = {"Manual Handling","Sprains/Strains","RSI/WRULDS"};
    private static final String[] Fire = {"Ignition Sources","Sources of Fuel","Oxygen Sources","Evacuation Issues"};
    private static final String[] Chem = {"Hazardous Substances","Machine Lubricants & Oils","Asbestos","Legionella","Toxic Gases"};
    private static final String[] Work = {"Portable Appliances & Hand Tools","Machinery","Lifting Equipment","Workplace Transport","Contact with Objects"};
    private static final String[] Cont = {"Electrical","Confined Spaces","Working at Height","Excavations"};
    private static final String[] Neig = {"Comah","Major Fire"};
    private static final String[] Buil = {"Slips & Trips","Adverse Weather","Lighting"};
    private static final String[] Huma = {"Slips","Lapses","Mistakes","Violations"};
    private static final String[] Empty = {"None"};
    
    Category(String DisplayName){
        this.displayName=DisplayName;
        
        
    }
    
     public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String[] getSubCats(){
        
        switch(this){
            case PHYSICAL:return Phy;
                
            case ERGONOMICS:return Erg;
                
            case FIRE: return Fire;
                
            case CHEMBIO: return Chem;
                
            case WORKEQUIP: return Work;
                
            case CONTRACTENGINEER: return Cont;
                
            case NEIGHBOURS: return Neig;
                
            case BULIDINGPREM: return Buil;
                
            case HUMANFACTORS: return Huma;
                
            default: return Empty;
        }
        
        
    }
        
    

    @Override
    public String toString() {
        return displayName;
    }
}