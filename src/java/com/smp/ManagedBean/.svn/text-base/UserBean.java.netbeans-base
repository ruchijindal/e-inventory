/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EDesignation;
import com.smp.EntityBean.EEmployee;
import com.smp.EntityBean.EEmployeeHasESites;
import com.smp.EntityBean.EEmployeeHasESitesPK;
import com.smp.EntityBean.EProject;
import com.smp.EntityBean.ESites;
import com.smp.SessionBean.EDesignationFacade;
import com.smp.SessionBean.EEmployeeFacade;
import com.smp.SessionBean.EEmployeeHasESitesFacade;
import com.smp.SessionBean.EProjectFacade;
import com.smp.SessionBean.ESitesFacade;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.persistence.Query;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author smp
 */
@ManagedBean(name = "userBean")
@SessionScoped
@Stateless
public class UserBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    @EJB
    EEmployeeFacade eEmployeeFacade;
    @EJB
    EDesignationFacade eDesignationFacade;
    @EJB
    EProjectFacade eProjectFacade;
    @EJB
    ESitesFacade eSitesFacade;
    @EJB
    EEmployeeHasESitesFacade eEmployeeHasESitesFacade;
    private final static int ITERATION_NUMBER = 1000;
    public EEmployee eEmployee = new EEmployee();
    public EDesignation eDesignation = new EDesignation();
    public static EEmployee eDelEmployee = new EEmployee();
    public List<EEmployee> employeeList = new ArrayList<EEmployee>();
    private ArrayList designationList = new ArrayList();
    public List<String> selectedProject = new ArrayList<String>();
    public List<EProject> treeProjectList = new ArrayList<EProject>();
    public List<ESites> treeSiteList = new ArrayList<ESites>();
    int designationId;
    int projectId, siteId;
    String sDigest;
    String sSalt;
    boolean flag, flag1;
    static boolean renderSite;
    public static String password;
    public static String confirmPassword;
    private List projectList = new ArrayList();
    public List siteList = new ArrayList();
    public List<ESites> psiteList = new ArrayList<ESites>();
    public List<ESites> siteList1 = new ArrayList<ESites>();
    private TreeNode root;
    private List<TreeNode> rootlist = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist = new ArrayList<TreeNode>();
    private TreeNode[] selectedNodes;
    public EEmployeeHasESites eEmployeeHasESites = new EEmployeeHasESites();
    public EEmployeeHasESitesPK eEmployeeHasESitesPK = new EEmployeeHasESitesPK();
    public List<Integer> sitesIdList = new ArrayList<Integer>();
    public List<Integer> projectIdList = new ArrayList<Integer>();
    public List<EEmployeeHasESites> eEmployeeHasESitesList = new ArrayList<EEmployeeHasESites>();
    public static List<ProjectSiteInterface> projectSiteList = new ArrayList<ProjectSiteInterface>();
    private List<TreeNode> parentlist = new ArrayList<TreeNode>();
    List<EProject> projectRootList = new ArrayList<EProject>();
    private List<TreeNode> rootlist1 = new ArrayList<TreeNode>();
    private List<TreeNode> childrootlist1 = new ArrayList<TreeNode>();
    public static TreeNode root1 = new DefaultTreeNode("Root", null);
    private List<TreeNode> parentRootList = new ArrayList<TreeNode>();
    public static EEmployee employeeDetail = new EEmployee();
    List<EEmployeeHasESites> siteDetails = new ArrayList<EEmployeeHasESites>();

    @PostConstruct
    public void populate() {
        designationList = new ArrayList();
        employeeList.clear();
        projectList.clear();
        designationId = 0;
        eEmployee = new EEmployee();
        selectedProject.clear();
        selectedNodes = null;
        for (int i = 0; i < eDesignationFacade.findAll().size(); i++) {
            designationList.add(new SelectItem(eDesignationFacade.findAll().get(i).getId(), eDesignationFacade.findAll().get(i).getDesignation()));
        }
        for (int i = 0; i < eProjectFacade.findAll().size(); i++) {
            projectList.add(new SelectItem(eProjectFacade.findAll().get(i).getId(), eProjectFacade.findAll().get(i).getName()));
        }

        employeeList = eEmployeeFacade.findAll();
        populateTree();
    }

    public boolean createUserLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (eEmployee.getUserId() != null && password != null && eEmployee.getUserId().length() <= 100) {
            // Uses a secure Random not a simple Random
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            // Salt generation 64 bits long
            byte[] bSalt = new byte[8];
            random.nextBytes(bSalt);
            // Digest computation
            byte[] bDigest = getHash(ITERATION_NUMBER, password, bSalt);
            sDigest = byteToBase64(bDigest);
            sSalt = byteToBase64(bSalt);

            return true;
        } else {
            return false;
        }
    }

    public void addEmployee() throws IOException, NoSuchAlgorithmException {

        if (!password.equals(confirmPassword)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password and confirm password must be same", ""));
        } else {

            flag = createUserLogin();

            if (flag == true) {

                eDesignation = eDesignationFacade.find(designationId);
                eEmployee.setEDesignation(eDesignation);
                eEmployee.setPassword(sDigest);
                eEmployee.setSalt(sSalt);
                eEmployeeFacade.create(eEmployee);
                addSites();

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Congrates! User Successfully Added"));
                populate();
            } else {
                System.out.println("inside else");
            }
        }
        eEmployee = new EEmployee();


    }

    public void addSites() throws IOException, NoSuchAlgorithmException {

        System.out.println("inside addsites");
        Query query = em.createNamedQuery("EEmployee.findByMaxEmpId");
        int maxEmpId = (Integer) query.getResultList().get(0);


        System.out.println("inside if");
        sitesIdList.clear();
        projectIdList.clear();
        System.out.println("size of selectnodes+++++ " + selectedNodes.length);
        projectSiteList.clear();
        for (TreeNode node : selectedNodes) {
            ProjectSiteInterface psI = (ProjectSiteInterface) node.getData();
            projectSiteList.add(psI);
        }

        for (int i = 0; i < projectSiteList.size(); i++) {


            List<ESites> eSiteseList = em.createNamedQuery("ESites.findById&Name").setParameter("id", projectSiteList.get(i).getId()).setParameter("name", projectSiteList.get(i).getName()).getResultList();
            if (!eSiteseList.isEmpty()) {
                sitesIdList.add(projectSiteList.get(i).getId());
            } else {
                projectIdList.add(projectSiteList.get(i).getId());

            }

        }
        System.out.println("sze of site id list++++++ " + sitesIdList.size());
        System.out.println("sze of projectid list++++++ " + projectIdList.size());

        boolean siteflag = false;
        for (int i = 0; i < projectIdList.size(); i++) {
            psiteList = eSitesFacade.findAll();
            for (int j = 0; j < psiteList.size(); j++) {
                siteflag = false;
                for (int k = 0; k < sitesIdList.size(); k++) {
                    if (sitesIdList.get(k) == psiteList.get(j).getId()) {
                        siteflag = true;
                        break;
                    }
                }
                if (siteflag == false) {
                    if (projectIdList.get(i) == psiteList.get(j).getEProject().getId()) {
                        eEmployeeHasESites.setEEmployeeHasESitesPK(eEmployeeHasESitesPK);
                        eEmployeeHasESitesPK.setEEmployeeId(maxEmpId);
                        eEmployeeHasESitesPK.setESitesId(psiteList.get(j).getId());
                        eEmployeeHasESitesFacade.create(eEmployeeHasESites);
                    }
                }
            }
        }

        for (int i = 0; i < sitesIdList.size(); i++) {


            eEmployeeHasESites.setEEmployeeHasESitesPK(eEmployeeHasESitesPK);
            eEmployeeHasESitesPK.setEEmployeeId(maxEmpId);
            eEmployeeHasESitesPK.setESitesId(sitesIdList.get(i));
            eEmployeeHasESitesFacade.create(eEmployeeHasESites);
        }
    }

    public void editEmployee(org.primefaces.event.RowEditEvent e) {

        EEmployee eEmployeenew = (EEmployee) e.getObject();
        eDesignation = eDesignationFacade.find(eEmployeenew.getEDesignation().getId());
        eEmployeenew.setEDesignation(eDesignation);
        eEmployeeFacade.edit(eEmployeenew);
        employeeList = eEmployeeFacade.findAll();

    }

    public void confirmDelete(EEmployee eEmployee) {
        eDelEmployee = eEmployee;

    }

    public void deleteEmployee() throws IOException {
        System.out.println("inside delete project...");
        eEmployeeFacade.remove(eDelEmployee);
        eEmployeeHasESitesList = em.createNamedQuery("EEmployeeHasESites.findByEEmployeeId").setParameter("eEmployeeId", eDelEmployee.getId()).getResultList();
        for (int j = 0; j < eEmployeeHasESitesList.size(); j++) {

            System.out.println("inside for****** " + j + " " + eEmployeeHasESitesList.get(j));

            //eEmployeeHasESitesList.get(j).setEEmployeeHasESitesPK(null);

            eEmployeeHasESitesFacade.remove(eEmployeeHasESitesList.get(j));


        }
        EEmployeeHasESites eEmployeeHasESitesnew = (EEmployeeHasESites) em.createNamedQuery("EEmployeeHasESites.findByEEmployeeId").setParameter("eEmployeeId", eDelEmployee.getId()).getResultList().get(0);

        System.out.println("empId" + eEmployeeHasESitesnew.getEEmployeeHasESitesPK().getEEmployeeId());
        System.out.println("siteid" + eEmployeeHasESitesnew.getEEmployeeHasESitesPK().getESitesId());
        eEmployeeHasESitesFacade.remove(eEmployeeHasESitesnew);


        employeeList = eEmployeeFacade.findAll();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/jsfpages/UserSettings/createUser.xhtml");

    }

    public String findDesignation(int designationId) {
        String desgName = eDesignationFacade.find(designationId).getDesignation();
        return desgName;
    }

    public void renderSite() {
        System.out.println("inside rendersite++++++");
        if (eDesignationFacade.find(designationId).getDesignation().equals("Project Manager")) {
            renderSite = false;
            populateOnlyProjectTree();
        } else {
            renderSite = true;
            populateTree();

        }
        System.out.println("inside rendersite***** " + renderSite);
    }

    public void populateTree() {
        System.out.println("inside populateTree");
        int count = 0;
        root = new DefaultTreeNode("Root", null);
        treeProjectList = eProjectFacade.findAll();
        for (int i = 0; i < treeProjectList.size(); i++) {
            psiteList = eSitesFacade.findAll();
            rootlist.add(count, new DefaultTreeNode(treeProjectList.get(i), root));
            int count1 = 0;
            for (int j = 0; j < psiteList.size(); j++) {
                if (treeProjectList.get(i).getId() == psiteList.get(j).getEProject().getId()) {
                    childrootlist.add(count1, new DefaultTreeNode(psiteList.get(j), rootlist.get(count)));
                    count1++;

                }
            }
            count++;

        }
        System.out.println("size of rootlist-----> " + rootlist.size());
    }

    public void populateOnlyProjectTree() {
        System.out.println("inside populateTree");
        int count = 0;
        root = new DefaultTreeNode("Root", null);
        treeProjectList = eProjectFacade.findAll();
        for (int i = 0; i < treeProjectList.size(); i++) {
            psiteList = eSitesFacade.findAll();
            rootlist.add(count, new DefaultTreeNode(treeProjectList.get(i), root));

            count++;

        }
        System.out.println("size of rootlist-----> " + rootlist.size());
    }

    public void showData() {

        System.out.println("+++++++++++++++++++++++generateXML");

        int pos = 0;
        projectSiteList.clear();
        parentlist.clear();
        projectRootList.clear();

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
                System.out.println("inside if id===>" + projectSiteList.get(i).getId());
                projectRootList.add((EProject) projectSiteList.get(i));
                rootlist1.add(count, new DefaultTreeNode(projectSiteList.get(i), root1));

                List<ESites> siteList1 = eSitesFacade.findAll();
                for (int j = 0; j < siteList1.size(); j++) {
                    if (siteList1.get(j).getEProject().getId() == projectSiteList.get(i).getId()) {
                        childrootlist1.add(new DefaultTreeNode(siteList1.get(j), rootlist1.get(count)));
                    }
                }
                count++;

            } else {
                System.out.println("inside else id===>");
                flag = false;

                for (int k = 0; k < projectRootList.size(); k++) {

                    if (projectRootList.get(k).getId() == ((EProject) parent.getData()).getId()) {
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
                        rootlist1.add(count, new DefaultTreeNode((EProject) parent.getData(), root1));
                        childrootlist1.add(new DefaultTreeNode(projectSiteList.get(i), rootlist1.get(count)));
                        count++;
                    } else {
                        childrootlist1.add(new DefaultTreeNode(projectSiteList.get(i), rootlist1.get(pos)));

                    }

                }
            }

        }
        System.out.println("size of rootlist1+++++ " + rootlist1.size());



    }

    public void editSite() {

        System.out.println("+++++++++++++++++++++++editSite");
        projectSiteList.clear();
        parentlist.clear();
        projectRootList.clear();

        for (TreeNode node : selectedNodes) {
            ProjectSiteInterface psI = (ProjectSiteInterface) node.getData();
            projectSiteList.add(psI);
            parentlist.add(node.getParent());
        }
        System.out.println("size of projectsiteList===>" + projectSiteList.size());
        for (int i = 0; i < projectSiteList.size(); i++) {
            TreeNode parent = parentlist.get(i);
            if (parent.toString().equals("Root")) {
                System.out.println("inside if id===>" + projectSiteList.get(i).getId());
                projectRootList.add((EProject) projectSiteList.get(i));

                List<ESites> siteList1 = eSitesFacade.findAll();
                for (int j = 0; j < siteList1.size(); j++) {
                    if (siteList1.get(j).getEProject().getId() == projectSiteList.get(i).getId()) {
                        List<EEmployeeHasESites> list = em.createNamedQuery("EEmployeeHasESites.findByESitesId&EEmployeeId").setParameter("eSitesId", siteList1.get(j).getId()).setParameter("eEmployeeId", employeeDetail.getId()).getResultList();
                        if (list.isEmpty()) {
                            eEmployeeHasESites = new EEmployeeHasESites();
                            eEmployeeHasESites.setEEmployeeHasESitesPK(eEmployeeHasESitesPK);
                            eEmployeeHasESitesPK.setEEmployeeId(employeeDetail.getId());
                            eEmployeeHasESitesPK.setESitesId(siteList1.get(j).getId());
                            eEmployeeHasESitesFacade.create(eEmployeeHasESites);

                        }
                    }
                }


            } else {
                System.out.println("inside else id===>");
                flag = false;

                for (int k = 0; k < projectRootList.size(); k++) {

                    if (projectRootList.get(k).getId() == ((EProject) parent.getData()).getId()) {
                        System.out.println("inside if===>");
                        flag = true;
                        break;
                    }
                }


                if (flag == false) {
                    List<EEmployeeHasESites> list = em.createNamedQuery("EEmployeeHasESites.findByESitesId&EEmployeeId").setParameter("eSitesId", projectSiteList.get(i).getId()).setParameter("eEmployeeId", employeeDetail.getId()).getResultList();
                    if (list.isEmpty()) {
                        parentRootList.add(parent);
                        eEmployeeHasESites = new EEmployeeHasESites();
                        eEmployeeHasESites.setEEmployeeHasESitesPK(eEmployeeHasESitesPK);
                        eEmployeeHasESitesPK.setEEmployeeId(employeeDetail.getId());
                        eEmployeeHasESitesPK.setESitesId(projectSiteList.get(i).getId());
                        eEmployeeHasESitesFacade.create(eEmployeeHasESites);

                    }

                }
            }

        }
        onRowSelect(employeeDetail);


    }

    public void onRowSelect(EEmployee event) {
        System.out.println("+++++++++++++++++++++++onrowSelect");
        List<EProject> parentIdList = new ArrayList<EProject>();
        employeeDetail=new EEmployee();
        employeeDetail = event;
         System.out.println("employee id+++++++++++++++++++++++ "+employeeDetail.getEmpName());
        siteDetails = em.createNamedQuery("EEmployeeHasESites.findByEEmployeeId").setParameter("eEmployeeId", employeeDetail.getId()).getResultList();
        root1 = new DefaultTreeNode("Root", null);
        childrootlist1 = new ArrayList<TreeNode>();
        rootlist1 = new ArrayList<TreeNode>();
        int count = 0;
        boolean f = false;
        int index = 0;
        for (int i = 0; i < siteDetails.size(); i++) {
            int sid = siteDetails.get(i).getEEmployeeHasESitesPK().getESitesId();
            f = false;
            System.out.println("size =>" + parentIdList.size());
            System.out.println("size of rootlist1 =>" + rootlist1.size());
            index = 0;
            for (int k = 0; k < parentIdList.size(); k++) {
                System.out.println("inside if=========>" + parentIdList.get(k).getId() + "   " + eSitesFacade.find(sid).getEProject().getId());
                if (parentIdList.get(k).equals(eSitesFacade.find(sid).getEProject())) {
                    System.out.println("inside if=========>" + f);
                    f = true;
                    index = rootlist1.indexOf(parentIdList.get(k));
                    break;
                }
            }
            System.out.println("f=========>" + f);
            if (f == true) {
                System.out.println("pos=========>" + index);
                childrootlist1.add(new DefaultTreeNode(eSitesFacade.find(sid), rootlist1.get(index + 1)));

            } else {
                parentIdList.add(eSitesFacade.find(sid).getEProject());
                rootlist1.add(count, new DefaultTreeNode(eSitesFacade.find(sid).getEProject(), root1));
                childrootlist1.add(new DefaultTreeNode(eSitesFacade.find(sid), rootlist1.get(count)));
                count++;
            }

        }
        populateTree();


    }

    public byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);


        byte[] input = digest.digest(password.getBytes("UTF-8"));


        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);


        }
        return input;


    }

    public static byte[] base64ToByte(String data) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();



        return decoder.decodeBuffer(data);


    }

    public static String byteToBase64(byte[] data) {
        BASE64Encoder endecoder = new BASE64Encoder();


        return endecoder.encode(data);

    }

    public ArrayList getDesignationList() {
        return designationList;
    }

    public void setDesignationList(ArrayList designationList) {
        this.designationList = designationList;
    }

    public List<EEmployee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EEmployee> employeeList) {
        this.employeeList = employeeList;
    }

    public EEmployee geteEmployee() {
        return eEmployee;
    }

    public void seteEmployee(EEmployee eEmployee) {
        this.eEmployee = eEmployee;
    }

    public EEmployee geteDelEmployee() {
        return eDelEmployee;
    }

    public void seteDelEmployee(EEmployee eDelEmployee) {
        this.eDelEmployee = eDelEmployee;
    }

    public int getDesignationId() {
        return designationId;
    }

    public void setDesignationId(int designationId) {
        this.designationId = designationId;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        UserBean.password = password;
    }

    public List getProjectList() {
        return projectList;
    }

    public void setProjectList(List projectList) {
        this.projectList = projectList;
    }

    public boolean isRenderSite() {
        System.out.print("inside get++++ " + renderSite);
        return renderSite;
    }

    public void setRenderSite(boolean renderSite) {
        this.renderSite = renderSite;
    }

    public List<String> getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(List<String> selectedProject) {
        this.selectedProject = selectedProject;
    }

    public List getSiteList() {
        System.out.println("sitelist size" + siteList.size());
        return siteList;
    }

    public void setSiteList(List siteList) {
        this.siteList = siteList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public List<ESites> getPsiteList() {
        return psiteList;
    }

    public void setPsiteList(List<ESites> psiteList) {
        this.psiteList = psiteList;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode[] getSelectedNodes() {
        System.out.println("size of selectlist" + selectedNodes.length);
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }

    public List<EEmployeeHasESites> getSiteDetails() {
        return siteDetails;
    }

    public void setSiteDetails(List<EEmployeeHasESites> siteDetails) {
        this.siteDetails = siteDetails;
    }
}
