/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.EntityBean;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_employee_has_e_sites")
@NamedQueries({
    @NamedQuery(name = "EEmployeeHasESites.findByESitesId&EEmployeeId", query = "SELECT e FROM EEmployeeHasESites e WHERE e.eEmployeeHasESitesPK.eSitesId = :eSitesId AND e.eEmployeeHasESitesPK.eEmployeeId = :eEmployeeId"),
    @NamedQuery(name = "EEmployeeHasESites.findAll", query = "SELECT e FROM EEmployeeHasESites e"),
    @NamedQuery(name = "EEmployeeHasESites.findByEEmployeeId", query = "SELECT e FROM EEmployeeHasESites e WHERE e.eEmployeeHasESitesPK.eEmployeeId = :eEmployeeId"),
    @NamedQuery(name = "EEmployeeHasESites.findByESitesId", query = "SELECT e FROM EEmployeeHasESites e WHERE e.eEmployeeHasESitesPK.eSitesId = :eSitesId")})
public class EEmployeeHasESites implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EEmployeeHasESitesPK eEmployeeHasESitesPK;

    public EEmployeeHasESites() {
    }

    public EEmployeeHasESites(EEmployeeHasESitesPK eEmployeeHasESitesPK) {
        this.eEmployeeHasESitesPK = eEmployeeHasESitesPK;
    }

    public EEmployeeHasESites(int eEmployeeId, int eSitesId) {
        this.eEmployeeHasESitesPK = new EEmployeeHasESitesPK(eEmployeeId, eSitesId);
    }

    public EEmployeeHasESitesPK getEEmployeeHasESitesPK() {
        return eEmployeeHasESitesPK;
    }

    public void setEEmployeeHasESitesPK(EEmployeeHasESitesPK eEmployeeHasESitesPK) {
        this.eEmployeeHasESitesPK = eEmployeeHasESitesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eEmployeeHasESitesPK != null ? eEmployeeHasESitesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EEmployeeHasESites)) {
            return false;
        }
        EEmployeeHasESites other = (EEmployeeHasESites) object;
        if ((this.eEmployeeHasESitesPK == null && other.eEmployeeHasESitesPK != null) || (this.eEmployeeHasESitesPK != null && !this.eEmployeeHasESitesPK.equals(other.eEmployeeHasESitesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EEmployeeHasESites[eEmployeeHasESitesPK=" + eEmployeeHasESitesPK + "]";
    }
}
