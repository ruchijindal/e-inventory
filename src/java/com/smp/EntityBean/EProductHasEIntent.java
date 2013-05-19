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
@Table(name = "e_product_has_e_intent")
@NamedQueries({
    @NamedQuery(name = "EProductHasEIntent.findAll", query = "SELECT e FROM EProductHasEIntent e"),
    @NamedQuery(name = "EProductHasEIntent.findByEProductId", query = "SELECT e FROM EProductHasEIntent e WHERE e.eProductHasEIntentPK.eProductId = :eProductId"),
    @NamedQuery(name = "EProductHasEIntent.findByEIntentId", query = "SELECT e FROM EProductHasEIntent e WHERE e.eProductHasEIntentPK.eIntentId = :eIntentId")})
public class EProductHasEIntent implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EProductHasEIntentPK eProductHasEIntentPK;

    public EProductHasEIntent() {
    }

    public EProductHasEIntent(EProductHasEIntentPK eProductHasEIntentPK) {
        this.eProductHasEIntentPK = eProductHasEIntentPK;
    }

    public EProductHasEIntent(int eProductId, int eIntentId) {
        this.eProductHasEIntentPK = new EProductHasEIntentPK(eProductId, eIntentId);
    }

    public EProductHasEIntentPK getEProductHasEIntentPK() {
        return eProductHasEIntentPK;
    }

    public void setEProductHasEIntentPK(EProductHasEIntentPK eProductHasEIntentPK) {
        this.eProductHasEIntentPK = eProductHasEIntentPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eProductHasEIntentPK != null ? eProductHasEIntentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EProductHasEIntent)) {
            return false;
        }
        EProductHasEIntent other = (EProductHasEIntent) object;
        if ((this.eProductHasEIntentPK == null && other.eProductHasEIntentPK != null) || (this.eProductHasEIntentPK != null && !this.eProductHasEIntentPK.equals(other.eProductHasEIntentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EProductHasEIntent[eProductHasEIntentPK=" + eProductHasEIntentPK + "]";
    }

}
