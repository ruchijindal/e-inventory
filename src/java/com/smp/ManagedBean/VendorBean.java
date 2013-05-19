/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProduct;
import com.smp.EntityBean.EProductCategory;
import com.smp.EntityBean.EProductCategoryHasEProduct;
import com.smp.EntityBean.EVendors;
import com.smp.EntityBean.EVendorsHasEProduct;
import com.smp.EntityBean.EVendorsHasEProductPK;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EVendorsFacade;
import com.smp.SessionBean.EVendorsHasEProductFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author smp
 */
@ManagedBean(name = "vendorBean")
@RequestScoped
public class VendorBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EVendorsFacade eVendorsFacade;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EVendorsHasEProductFacade eVendorsHasEProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    EProduct eProduct;
    EVendors eVendors;
    List<EVendorsHasEProduct> eVendorsHasEProductsList = new ArrayList<EVendorsHasEProduct>();
    List<EVendors> vendorList;
    public List<String> selectedProductList = new ArrayList<String>();
    public static EVendors delEvendor = new EVendors();
    EVendorsHasEProduct eVendorsHasEProduct = new EVendorsHasEProduct();
    EVendorsHasEProductPK eVendorsHasEProductPK = new EVendorsHasEProductPK();
    int productid;
    private TreeNode root;
    private TreeNode[] selectedNodes;
    private List<TreeNode> rootlist = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist = new ArrayList<TreeNode>();
    List<EProductCategory> productCategoryList = new ArrayList<EProductCategory>();
    List<EProductCategoryHasEProduct> productList = new ArrayList<EProductCategoryHasEProduct>();
    public static List<ProjectSiteInterface> projectSiteList = new ArrayList<ProjectSiteInterface>();
    private List<TreeNode> parentlist = new ArrayList<TreeNode>();
    List<EProductCategory> productcategoryList = new ArrayList<EProductCategory>();
    List<EProductCategoryHasEProduct> hasProductList = new ArrayList<EProductCategoryHasEProduct>();
    boolean flag;

    public VendorBean() {
        eVendors = new EVendors();
        vendorList = new ArrayList<EVendors>();

    }

    @PostConstruct
    public void populate() {
        System.out.println("userId====>"+LoginBean.userId);
        vendorList.clear();
        productList.clear();
        vendorList = eVendorsFacade.findAll();
        eVendors = new EVendors();


        int count = 0;
        root = new DefaultTreeNode("Root", null);
        productCategoryList = eProductCategoryFacade.findAll();
        productList.clear();
        for (int i = 0; i < productCategoryList.size(); i++) {
            rootlist.add(count, new DefaultTreeNode(productCategoryList.get(i), root));
            productList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", productCategoryList.get(i).getId()).getResultList();
            for (int j = 0; j < productList.size(); j++) {
                int count1 = 0;
                EProduct eProduct = eProductFacade.find(productList.get(j).getEProductCategoryHasEProductPK().getEProductId());
                childrootlist.add(count1, new DefaultTreeNode(eProduct, rootlist.get(count)));
                count1++;
            }
            count++;
        }

    }

    public void addvendor() {
        eVendorsFacade.create(eVendors);
        eVendors=new EVendors();


        Query query = em.createNamedQuery("EVendors.findByMaxId");
        Object maxVendorId = query.getResultList().get(0);
        System.out.println("****" + maxVendorId);

        projectSiteList.clear();
        parentlist.clear();
        for (TreeNode node : selectedNodes) {
            ProjectSiteInterface psI = (ProjectSiteInterface) node.getData();
            projectSiteList.add(psI);
            parentlist.add(node.getParent());
        }

        for (int i = 0; i < projectSiteList.size(); i++) {
            TreeNode parent = parentlist.get(i);
            if (parent.toString().equals("Root")) {
                productcategoryList.add((EProductCategory) projectSiteList.get(i));
                addProductCategory(projectSiteList.get(i), maxVendorId);
            } else {
                addProduct(projectSiteList.get(i), parent, maxVendorId);
            }


        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates!Vendor Successfully Added"));
        populate();




    }

    void addProductCategory(ProjectSiteInterface psI, Object maxVendorId) {
        System.out.println("inside addProductCategory");

        hasProductList.clear();
        hasProductList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", psI.getId()).getResultList();
        for (int j = 0; j < hasProductList.size(); j++) {
            eVendorsHasEProduct.setEVendorsHasEProductPK(eVendorsHasEProductPK);
            eVendorsHasEProductPK.setEProductId(hasProductList.get(j).getEProductCategoryHasEProductPK().getEProductId());
            eVendorsHasEProductPK.setEVendorsId((Integer) maxVendorId);
            eVendorsHasEProductFacade.create(eVendorsHasEProduct);

        }


    }

    public void addProduct(ProjectSiteInterface psI, TreeNode parent, Object maxVendorId) {
        System.out.println("inside addProduct");

        flag = false;       
        for (int k = 0; k < productcategoryList.size(); k++) {
            if (productCategoryList.get(k).getId() == ((EProductCategory) parent.getData()).getId()) {
                System.out.println("inside if===>");
                flag = true;
                break;
            }
        }
        if (flag == false) {
            eVendorsHasEProduct.setEVendorsHasEProductPK(eVendorsHasEProductPK);
            eVendorsHasEProductPK.setEProductId(psI.getId());
            eVendorsHasEProductPK.setEVendorsId((Integer) maxVendorId);
            eVendorsHasEProductFacade.create(eVendorsHasEProduct);

        }

    }

    public void editvendor(RowEditEvent event) {
        eVendorsFacade.edit((EVendors) event.getObject());
        vendorList = eVendorsFacade.findAll();
    }

    public String findProduct(int projectId) {
        return eProductFacade.find(productid).getName();
    }

    public void confirmDelete(EVendors eVendors1) {
        System.out.println("inside  confirm delete productcategory...");
        delEvendor = eVendors1;


    }

    public void deletevendor() throws IOException {
        System.out.println("delete ......");
        eVendorsHasEProductsList.clear();
        eVendorsHasEProductsList = (List<EVendorsHasEProduct>) em.createNamedQuery("EVendorsHasEProduct.findByEVendorsId").setParameter("eVendorsId", delEvendor.getId()).getResultList();

        System.out.println("size of eVendorHasEProductsList*******" + delEvendor.getId());


        for (int j = eVendorsHasEProductsList.size() - 1; j >= 0; j--) {

            System.out.println("inside for****** " + j + " " + eVendorsHasEProductsList.get(j));

            eVendorsHasEProductFacade.remove(eVendorsHasEProductsList.get(j));


        }
        eVendorsFacade.remove(delEvendor);
        vendorList = eVendorsFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/Vendor/addVendor.xhtml");
    }

    public EVendors geteVendors() {
        return eVendors;
    }

    public void seteVendors(EVendors eVendors) {
        this.eVendors = eVendors;
    }

    public List<EVendors> getVendorList() {
        return vendorList;
    }

    public void setVendorList(List<EVendors> vendorList) {
        this.vendorList = vendorList;
    }

    public EProduct geteProduct() {
        return eProduct;
    }

    public void seteProduct(EProduct eProduct) {
        this.eProduct = eProduct;
    }

    public EVendorsHasEProduct geteVendorsHasEProduct() {
        return eVendorsHasEProduct;
    }

    public void seteVendorsHasEProduct(EVendorsHasEProduct eVendorsHasEProduct) {
        this.eVendorsHasEProduct = eVendorsHasEProduct;
    }

    public EVendorsHasEProductPK geteVendorsHasEProductPK() {
        return eVendorsHasEProductPK;
    }

    public void seteVendorsHasEProductPK(EVendorsHasEProductPK eVendorsHasEProductPK) {
        this.eVendorsHasEProductPK = eVendorsHasEProductPK;
    }

    public List<EVendorsHasEProduct> geteVendorsHasEProductsList() {
        return eVendorsHasEProductsList;
    }

    public void seteVendorsHasEProductsList(List<EVendorsHasEProduct> eVendorsHasEProductsList) {
        this.eVendorsHasEProductsList = eVendorsHasEProductsList;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public List<String> getSelectedProductList() {
        return selectedProductList;
    }

    public void setSelectedProductList(List<String> selectedProductList) {
        this.selectedProductList = selectedProductList;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }
}
