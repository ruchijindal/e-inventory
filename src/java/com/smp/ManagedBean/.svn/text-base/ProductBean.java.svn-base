/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProduct;
import com.smp.EntityBean.EProductCategory;
import com.smp.EntityBean.EProductCategoryHasEProduct;
import com.smp.EntityBean.EProductCategoryHasEProductPK;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author smp
 */
@ManagedBean(name = "productBean")
@RequestScoped
public class ProductBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProductCategoryHasEProductFacade eProductCategoryHasEProductFacade;
    EProduct eProduct;
    EProductCategory eProductCategory;
    EProductCategory eProductCategory1;
    List<EProduct> productList;
    List<EProductCategoryHasEProduct> eProductCategoryHasEProductsList = new ArrayList<EProductCategoryHasEProduct>();
    private ArrayList productcategoryList = new ArrayList();
    public static EProduct delEProduct = new EProduct();
    int productCategoryid;
    EProductCategoryHasEProduct eProductCategoryHasEProduct = new EProductCategoryHasEProduct();
    EProductCategoryHasEProductPK eProductPK = new EProductCategoryHasEProductPK();
    int eid;
    public int id;
    ProductCategoryBean productCategoryBean = new ProductCategoryBean();
    public String productCategoryName;
   String product;

    /** Creates a new instance of ProductBean */
    public ProductBean() {
        eProduct = new EProduct();
        eProductCategory = new EProductCategory();
        productList = new ArrayList<EProduct>();
    }

    @PostConstruct
    public void populate() {
        productList.clear();
        eProduct = new EProduct();

        id = productCategoryBean.getId();
        System.out.println("inside populate+++++++++ " + id);
        productCategoryName = eProductCategoryFacade.find(id).getName();
        eProductCategoryHasEProductsList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", id).getResultList();
       System.out.println(" eProductCategoryHasEProductsList***********"+eProductCategoryHasEProductsList.size());
        for (int i = 0; i < eProductCategoryHasEProductsList.size(); i++) {
            productList.add(eProductFacade.find(eProductCategoryHasEProductsList.get(i).getEProductCategoryHasEProductPK().getEProductId()));
           
        }
    }

    public void addProduct() {
        eProductFacade.create(eProduct);
        Query query = em.createNamedQuery("EProduct.findByMxId");
        Object maxProductId = query.getResultList().get(0);
        System.out.println("****" + maxProductId);

        eProductCategoryHasEProduct.setEProductCategoryHasEProductPK(eProductPK);
        eProductPK.setEProductCategoryId(id);
        eProductPK.setEProductId((Integer) maxProductId);
        eProductCategoryHasEProductFacade.create(eProductCategoryHasEProduct);

        eProduct = new EProduct();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Product Successfully Added"));
        populate();
    }

    public void editProduct(RowEditEvent event) {
        eProductFacade.edit((EProduct) event.getObject());
        productList = eProductFacade.findAll();
//           eSitesFacade.edit((ESites) e.getObject());
//        siteList = eSitesFacade.findAll();
    }

    public String findProductCategory(int projectCategoryId) {
        return eProductCategoryFacade.find(productCategoryid).getName();
    }

    public void confirmDelete(EProduct eProduct) {
        System.out.println("inside  confirm delete product...");
        delEProduct = eProduct;


    }

    public void deleteProduct() throws IOException {

        // int k=delEProduct.getId();


        eProductCategoryHasEProductsList.clear();
        eProductCategoryHasEProductsList = (List<EProductCategoryHasEProduct>) em.createNamedQuery("EProductCategoryHasEProduct.findByEProductId").setParameter("eProductId", delEProduct.getId()).getResultList();

        System.out.println("size of eProductCategoryHasEProductsList*******" + delEProduct.getId());


        for (int j = eProductCategoryHasEProductsList.size() - 1; j >= 0; j--) {

            System.out.println("inside for****** " + j + " " + eProductCategoryHasEProductsList.get(j));

            eProductCategoryHasEProductFacade.remove(eProductCategoryHasEProductsList.get(j));


        }

        eProductFacade.remove(delEProduct);
        eProductCategoryHasEProductsList.clear();
//         System.out.println("size of eProductCategoryHasEProductsList*********" + eProductCategoryHasEProductsList.size());
//        eProductCategoryHasEProductsList=(List<EProductCategoryHasEProduct>) em.createNamedQuery("EProductCategoryHasEProduct.findByEProductId").setParameter("eProductId",k ).getResultList();
//         System.out.println("size of eProductCategoryHasEProductsList*********" + eProductCategoryHasEProductsList.size());
//        System.out.println("outside for ****** 0"+ eProductCategoryHasEProductsList.get(0));
//        eProductCategoryHasEProductsList=eProductCategoryHasEProductFacade.findAll();
//         System.out.println("size of eProductCategoryHasEProductsList*********" + eProductCategoryHasEProductsList.size());
//          //eProductCategoryHasEProductFacade.remove(eProductCategoryHasEProductsList.get(0));
//         System.out.println("outside for ****** 0"+ eProductCategoryHasEProductsList.get(0).getEProductCategoryHasEProductPK());

        // productList = eProductFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Product/addProduct.xhtml");
    }

    public EProduct geteProduct() {
        return eProduct;
    }

    public void seteProduct(EProduct eProduct) {
        this.eProduct = eProduct;
    }

    public List<EProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<EProduct> productList) {
        this.productList = productList;
    }

    public EProductCategory geteProductCategory() {
        return eProductCategory;
    }

    public void seteProductCategory(EProductCategory eProductCategory) {
        this.eProductCategory = eProductCategory;
    }

    public int getProductCategoryid() {
        return productCategoryid;
    }

    public void setProductCategoryid(int productCategoryid) {
        this.productCategoryid = productCategoryid;
    }

    public ArrayList getProductcategoryList() {
        return productcategoryList;
    }

    public void setProductcategoryList(ArrayList productcategoryList) {
        this.productcategoryList = productcategoryList;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

  

}
