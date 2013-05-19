/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.ESites;
import com.smp.GenericBean.AddMainBoq;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProjectFacade;
import com.smp.SessionBean.ESitesFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "addSiteStockBean")
@SessionScoped
@Stateless
public class AddSiteStockBean {

    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    ESitesFacade eSitesFacade;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    ArrayList projectList = new ArrayList();
    List<ESites> esiteList = new ArrayList<ESites>();
    public static ArrayList siteList = new ArrayList();
    public AddMainBoq addMainBoq = new AddMainBoq();
    public static List<AddMainBoq> mainBoqList = new ArrayList<AddMainBoq>();
    List<Integer> categoryIdList = new ArrayList<Integer>();
    String xmlFile;
    public static int projectId;
    int siteId;

    @PostConstruct
    public void populate() {
        int empid = LoginBean.employeeId;
        System.out.print("empId=========>" + empid);
        projectList.clear();
        mainBoqList.clear();
        for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
            projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
        }

    }

    public void populateSite() throws IOException {
        System.out.println("inside populatesite++++ " + projectId);

        esiteList.clear();
        esiteList = eSitesFacade.findAll();
        siteList.clear();
        for (int i = 0; i < esiteList.size(); i++) {
            if (esiteList.get(i).getEProject().getId() == projectId) {
                siteList.add(new SelectItem(esiteList.get(i).getId(), esiteList.get(i).getName()));

            }
        }
        System.out.println("sitelist ++++ " + siteList.size());


    }

    public void showData() throws IOException {
         System.out.println("showdata " + projectId);

        mainBoqList.clear();

        try {

            int l = 0;
            xmlFile = eProjectFacade.find(projectId).getOrgBoqXml();
            if (!xmlFile.equals("null")) {

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(new StringReader(xmlFile)));
                doc.getDocumentElement().normalize();
                NodeList boq = doc.getElementsByTagName("productCategory");
                System.out.println("boq size+++++++ " + boq.getLength());

                for (int i = 0; i < boq.getLength(); i++) {


                    Node boqItem = boq.item(i);
                    if (boqItem.getNodeType() == Node.ELEMENT_NODE) {
                        Element boqElement = (Element) boqItem;
                        String pCatId = boqElement.getAttribute("id").trim();
                        System.out.println("pCatId+++++++ " + pCatId);


                        NodeList prodList = boqElement.getElementsByTagName("product");
                        System.out.println("prodList++ " + prodList.getLength());

                        for (int j = 0; j < prodList.getLength(); j++) {
                            addMainBoq = new AddMainBoq();
                            Element prodElement = (Element) prodList.item(j);
                            String prodId = prodElement.getAttribute("id").trim();

                            NodeList quantityList = prodElement.getElementsByTagName("Quantity");
                            Element qElement = (Element) quantityList.item(0);
                            NodeList qList = qElement.getChildNodes();
                            String q = ((Node) qList.item(0)).getNodeValue().trim();




                            addMainBoq.setMinQuantity(Double.parseDouble(q));
                            addMainBoq.setProductCategoryId(Integer.parseInt(pCatId));
                            addMainBoq.setProductId(Integer.parseInt(prodId));

                            mainBoqList.add(l, addMainBoq);
                            l++;
                        }


                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }


    }

    public void generateXML() {
        String orgBoq = "<?xml version='1.0' encoding='UTF-8'?><Boq>";
        System.out.println("size of main BOQ list==>" + mainBoqList.size());
        for (int i = 0; i < mainBoqList.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < categoryIdList.size(); j++) {
                if (categoryIdList.get(j) == mainBoqList.get(i).getProductCategoryId()) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                categoryIdList.add(mainBoqList.get(i).getProductCategoryId());
            }

        }
        System.out.println("size of categoryid list+++++ " + categoryIdList.size());


        for (int i = 0; i < categoryIdList.size(); i++) {
            orgBoq = orgBoq + "<productCategory id=\"" + categoryIdList.get(i) + "\">";
            for (int j = 0; j < mainBoqList.size(); j++) {
                if (categoryIdList.get(i) == mainBoqList.get(j).getProductCategoryId()) {

                    orgBoq = orgBoq + "<product id=\"" + mainBoqList.get(j).getProductId() + "\">";
                    orgBoq = orgBoq + "<Surplus>" + mainBoqList.get(j).getSurplus() + "</Surplus>";
                    orgBoq = orgBoq + "<Shortage>" + mainBoqList.get(j).getShortage() + "</Shortage>";
                    orgBoq = orgBoq + "</product>";
                }

            }
            orgBoq = orgBoq + "</productCategory>";
        }
        orgBoq = orgBoq + "</Boq>";
        System.out.println("string===> " + orgBoq);

        ESites eSitesNew = eSitesFacade.find(siteId);
        eSitesNew.setSiteStockLevelXml(orgBoq);
        eSitesFacade.edit(eSitesNew);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Site Stock Successfully Added"));




    }

    public String findCategory(int id) {

        return eProductCategoryFacade.find(id).getName();
    }

    public String findProduct(int id) {
        String name = "";
        if (id != 0) {
            name = eProductFacade.find(id).getName();
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

    public ArrayList getSiteList() {
        return siteList;
    }

    public void setSiteList(ArrayList siteList) {
        this.siteList = siteList;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public List<AddMainBoq> getMainBoqList() {
        return mainBoqList;
    }

    public void setMainBoqList(List<AddMainBoq> mainBoqList) {
        this.mainBoqList = mainBoqList;
    }
}
