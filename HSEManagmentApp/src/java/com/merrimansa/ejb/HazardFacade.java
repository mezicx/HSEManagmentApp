/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.ejb;

import com.merrimansa.entities.Hazard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Steve
 */
@Stateless
public class HazardFacade extends AbstractFacade<Hazard> {
    @PersistenceContext(unitName = "HSEManagmentAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HazardFacade() {
        super(Hazard.class);
    }
    
}
