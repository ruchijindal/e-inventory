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
public class EVendorsHasEProductPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "e_vendors_id")
    private int eVendorsId;
    @Basic(optional = false)
    @Column(name = "e_product_id")
    private int eProductId;

    public EVendorsHasEProductPK() {
    }

    public EVendorsHasEProductPK(int eVendorsId, int eProductId) {
        this.eVendorsId = eVendorsId;
        this.eProductId = eProductId;
    }

    public int getEVendorsId() {
        return eVendorsId;
    }

    public void setEVendorsId(int eVendorsId) {
        this.eVendorsId = eVendorsId;
    }

    public int getEProductId() {
        return eProductId;
    }

    public void setEProductId(int eProductId) {
        this.eProductId = eProductId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) eVendorsId;
        hash += (int) eProductId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EVendorsHasEProductPK)) {
            return false;
        }
        EVendorsHasEProductPK other = (EVendorsHasEProductPK) object;
        if (this.eVendorsId != other.eVendorsId) {
            return false;
        }
        if (this.eProductId != other.eProductId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EVendorsHasEProductPK[eVendorsId=" + eVendorsId + ", eProductId=" + eProductId + "]";
    }

}
