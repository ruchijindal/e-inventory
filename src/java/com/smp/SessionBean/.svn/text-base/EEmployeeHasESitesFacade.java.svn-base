/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.SessionBean;

import com.smp.EntityBean.EEmployeeHasESites;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@Stateless
public class EEmployeeHasESitesFacade extends AbstractFacade<EEmployeeHasESites> {
    @PersistenceContext(unitName = "e-InventoryPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EEmployeeHasESitesFacade() {
        super(EEmployeeHasESites.class);
    }

}
