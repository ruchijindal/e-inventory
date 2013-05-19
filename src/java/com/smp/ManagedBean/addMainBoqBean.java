/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EProduct;
import com.smp.EntityBean.EProductCategory;
import com.smp.EntityBean.EProductCategoryHasEProduct;
import com.smp.EntityBean.EProject;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.smp.GenericBean.AddMainBoq;
import com.smp.SessionBean.EProductCategoryFacade;
import com.smp.SessionBean.EProductFacade;
import com.smp.SessionBean.EProjectFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp
 */
@ManagedBean(name = "addmainboq")
@SessionScoped
public class addMainBoqBean implements Serializable {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EProductFacade eProductFacade;
    @EJB
    EProductCategoryFacade eProductCategoryFacade;
    @EJB
    EProjectFacade eProjectFacade;
    List categorys = new ArrayList<EProductCategory>();
    List<EProductCategoryHasEProduct> list = new ArrayList<EProductCategoryHasEProduct>();
    List<EProductCategory> eProducts = new ArrayList<EProductCategory>();
    List eProductList = new ArrayList<EProduct>();
    private AddMainBoq addMainBoq = new AddMainBoq();
    private List<AddMainBoq> mainBoqList = new ArrayList<AddMainBoq>();
    boolean render = false;
    List<Integer> categoryIdList = new ArrayList<Integer>();
    private ArrayList projectList = new ArrayList();
    int projectId;

    /** Creates a new instance of BookManagedBean */
    public addMainBoqBean() {
    }

    @PostConstruct
    public void populate() {
        eProducts = eProductCategoryFacade.findAll();
        projectList.clear();
        eProductList.clear();
        mainBoqList.clear();
        projectId=0;


        for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
            projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
        }

        for (int i = 0; i < eProducts.size(); i++) {
            eProductList.add(new SelectItem(eProducts.get(i).getId(), eProducts.get(i).getName()));
        }
    }

    public void changeProject()
    {
        mainBoqList.clear();

    }

    public void renderSite() {
        categorys.clear();
        //System.out.println("product-->" + productId);
        list = em.createNamedQuery("EProductCategoryHasEProduct.findByEProductCategoryId").setParameter("eProductCategoryId", addMainBoq.getProductCategoryId()).getResultList();
        // System.out.println("list size-->" + list.size());
        if (list.isEmpty()) {
            render = true;
        } else {
            for (int i = 0; i < list.size(); i++) {
                //System.out.println(list.get(i).getEProductCategoryHasEProductPK().getEProductCategoryId());
                EProduct prodId = eProductFacade.find(list.get(i).getEProductCategoryHasEProductPK().getEProductId());
                categorys.add(new SelectItem(prodId.getId(), prodId.getName()));
            }
            render = false;
        }
    }

    public void show() {
        for (int i = 0; i < mainBoqList.size(); i++) {
            System.out.println("product Id-->" + mainBoqList.get(i).getProductCategoryId());
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
                    orgBoq = orgBoq + "<Quantity>"+ mainBoqList.get(j).getQuntity() +"</Quantity>";
                    orgBoq = orgBoq + "<Rate>" + mainBoqList.get(j).getRate()+"</Rate>";
                    orgBoq = orgBoq + "</product>";
                }

            }
            orgBoq = orgBoq + "</productCategory>";
        }
        orgBoq = orgBoq + "</Boq>";
        System.out.println("string===> " + orgBoq);

        EProject eProject = eProjectFacade.find(projectId);
        eProject.setMainBoqXml(orgBoq);
        eProjectFacade.edit(eProject);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Congrates! Main BOQ  Successfully Added"));



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

    public List getCategorys() {
        return categorys;
    }

    public void setCategorys(List categorys) {
        this.categorys = categorys;
    }

    public List geteProductList() {
        return eProductList;
    }

    public void seteProductList(List eProductList) {
        this.eProductList = eProductList;
    }

    public List<EProductCategory> geteProducts() {
        return eProducts;
    }

    public void seteProducts(List<EProductCategory> eProducts) {
        this.eProducts = eProducts;
    }

    public List<EProductCategoryHasEProduct> getList() {
        return list;
    }

    public void setList(List<EProductCategoryHasEProduct> list) {
        this.list = list;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public ArrayList getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList projectList) {
        this.projectList = projectList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
