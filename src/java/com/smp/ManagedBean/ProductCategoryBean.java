/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProduct;
import com.smp.EntityBean.EProductCategory;
import com.smp.EntityBean.EProductCategoryHasEProduct;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductCategoryHasEProductFacade;
import com.smp.SessionBean.EProductFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "productCategoryBean")
@RequestScoped
public class ProductCategoryBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProductCategoryHasEProductFacade eProductCategoryHasEProductFacade;
    @EJB
    EProductFacade eProductFacade;
    EProductCategory eProductCategory;
    List<EProductCategory> productcategoryList;
    public static EProductCategory delEProductcategory = new EProductCategory();
    List<EProductCategoryHasEProduct> eProductCategoryHasEProductsList = new ArrayList<EProductCategoryHasEProduct>();
    String productDetails = new String("no");
    boolean renderRate = true;
    public static int Id;

    /** Creates a new instance of ProductCategoryBean */
    public ProductCategoryBean() {
        eProductCategory = new EProductCategory();
        productcategoryList = new ArrayList<EProductCategory>();
    }

    @PostConstruct
    public void populate() {
        eProductCategory = new EProductCategory();
        productcategoryList.clear();
        productcategoryList = eProductCategoryFacade.findAll();
    }

    public void addProductCategory() throws IOException {


        eProductCategoryFacade.create(eProductCategory);
        eProductCategory = new EProductCategory();

        Query query = em.createNamedQuery("EProductCategory.findByMxId");
        Object maxProductId = query.getResultList().get(0);
        Id = (Integer) maxProductId;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Product Category Successfully Added"));
        populate();

        if (productDetails.equals("yes")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Product/addProduct.xhtml");
        } else {
            eProductCategory = new EProductCategory();
        }

    }

    public void editProductCategory(RowEditEvent event) {
        eProductCategoryFacade.edit((EProductCategory) event.getObject());
        productcategoryList = eProductCategoryFacade.findAll();
    }

    public void confirmDelete(EProductCategory eProductCategory1) {
        System.out.println("inside  confirm delete productcategory...");
        delEProductcategory = eProductCategory1;


    }

    public void deleteProductCategory() throws IOException {
        System.out.println("delete ......");

        eProductCategoryHasEProductsList.clear();
        eProductCategoryHasEProductsList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", delEProductcategory.getId()).getResultList();

        System.out.println("size of eProductCategoryHasEProductsList*******" + delEProductcategory.getId());


        for (int j = eProductCategoryHasEProductsList.size() - 1; j >= 0; j--) {

            System.out.println("inside for****** " + j + " " + eProductCategoryHasEProductsList.get(j));

             EProduct eProduct=eProductFacade.find(eProductCategoryHasEProductsList.get(j).getEProductCategoryHasEProductPK().getEProductId());
             eProductFacade.remove(eProduct);
             eProductCategoryHasEProductFacade.remove(eProductCategoryHasEProductsList.get(j));
           


        }
        eProductCategoryFacade.remove(delEProductcategory);
        productcategoryList = eProductCategoryFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Product/addProductCategory.xhtml");
    }

    public void addProduct() {
        if (productDetails.equals("yes")) {
            renderRate = false;
        } else if (productDetails.equals("no")) {
            renderRate = true;
        }
    }

    public void Detail(EProductCategory eProductCategory) throws IOException {
        System.out.println("Details***********");
        Id = eProductCategory.getId();
        System.out.println("Category Id********" + Id);
        populate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Product/addProduct.xhtml");
    }

    public EProductCategory geteProductCategory() {
        return eProductCategory;
    }

    public void seteProductCategory(EProductCategory eProductCategory) {
        this.eProductCategory = eProductCategory;
    }

    public List<EProductCategory> getProductcategoryList() {
        return productcategoryList;
    }

    public void setProductcategoryList(List<EProductCategory> productcategoryList) {
        this.productcategoryList = productcategoryList;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
