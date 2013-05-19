/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.SessionBean;

import com.smp.EntityBean.EOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@Stateless
public class EOrderFacade extends AbstractFacade<EOrder> {
    @PersistenceContext(unitName = "e-InventoryPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EOrderFacade() {
        super(EOrder.class);
    }

}
