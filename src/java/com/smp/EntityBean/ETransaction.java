/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.EntityBean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_transaction")
@NamedQueries({
    @NamedQuery(name = "ETransaction.findByOrder&Site", query = "SELECT e FROM ETransaction e where e.eOrder = :eOrder AND e.eSites = :eSites"),
    @NamedQuery(name = "ETransaction.findAll", query = "SELECT e FROM ETransaction e"),
    @NamedQuery(name = "ETransaction.findById", query = "SELECT e FROM ETransaction e WHERE e.id = :id"),
    @NamedQuery(name = "ETransaction.findByReasonOfUpdate", query = "SELECT e FROM ETransaction e WHERE e.reasonOfUpdate = :reasonOfUpdate"),
    @NamedQuery(name = "ETransaction.findByDateOfUpdate", query = "SELECT e FROM ETransaction e WHERE e.dateOfUpdate = :dateOfUpdate"),
    @NamedQuery(name = "ETransaction.findByStockDelta", query = "SELECT e FROM ETransaction e WHERE e.stockDelta = :stockDelta")})
public class ETransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "reason_of_update")
    private String reasonOfUpdate;
    @Basic(optional = false)
    @Column(name = "date_of_update")
    @Temporal(TemporalType.DATE)
    private Date dateOfUpdate;
    @Column(name = "stock_delta")
    private Long stockDelta;
    @JoinColumn(name = "e_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EOrder eOrder;
    @JoinColumn(name = "e_product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EProduct eProduct;
    @JoinColumn(name = "e_sites_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ESites eSites;

    public ETransaction() {
    }

    public ETransaction(Integer id) {
        this.id = id;
    }

    public ETransaction(Integer id, String reasonOfUpdate, Date dateOfUpdate) {
        this.id = id;
        this.reasonOfUpdate = reasonOfUpdate;
        this.dateOfUpdate = dateOfUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReasonOfUpdate() {
        return reasonOfUpdate;
    }

    public void setReasonOfUpdate(String reasonOfUpdate) {
        this.reasonOfUpdate = reasonOfUpdate;
    }

    public Date getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(Date dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public Long getStockDelta() {
        return stockDelta;
    }

    public void setStockDelta(Long stockDelta) {
        this.stockDelta = stockDelta;
    }

    public EOrder getEOrder() {
        return eOrder;
    }

    public void setEOrder(EOrder eOrder) {
        this.eOrder = eOrder;
    }

    public EProduct getEProduct() {
        return eProduct;
    }

    public void setEProduct(EProduct eProduct) {
        this.eProduct = eProduct;
    }

    public ESites getESites() {
        return eSites;
    }

    public void setESites(ESites eSites) {
        this.eSites = eSites;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ETransaction)) {
            return false;
        }
        ETransaction other = (ETransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.ETransaction[id=" + id + "]";
    }
}
