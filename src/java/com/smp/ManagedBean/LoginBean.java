/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.ManagedBean;

import com.smp.EntityBean.EEmployee;
import com.smp.SessionBean.EEmployeeFacade;
import java.io.IOException;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.EJB;
import sun.misc.BASE64Decoder;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginBean")
@ApplicationScoped
@Stateful
public class LoginBean {

    @PersistenceContext(unitName = "e-InventoryPU")
    EntityManager em;
    private final static int ITERATION_NUMBER = 1000;
    @EJB
    EEmployeeFacade eEmployeeFacade;
    int tab = 0;
    public static String userId;
    String password;
    boolean userFlag;
    List<EEmployee> employeeList = new ArrayList<EEmployee>();
    EEmployee eEmployee = new EEmployee();
    public static  int employeeId;

    public void tabSet(int i) {
        tab = i;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
    FacesMessage msg = null;

    public void login() throws IOException, SQLException, NoSuchAlgorithmException {

        boolean flag = authenticate();
        if (flag == true) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/Purchase/inventoryDashboard.xhtml");
            EEmployee eEmployeeNew=(EEmployee) em.createNamedQuery("EEmployee.findByUserId").setParameter("userId", userId).getResultList().get(0);
            employeeId=eEmployeeNew.getId();

        } else {

            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public boolean authenticate() throws SQLException, NoSuchAlgorithmException {
        try {
            boolean userExist = true;
            if (userId == null || password == null) {
                userExist = false;
                password = "";
                userId = "";
            }


            System.out.println("userid-----> " + userId);


            employeeList = eEmployeeFacade.findAll();
            userFlag = false;
            eEmployee = new EEmployee();
            for (int i = 0; i < employeeList.size(); i++) {
                if (userId.equals(employeeList.get(i).getUserId())) {
                    userFlag = true;
                    eEmployee = employeeList.get(i);
                    break;

                }
            }

            String digest, salt;

            if (userFlag == false) {
                digest = "000000000000000000000000000=";
                salt = "00000000000=";
                userExist = false;
            } else {
                digest = eEmployee.getPassword();
                salt = eEmployee.getSalt();
                if (digest == null || salt == null) {
                    throw new SQLException("Database inconsistant Salt or Digested Password altered");
                }
            }

            byte[] bDigest = base64ToByte(digest);
            byte[] bSalt = base64ToByte(salt);
            byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);

            return Arrays.equals(proposedDigest, bDigest) && userExist;
        } catch (IOException ex) {
            throw new SQLException("Database inconsistant Salt or Digested Password altered");
        }
    }

    public byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);


        byte[] input = digest.digest(password.getBytes("UTF-8"));


        for (int i = 0; i
                < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);


        }
        return input;


    }

    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        eEmployee = new EEmployee();
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/e-Inventory/faces/index.xhtml");
    }

    public static byte[] base64ToByte(String data) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(data);

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
