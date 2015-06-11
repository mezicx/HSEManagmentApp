/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans.converters;

import com.merrimansa.ejb.InjuredPartyFacade;
import com.merrimansa.entities.Asset;
import com.merrimansa.entities.InjuredParty;
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
@Named(value = "injuredPartyConverter")
public class InjuredPartyConverter implements Converter {
    
    @Inject
    InjuredPartyFacade IPF;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       if(value == null || value.isEmpty()){
            return null;
        }
        
        try{
            return IPF.find(Integer.valueOf(value));
        }catch(NumberFormatException e){
            throw new ConverterException(new FacesMessage(value +" is not a valid InjuredParty Id"));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if(value == null){
            return "";
        }
        
        //System.out.println("Here it is"+value.getClass().toString());
        
        
        if(value instanceof InjuredParty){
            return String.valueOf(((InjuredParty)value).getPartyId());
        }else{
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID",value))); 
            
        }
        
    }
    
}
