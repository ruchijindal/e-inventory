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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author smp
 */
@ManagedBean(name="intentbean")
@RequestScoped
public class IntentBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProductCategoryHasEProductFacade eProductCategoryHasEProductFacade;

     private TreeNode root;
         private TreeNode[] selectedNodes;

 List<EProductCategory> productCategoryList = new ArrayList<EProductCategory>();
 List<EProductCategoryHasEProduct> productList = new ArrayList<EProductCategoryHasEProduct>();
     private List<TreeNode> rootlist = new ArrayList<TreeNode>();
       private List<TreeNode> childrootlist = new ArrayList<TreeNode>();
    /** Creates a new instance of IntentBean */
    public IntentBean() {
    }
 
@PostConstruct
    public void populate() {
        try {

            int count = 0;
            root = new DefaultTreeNode("Root", null);
            productCategoryList = eProductCategoryFacade.findAll();
            productList.clear();
            for (int i = 0; i < productCategoryList.size(); i++) {
                rootlist.add(new DefaultTreeNode(productCategoryList.get(i), root));
                productList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", productCategoryList.get(i).getId()).getResultList();
                for (int j = 0; j < productList.size(); j++) {
                    int count1 = 0;
                    EProduct eProduct = eProductFacade.find(productList.get(j).getEProductCategoryHasEProductPK().getEProductId());
                    childrootlist.add(new DefaultTreeNode(eProduct,  rootlist.get(count)));
                    count1++;
                }
                count++;
            }

            //showData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

}



    public List<EProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<EProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public List<EProductCategoryHasEProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<EProductCategoryHasEProduct> productList) {
        this.productList = productList;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public List<TreeNode> getRootlist() {
        return rootlist;
    }

    public void setRootlist(List<TreeNode> rootlist) {
        this.rootlist = rootlist;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }
    
}
