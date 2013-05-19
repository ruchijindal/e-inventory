/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.ManagedBean;

/**
 *
 * @author smp
 */
public class OrderList {
      public int Item;
    public String Description;
    public String VendorProduct;
    public int quantity;
    public float price;
    public float discount;
    public int subtotal;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getItem() {
        return Item;
    }

    public void setItem(int Item) {
        this.Item = Item;
    }

    public String getVendorProduct() {
        return VendorProduct;
    }

    public void setVendorProduct(String VendorProduct) {
        this.VendorProduct = VendorProduct;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

   

}
