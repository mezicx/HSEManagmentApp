/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.ejb;

import com.merrimansa.entities.ProcessAssessment;
import com.merrimansa.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Steve
 */
@Stateless
public class ProcessAssessmentFacade extends AbstractFacade<ProcessAssessment> {
    @PersistenceContext(unitName = "HSEManagmentAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcessAssessmentFacade() {
        super(ProcessAssessment.class);
    }
    
    public List<ProcessAssessment> getProcessAssessmentsByUser(User user){
        Query q = em.createQuery("SELECT p FROM ProcessAssessment p WHERE p.userId = :userId");
       //Set query parameters taken from named query in User entity 
       q.setParameter("userId", user);
       
       return q.getResultList();
    }
    
}
