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
@Table(name = "e_vendors_has_e_product")
@NamedQueries({
    @NamedQuery(name = "EVendorsHasEProduct.findAll", query = "SELECT e FROM EVendorsHasEProduct e"),
    @NamedQuery(name = "EVendorsHasEProduct.findByEVendorsId", query = "SELECT e FROM EVendorsHasEProduct e WHERE e.eVendorsHasEProductPK.eVendorsId = :eVendorsId"),
    @NamedQuery(name = "EVendorsHasEProduct.findByEProductId", query = "SELECT e FROM EVendorsHasEProduct e WHERE e.eVendorsHasEProductPK.eProductId = :eProductId")})
public class EVendorsHasEProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EVendorsHasEProductPK eVendorsHasEProductPK;

    public EVendorsHasEProduct() {
    }

    public EVendorsHasEProduct(EVendorsHasEProductPK eVendorsHasEProductPK) {
        this.eVendorsHasEProductPK = eVendorsHasEProductPK;
    }

    public EVendorsHasEProduct(int eVendorsId, int eProductId) {
        this.eVendorsHasEProductPK = new EVendorsHasEProductPK(eVendorsId, eProductId);
    }

    public EVendorsHasEProductPK getEVendorsHasEProductPK() {
        return eVendorsHasEProductPK;
    }

    public void setEVendorsHasEProductPK(EVendorsHasEProductPK eVendorsHasEProductPK) {
        this.eVendorsHasEProductPK = eVendorsHasEProductPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eVendorsHasEProductPK != null ? eVendorsHasEProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EVendorsHasEProduct)) {
            return false;
        }
        EVendorsHasEProduct other = (EVendorsHasEProduct) object;
        if ((this.eVendorsHasEProductPK == null && other.eVendorsHasEProductPK != null) || (this.eVendorsHasEProductPK != null && !this.eVendorsHasEProductPK.equals(other.eVendorsHasEProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EVendorsHasEProduct[eVendorsHasEProductPK=" + eVendorsHasEProductPK + "]";
    }

}
