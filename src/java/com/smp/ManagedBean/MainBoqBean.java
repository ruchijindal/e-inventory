/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProduct;
import com.smp.EntityBean.EProductCategory;
import com.smp.EntityBean.EProductCategoryHasEProduct;
import com.smp.EntityBean.EProject;
import com.smp.GenericBean.AddMainBoq;
import com.smp.GenericBean.ReadXMl;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProjectFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "mainBoq")
@SessionScoped
@Stateless
public class MainBoqBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    ArrayList projectList = new ArrayList();
    public static int projectId;    
    private TreeNode root;
    private List<TreeNode> rootlist = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist = new ArrayList<TreeNode>();
    List<EProductCategory> productCategoryList = new ArrayList<EProductCategory>();
    List<EProductCategoryHasEProduct> productList = new ArrayList<EProductCategoryHasEProduct>();
    private TreeNode[] selectedNodes;
    AddMainBoq addMainBoq = new AddMainBoq();
    List<AddMainBoq> mainBoqList = new ArrayList<AddMainBoq>();
    public static List<AddMainBoq> reBoqList = new ArrayList<AddMainBoq>();
    public List<ProjectSiteInterface> projectSiteList = new ArrayList<ProjectSiteInterface>();
    private List<TreeNode> parentlist = new ArrayList<TreeNode>();
    List<EProductCategoryHasEProduct> hasProductList = new ArrayList<EProductCategoryHasEProduct>();
    List<EProductCategory> productRootList = new ArrayList<EProductCategory>();
    boolean flag = false;
    ReadXMl readXMl = new ReadXMl();
    List<Integer> categoryIdList = new ArrayList<Integer>();

    @PostConstruct
    public void populate() {
        // reBoqList.clear();

        // projectId = 0;
        projectList.clear();
        for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
            projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
        }

        int count = 0;
        root = new DefaultTreeNode("Root", null);
        productCategoryList.clear();
        productCategoryList = eProductCategoryFacade.findAll();

        for (int i = 0; i < productCategoryList.size(); i++) {
            productList.clear();
            productList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", productCategoryList.get(i).getId()).getResultList();
            if (!productList.isEmpty()) {
                rootlist.add(new DefaultTreeNode(productCategoryList.get(i), root));
                for (int j = 0; j < productList.size(); j++) {
                    EProduct eProduct = eProductFacade.find(productList.get(j).getEProductCategoryHasEProductPK().getEProductId());
                    childrootlist.add(new DefaultTreeNode(eProduct, rootlist.get(count)));

                }
                count++;
            }
        }

    }

    public void readBoq() throws IOException {
        mainBoqList.clear();
        reBoqList.clear();
        System.out.println("inside readBoq");
        String boqXml = eProjectFacade.find(projectId).getMainBoqXml();
        if (!boqXml.equals("null")) {
            mainBoqList = readXMl.readMainBoqXml(boqXml);
        }
        reBoqList = mainBoqList;
        System.out.println("size of reBoqList==========>" + reBoqList.size());

        for (int i = 0; i < reBoqList.size(); i++) {
            System.out.println("categoryid=========>" + reBoqList.get(i).getProductCategoryId());
            System.out.println("product id=========>" + reBoqList.get(i).getProductId());

        }

    }

    public void showData() throws IOException {
        System.out.println("size of list=======>" + reBoqList.size());
        boolean available = false;

        for (TreeNode node : selectedNodes) {
            ProjectSiteInterface psI = (ProjectSiteInterface) node.getData();
            projectSiteList.add(psI);
            parentlist.add(node.getParent());
        }

        mainBoqList.clear();
        for (int i = 0; i < projectSiteList.size(); i++) {
            TreeNode parent = parentlist.get(i);
            if (parent.toString().equals("Root")) {
                //System.out.println("id===>" + projectSiteList.get(i).getId());
                productRootList.add((EProductCategory) projectSiteList.get(i));

                hasProductList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", projectSiteList.get(i).getId()).getResultList();
                for (int j = 0; j < hasProductList.size(); j++) {


                    addMainBoq = new AddMainBoq();
                    addMainBoq.setProductCategoryId(projectSiteList.get(i).getId());
                    addMainBoq.setProductId(hasProductList.get(j).getEProductCategoryHasEProductPK().getEProductId());
                    available = false;
                    for (int l = 0; l < reBoqList.size(); l++) {
                        if ((reBoqList.get(l).getProductId() == addMainBoq.getProductId()) && (reBoqList.get(l).getProductCategoryId() == addMainBoq.getProductCategoryId())) {
                            System.out.println("inside if..............");
                            available = true;
                            break;
                        }

                    }
                    System.out.println("vailable=====> " + available);
                    if (available == false) {
                        mainBoqList.add(addMainBoq);
                    }

                }


            } else {
                flag = false;
                // System.out.println("size of productcategorylist***** " + productRootList.size());
                for (int k = 0; k < productRootList.size(); k++) {
                    System.out.println("((EProductCategory) parent.getData()).getId()==>" + ((EProductCategory) parent.getData()).getId());
                    if (productRootList.get(k).getId() == ((EProductCategory) parent.getData()).getId()) {
                        System.out.println("inside if===>");
                        flag = true;
                        break;
                    }
                }

                if (flag == false) {
                    addMainBoq = new AddMainBoq();
                    addMainBoq.setProductCategoryId(((EProductCategory) parent.getData()).getId());
                    addMainBoq.setProductId(projectSiteList.get(i).getId());
                    available = false;
                    for (int l = 0; l < reBoqList.size(); l++) {
                        if ((reBoqList.get(l).getProductId() == addMainBoq.getProductId()) && (reBoqList.get(l).getProductCategoryId() == addMainBoq.getProductCategoryId())) {
                            System.out.println("inside if..............");
                            available = true;
                            break;
                        }

                    }
                    System.out.println("vailable=====> " + available);
                    if (available == false) {
                        mainBoqList.add(addMainBoq);
                    }

                }

            }
        }
        for (int i = 0; i < mainBoqList.size(); i++) {
            reBoqList.add(mainBoqList.get(i));
        }

        System.out.println("size of list=======>" + reBoqList.size());

    }

    public void delete(AddMainBoq addMainBoq) {
        System.out.println("inside delete.........." + projectId);
        reBoqList.remove(addMainBoq);
    }

    public void generateXML() {
        String mainBoq = "<?xml version='1.0' encoding='UTF-8'?><Boq>";
        for (int i = 0; i < reBoqList.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < categoryIdList.size(); j++) {
                if (categoryIdList.get(j) == reBoqList.get(i).getProductCategoryId()) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                categoryIdList.add(reBoqList.get(i).getProductCategoryId());
            }

        }
        System.out.println("size of categoryid list+++++ " + categoryIdList.size());


        for (int i = 0; i < categoryIdList.size(); i++) {
            mainBoq = mainBoq + "<productCategory id=\"" + categoryIdList.get(i) + "\">";
            for (int j = 0; j < reBoqList.size(); j++) {
                if (categoryIdList.get(i) == reBoqList.get(j).getProductCategoryId()) {




                    mainBoq = mainBoq + "<product id=\"" + reBoqList.get(j).getProductId() + "\">";
                    mainBoq = mainBoq + "<Quantity>" + reBoqList.get(j).getQuntity() + "</Quantity>";
                    mainBoq = mainBoq + "<Rate>" + reBoqList.get(j).getRate() + "</Rate>";
                    mainBoq = mainBoq + "</product>";
                }

            }
            mainBoq = mainBoq + "</productCategory>";
        }
        mainBoq = mainBoq + "</Boq>";
        System.out.println("string===> " + mainBoq);

        System.out.println("projectId========> " + projectId);
        EProject eProject = eProjectFacade.find(projectId);
        eProject.setMainBoqXml(mainBoq);
        eProjectFacade.edit(eProject);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Main BOQ  Successfully Added"));



    }

    public String showProductCategory(int id) {
        System.out.println("id===========>" + id);
        String name = null;
        if (id != 0) {
            name = eProductCategoryFacade.find(id).getName();
        }
        return name;
    }

    public String showProduct(int id) {
        System.out.println("id1===========>" + id);
        String name = null;
        if (id != 0) {
            name = eProductFacade.find(id).getName();
        }
        return name;
    }

    public String showUnit(int id) {
        System.out.println("id2===========>" + id);
        String name = null;
        if (id != 0) {
            name = eProductFacade.find(id).getUnit();
        }
        return name;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public ArrayList getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList projectList) {
        this.projectList = projectList;
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

    public List<AddMainBoq> getMainBoqList() {
        return mainBoqList;
    }

    public void setMainBoqList(List<AddMainBoq> mainBoqList) {
        this.mainBoqList = mainBoqList;
    }

    public List<AddMainBoq> getReBoqList() {
        return reBoqList;
    }

    public void setReBoqList(List<AddMainBoq> reBoqList) {
        this.reBoqList = reBoqList;
    }
}
