/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.structures;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Steve
 */
public class Categories {
    
    
    
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
    
    private Map Cat;
    
    public Categories(){
        
        Cat = new HashMap<String,String[]>();
        
        Cat.put("Physical Hazards",Phy);
        Cat.put("Ergonomics", Erg);
        Cat.put("Fire",Fire);
        Cat.put("Chemical/Biological", Chem);
        Cat.put("Work Equipment", Work);
        Cat.put("Contractors & Engineering", Cont);
        Cat.put("Neighbours", Neig);
        Cat.put("Buildings & Premises", Buil);
        Cat.put("Human Factors", Huma);
         
        
    }

    /**
     * Returns a map containing categories as keys and String Arrays of sub 
     * categories as values 
     * 
     * Pre:None
     * 
     * Post:An Unmodifiable Map of String Arrays will have been returned
     * 
     * @return Unmodifiable Map of String Arrays
     */
    public Map<String,String[]> getCat() {
        return Collections.unmodifiableMap(Cat);
    }

    
    
    
    
}
