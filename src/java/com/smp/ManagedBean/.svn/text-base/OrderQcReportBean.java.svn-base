/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EEmployee;
import com.smp.EntityBean.EOrder;
import com.smp.EntityBean.EOrderQc;
import com.smp.EntityBean.EQuotation;
import com.smp.EntityBean.ETransaction;
import com.smp.GenericBean.OrderListClass;
import com.smp.SessionBean.EDesignationFacade;
import com.smp.SessionBean.EEmployeeFacade;
import com.smp.SessionBean.EIntentFacade;
import com.smp.SessionBean.EOrderFacade;
import com.smp.SessionBean.EOrderQcFacade;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EQuotationFacade;
import com.smp.SessionBean.ESitesFacade;
import com.smp.SessionBean.ETransactionFacade;
import com.smp.SessionBean.EVendorsFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;

/**
 *
 * @author smp
 */
@ManagedBean(name = "orderQcReportBean")
@RequestScoped
public class OrderQcReportBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EOrderFacade eOrderFacade;
    @EJB
    ESitesFacade eSitesFacade;
    @EJB
    EIntentFacade eIntentFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EVendorsFacade eVendorsFacade;
    @EJB
    EQuotationFacade eQuotationFacade;
    @EJB
    EEmployeeFacade eEmployeeFacade;
    @EJB
    EDesignationFacade eDesignationFacade;
    @EJB
    EOrderQcFacade eOrderQcFacade;
    @EJB
    ETransactionFacade eTransactionFacade;
    List<EOrder> orderList = new ArrayList<EOrder>();
    OrderListClass orderListClass = new OrderListClass();
    public static OrderListClass requestorder = new OrderListClass();
    List<OrderListClass> list = new ArrayList<OrderListClass>();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    EOrderQc eOrderQc = new EOrderQc();
    List<EEmployee> employeList = new ArrayList<EEmployee>();
    public static ArrayList selectEmployeeList = new ArrayList();
    int employeeId;
    public List<ETransaction> transactionList = new ArrayList<ETransaction>();
    boolean renderRow;

    @PostConstruct
    public void populate() {
        orderList.clear();
        list.clear();
        employeeId = 0;
        orderList = em.createNamedQuery("EOrder.findByType&Status").setParameter("orderType", "Purchase").setParameter("status", "QC_Waiting").getResultList();
        for (int i = 0; i < orderList.size(); i++) {
            String prodCode = orderList.get(i).getEIntent().getProductCode();
            int prodCatId = Integer.parseInt(prodCode.substring(0, prodCode.indexOf(".")));
            int prodId = Integer.parseInt(prodCode.substring(prodCode.indexOf(".") + 1));
            orderListClass = new OrderListClass();
            orderListClass.setSiteId(orderList.get(i).getEIntent().getESites().getId());
            orderListClass.setSiteName(eSitesFacade.find(orderListClass.getSiteId()).getName());
            orderListClass.setProductCategoryId(prodCatId);
            orderListClass.setProductCategoryName(eProductCategoryFacade.find(prodCatId).getName());
            orderListClass.setProductId(prodId);
            orderListClass.setProductName(eProductFacade.find(prodId).getName());
            orderListClass.setOrderId(orderList.get(i).getId());
            orderListClass.setOrderDate(dateFormat.format(orderList.get(i).getDate()));
            orderListClass.setOrderQuantity(orderList.get(i).getProdQuantity());
            orderListClass.setQuotationId(orderList.get(i).getSelectQuotationId());
            orderListClass.setUnitPrice(eQuotationFacade.find(orderListClass.getQuotationId()).getUnitPrice());
            orderListClass.setVendorId(eQuotationFacade.find(orderListClass.getQuotationId()).getEVendors().getId());
            orderListClass.setVendorName(eVendorsFacade.find(orderListClass.getVendorId()).getVenName());
            list.add(orderListClass);

        }
    }

    public void forDialog(OrderListClass orderListClass) {
        requestorder = orderListClass;
        employeList.clear();
        selectEmployeeList.clear();
        employeList = eEmployeeFacade.findAll();
        for (int i = 0; i < employeList.size(); i++) {
            if (employeList.get(i).getEDesignation().getDesignation().equals("Quality Manager")) {
                selectEmployeeList.add(new SelectItem(employeList.get(i).getId(), employeList.get(i).getEmpName()));
            }
        }

    }

    public void addOrderQc() throws IOException {
        eOrderQc.setEEmployee(eEmployeeFacade.find(employeeId));
        eOrderQc.setEOrder(eOrderFacade.find(requestorder.getOrderId()));
        eOrderQcFacade.create(eOrderQc);
        eOrderQc = new EOrderQc();
        setTransaction();

    }

    public void setTransaction() throws IOException {
        Query query = em.createNamedQuery("EOrderQc.findByMaxId");
        int eOrderId = (Integer) query.getResultList().get(0);
        long currentStock = 0;

        System.out.println("orderQcId++++++++ " + eOrderId);
        EOrderQc eOrderNew = eOrderQcFacade.find(eOrderId);
        transactionList = eTransactionFacade.findAll();
        for (int k = 0; k < transactionList.size(); k++) {
            if ((transactionList.get(k).getEProduct().getId() == requestorder.getProductId()) && (transactionList.get(k).getESites().getId() == requestorder.getSiteId())) {
                currentStock = currentStock + transactionList.get(k).getStockDelta();
            }
        }
        long updateStock = currentStock + eOrderNew.getQcQuantityAccepted();
        long delta = updateStock - currentStock;

        ETransaction eTransaction = new ETransaction();
        eTransaction.setReasonOfUpdate(eOrderNew.getEOrder().getOrderType());
        eTransaction.setDateOfUpdate(eOrderNew.getDate());
        eTransaction.setESites(eSitesFacade.find(requestorder.getSiteId()));
        eTransaction.setEProduct(eProductFacade.find(requestorder.getProductId()));
        eTransaction.setStockDelta(delta);
        eTransaction.setEOrder(eOrderNew.getEOrder());
        eTransactionFacade.create(eTransaction);

        EOrder eOrder = new EOrder();
        eOrder = eOrderNew.getEOrder();
        eOrder.setStatus("QC_Respond");
        eOrderFacade.edit(eOrder);
       // populate();

    }

    public boolean setColor(OrderListClass order) {
        System.out.println("inside setcolor...............");
        List<EOrderQc> qlist = em.createNamedQuery("EOrderQc.findByOrder").setParameter("eOrder", eOrderFacade.find(order.getOrderId())).getResultList();
        renderRow = false;
        if (!qlist.isEmpty()) {
            renderRow = true;
        }

        return renderRow;


    }

    public List<OrderListClass> getList() {
        return list;
    }

    public void setList(List<OrderListClass> list) {
        this.list = list;
    }

    public EOrderQc geteOrderQc() {
        return eOrderQc;
    }

    public void seteOrderQc(EOrderQc eOrderQc) {
        this.eOrderQc = eOrderQc;
    }

    public ArrayList getSelectEmployeeList() {
        return selectEmployeeList;
    }

    public void setSelectEmployeeList(ArrayList selectEmployeeList) {
        this.selectEmployeeList = selectEmployeeList;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isRenderRow() {
        return renderRow;
    }

    public void setRenderRow(boolean renderRow) {
        this.renderRow = renderRow;
    }
}
