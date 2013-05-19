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
@Table(name = "e_product_category_has_e_product")
@NamedQueries({
    @NamedQuery(name = "EProductCategoryHasEProduct.findAll", query = "SELECT e FROM EProductCategoryHasEProduct e"),
    @NamedQuery(name = "EProductCategoryHasEProduct.findByEProductCategoryId", query = "SELECT e FROM EProductCategoryHasEProduct e WHERE e.eProductCategoryHasEProductPK.eProductCategoryId = :eProductCategoryId"),
    @NamedQuery(name = "EProductCategoryHasEProduct.findByEProductId", query = "SELECT e FROM EProductCategoryHasEProduct e WHERE e.eProductCategoryHasEProductPK.eProductId = :eProductId")})
public class EProductCategoryHasEProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EProductCategoryHasEProductPK eProductCategoryHasEProductPK;

    public EProductCategoryHasEProduct() {
    }

    public EProductCategoryHasEProduct(EProductCategoryHasEProductPK eProductCategoryHasEProductPK) {
        this.eProductCategoryHasEProductPK = eProductCategoryHasEProductPK;
    }

    public EProductCategoryHasEProduct(int eProductCategoryId, int eProductId) {
        this.eProductCategoryHasEProductPK = new EProductCategoryHasEProductPK(eProductCategoryId, eProductId);
    }

    public EProductCategoryHasEProductPK getEProductCategoryHasEProductPK() {
        return eProductCategoryHasEProductPK;
    }

    public void setEProductCategoryHasEProductPK(EProductCategoryHasEProductPK eProductCategoryHasEProductPK) {
        this.eProductCategoryHasEProductPK = eProductCategoryHasEProductPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eProductCategoryHasEProductPK != null ? eProductCategoryHasEProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EProductCategoryHasEProduct)) {
            return false;
        }
        EProductCategoryHasEProduct other = (EProductCategoryHasEProduct) object;
        if ((this.eProductCategoryHasEProductPK == null && other.eProductCategoryHasEProductPK != null) || (this.eProductCategoryHasEProductPK != null && !this.eProductCategoryHasEProductPK.equals(other.eProductCategoryHasEProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EProductCategoryHasEProduct[eProductCategoryHasEProductPK=" + eProductCategoryHasEProductPK + "]";
    }

}
