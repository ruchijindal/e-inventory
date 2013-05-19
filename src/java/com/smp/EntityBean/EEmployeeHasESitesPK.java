/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.EntityBean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author smp
 */
@Embeddable
public class EEmployeeHasESitesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "e_employee_id")
    private int eEmployeeId;
    @Basic(optional = false)
    @Column(name = "e_sites_id")
    private int eSitesId;

    public EEmployeeHasESitesPK() {
    }

    public EEmployeeHasESitesPK(int eEmployeeId, int eSitesId) {
        this.eEmployeeId = eEmployeeId;
        this.eSitesId = eSitesId;
    }

    public int getEEmployeeId() {
        return eEmployeeId;
    }

    public void setEEmployeeId(int eEmployeeId) {
        this.eEmployeeId = eEmployeeId;
    }

    public int getESitesId() {
        return eSitesId;
    }

    public void setESitesId(int eSitesId) {
        this.eSitesId = eSitesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) eEmployeeId;
        hash += (int) eSitesId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EEmployeeHasESitesPK)) {
            return false;
        }
        EEmployeeHasESitesPK other = (EEmployeeHasESitesPK) object;
        if (this.eEmployeeId != other.eEmployeeId) {
            return false;
        }
        if (this.eSitesId != other.eSitesId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EEmployeeHasESitesPK[eEmployeeId=" + eEmployeeId + ", eSitesId=" + eSitesId + "]";
    }

}
