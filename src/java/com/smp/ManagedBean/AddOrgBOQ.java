/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProject;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.smp.GenericBean.AddMainBoq;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProjectFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@ManagedBean(name = "addOrgBean")
@SessionScoped
public class AddOrgBOQ {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProjectFacade eProjectFacade;
    private AddMainBoq addMainBoq = new AddMainBoq();
    private List<AddMainBoq> mainBoqList = new ArrayList<AddMainBoq>();
    private ArrayList projectList = new ArrayList();
    public static int projectId;
    String xmlFile;
    List<Integer> categoryIdList = new ArrayList<Integer>();

    /** Creates a new instance of AddOrgBOQ */
    public AddOrgBOQ() {
    }

    @PostConstruct
    public void populate() {
        mainBoqList.clear();
        for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
            projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
        }
    }

    public void showData() throws IOException {
        mainBoqList.clear();


        try {


            int l = 0;
            xmlFile = eProjectFacade.find(projectId).getMainBoqXml();
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

                            NodeList rateList = prodElement.getElementsByTagName("Rate");
                            Element rateElement = (Element) rateList.item(0);
                            NodeList rList = rateElement.getChildNodes();
                            String r = ((Node) rList.item(0)).getNodeValue().trim();


                            addMainBoq.setQuntity(Double.parseDouble(q));
                            addMainBoq.setRate(Double.parseDouble(r));
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

    public void checkQuantity(AddMainBoq MainBoq) {
        System.out.println("inside checkquantity" + MainBoq.getMinQuantity() + MainBoq.getQuntity());
        if (MainBoq.getMinQuantity() > MainBoq.getQuntity()) {
            System.out.println("inside if==>");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Org Boq Quantity should be less than Main Boq quantity", ""));

        }

    }

    public void generateXML() {
        String orgBoq = "<?xml version='1.0' encoding='UTF-8'?><Boq>";
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
                    orgBoq = orgBoq + "<Quantity>" + mainBoqList.get(j).getMinQuantity() + "</Quantity>";
                    orgBoq = orgBoq + "</product>";
                }

            }
            orgBoq = orgBoq + "</productCategory>";
        }
        orgBoq = orgBoq + "</Boq>";
        System.out.println("string===> " + orgBoq);

        EProject eProject = eProjectFacade.find(projectId);
        eProject.setOrgBoqXml(orgBoq);
        eProjectFacade.edit(eProject);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Organization  BOQ  Successfully Added"));




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

   
    public String reinit() {
        addMainBoq = new AddMainBoq();

        return null;
    }

    public AddMainBoq getAddMainBoq() {
        return addMainBoq;
    }

    public void setAddMainBoq(AddMainBoq addMainBoq) {
        this.addMainBoq = addMainBoq;
    }

    public List<AddMainBoq> getMainBoqList() {
        return mainBoqList;
    }

    public void setMainBoqList(List<AddMainBoq> mainBoqList) {
        this.mainBoqList = mainBoqList;
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
}
