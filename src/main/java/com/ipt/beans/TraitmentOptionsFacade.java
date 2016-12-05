/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipt.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sabrine
 */
@Stateless
public class TraitmentOptionsFacade extends AbstractFacade<TraitmentOptions> {
    @PersistenceContext(unitName = "com.mycompany_IPT-Bio-webApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TraitmentOptionsFacade() {
        super(TraitmentOptions.class);
    }
    
}
