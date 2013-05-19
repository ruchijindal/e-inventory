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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_order_qc")
@NamedQueries({
    @NamedQuery(name = "EOrderQc.findByOrder", query = "SELECT e FROM  EOrderQc  e WHERE e.eOrder=:eOrder"),
    @NamedQuery(name = "EOrderQc.findByMaxId", query = "SELECT MAX(e.id) FROM EOrderQc  e"),
    @NamedQuery(name = "EOrderQc.findAll", query = "SELECT e FROM EOrderQc e"),
    @NamedQuery(name = "EOrderQc.findById", query = "SELECT e FROM EOrderQc e WHERE e.id = :id"),
    @NamedQuery(name = "EOrderQc.findByDate", query = "SELECT e FROM EOrderQc e WHERE e.date = :date"),
    @NamedQuery(name = "EOrderQc.findByQcQuantityAccepted", query = "SELECT e FROM EOrderQc e WHERE e.qcQuantityAccepted = :qcQuantityAccepted"),
    @NamedQuery(name = "EOrderQc.findByQcQuantityReject", query = "SELECT e FROM EOrderQc e WHERE e.qcQuantityReject = :qcQuantityReject")})
public class EOrderQc implements Serializable {

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
    @Column(name = "qc_quantity_accepted")
    private Long qcQuantityAccepted;
    @Column(name = "qc_quantity_reject")
    private Long qcQuantityReject;
    @JoinColumn(name = "e_employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EEmployee eEmployee;
    @JoinColumn(name = "e_order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EOrder eOrder;

    public EOrderQc() {
    }

    public EOrderQc(Integer id) {
        this.id = id;
    }

    public EOrderQc(Integer id, Date date) {
        this.id = id;
        this.date = date;
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

    public Long getQcQuantityAccepted() {
        return qcQuantityAccepted;
    }

    public void setQcQuantityAccepted(Long qcQuantityAccepted) {
        this.qcQuantityAccepted = qcQuantityAccepted;
    }

    public Long getQcQuantityReject() {
        return qcQuantityReject;
    }

    public void setQcQuantityReject(Long qcQuantityReject) {
        this.qcQuantityReject = qcQuantityReject;
    }

    public EEmployee getEEmployee() {
        return eEmployee;
    }

    public void setEEmployee(EEmployee eEmployee) {
        this.eEmployee = eEmployee;
    }

    public EOrder getEOrder() {
        return eOrder;
    }

    public void setEOrder(EOrder eOrder) {
        this.eOrder = eOrder;
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
        if (!(object instanceof EOrderQc)) {
            return false;
        }
        EOrderQc other = (EOrderQc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EOrderQc[id=" + id + "]";
    }
}
