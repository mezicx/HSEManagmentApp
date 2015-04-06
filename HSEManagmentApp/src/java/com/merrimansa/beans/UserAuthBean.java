/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.merrimansa.beans;

import com.merrimansa.businessObjects.UserVO;
import com.merrimansa.ejb.UserFacade;
import com.merrimansa.entities.Role;
import com.merrimansa.entities.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;



/**
 *
 * @author Steve
 */
@Named(value = "userAuthBean")
@SessionScoped
public class UserAuthBean implements Serializable {

    /**
     * Creates a new instance of UserAuthBean
     */
    public UserAuthBean() {
    }

    private String UserName, Password;
    private String originalURL;
    private String Role;

    @Inject
    UserFacade userFacade;
    
    @Inject
    CurrentUserBean currentUserBean;

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
        System.out.println(originalURL);
        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/faces/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        ExternalContext externalContext = context.getExternalContext();
        User AuthUser;
        String ErrorMsg = "Login Error";
        try {

            request.login(UserName, Password);
            System.out.println("Login Sucessful");
            //Get User object based on principle 
            AuthUser = userFacade.findByEmail(externalContext.getUserPrincipal().getName());
            //Ensure user is active and has been found in the database 

            if (AuthUser.getActive()) {
                currentUserBean.setTheUser(new UserVO(AuthUser));
            } else {
                externalContext.invalidateSession();
                ErrorMsg = "User is Inactive";
                throw new ServletException();
            }

            externalContext.redirect(originalURL);

        } catch (ServletException ex) {
            //Logger.getLogger(UserAuthBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage("growl", new FacesMessage(ErrorMsg ,"Please check user name amd password"));
            
            System.out.println("Login Failed by Steve");
            //String error = externalContext.getRequestContextPath() + "/loginError.xhtml";
            //externalContext.redirect(error);
        }

    }
    
    public void logout() throws IOException{
        System.out.println("Log out called");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect("./faces/index.xhtml");
        
    }

    

}
