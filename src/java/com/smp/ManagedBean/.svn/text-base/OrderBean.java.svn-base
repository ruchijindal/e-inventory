/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.ESites;
import com.smp.SessionBean.ESitesFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "orderbean")
@SessionScoped
@Stateless
public class OrderBean implements Serializable {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    ESitesFacade eSitesFacade;
    int siteId;
   

    @PostConstruct
    public void populate() {
        

        
    }

   

  
   
}
