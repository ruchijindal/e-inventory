/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.GenericBean;

/**
 *
 * @author smp
 */
import com.smp.EntityBean.EProductCategory;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless

public class ReadXMl {
  @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProductFacade eProductFacade;
    private List<AddMainBoq> mainBoqList = new ArrayList<AddMainBoq>();
    private AddMainBoq addMainBoq = new AddMainBoq();

    public List<AddMainBoq> readMainBoqXml(String xmlFile) throws IOException {
        mainBoqList.clear();


        try {
            int l = 0;

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

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return mainBoqList;


    }

    public List<AddMainBoq> readOrgBoqXml(String xmlFile) throws IOException {
        mainBoqList.clear();


        try {


            int l = 0;


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

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return mainBoqList;


    }

    public List<AddMainBoq> readSiteStockXml(String xmlFile) throws IOException {
        mainBoqList.clear();


        try {


            int l = 0;


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

                        NodeList surplusList = prodElement.getElementsByTagName("Surplus");
                        Element surplusElement = (Element) surplusList.item(0);
                        NodeList sList = surplusElement.getChildNodes();
                        String surplus = ((Node) sList.item(0)).getNodeValue().trim();

                        NodeList shortageList = prodElement.getElementsByTagName("Shortage");
                        Element shortageElement = (Element) shortageList.item(0);
                        NodeList qList = shortageElement.getChildNodes();
                        String shortage = ((Node) qList.item(0)).getNodeValue().trim();




                        addMainBoq.setSurplus(Integer.parseInt(surplus));
                        addMainBoq.setShortage(Integer.parseInt(shortage));
                        addMainBoq.setProductCategoryId(Integer.parseInt(pCatId));
                        addMainBoq.setProductId(Integer.parseInt(prodId));


                        mainBoqList.add(l, addMainBoq);
                        l++;
                    }


                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return mainBoqList;


    }
}
