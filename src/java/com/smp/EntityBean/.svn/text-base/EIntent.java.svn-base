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
@Table(name = "e_intent")
@NamedQueries({
    @NamedQuery(name = "EIntent.findByMaxEmpId", query = "SELECT Max(e.id) FROM EIntent e"),
    @NamedQuery(name = "EIntent.findAll", query = "SELECT e FROM EIntent e"),
    @NamedQuery(name = "EIntent.findById", query = "SELECT e FROM EIntent e WHERE e.id = :id"),
    @NamedQuery(name = "EIntent.findByDate", query = "SELECT e FROM EIntent e WHERE e.date = :date"),
    @NamedQuery(name = "EIntent.findByRemark", query = "SELECT e FROM EIntent e WHERE e.remark = :remark"),
    @NamedQuery(name = "EIntent.findByStatus", query = "SELECT e FROM EIntent e WHERE e.status = :status"),
    @NamedQuery(name = "EIntent.findByProdQuantity", query = "SELECT e FROM EIntent e WHERE e.prodQuantity = :prodQuantity"),
    @NamedQuery(name = "EIntent.findByProductCode", query = "SELECT e FROM EIntent e WHERE e.productCode = :productCode")})
public class EIntent implements Serializable {
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
    @Column(name = "remark")
    private String remark;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Column(name = "prod_quantity")
    private Long prodQuantity;
    @Basic(optional = false)
    @Column(name = "product_code")
    private String productCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eIntent")
    private Collection<EOrder> eOrderCollection;
    @JoinColumn(name = "e_employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EEmployee eEmployee;
    @JoinColumn(name = "e_sites_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ESites eSites;

    public EIntent() {
    }

    public EIntent(Integer id) {
        this.id = id;
    }

    public EIntent(Integer id, Date date, String status, String productCode) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.productCode = productCode;
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

    public Long getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Long prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Collection<EOrder> getEOrderCollection() {
        return eOrderCollection;
    }

    public void setEOrderCollection(Collection<EOrder> eOrderCollection) {
        this.eOrderCollection = eOrderCollection;
    }

    public EEmployee getEEmployee() {
        return eEmployee;
    }

    public void setEEmployee(EEmployee eEmployee) {
        this.eEmployee = eEmployee;
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
        if (!(object instanceof EIntent)) {
            return false;
        }
        EIntent other = (EIntent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EIntent[id=" + id + "]";
    }

}