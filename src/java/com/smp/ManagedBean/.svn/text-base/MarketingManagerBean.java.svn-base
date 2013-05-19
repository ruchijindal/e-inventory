/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;

/**
 *
 * @author smp
 */
@ManagedBean(name = "marketingManagerBean")
@RequestScoped
public class MarketingManagerBean {

    OrderInfo orderInfo = new OrderInfo();
    List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
    boolean flag;

    @PostConstruct
    public void populate() {
        orderInfos.clear();
        orderInfo.setOrederNo("101");
        orderInfo.setName("Steel");
        orderInfo.setOrderDate("27-Mar-2011");
        orderInfo.setOrderBy("Nishant");
        orderInfo.setPrice(2500);
        orderInfo.setQuantity(23);
        orderInfo.setStatus("Cleared");
        orderInfos.add(orderInfo);
        orderInfo = new OrderInfo();
        orderInfo.setOrederNo("102");
        orderInfo.setName("Cement");
        orderInfo.setOrderDate("23-May-2011");
        orderInfo.setOrderBy("Arun");
        orderInfo.setPrice(6325);
        orderInfo.setQuantity(86);
        orderInfo.setStatus("Pending");
        orderInfos.add(orderInfo);
        orderInfo = new OrderInfo();
        orderInfo.setOrederNo("103");
        orderInfo.setName("Bricks");
        orderInfo.setOrderDate("28-Mar-2011");
        orderInfo.setOrderBy("Prashant");
        orderInfo.setPrice(3000);
        orderInfo.setQuantity(200);
        orderInfo.setStatus("Cleared");
        orderInfos.add(orderInfo);
    }

    public void clear() {
        flag = !flag;
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
