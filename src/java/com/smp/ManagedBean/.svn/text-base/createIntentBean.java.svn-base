/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EEmployeeHasESites;
import com.smp.EntityBean.EProductCategory;
import com.smp.GenericBean.AddMainBoq;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.ESitesFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.smp.GenericBean.ReadXMl;
import com.smp.SessionBean.EEmployeeFacade;
import com.smp.SessionBean.EEmployeeHasESitesFacade;
import com.smp.SessionBean.EIntentFacade;
import com.smp.SessionBean.EProductHasEIntentFacade;
import java.io.IOException;
import javax.ejb.Stateless;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author smp
 */
@ManagedBean(name = "createIntentBean")
@SessionScoped
@Stateless
public class createIntentBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    ESitesFacade eSitesFacade;
    @EJB
    EEmployeeHasESitesFacade eEmployeeHasESitesFacade;
    @EJB
    EProductCategoryFacade categoryFacade;
    @EJB
    EProductFacade productFacade;
    @EJB
    EIntentFacade eIntentFacade;
    @EJB
    EEmployeeFacade eEmployeeFacade;
    @EJB
    EProductHasEIntentFacade eProductHasEIntentFacade;
    List<EEmployeeHasESites> listOfSites = new ArrayList<EEmployeeHasESites>();
    public static ArrayList siteList = new ArrayList();
    static List<AddMainBoq> stockList = new ArrayList<AddMainBoq>();
    private static TreeNode root = new DefaultTreeNode("Root", null);
    private List<TreeNode> rootlist = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist = new ArrayList<TreeNode>();
    ReadXMl readXMl = new ReadXMl();
    public static int siteId;
    List<EProductCategory> parentList = new ArrayList<EProductCategory>();
    private TreeNode[] selectedNodes;
    private TreeNode root1 = new DefaultTreeNode("Root", null);
    private List<TreeNode> rootlist1 = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist1 = new ArrayList<TreeNode>();
    public static List<ProjectSiteInterface> projectSiteList = new ArrayList<ProjectSiteInterface>();
    private List<TreeNode> parentlist = new ArrayList<TreeNode>();
    List<EProductCategory> productRootList = new ArrayList<EProductCategory>();
    boolean flag2;
    private List<TreeNode> parentRootList = new ArrayList<TreeNode>();

    @PostConstruct
    public void populate() throws IOException {
        try {
            listOfSites.clear();
            siteList.clear();
            listOfSites = em.createNamedQuery("EEmployeeHasESites.findByEEmployeeId").setParameter("eEmployeeId", LoginBean.employeeId).getResultList();
            System.out.println("esiteList populate**********" + listOfSites.size());
            siteList.clear();
            for (int i = 0; i < listOfSites.size(); i++) {
                siteList.add(new SelectItem(listOfSites.get(i).getEEmployeeHasESitesPK().getESitesId(), eSitesFacade.find(listOfSites.get(i).getEEmployeeHasESitesPK().getESitesId()).getName()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //showData();
    }

    public void populateTree() throws IOException {
        try {
            root = new DefaultTreeNode("Root", null);
            rootlist.clear();
            childrootlist.clear();
            System.out.println("populate************" + siteId);
            stockList.clear();
            stockList = readXMl.readSiteStockXml(eSitesFacade.find(siteId).getSiteStockLevelXml());

            boolean flag = false;
            int pos = 0;
            int count = 0;
            for (int i = 0; i < stockList.size(); i++) {
                int PcatId = stockList.get(i).getProductCategoryId();
                EProductCategory eProductCategory = categoryFacade.find(PcatId);
                flag = false;
                for (int j = 0; j < parentList.size(); j++) {
                    if (parentList.get(j).equals(eProductCategory)) {
                        flag = true;
                        pos = rootlist.indexOf(eProductCategory);
                        break;

                    }

                }
                if (flag == true) {
                    childrootlist.add(new DefaultTreeNode(productFacade.find(stockList.get(i).getProductId()), rootlist.get(pos + 1)));

                } else {
                    parentList.add(eProductCategory);
                    rootlist.add(count, new DefaultTreeNode(eProductCategory, root));
                    childrootlist.add(new DefaultTreeNode(productFacade.find(stockList.get(i).getProductId()), rootlist.get(count)));
                    count++;

                }

            }
            System.out.println("size of rootlist==>" + rootlist.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showData() {

        System.out.println("+++++++++++++++++++++++showdata" + siteId);

        int pos = 0;
        projectSiteList.clear();
        parentlist.clear();
        productRootList.clear();
        root1 = new DefaultTreeNode("Root", null);
        rootlist1.clear();
        childrootlist1.clear();


        int count = 0;

        for (TreeNode node : selectedNodes) {
            ProjectSiteInterface psI = (ProjectSiteInterface) node.getData();
            projectSiteList.add(psI);
            parentlist.add(node.getParent());
        }

        System.out.println("size of projectsiteList===>" + projectSiteList.size());
        for (int i = 0; i < projectSiteList.size(); i++) {
            TreeNode parent = parentlist.get(i);
            if (parent.toString().equals("Root")) {
                System.out.println("id===>" + projectSiteList.get(i).getId());
                productRootList.add((EProductCategory) projectSiteList.get(i));
                rootlist1.add(count, new DefaultTreeNode(projectSiteList.get(i), root1));


                System.out.print("stocklist size==>" + stockList.size());
                for (int j = 0; j < stockList.size(); j++) {
                    System.out.print("for++++++" + j);
                    if (stockList.get(j).getProductCategoryId() == projectSiteList.get(i).getId()) {
                        System.out.print("ifr++++++" + j);
                        childrootlist1.add(new DefaultTreeNode(productFacade.find(stockList.get(j).getProductId()), rootlist1.get(count)));

                    }
                }
                count++;


            } else {
                flag2 = false;
                System.out.println("size of productcategorylist***** " + productRootList.size());
                for (int k = 0; k < productRootList.size(); k++) {

                    System.out.println("((EProductCategory) parent.getData()).getId()==>" + ((EProductCategory) parent.getData()).getId());
                    if (productRootList.get(k).getId() == ((EProductCategory) parent.getData()).getId()) {
                        System.out.println("inside if===>");
                        flag2 = true;
                        break;
                    }
                }
                boolean flag1 = false;
                for (int k = 0; k < parentRootList.size(); k++) {
                    if (parent.equals(parentRootList.get(k))) {
                        flag1 = true;
                        pos = rootlist1.indexOf(parent);
                        System.out.println("pos====>" + pos);

                        break;
                    }

                }
                if (flag2 == false) {
                    if (flag1 == false) {
                        parentRootList.add(parent);
                        rootlist1.add(count, new DefaultTreeNode((EProductCategory) parent.getData(), root1));
                        childrootlist1.add(new DefaultTreeNode(projectSiteList.get(i), rootlist1.get(count)));
                        count++;
                    } else {
                        childrootlist1.add(new DefaultTreeNode(projectSiteList.get(i), rootlist1.get(pos)));

                    }

                }
            }

        }



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

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public ArrayList getSiteList() {
        System.out.println("size in get++++++ " + siteList.size());
        return siteList;
    }

    public void setSiteList(ArrayList siteList) {
        System.out.println("size in get++++++ " + siteList.size());
        this.siteList = siteList;
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }
}
