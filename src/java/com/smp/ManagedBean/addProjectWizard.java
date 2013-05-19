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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smp
 */
@ManagedBean(name = "projectWizardBean")
@ApplicationScoped
@Stateless
public class addProjectWizard {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    ESitesFacade eSitesFacade;
    public EProject eProject = new EProject();
    public EProject eProjectNew = new EProject();
    public static EProject delEProject = new EProject();
    public ESites eSites = new ESites();
    int projectId;

    public void addProjectsite() throws IOException
    {
        addProject();
        addSite();
         FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! details Successfully Added"));
    }

    public void addProject() throws IOException {

        eProject.setMainBoqXml(null);
        eProject.setOrgBoqXml(null);
        eProjectFacade.create(eProject);       

    }

    public void addSite() throws IOException {

        Query query = em.createNamedQuery("EProject.findByMaxId");
        projectId = (Integer) query.getResultList().get(0);

        eSites.setSiteStockLevelXml(null);
        eProjectNew = eProjectFacade.find(projectId);
        eSites.setEProject(eProjectNew);
        eSitesFacade.create(eSites);
       

    }

    public EProject geteProject() {
        return eProject;
    }

    public void seteProject(EProject eProject) {
        this.eProject = eProject;
    }

    public ESites geteSites() {
        return eSites;
    }

    public void seteSites(ESites eSites) {
        this.eSites = eSites;
    }
    
}
