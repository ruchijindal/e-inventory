/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.ESites;
import com.smp.SessionBean.EProjectFacade;
import com.smp.SessionBean.ESitesFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.context.FacesContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "intentListBean")
@SessionScoped
@Stateless
public class IntentListBean {

    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    ESitesFacade eSitesFacade;
    List<ESites> esiteList = new ArrayList<ESites>();
    ArrayList projectList = new ArrayList();
    public static List<ESites> siteList;
    int projectId;

    @PostConstruct
    public void populate() {

        projectList.clear();
        for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
            projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
        }

    }

    public void populateSite() {
        System.out.println("inside populateSite()");

        esiteList.clear();
        esiteList = eSitesFacade.findAll();
        siteList = new ArrayList<ESites>();
        for (int i = 0; i < esiteList.size(); i++) {
            if (esiteList.get(i).getEProject().getId() == projectId) {
                siteList.add(esiteList.get(i));

            }
        }
        System.out.println("size" + siteList.size());
    }

    public void reDirect(ESites eSites) throws IOException {
        System.out.println("inside redirect");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Intent/addIntent.xhtml?siteId=" + eSites.getId()+"");
    }

    public ArrayList getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList projectList) {
        this.projectList = projectList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<ESites> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<ESites> siteList) {
        this.siteList = siteList;
    }
}
