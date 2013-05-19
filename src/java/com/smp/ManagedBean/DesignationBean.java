/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EDesignation;
import com.smp.SessionBean.EDesignationFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "designationBean")
@ApplicationScoped
@Stateless
public class DesignationBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EDesignationFacade eDesignationFacade;
    public  EDesignation eDesignation = new EDesignation();
    public static EDesignation eDelDesignation = new EDesignation();
    public List<EDesignation> designationList = new ArrayList<EDesignation>();

    @PostConstruct
    public void populate() {
        eDesignation = new EDesignation();
        designationList.clear();
        designationList = eDesignationFacade.findAll();
    }

    public void addDesignation() throws IOException {

        eDesignationFacade.create(eDesignation);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Designation Successfully Added"));
        populate();
    }

    public void editDesignation(org.primefaces.event.RowEditEvent e) {

        eDesignationFacade.edit((EDesignation) e.getObject());
        designationList = eDesignationFacade.findAll();

    }

    public void confirmDelete(EDesignation eDesignation) {
        eDelDesignation = eDesignation;
    }

    public void deleteproject() throws IOException {

        eDesignationFacade.remove(eDelDesignation);
        designationList = eDesignationFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/UserSettings/addDesignation.xhtml");

    }

    public List<EDesignation> getDesignationList() {
        return designationList;
    }

    public void setDesignationList(List<EDesignation> designationList) {
        this.designationList = designationList;
    }

    public EDesignation geteDesignation() {
        return eDesignation;
    }

    public void seteDesignation(EDesignation eDesignation) {
        this.eDesignation = eDesignation;
    }

   
}
