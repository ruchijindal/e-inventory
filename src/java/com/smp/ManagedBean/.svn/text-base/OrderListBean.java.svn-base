/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EEmployeeHasESites;
import com.smp.EntityBean.EIntent;
import com.smp.EntityBean.EOrder;
import com.smp.EntityBean.EProductHasEIntent;
import com.smp.EntityBean.EProject;
import com.smp.EntityBean.EQuotation;
import com.smp.EntityBean.ESites;
import com.smp.EntityBean.ETransaction;
import com.smp.EntityBean.EVendorsHasEProduct;
import com.smp.GenericBean.AddMainBoq;
import com.smp.GenericBean.OrderListClass;
import com.smp.GenericBean.Quotation;
import com.smp.GenericBean.ReadXMl;
import com.smp.SessionBean.EEmployeeFacade;
import com.smp.SessionBean.EIntentFacade;
import com.smp.SessionBean.EOrderFacade;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProjectFacade;
import com.smp.SessionBean.EQuotationFacade;
import com.smp.SessionBean.ESitesFacade;
import com.smp.SessionBean.ETransactionFacade;
import com.smp.SessionBean.EVendorsFacade;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smp
 */
@ManagedBean(name = "orderListBean")
@SessionScoped
@Stateless
public class OrderListBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    ESitesFacade eSitesFacade;
    @EJB
    EIntentFacade eIntentFacade;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EEmployeeFacade eEmployeeFacade;
    @EJB
    ETransactionFacade eTransactionFacade;
    @EJB
    EOrderFacade eOrderFacade;
    @EJB
    EVendorsFacade eVendorsFacade;
    @EJB
    EQuotationFacade eQuotationFacade;
    List<ESites> esiteList = new ArrayList<ESites>();
    ArrayList projectList = new ArrayList();
    public static List<ESites> siteList;
    static int projectId;
    List<EEmployeeHasESites> listOfSites = new ArrayList<EEmployeeHasESites>();
    List<EProject> parentList = new ArrayList<EProject>();
    List<EProductHasEIntent> intentproductList = new ArrayList<EProductHasEIntent>();
    List<EIntent> intentList = new ArrayList<EIntent>();
    boolean renderOrder;
    int intentCount = 0;
    public static List<OrderListClass> orderList = new ArrayList<OrderListClass>();
    OrderListClass orderListClass = new OrderListClass();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static String siteName;
    int fromsiteId;
    static int siteId;
    public static ArrayList projectsiteList = new ArrayList();
    ReadXMl readXml = new ReadXMl();
    private List<AddMainBoq> mainBoqList = new ArrayList<AddMainBoq>();
    static OrderListClass requestOrder = new OrderListClass();
    static List<AddMainBoq> chartList = new ArrayList<AddMainBoq>();
    static AddMainBoq chartClass = new AddMainBoq();
    static List<ESites> selectSite = new ArrayList<ESites>();
    public List<ETransaction> transactionList = new ArrayList<ETransaction>();
    Date orderDate;
    EOrder eOrder = new EOrder();
    List<EOrder> orderIntentList = new ArrayList<EOrder>();
    List<ETransaction> transactionIntentLIst = new ArrayList<ETransaction>();
    static int orderType = 0;
    public static int intentId;
    List<EVendorsHasEProduct> vendorProductList = new ArrayList<EVendorsHasEProduct>();
    Quotation quotation = new Quotation();
    public static  Quotation requestQuotation = new Quotation();
    public static List<Quotation> quotationList = new ArrayList<Quotation>();

    @PostConstruct
    public void populate() {

        listOfSites = em.createNamedQuery("EEmployeeHasESites.findByEEmployeeId").setParameter("eEmployeeId", LoginBean.employeeId).getResultList();

        for (int i = 0; i < listOfSites.size(); i++) {
            int sid = listOfSites.get(i).getEEmployeeHasESitesPK().getESitesId();
            boolean flag = false;
            for (int j = 0; j < parentList.size(); j++) {
                if (parentList.get(j).equals(eSitesFacade.find(sid).getEProject())) {
                    flag = true;
                    break;

                }
            }

            if (flag == false) {
                parentList.add(eSitesFacade.find(sid).getEProject());
            }


        }
        for (int i = 0; i < parentList.size(); i++) {
            projectList.add(new SelectItem(parentList.get(i).getId(), parentList.get(i).getName()));
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

    public boolean checkIntent(ESites eSites) {

        intentCount = 0;
        renderOrder = false;
        for (int i = 0; i < eIntentFacade.findAll().size(); i++) {

            if (eSites.getId() == eIntentFacade.findAll().get(i).getESites().getId()) {
                if (eIntentFacade.findAll().get(i).getStatus().equals("Pending")) {
                    //System.out.println("inside if");
                    renderOrder = true;
                    intentCount++;


                }
            }

        }
        return true;


    }

    public void reDirect(ESites eSites) throws IOException {

        System.out.println("inside redirect");

        siteId = eSites.getId();
        siteName = eSitesFacade.find(eSites.getId()).getName();
        orderList.clear();
        intentList.clear();
        for (int i = 0; i < eIntentFacade.findAll().size(); i++) {
            if (eIntentFacade.findAll().get(i).getESites().getId() == eSites.getId()) {
                if (eIntentFacade.findAll().get(i).getStatus().equals("Pending")) {
                    intentList.add(eIntentFacade.findAll().get(i));
                }
            }
        }
        for (int i = 0; i < intentList.size(); i++) {
            int prodId, prodCatId;
            String prodCode = intentList.get(i).getProductCode();
            System.out.println("prodcat++++++" + prodCode.substring(0, prodCode.indexOf(".")));
            prodCatId = Integer.parseInt(prodCode.substring(0, prodCode.indexOf(".")));
            prodId = Integer.parseInt(prodCode.substring(prodCode.indexOf(".") + 1));

            orderListClass = new OrderListClass();
            orderListClass.setIntentId(intentList.get(i).getId());
            orderListClass.setProductId(prodId);
            orderListClass.setProductName(eProductFacade.find(orderListClass.getProductId()).getName());
            orderListClass.setProductCategoryId(prodCatId);
            orderListClass.setProductCategoryName(eProductCategoryFacade.find(prodCatId).getName());
            orderListClass.setEmployeeId(intentList.get(i).getEEmployee().getId());
            orderListClass.setEmployeeName(eEmployeeFacade.find(orderListClass.getEmployeeId()).getEmpName());
            orderListClass.setIntentDate(dateFormat.format(intentList.get(i).getDate()));

            orderIntentList.clear();
            orderIntentList = em.createNamedQuery("EOrder.findByIntent").setParameter("eIntent", eIntentFacade.find(intentList.get(i).getId())).getResultList();

            System.out.println("size of orderIntentList===> " + orderIntentList.size());
            transactionIntentLIst.clear();
            long receivedStock = 0;
            for (int j = 0; j < orderIntentList.size(); j++) {
                transactionIntentLIst = em.createNamedQuery("ETransaction.findByOrder&Site").setParameter("eOrder", orderIntentList.get(j)).setParameter("eSites", eSitesFacade.find(siteId)).getResultList();
                if(!transactionIntentLIst.isEmpty())
                receivedStock = receivedStock + transactionIntentLIst.get(0).getStockDelta();
            }



            System.out.println("receivedstock " + receivedStock);
            orderListClass.setIntentQuantity(intentList.get(i).getProdQuantity() - receivedStock);


            orderListClass.setProductCode(intentList.get(i).getProductCode());
            orderList.add(orderListClass);
        }
        System.out.println("size of orderlist==>" + orderList.size());


        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Order/addOrder.xhtml");
    }

    public void setOrderType(int id) throws IOException {
        System.out.println("inside setOrderType.............." + intentId);
        orderType = id;
        esiteList.clear();
        esiteList = eSitesFacade.findAll();
        projectsiteList.clear();
        for (int i = 0; i < esiteList.size(); i++) {

            if (esiteList.get(i).getEProject().getId() == projectId) {
                //System.out.println("for siteId==>"+esiteList.get(i).getEProject().getId()+" "+siteId);
                if (esiteList.get(i).getId() != siteId) {
                    projectsiteList.add(new SelectItem(esiteList.get(i).getId(), esiteList.get(i).getName()));

                }

            }
        }
        if (id == 2) {
            purchaseOrder();
        }

    }

    public void chart(OrderListClass olc) throws IOException {

        requestOrder = olc;
        esiteList.clear();
        eOrder = new EOrder();
        esiteList = eSitesFacade.findAll();
        intentId = olc.getIntentId();

        selectSite.clear();
        System.out.println("intentId==>" + intentId);
        for (int i = 0; i < esiteList.size(); i++) {

            if (esiteList.get(i).getEProject().getId() == projectId) {

                selectSite.add(esiteList.get(i));
            }
        }
        System.out.println("inside chart........." + requestOrder.getProductCode());
        int prodId, prodCatId;
        String prodCode = requestOrder.getProductCode();
        System.out.println("prodcat++++++" + prodCode.substring(0, prodCode.indexOf(".")));
        prodCatId = Integer.parseInt(prodCode.substring(0, prodCode.indexOf(".")));
        prodId = Integer.parseInt(prodCode.substring(prodCode.indexOf(".") + 1));
        System.out.println("prodcat++++++" + prodCatId);
        System.out.println("prod++++++" + prodId);
        transactionList.clear();
        transactionList = eTransactionFacade.findAll();
        long currentStock = 0;
        long shortage1 = 0;
        long surplus1 = 0;

        chartList.clear();
        for (int i = 0; i < selectSite.size(); i++) {
            System.out.println("inside ........." + selectSite.get(i).getId());
            String XMl = eSitesFacade.find(selectSite.get(i).getId()).getSiteStockLevelXml();
            mainBoqList = readXml.readSiteStockXml(XMl);
            for (int j = 0; j < mainBoqList.size(); j++) {
                if ((mainBoqList.get(j).getProductId() == prodId) && (mainBoqList.get(j).getProductCategoryId() == prodCatId)) {
                    currentStock = 0;
                    shortage1 = 0;
                    surplus1 = 0;

                    for (int k = 0; k < transactionList.size(); k++) {
                        if ((transactionList.get(k).getEProduct().getId() == requestOrder.getProductId()) && (transactionList.get(k).getESites().getId() == selectSite.get(i).getId())) {
                            currentStock = currentStock + transactionList.get(k).getStockDelta();
                        }
                    }
                    shortage1 = (long) mainBoqList.get(j).getShortage();
                    surplus1 = (long) mainBoqList.get(j).getSurplus();
                    System.out.println("current Stock+++ " + currentStock);

                    if (currentStock >= shortage1 && currentStock <= surplus1) {
                        chartClass = new AddMainBoq();
                        chartClass.setSiteName(selectSite.get(i).getName());
                        chartClass.setShortageLevel(shortage1);
                        chartClass.setSurplusLevel(0);
                        chartClass.setCurrentStockLevel(currentStock - shortage1);
                        chartList.add(chartClass);
                    }
                    if (currentStock >= shortage1 && currentStock >= surplus1) {
                        chartClass = new AddMainBoq();
                        chartClass.setSiteName(selectSite.get(i).getName());
                        chartClass.setShortageLevel(shortage1);
                        chartClass.setSurplusLevel((currentStock - surplus1));
                        chartClass.setCurrentStockLevel(currentStock - (shortage1 + currentStock - surplus1));
                        chartList.add(chartClass);

                    }
                    if (currentStock <= shortage1 && currentStock <= surplus1) {
                        chartClass = new AddMainBoq();
                        chartClass.setSiteName(selectSite.get(i).getName());
                        chartClass.setShortageLevel(currentStock);
                        chartClass.setSurplusLevel(0);
                        chartClass.setCurrentStockLevel(0);
                        chartList.add(chartClass);

                    }

                }

            }


        }
        System.out.println("size of cahrtlist........." + chartList.size());

    }

    public void addOrder() throws IOException {
        System.out.println("addorder++++++++ " + orderType + intentId);
        if (orderType == 1) {
            eOrder.setOrderType("Transfer");
        }
        eOrder.setFromSite(fromsiteId);
        eOrder.setToSite(siteId);
        eOrder.setEIntent(eIntentFacade.find(requestOrder.getIntentId()));
        eOrder.setEEmployee(eEmployeeFacade.find(requestOrder.getEmployeeId()));
        eOrder.setStatus("Pending");
        eOrderFacade.create(eOrder);
        setTransaction();


    }

    public void setTransaction() throws IOException {
        Query query = em.createNamedQuery("EOrder.findByMaxId");
        int eOrderId = (Integer) query.getResultList().get(0);
        long currentStock = 0;


        EOrder eOrderNew = eOrderFacade.find(eOrderId);
        for (int k = 0; k < transactionList.size(); k++) {
            if ((transactionList.get(k).getEProduct().getId() == requestOrder.getProductId()) && (transactionList.get(k).getESites().getId() == siteId)) {
                currentStock = currentStock + transactionList.get(k).getStockDelta();
            }
        }
        long updateStock = currentStock + eOrderNew.getProdQuantity();
        long delta = updateStock - currentStock;

        ETransaction eTransaction = new ETransaction();
        eTransaction.setReasonOfUpdate(eOrderNew.getOrderType());
        eTransaction.setDateOfUpdate(eOrderNew.getDate());
        eTransaction.setESites(eSitesFacade.find(siteId));
        eTransaction.setEProduct(eProductFacade.find(requestOrder.getProductId()));
        eTransaction.setStockDelta(delta);
        eTransaction.setEOrder(eOrderNew);
        eTransactionFacade.create(eTransaction);
        updateStock = 0;
        delta = 0;

        for (int k = 0; k < transactionList.size(); k++) {
            if ((transactionList.get(k).getEProduct().getId() == requestOrder.getProductId()) && (transactionList.get(k).getESites().getId() == eOrderNew.getFromSite())) {
                currentStock = currentStock + transactionList.get(k).getStockDelta();
            }
        }

        updateStock = currentStock - eOrderNew.getProdQuantity();
        delta = updateStock - currentStock;

        eTransaction = new ETransaction();
        eTransaction.setReasonOfUpdate(eOrderNew.getOrderType());
        eTransaction.setDateOfUpdate(eOrderNew.getDate());
        eTransaction.setESites(eSitesFacade.find(eOrderNew.getFromSite()));
        eTransaction.setEProduct(eProductFacade.find(requestOrder.getProductId()));
        eTransaction.setStockDelta(delta);
        eTransaction.setEOrder(eOrderNew);
        eTransactionFacade.create(eTransaction);

        orderIntentList.clear();
        orderIntentList = em.createNamedQuery("EOrder.findByIntent").setParameter("eIntent", eIntentFacade.find(requestOrder.getIntentId())).getResultList();

        System.out.println("size of orderIntentList===> " + orderIntentList.size());
        transactionIntentLIst.clear();
        long receivedStock = 0;
        for (int j = 0; j < orderIntentList.size(); j++) {
            transactionIntentLIst = em.createNamedQuery("ETransaction.findByOrder&Site").setParameter("eOrder", orderIntentList.get(j)).setParameter("eSites", eSitesFacade.find(siteId)).getResultList();
            receivedStock = receivedStock + transactionIntentLIst.get(0).getStockDelta();
        }

        if (receivedStock == eIntentFacade.find(requestOrder.getIntentId()).getProdQuantity()) {
            EIntent updateIntent = eOrderNew.getEIntent();
            updateIntent.setStatus("Cleared");
            eIntentFacade.edit(updateIntent);
        }

        reDirect(eSitesFacade.find(siteId));


    }

    public void purchaseOrder() throws IOException {

        int prodId, prodCatId;
        String prodCode = requestOrder.getProductCode();
        System.out.println("prodcat++++++" + prodCode.substring(0, prodCode.indexOf(".")));
        prodCatId = Integer.parseInt(prodCode.substring(0, prodCode.indexOf(".")));
        prodId = Integer.parseInt(prodCode.substring(prodCode.indexOf(".") + 1));
        vendorProductList = em.createNamedQuery("EVendorsHasEProduct.findByEProductId").setParameter("eProductId", prodId).getResultList();
        quotationList.clear();
        for (int i = 0; i < vendorProductList.size(); i++) {
            List<EQuotation> qlist = em.createNamedQuery("EQuotation.findByVendor").setParameter("eVendors", eVendorsFacade.find(vendorProductList.get(i).getEVendorsHasEProductPK().getEVendorsId())).getResultList();
            for (int j = 0; j < qlist.size(); j++) {
                Date intentDate = eIntentFacade.find(requestOrder.getIntentId()).getDate();
                if (qlist.get(j).getReceivedDate().equals(intentDate) || qlist.get(j).getReceivedDate().after(intentDate)) {
                    quotation = new Quotation();
                    quotation.setQuotationId(qlist.get(j).getId());
                    quotation.setIntentId(requestOrder.getIntentId());
                    quotation.setProductCategoryName(requestOrder.getProductCategoryName());
                    quotation.setProductName(requestOrder.getProductName());
                    quotation.setUnitPrice(qlist.get(j).getUnitPrice());
                    quotation.setDeliveryQuantity(qlist.get(j).getDeliverQuantity());
                    quotation.setDeliveryDate(dateFormat.format(qlist.get(j).getDateOfDelivery()));
                    quotation.setqReceivedDate(dateFormat.format(qlist.get(j).getReceivedDate()));
                    quotation.setVendorId(qlist.get(j).getEVendors().getId());
                    quotation.setVendorName(eVendorsFacade.find(quotation.getVendorId()).getVenName());
                    quotationList.add(quotation);


                }

            }


        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Order/purchaseOrder.xhtml");




    }

    public void forPurchaseOrder(Quotation quotation)
    {
        requestQuotation=quotation;
    }

    public void addPurcahseOrder()
    {
      if (orderType == 2) {
            eOrder.setOrderType("Purchase");
        }

        eOrder.setEIntent(eIntentFacade.find(requestOrder.getIntentId()));
        eOrder.setEEmployee(eEmployeeFacade.find(requestOrder.getEmployeeId()));
        eOrder.setStatus("QC_Waiting");
        eOrder.setSelectQuotationId(requestQuotation.getQuotationId());
        eOrderFacade.create(eOrder);
        FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Congrates! Order Successfully Sent for QC Report"));

    }

    public EOrder geteOrder() {
        return eOrder;
    }

    public void seteOrder(EOrder eOrder) {
        this.eOrder = eOrder;
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

    public int getIntentCount() {
        return intentCount;
    }

    public void setIntentCount(int intentCount) {
        this.intentCount = intentCount;
    }

    public boolean isRenderOrder() {
        return renderOrder;
    }

    public void setRenderOrder(boolean renderOrder) {
        this.renderOrder = renderOrder;
    }

    public List<OrderListClass> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListClass> orderList) {
        this.orderList = orderList;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public int getFromsiteId() {
        return fromsiteId;
    }

    public void setFromsiteId(int fromsiteId) {
        System.out.println("inside set+++++" + fromsiteId);
        this.fromsiteId = fromsiteId;
    }

    public ArrayList getProjectsiteList() {
        return projectsiteList;
    }

    public void setProjectsiteList(ArrayList projectsiteList) {
        this.projectsiteList = projectsiteList;
    }

    public List<AddMainBoq> getChartList() {
        return chartList;
    }

    public void setChartList(List<AddMainBoq> chartList) {
        this.chartList = chartList;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getIntentId() {
        return intentId;
    }

    public void setIntentId(int intentId) {
        this.intentId = intentId;
    }

    public List<Quotation> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }
}
