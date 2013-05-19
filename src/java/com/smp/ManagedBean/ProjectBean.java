/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProject;
import com.smp.SessionBean.EProjectFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "projectBean")
@ApplicationScoped
@Stateless
public class ProjectBean implements Serializable {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProjectFacade eProjectFacade;
    public EProject eProject = new EProject();
    public static EProject delEProject = new EProject();
    public List<EProject> projectList = new ArrayList<EProject>();

    @PostConstruct
    public void populate() {
        eProject = new EProject();
        projectList.clear();
        projectList = eProjectFacade.findAll();
    }

    public void addProject() throws IOException {

        eProject.setMainBoqXml("null");
        eProject.setOrgBoqXml("null");
        eProjectFacade.create(eProject);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Project Successfully Added"));
        populate();
    }

    public void editProject(org.primefaces.event.RowEditEvent e) {

        eProjectFacade.edit((EProject) e.getObject());
        projectList = eProjectFacade.findAll();

    }

    public void deleteproject() throws IOException {
        System.out.println("inside delete project...");

        eProjectFacade.remove(delEProject);
        projectList = eProjectFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Project/addProject.xhtml");

    }

    public void confirmDelete(EProject eProject) {
        System.out.println("inside  confirm delete project...");
        delEProject = eProject;
       // System.out.println("project" + delEProject.getProjName());

    }

    public EProject geteProject() {
        return eProject;
    }

    public void seteProject(EProject eProject) {
        this.eProject = eProject;
    }

    public List<EProject> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<EProject> projectList) {
        this.projectList = projectList;
    }
}
