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
public class EProductHasEIntentPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "e_product_id")
    private int eProductId;
    @Basic(optional = false)
    @Column(name = "e_intent_id")
    private int eIntentId;

    public EProductHasEIntentPK() {
    }

    public EProductHasEIntentPK(int eProductId, int eIntentId) {
        this.eProductId = eProductId;
        this.eIntentId = eIntentId;
    }

    public int getEProductId() {
        return eProductId;
    }

    public void setEProductId(int eProductId) {
        this.eProductId = eProductId;
    }

    public int getEIntentId() {
        return eIntentId;
    }

    public void setEIntentId(int eIntentId) {
        this.eIntentId = eIntentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) eProductId;
        hash += (int) eIntentId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EProductHasEIntentPK)) {
            return false;
        }
        EProductHasEIntentPK other = (EProductHasEIntentPK) object;
        if (this.eProductId != other.eProductId) {
            return false;
        }
        if (this.eIntentId != other.eIntentId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EProductHasEIntentPK[eProductId=" + eProductId + ", eIntentId=" + eIntentId + "]";
    }

}
