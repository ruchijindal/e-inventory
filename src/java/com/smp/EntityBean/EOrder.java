/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.EntityBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_order")
@NamedQueries({
    @NamedQuery(name = "EOrder.findByType&Status", query = "SELECT e FROM EOrder e WHERE e.orderType = :orderType AND e.status = :status"),
    @NamedQuery(name = "EOrder.findByIntent", query = "SELECT e FROM EOrder e WHERE e.eIntent = :eIntent"),
    @NamedQuery(name = "EOrder.findByMaxId", query = "SELECT MAX(e.id) FROM EOrder  e"),
    @NamedQuery(name = "EOrder.findAll", query = "SELECT e FROM EOrder e"),
    @NamedQuery(name = "EOrder.findById", query = "SELECT e FROM EOrder e WHERE e.id = :id"),
    @NamedQuery(name = "EOrder.findByDate", query = "SELECT e FROM EOrder e WHERE e.date = :date"),
    @NamedQuery(name = "EOrder.findByOrderType", query = "SELECT e FROM EOrder e WHERE e.orderType = :orderType"),
    @NamedQuery(name = "EOrder.findByRemark", query = "SELECT e FROM EOrder e WHERE e.remark = :remark"),
    @NamedQuery(name = "EOrder.findByStatus", query = "SELECT e FROM EOrder e WHERE e.status = :status"),
    @NamedQuery(name = "EOrder.findByFromSite", query = "SELECT e FROM EOrder e WHERE e.fromSite = :fromSite"),
    @NamedQuery(name = "EOrder.findByToSite", query = "SELECT e FROM EOrder e WHERE e.toSite = :toSite"),
    @NamedQuery(name = "EOrder.findByProdQuantity", query = "SELECT e FROM EOrder e WHERE e.prodQuantity = :prodQuantity"),
    @NamedQuery(name = "EOrder.findBySelectQuotationId", query = "SELECT e FROM EOrder e WHERE e.selectQuotationId = :selectQuotationId")})
public class EOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "order_type")
    private String orderType;
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Column(name = "from_site")
    private Integer fromSite;
    @Column(name = "to_site")
    private Integer toSite;
    @Column(name = "prod_quantity")
    private Long prodQuantity;
    @Column(name = "select_quotation_id")
    private Integer selectQuotationId;
    @JoinColumn(name = "e_employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EEmployee eEmployee;
    @JoinColumn(name = "e_intent_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EIntent eIntent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eOrder")
    private Collection<EQuotation> eQuotationCollection;

    public EOrder() {
    }

    public EOrder(Integer id) {
        this.id = id;
    }

    public EOrder(Integer id, Date date, String orderType, String status) {
        this.id = id;
        this.date = date;
        this.orderType = orderType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFromSite() {
        return fromSite;
    }

    public void setFromSite(Integer fromSite) {
        this.fromSite = fromSite;
    }

    public Integer getToSite() {
        return toSite;
    }

    public void setToSite(Integer toSite) {
        this.toSite = toSite;
    }

    public Long getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Long prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public Integer getSelectQuotationId() {
        return selectQuotationId;
    }

    public void setSelectQuotationId(Integer selectQuotationId) {
        this.selectQuotationId = selectQuotationId;
    }

    public EEmployee getEEmployee() {
        return eEmployee;
    }

    public void setEEmployee(EEmployee eEmployee) {
        this.eEmployee = eEmployee;
    }

    public EIntent getEIntent() {
        return eIntent;
    }

    public void setEIntent(EIntent eIntent) {
        this.eIntent = eIntent;
    }

    public Collection<EQuotation> getEQuotationCollection() {
        return eQuotationCollection;
    }

    public void setEQuotationCollection(Collection<EQuotation> eQuotationCollection) {
        this.eQuotationCollection = eQuotationCollection;
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
        if (!(object instanceof EOrder)) {
            return false;
        }
        EOrder other = (EOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EOrder[id=" + id + "]";
    }

}
