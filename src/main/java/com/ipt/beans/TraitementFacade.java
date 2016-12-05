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
public class TraitementFacade extends AbstractFacade<Traitement> {
    @PersistenceContext(unitName = "com.mycompany_IPT-Bio-webApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TraitementFacade() {
        super(Traitement.class);
    }
    
}
