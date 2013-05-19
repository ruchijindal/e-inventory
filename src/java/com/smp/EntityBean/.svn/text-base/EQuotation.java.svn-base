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
@Table(name = "e_quotation")
@NamedQueries({
    @NamedQuery(name = "EQuotation.findByVendor", query = "SELECT e FROM EQuotation e WHERE e.eVendors = :eVendors"),
    @NamedQuery(name = "EQuotation.findAll", query = "SELECT e FROM EQuotation e"),
    @NamedQuery(name = "EQuotation.findById", query = "SELECT e FROM EQuotation e WHERE e.id = :id"),
    @NamedQuery(name = "EQuotation.findByUnitPrice", query = "SELECT e FROM EQuotation e WHERE e.unitPrice = :unitPrice"),
    @NamedQuery(name = "EQuotation.findByDeliverQuantity", query = "SELECT e FROM EQuotation e WHERE e.deliverQuantity = :deliverQuantity"),
    @NamedQuery(name = "EQuotation.findByDateOfDelivery", query = "SELECT e FROM EQuotation e WHERE e.dateOfDelivery = :dateOfDelivery"),
    @NamedQuery(name = "EQuotation.findByReceivedDate", query = "SELECT e FROM EQuotation e WHERE e.receivedDate = :receivedDate")})
public class EQuotation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "unit_price")
    private Long unitPrice;
    @Column(name = "deliver_quantity")
    private Long deliverQuantity;
    @Column(name = "date_of_delivery")
    @Temporal(TemporalType.DATE)
    private Date dateOfDelivery;
    @Column(name = "received_date")
    @Temporal(TemporalType.DATE)
    private Date receivedDate;
    @JoinColumn(name = "e_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EOrder eOrder;
    @JoinColumn(name = "e_vendors_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EVendors eVendors;

    public EQuotation() {
    }

    public EQuotation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getDeliverQuantity() {
        return deliverQuantity;
    }

    public void setDeliverQuantity(Long deliverQuantity) {
        this.deliverQuantity = deliverQuantity;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public EOrder getEOrder() {
        return eOrder;
    }

    public void setEOrder(EOrder eOrder) {
        this.eOrder = eOrder;
    }

    public EVendors getEVendors() {
        return eVendors;
    }

    public void setEVendors(EVendors eVendors) {
        this.eVendors = eVendors;
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
        if (!(object instanceof EQuotation)) {
            return false;
        }
        EQuotation other = (EQuotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EQuotation[id=" + id + "]";
    }

}
