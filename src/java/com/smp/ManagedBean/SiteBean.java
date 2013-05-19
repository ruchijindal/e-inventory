/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProject;
import com.smp.EntityBean.ESites;
import com.smp.SessionBean.EProjectFacade;
import com.smp.SessionBean.ESitesFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "siteBean")
@ApplicationScoped
@Stateless
public class SiteBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    ESitesFacade eSitesFacade;
    public EProject eProject = new EProject();
    public ESites eSites = new ESites();
    public static ESites eDelSites = new ESites();
    private ArrayList projectList = new ArrayList();
    public List<ESites> siteList = new ArrayList<ESites>();
    int projectId;

    @PostConstruct
    public void populate() {
        projectList = new ArrayList();
        siteList.clear();
        eSites = new ESites();
        eProject = new EProject();
        projectId = 0;
        for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
            projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
        }
        siteList = eSitesFacade.findAll();


    }

    public void addSite() throws IOException {

        System.out.println("project id=====> " + eProject.getId());
        eSites.setSiteStockLevelXml("null");
        eProject = eProjectFacade.find(projectId);
        eSites.setEProject(eProject);
        eSitesFacade.create(eSites);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Site Successfully Added"));
        populate();
    }

    public void editSite(org.primefaces.event.RowEditEvent e) {
        ESites eSiteNew = (ESites) e.getObject();
        eProject = eProjectFacade.find(eSiteNew.getEProject().getId());
        eSiteNew.setEProject(eProject);
        eSitesFacade.edit(eSiteNew);
        siteList = eSitesFacade.findAll();

    }

    public void deleteSite() throws IOException {

        System.out.println("inside delete project..."+ eDelSites.getName());
        
        eSitesFacade.remove(eDelSites);
        siteList = eSitesFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Project/addSite.xhtml");

    }

    public String findProject(int projectId) {
        return eProjectFacade.find(projectId).getName();
    }

    public void confirmDelete(ESites eSites) {
        System.out.println("inside  confirm delete project...");
        eDelSites = eSites;
        

    }

    public ESites geteSites() {
        return eSites;
    }

    public void seteSites(ESites eSites) {
        this.eSites = eSites;
    }

    public ArrayList getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList projectList) {
        this.projectList = projectList;
    }

    public List<ESites> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<ESites> siteList) {
        this.siteList = siteList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;

    }

    public EProject geteProject() {
        System.out.println("project id=====> " + eProject.getId());
        return eProject;
    }

    public void seteProject(EProject eProject) {
        this.eProject = eProject;
        System.out.println("project id=====> " + eProject.getId());
    }

    

    
}
