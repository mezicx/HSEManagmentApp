/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.ejb;

import com.merrimansa.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Steve
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "HSEManagmentAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    /**
     * Find User Object by email, email is unique
     * @param Email
     * @return User object Identified
     */
    public User findByEmail(String Email){
       //Construct query based on Named query in User Entity
       Query q = em.createNamedQuery("User.findByEmail");
       //Set query parameters taken from named query in User entity 
       q.setParameter("email", Email);
       // Execute query to obtain single result and cast to User object type
       User u = (User)q.getSingleResult();
       //return result
       return u;
               
    }
    
}
