/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DualListModel;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



/**
 *
 * @author smp
 */
@ManagedBean
@RequestScoped
public class PickListBean {

    /** Creates a new instance of PickListBean */
    static private DualListModel<String> cities;
    boolean render;
    static List<String> citiesSource = new ArrayList<String>();
    static List<String> citiesTarget = new ArrayList<String>();
    public List<OrderInfo> inventoryList = new ArrayList<OrderInfo>();
    public OrderInfo orderInfo = new OrderInfo();
    long total = 0;
    public boolean status;
    String siteLocation="";

    public PickListBean() {
        citiesSource.clear();
        citiesTarget.clear();
        citiesSource.add("Bricks");
        citiesSource.add("Steel");
        citiesSource.add("Timber");
        citiesSource.add("Wood");
        citiesSource.add("Cement");
        citiesSource.add("Stone Dust");
        citiesSource.add("Red Bajri");
        citiesSource.add("Sand");
         
        cities = new DualListModel<String>(citiesSource, citiesTarget);


        orderInfo.setName("Bricks");
        orderInfo.setPrice(500);
        orderInfo.setQuantity(4);
        orderInfo.setStatus("Out-Limit");
        inventoryList.add(orderInfo);
        orderInfo = new OrderInfo();
        orderInfo.setName("Sand");
        orderInfo.setPrice(600);
        orderInfo.setQuantity(100);
        orderInfo.setStatus("In-Limit");
        inventoryList.add(orderInfo);
        orderInfo = new OrderInfo();
        orderInfo.setName("Cement");
        orderInfo.setPrice(800);
        orderInfo.setQuantity(7);
        orderInfo.setStatus("In-Limit");
        inventoryList.add(orderInfo);
        total = 0;
        for (int i = 0; i < inventoryList.size(); i++) {
            total = (long) total + (inventoryList.get(i).getPrice() * inventoryList.get(i).getQuantity());
        }

        if (total >= 50000) {
            status = true;
        }


    }

    public void redirectrequest() throws IOException {
        System.out.println("hello");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/FbsFaces/Inventry/inventoryDetail.xhtml");
    }

    public void renderList() {
        System.out.println("+++++++++++++");
        render = true;

    }
    public void showSiteLocation(){
        siteLocation="B-2, Sector 19 Noida";
    }
    public void addInfo(ActionEvent actionEvent) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Ordered Placed Successfully",""));
	}
    public void save(ActionEvent actionEvent) {
        System.out.println("Method is caleed.............");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucessful","Order Placed Successfully"));
	}
     public void save1(ActionEvent actionEvent) {
        System.out.println("Method is caleed.............");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucessful","Verify Successfully"));
	}

     public void save2(ActionEvent actionEvent) {
        System.out.println("Method is caleed.............");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucessful","Local Purchased Successfully"));
	}
    public DualListModel<String> getCities() {
        return cities;
    }

    public void setCities(DualListModel<String> cities) {
        this.cities = cities;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public List<OrderInfo> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<OrderInfo> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(String siteLocation) {
        this.siteLocation = siteLocation;
    }


//
}
