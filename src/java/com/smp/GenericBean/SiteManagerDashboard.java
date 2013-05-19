/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.GenericBean;

/**
 *
 * @author smp
 */
public class SiteManagerDashboard {

    int productCategoryId;
    int productId;
    double quantity;
    String prodName;

    public SiteManagerDashboard() {
    }

    public SiteManagerDashboard(String i, int j, int k) {
        prodName = i;
        productId = j;
        quantity = k;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
