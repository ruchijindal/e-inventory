/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.SessionBean;

import com.smp.EntityBean.EProductCategoryHasEProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@Stateless
public class EProductCategoryHasEProductFacade extends AbstractFacade<EProductCategoryHasEProduct> {
    @PersistenceContext(unitName = "e-InventoryPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public EProductCategoryHasEProductFacade() {
        super(EProductCategoryHasEProduct.class);
    }

}
