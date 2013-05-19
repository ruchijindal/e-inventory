/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EOrder;
import com.smp.EntityBean.EProductHasEIntent;
import com.smp.EntityBean.EQuotation;
import com.smp.EntityBean.EVendors;
import com.smp.EntityBean.EVendorsHasEProduct;
import com.smp.GenericBean.Quotation;
import com.smp.SessionBean.EIntentFacade;
import com.smp.SessionBean.EOrderFacade;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProductHasEIntentFacade;
import com.smp.SessionBean.EQuotationFacade;
import com.smp.SessionBean.EVendorsFacade;
import com.smp.SessionBean.EVendorsHasEProductFacade;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "addQuotationBean")
@RequestScoped
public class AddQuotationBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EOrderFacade eOrderFacade;
    @EJB
    EIntentFacade eIntentFacade;
    @EJB
    EProductHasEIntentFacade eProductHasEIntentFacade;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EVendorsHasEProductFacade eVendorsHasEProductFacade;
    @EJB
    EVendorsFacade eVendorsFacade;
    @EJB
    EQuotationFacade eQuotationFacade;
    EOrder eOrder = new EOrder();
    List<EOrder> eorderList;
    List<Quotation> quotationList = new ArrayList<Quotation>();
    Quotation quotation = new Quotation();
    static Quotation requestedQuotation = new Quotation();
    int orderId;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<EVendorsHasEProduct> vendorProductList = new ArrayList<EVendorsHasEProduct>();
    public static List<EVendors> vendorList = new ArrayList<EVendors>();
    EVendors eVendors = new EVendors();
    static EVendors requestvendor = new EVendors();
    static int vendorId;
    EQuotation eQuotation = new EQuotation();
    boolean renderRow = false;

    /** Creates a new instance of AddQuotationBean */
    public AddQuotationBean() {
        eorderList = new ArrayList<EOrder>();
    }

    @PostConstruct
    public void populate() {
        eorderList.clear();
        quotationList.clear();
        eorderList = em.createNamedQuery("EOrder.findByType&Status").setParameter("orderType", "Purchase").setParameter("status", "Pending").getResultList();
        for (int i = 0; i < eorderList.size(); i++) {
            quotation = new Quotation();
            String prodCode = eorderList.get(i).getEIntent().getProductCode();
            int prodCatId = Integer.parseInt(prodCode.substring(0, prodCode.indexOf(".")));
            int prodId = Integer.parseInt(prodCode.substring(prodCode.indexOf(".") + 1));
            quotation.setProductCode(prodCode);
            quotation.setProductCategoryId(prodCatId);
            quotation.setProductCategoryName(eProductCategoryFacade.find(prodCatId).getName());
            quotation.setProductId(prodId);
            quotation.setProductName(eProductFacade.find(prodId).getName());
            quotation.setDateOfOrder(dateFormat.format(eorderList.get(i).getDate()));
            quotation.setOrderQuantity(eorderList.get(i).getProdQuantity());
            quotation.setOrderId(eorderList.get(i).getId());
            quotation.setIntentId(eorderList.get(i).getEIntent().getId());
            quotationList.add(quotation);


        }

    }

    public void reDirect(Quotation quotation) throws IOException {
        requestedQuotation = quotation;
        System.out.println("requestquId==========>" + requestedQuotation.getOrderId());
        vendorList.clear();
        vendorProductList.clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Quotation/addQuotation.xhtml");
        vendorProductList = em.createNamedQuery("EVendorsHasEProduct.findByEProductId").setParameter("eProductId", requestedQuotation.getProductId()).getResultList();
        for (int j = 0; j < vendorProductList.size(); j++) {

            eVendors = new EVendors();
            eVendors = eVendorsFacade.find(vendorProductList.get(j).getEVendorsHasEProductPK().getEVendorsId());
            vendorList.add(eVendors);

        }



    }

    public void forDialog(EVendors eVendors) {
        requestvendor = eVendors;
        System.out.println("requestVendorId==========>" + requestvendor.getId());
    }

    public void addQuotation() throws IOException {
        System.out.println("inside add quotation...............");
        System.out.println("requestquId==========>" + requestedQuotation.getOrderId());
        eQuotation.setEVendors(requestvendor);
        eQuotation.setEOrder(eOrderFacade.find(requestedQuotation.getOrderId()));
        eQuotationFacade.create(eQuotation);
        eQuotation=new EQuotation();
        reDirect(requestedQuotation);
        
    }

    public boolean setColor(EVendors eVendors) {
        System.out.println("inside setcolor...............");
        List<EQuotation> qlist = em.createNamedQuery("EQuotation.findByVendor").setParameter("eVendors", eVendors).getResultList();
        renderRow = false;
        for (int i = 0; i < qlist.size(); i++) {
            Date intentDate = eIntentFacade.find(requestedQuotation.getIntentId()).getDate();
            renderRow = false;
            if (qlist.get(i).getReceivedDate().equals(intentDate) || qlist.get(i).getReceivedDate().after(intentDate)) {
                renderRow = true;
                break;
            }

        }
        return renderRow;


    }

    public List<Quotation> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public List<EVendors> getVendorList() {
        return vendorList;
    }

    public void setVendorList(List<EVendors> vendorList) {
        this.vendorList = vendorList;
    }

    public EQuotation geteQuotation() {
        return eQuotation;
    }

    public void seteQuotation(EQuotation eQuotation) {
        this.eQuotation = eQuotation;
    }

    public boolean isRenderRow() {
        return renderRow;
    }

    public void setRenderRow(boolean renderRow) {
        this.renderRow = renderRow;
    }
    
}
