/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.beans;

import com.merrimansa.ejb.UserFacade;
import com.merrimansa.entities.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Steve
 */
@Named(value = "userBean")
@RequestScoped
public class userBean {

    /**
     * Creates a new instance of userBean
     */
    public userBean() {
    }
    @Inject
    UserFacade userFacade;
    
    public List<User> getUsers(){
        return (List)userFacade.findAll();
    }
    
}
