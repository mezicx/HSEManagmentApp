/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.ejb;

import com.merrimansa.entities.PostcontrolAssessment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Steve
 */
@Stateless
public class PostcontrolAssessmentFacade extends AbstractFacade<PostcontrolAssessment> {
    @PersistenceContext(unitName = "HSEManagmentAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostcontrolAssessmentFacade() {
        super(PostcontrolAssessment.class);
    }
    
}
