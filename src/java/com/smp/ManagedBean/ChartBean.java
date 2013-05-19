/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.ManagedBean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.smp.GenericBean.AddMainBoq;
import java.util.ArrayList;
/**
 *
 * @author smp
 */
@ManagedBean(name = "chartBean")
@SessionScoped
  public class ChartBean implements Serializable {

    private List<AddMainBoq> births;

    public ChartBean() {
//        births = new ArrayList<AddMainBoq>();
//        births.add(new AddMainBoq("Cement", 120, 52));
//        births.add(new AddMainBoq("Rodi", 100, 60));
//        births.add(new AddMainBoq("Steel", 44, 210));
//        births.add(new AddMainBoq("Sand", 150, 135));
//        births.add(new AddMainBoq("Brics", 125, 120));
    }

    public List<AddMainBoq> getBirths() {
        return births;
    }
}
