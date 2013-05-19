/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProduct;
import com.smp.EntityBean.EProductCategory;
import com.smp.EntityBean.EProductCategoryHasEProduct;
import com.smp.EntityBean.ESites;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductCategoryHasEProductFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProjectFacade;
import com.smp.SessionBean.ESitesFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author smp
 */
@ManagedBean(name = "siteStockBean")
@SessionScoped
@Stateless
public class SiteStockBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProductCategoryHasEProductFacade eProductCategoryHasEProductFacade;
    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    ESitesFacade eSitesFacade;
    private TreeNode root;
    private TreeNode root1 = new DefaultTreeNode("Root", null);

    ;
    private TreeNode[] selectedNodes;
    private List<TreeNode> rootlist = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist = new ArrayList<TreeNode>();
    private List<TreeNode> rootlist1 = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist1 = new ArrayList<TreeNode>();
    private List<TreeNode> parentlist = new ArrayList<TreeNode>();
    private List<TreeNode> parentRootList = new ArrayList<TreeNode>();
    List<EProductCategory> productCategoryList = new ArrayList<EProductCategory>();
    List<EProductCategoryHasEProduct> productList = new ArrayList<EProductCategoryHasEProduct>();
    List<EProductCategoryHasEProduct> hasProductList = new ArrayList<EProductCategoryHasEProduct>();
    public static List<ProjectSiteInterface> projectSiteList = new ArrayList<ProjectSiteInterface>();
    public static List<ProjectSiteInterface> projectSiteDetailList = new ArrayList<ProjectSiteInterface>();
    public static List<Integer> surplusList = new ArrayList<Integer>();
    public static List<Integer> shortageList = new ArrayList<Integer>();
    List<EProductCategory> productRootList = new ArrayList<EProductCategory>();
    List<ESites> esiteList = new ArrayList<ESites>();
    boolean flag = false;
    ArrayList projectList = new ArrayList();
    public ArrayList siteList = new ArrayList();
    public int projectId, siteId;
    String siteBoq = "<?xml version='1.0' encoding='UTF-8'?><Boq>";
    String xmlFile = "";
    List<SiteBoq> siteBoqList = new ArrayList<SiteBoq>();
    int surplus, shortage, index = 0;
    boolean renderTextbox;
    boolean expandTree;
    ProjectSiteInterface deletePSI;

    @PostConstruct
    public void populate() {
        try {
            projectList.clear();
            //        projectId = 0;
            //        siteId = 0;
            projectSiteList.clear();
            for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
                projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
            }
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
                    childrootlist.add(new DefaultTreeNode(eProduct, rootlist.get(count)));
                    count1++;
                }
                count++;
            }
           // populateSite();
            //showData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void populateSite() {
        expandTree = false;
        esiteList.clear();
        esiteList = eSitesFacade.findAll();
        siteList.clear();
        for (int i = 0; i < esiteList.size(); i++) {
             if (esiteList.get(i).getEProject().getId() == projectId) {
            siteList.add(new SelectItem(esiteList.get(i).getId(), esiteList.get(i).getName()));

             }
        }

    }

    public void showData() {

        System.out.println("+++++++++++++++++++++++generateXML");

        int pos = 0;
        projectSiteList.clear();
        parentlist.clear();
        productRootList.clear();

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

                hasProductList = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", projectSiteList.get(i).getId()).getResultList();
                for (int j = 0; j < hasProductList.size(); j++) {
                    childrootlist1.add(new DefaultTreeNode(eProductFacade.find(hasProductList.get(j).getEProductCategoryHasEProductPK().getEProductId()), rootlist1.get(count)));
                }
                count++;

            } else {
                flag = false;
                System.out.println("size of productcategorylist***** " + productRootList.size());
                for (int k = 0; k < productRootList.size(); k++) {
                    System.out.println("productCategoryList.get(k).getId()==>" + productCategoryList.get(k).getId());
                    System.out.println("((EProductCategory) parent.getData()).getId()==>" + ((EProductCategory) parent.getData()).getId());
                    if (productRootList.get(k).getId()== ((EProductCategory) parent.getData()).getId()) {
                        System.out.println("inside if===>");
                        flag = true;
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
                if (flag == false) {
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

        expandTree = true;

    }

//    public void showData() throws IOException {
//        siteBoqList.clear();
//        root1 = new DefaultTreeNode("Root", null);
//
//        try {
//            for (int a = 0; a < eSitesFacade.findAll().size(); a++) {
//                SiteBoq siteBoq = new SiteBoq();
//                int count = 0;
//                int l = 0;
//                xmlFile = eSitesFacade.findAll().get(a).getSiteStockLevelXml();
//                siteBoq.setSiteId(eSitesFacade.findAll().get(a).getId());
//                siteBoq.setSiteName(eSitesFacade.findAll().get(a).getName());
//                rootlist1.add(count, new DefaultTreeNode(eSitesFacade.findAll().get(a), root1));
//
//                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//                DocumentBuilder db = dbf.newDocumentBuilder();
//                Document doc = db.parse(new InputSource(new StringReader(xmlFile)));
//                doc.getDocumentElement().normalize();
//                NodeList boq = doc.getElementsByTagName("productCategory");
//                System.out.println("boq size+++++++ " + boq.getLength());
//                if (boq.getLength() == 0) {
//                    siteBoqList.add(l, siteBoq);
//
//                    l++;
//                    siteBoq = new SiteBoq();
//                    siteBoq.setSiteId(eSitesFacade.findAll().get(a).getId());
//                    siteBoq.setSiteName(eSitesFacade.findAll().get(a).getName());
//
//                } else {
//                    for (int i = 0; i < boq.getLength(); i++) {
//                        int count1 = 0;
//                        Node boqItem = boq.item(i);
//                        if (boqItem.getNodeType() == Node.ELEMENT_NODE) {
//                            Element boqElement = (Element) boqItem;
//                            String pCatId = boqElement.getAttribute("id").trim();
//                            System.out.println("pCatId+++++++ " + pCatId);
//                            childrootlist1.add(count1, new DefaultTreeNode(eProductCategoryFacade.find(Integer.parseInt(pCatId)), rootlist1.get(count)));
//                            siteBoq.setProductcategoryId(Integer.parseInt(pCatId));
//                            siteBoq.setProductCategoryName(eProductCategoryFacade.find(Integer.parseInt(pCatId)).getName());
//                            NodeList prodList = boqElement.getElementsByTagName("product");
//                            System.out.println("prodList++ " + prodList.getLength());
//                            if (prodList.getLength() == 0) {
//                                siteBoqList.add(l, siteBoq);
//                                l++;
//                                siteBoq = new SiteBoq();
//                                siteBoq.setSiteId(eSitesFacade.findAll().get(a).getId());
//                                siteBoq.setSiteName(eSitesFacade.findAll().get(a).getName());
//
//                            } else {
//                                for (int j = 0; j < prodList.getLength(); j++) {
//                                    Element prodElement = (Element) prodList.item(j);
//                                    String prodId = prodElement.getAttribute("id").trim();
//                                    siteBoq.setProductId(Integer.parseInt(prodId));
//                                    siteBoq.setProductName(eProductFacade.find(Integer.parseInt(prodId)).getName());
//                                    childrootlist2.add(count1, new DefaultTreeNode(eProductFacade.find(Integer.parseInt(prodId)), childrootlist1.get(count1)));
//                                    siteBoqList.add(l, siteBoq);
//                                    l++;
//                                    siteBoq = new SiteBoq();
//                                    siteBoq.setSiteId(eSitesFacade.findAll().get(a).getId());
//                                    siteBoq.setSiteName(eSitesFacade.findAll().get(a).getName());
//                                    siteBoq.setProductcategoryId(Integer.parseInt(pCatId));
//                                    siteBoq.setProductCategoryName(eProductCategoryFacade.find(Integer.parseInt(pCatId)).getName());
//
//                                }
//                            }
//                        }
//                        count1++;
//                    }
//                }
//                count++;
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//
//        }
////        System.out.println("size of siteBoqList======>" + siteBoqList.size());
////        for (int i = 0; i < siteBoqList.size(); i++) {
////            System.out.println("siteid===>" + siteBoqList.get(i).getSiteId());
////            System.out.println("productId====>" + siteBoqList.get(i).getProductId());
////            System.out.println("productname====>" + siteBoqList.get(i).getProductName());
////            System.out.println("productcatId====>" + siteBoqList.get(i).getProductcategoryId());
////            System.out.println("productcatname====>" + siteBoqList.get(i).getProductCategoryName());
////        }
//
//    }
    public boolean chkstatus(ProjectSiteInterface psI) {
        //System.out.println("inside chkstatus.........");
        renderTextbox = false;

        List<EProductCategory> pcategoryList = em.createNamedQuery("EProductCategory.findId&Name").setParameter("id", psI.getId()).setParameter("name", psI.getName()).getResultList();
        if (!pcategoryList.isEmpty()) {
            List<EProductCategoryHasEProduct> list = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", psI.getId()).getResultList();
            if (list.isEmpty()) {
                renderTextbox = true;
            } else {
                renderTextbox = false;
            }
        } else {

            renderTextbox = true;
        }

        return renderTextbox;


    }

    public void delete( ) {
        deletePSI=null;  
        System.out.println("inside delete.."+deletePSI.getName());
        for (int i = 0; i < rootlist1.size(); i++) {
           System.out.println("in for loop.."+deletePSI.equals(rootlist1.get(i)));
            if (rootlist1.get(i).equals(deletePSI)) {
                rootlist.remove(rootlist1.get(i));
                showData();
            }

        }
    }

    public void addDetails(ProjectSiteInterface psI) {
        System.out.println("inside adddetails/.............");
        SiteBoq siteBoq = new SiteBoq();
        siteBoq.setProjectSiteInterface(psI);
        siteBoq.setShortage(shortage);
        siteBoq.setSurplus(surplus);
        siteBoqList.add(siteBoq);
    }

    public void show() {
        System.out.println("size of siteBoqlist==>" + siteBoqList.size());
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

    public ArrayList getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList projectList) {
        this.projectList = projectList;
    }

    public int getProjectId() {
        System.out.println("siteprojid in set===> " + projectId);
        return projectId;
    }

    public void setProjectId(int projectId) {
        System.out.println("siteprojid in set===> " + projectId);
        expandTree = false;
        this.projectId = projectId;
    }

    public int getSiteId() {
        System.out.println("siteid in get===> " + siteId);
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
        expandTree = false;
        System.out.println("siteid in set===> " + siteId);
    }

    public ArrayList getSiteList() {
        return siteList;
    }

    public void setSiteList(ArrayList siteList) {
        this.siteList = siteList;
    }

    public List<SiteBoq> getSiteBoqList() {
        return siteBoqList;
    }

    public void setSiteBoqList(List<SiteBoq> siteBoqList) {
        this.siteBoqList = siteBoqList;
    }

    public int getShortage() {
        return shortage;
    }

    public void setShortage(int shortage) {
        this.shortage = shortage;
    }

    public int getSurplus() {
        return surplus;
    }

    public void setSurplus(int surplus) {
        this.surplus = surplus;
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }

    public boolean isRenderTextbox() {
        return renderTextbox;
    }

    public void setRenderTextbox(boolean renderTextbox) {
        this.renderTextbox = renderTextbox;
    }

    public boolean isExpandTree() {
        return expandTree;
    }

    public void setExpandTree(boolean expandTree) {
        this.expandTree = expandTree;
    }

    public ProjectSiteInterface getDeletePSI() {
        System.out.println("inside get........");
        return deletePSI;
    }

    public void setDeletePSI(ProjectSiteInterface deletePSI) {
         System.out.println("inside set........");
        this.deletePSI = deletePSI;
    }

    
    
}
