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
public class EProductCategoryHasEProductPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "e_product_category_id")
    private int eProductCategoryId;
    @Basic(optional = false)
    @Column(name = "e_product_id")
    private int eProductId;

    public EProductCategoryHasEProductPK() {
    }

    public EProductCategoryHasEProductPK(int eProductCategoryId, int eProductId) {
        this.eProductCategoryId = eProductCategoryId;
        this.eProductId = eProductId;
    }

    public int getEProductCategoryId() {
        return eProductCategoryId;
    }

    public void setEProductCategoryId(int eProductCategoryId) {
        this.eProductCategoryId = eProductCategoryId;
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
        hash += (int) eProductCategoryId;
        hash += (int) eProductId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EProductCategoryHasEProductPK)) {
            return false;
        }
        EProductCategoryHasEProductPK other = (EProductCategoryHasEProductPK) object;
        if (this.eProductCategoryId != other.eProductCategoryId) {
            return false;
        }
        if (this.eProductId != other.eProductId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EProductCategoryHasEProductPK[eProductCategoryId=" + eProductCategoryId + ", eProductId=" + eProductId + "]";
    }

}
