/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans.converters;


import com.merrimansa.ejb.ProcessStepFacade;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.ProcessStep;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value="processStepConverter")
public class ProcessStepConverter implements Converter {
    
    @Inject
    ProcessStepFacade PSF;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty()){
            return null;
        }
        
        try{
            return PSF.find(Integer.valueOf(value));
        }catch(NumberFormatException e){
            throw new ConverterException(new FacesMessage(value +" is not a valid Asset Id 1"));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return "";
        }
        
        System.out.println("Here it is"+value.getClass().toString());
        
        
        if(value instanceof ProcessStep){
            return String.valueOf(((ProcessStep)value).getProcessStepId());
        }else{
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID",value))); 
            
        }
        
    }
}
