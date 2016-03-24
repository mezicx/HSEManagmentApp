/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans.converters;

import com.merrimansa.ejb.AssetFacade;
import com.merrimansa.entities.Asset;
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
@Named(value ="assetConverter")
public class AssetConverter implements Converter {

    @Inject
    AssetFacade AF;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty()){
            return null;
        }
        
        try{
            return AF.find(Integer.valueOf(value));
        }catch(NumberFormatException e){
            throw new ConverterException(new FacesMessage(value +" is not a valid Asset Id 1"));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return "";
        }
        
        
        
        if(value instanceof Asset){
            return String.valueOf(((Asset)value).getAssetId());
        }else{
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid ID",value))); 
           
        }
        
    }
    
}
