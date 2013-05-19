/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.SessionBean;

import com.smp.EntityBean.EEmployee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@Stateless
public class EEmployeeFacade extends AbstractFacade<EEmployee> {
    @PersistenceContext(unitName = "e-InventoryPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EEmployeeFacade() {
        super(EEmployee.class);
    }

}
