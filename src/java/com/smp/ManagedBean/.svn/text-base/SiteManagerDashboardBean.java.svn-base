/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EEmployeeHasESites;
import com.smp.EntityBean.EIntent;
import com.smp.EntityBean.EProductHasEIntent;
import com.smp.EntityBean.ESites;
import com.smp.EntityBean.ETransaction;
import com.smp.GenericBean.AddMainBoq;
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
import com.smp.SessionBean.EEmployeeHasESitesFacade;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.smp.GenericBean.ReadXMl;
import com.smp.SessionBean.EEmployeeFacade;
import com.smp.SessionBean.EIntentFacade;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProductHasEIntentFacade;
import com.smp.SessionBean.ETransactionFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author smp
 */
@ManagedBean(name = "sitemanagerdashboardbean")
@SessionScoped
@Stateless
public class SiteManagerDashboardBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    ESitesFacade eSitesFacade;
    @EJB
    EEmployeeHasESitesFacade eEmployeeHasESitesFacade;
    @EJB
    EProductCategoryFacade categoryFacade;
    @EJB
    EProductFacade productFacade;
    @EJB
    EIntentFacade eIntentFacade;
    @EJB
    EEmployeeFacade eEmployeeFacade;
    @EJB
    EProductHasEIntentFacade eProductHasEIntentFacade;
    @EJB
    ETransactionFacade eTransactionFacade;
    ESites eSites;
    public static int siteid;
    public ArrayList siteList = new ArrayList();
    List<ESites> esiteList = new ArrayList<ESites>();
    List<EEmployeeHasESites> listOfSites = new ArrayList<EEmployeeHasESites>();
    List<AddMainBoq> listofProductBySiteXML = new ArrayList<AddMainBoq>();
    List<AddMainBoq> insideList = new ArrayList<AddMainBoq>();
    AddMainBoq addMainBoq = new AddMainBoq();
    ReadXMl readXMl = new ReadXMl();
    int empId;
    public static AddMainBoq forDialogBox;
    public static EIntent eintent;
    List<EIntent> eintentList = new ArrayList<EIntent>();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String date;
    public List<ETransaction> transactionList = new ArrayList<ETransaction>();

    /** Creates a new instance of SiteManagerDashboardBean */
    @PostConstruct
    public void populate() throws IOException {

        listOfSites.clear();
        listOfSites = em.createNamedQuery("EEmployeeHasESites.findByEEmployeeId").setParameter("eEmployeeId", LoginBean.employeeId).getResultList();

        System.out.println("esiteList**********" + listOfSites.size());
        siteList.clear();
        for (int i = 0; i < listOfSites.size(); i++) {
            siteList.add(new SelectItem(listOfSites.get(i).getEEmployeeHasESitesPK().getESitesId(), eSitesFacade.find(listOfSites.get(i).getEEmployeeHasESitesPK().getESitesId()).getName()));
        }

    }

    public void populateIntent() throws IOException {
        System.out.println("inside populateintent+++++++++ " + siteid);
        if (!eSitesFacade.find(siteid).getSiteStockLevelXml().equals("null")) {
            insideList = readXMl.readSiteStockXml(eSitesFacade.find(siteid).getSiteStockLevelXml());
            System.out.println("Inside List**********" + insideList.size());
            for (int j = 0; j < insideList.size(); j++) {
                transactionList = eTransactionFacade.findAll();
                long currentStock = 0;
                for (int k = 0; k < transactionList.size(); k++) {
                    if ((transactionList.get(k).getEProduct().getId() == insideList.get(j).getProductId()) && (transactionList.get(k).getESites().getId() == siteid)) {
                        currentStock = currentStock + transactionList.get(k).getStockDelta();
                    }
                }


                System.out.println("Condition-->" + (currentStock < insideList.get(j).getShortage()));
                System.out.println("product Category id====> " + insideList.get(j).getProductCategoryId());
                System.out.println("product  id====> " + insideList.get(j).getProductId());
                System.out.println("Condition-->" + (currentStock < insideList.get(j).getShortage()));
                if (currentStock <= insideList.get(j).getShortage()) {
                    addMainBoq = new AddMainBoq();
                    addMainBoq.setProductCategoryName(categoryFacade.find(insideList.get(j).getProductCategoryId()).getName());
                    addMainBoq.setProductName(productFacade.find(insideList.get(j).getProductId()).getName());
                    addMainBoq.setCurrentStock(currentStock);
                    addMainBoq.setShortage(insideList.get(j).getShortage());
                    addMainBoq.setSurplus(insideList.get(j).getSurplus());
                    listofProductBySiteXML.add(addMainBoq);
                }
            }


            System.out.println("list of List**********" + listofProductBySiteXML.size());
        }

    }

    public boolean setColor(AddMainBoq addMainBoq) {
        System.out.println("inside setcolor...............");
        if (addMainBoq.getCurrentStock() <= addMainBoq.getShortage()) {
            return true;
        } else {
            return false;
        }


    }

    public void dialogBoxValue(AddMainBoq addMainBoq) {
        System.out.println("hi--->" + addMainBoq.getProductCategoryName());

        forDialogBox = addMainBoq;
        System.out.println("forDialog--->" + forDialogBox.getProductName());
        eintent = new EIntent();
        date = dateFormat.format(new Date());

    }

    public void generateIntent() {
        System.out.println("Site Id-->" + siteid);
        System.out.println("hello--->" + eSitesFacade.find(siteid));
        eintent.setESites(eSitesFacade.find(forDialogBox.getSiteId()));
        eintent.setStatus("Pending");
        eintent.setProductCode(forDialogBox.getProductCategoryId() + "." + forDialogBox.getProductId());
        eintent.setEEmployee(eEmployeeFacade.find(LoginBean.employeeId));
        eintent.setDate(new Date());
        eIntentFacade.create(eintent);




        Query query = em.createNamedQuery("EIntent.findByMaxEmpId");
        int eIntent = (Integer) query.getResultList().get(0);

        //EProductHasEIntentPK eProductHasEIntentPK = new EProductHasEIntentPK(eIntent,forDialogBox.getProductId() );
        EProductHasEIntent eProductHasEIntent = new EProductHasEIntent(forDialogBox.getProductId(), eIntent);
        eProductHasEIntentFacade.create(eProductHasEIntent);
    }

    public ESites geteSites() {
        return eSites;
    }

    public void seteSites(ESites eSites) {
        this.eSites = eSites;
    }

    public int getSiteid() {
        return siteid;
    }

    public void setSiteid(int siteid) {
        this.siteid = siteid;
    }

    public List<ESites> getEsiteList() {
        return esiteList;
    }

    public void setEsiteList(List<ESites> esiteList) {
        this.esiteList = esiteList;
    }

    public ArrayList getSiteList() {
        return siteList;
    }

    public void setSiteList(ArrayList siteList) {
        this.siteList = siteList;
    }

    public List<EEmployeeHasESites> getListOfSites() {
        return listOfSites;
    }

    public void setListOfSites(List<EEmployeeHasESites> listOfSites) {
        this.listOfSites = listOfSites;
    }

    public List<AddMainBoq> getListofProductBySiteXML() {
        return listofProductBySiteXML;
    }

    public void setListofProductBySiteXML(List<AddMainBoq> listofProductBySiteXML) {
        this.listofProductBySiteXML = listofProductBySiteXML;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public AddMainBoq getForDialogBox() {
        return forDialogBox;
    }

    public void setForDialogBox(AddMainBoq forDialogBox) {
        this.forDialogBox = forDialogBox;
    }

    public EIntent getEintent() {
        return eintent;
    }

    public void setEintent(EIntent eintent) {
        this.eintent = eintent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
