/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.EntityBean;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_employee")
@NamedQueries({
    @NamedQuery(name = "EEmployee.findAll", query = "SELECT e FROM EEmployee e"),
    @NamedQuery(name = "EEmployee.findById", query = "SELECT e FROM EEmployee e WHERE e.id = :id"),
    @NamedQuery(name = "EEmployee.findByEmpName", query = "SELECT e FROM EEmployee e WHERE e.empName = :empName"),
    @NamedQuery(name = "EEmployee.findByEmpAddress", query = "SELECT e FROM EEmployee e WHERE e.empAddress = :empAddress"),
    @NamedQuery(name = "EEmployee.findByEmpPhoneNo", query = "SELECT e FROM EEmployee e WHERE e.empPhoneNo = :empPhoneNo"),
    @NamedQuery(name = "EEmployee.findByEmpMobileNo", query = "SELECT e FROM EEmployee e WHERE e.empMobileNo = :empMobileNo"),
    @NamedQuery(name = "EEmployee.findByEmpEmailid", query = "SELECT e FROM EEmployee e WHERE e.empEmailid = :empEmailid"),
    @NamedQuery(name = "EEmployee.findByUserId", query = "SELECT e FROM EEmployee e WHERE e.userId = :userId"),
    @NamedQuery(name = "EEmployee.findByPassword", query = "SELECT e FROM EEmployee e WHERE e.password = :password"),
    @NamedQuery(name = "EEmployee.findBySalt", query = "SELECT e FROM EEmployee e WHERE e.salt = :salt"),
    @NamedQuery(name = "EEmployee.findByMaxEmpId", query = "SELECT Max(e.id) FROM EEmployee e"),
    @NamedQuery(name = "EEmployee.findByEmpId&Password", query = "SELECT e FROM EEmployee e WHERE e.userId = :userId AND e.password = :password ")
})
public class EEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "emp_name")
    private String empName;
    @Basic(optional = false)
    @Column(name = "emp_address")
    private String empAddress;
    @Column(name = "emp_phone_no")
    private String empPhoneNo;
    @Column(name = "emp_mobile_no")
    private String empMobileNo;
    @Column(name = "emp_emailid")
    private String empEmailid;
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @JoinColumn(name = "e_designation_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EDesignation eDesignation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eEmployee")
    private Collection<EIntent> eIntentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eEmployee")
    private Collection<EOrderQc> eOrderQcCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eEmployee")
    private Collection<EOrder> eOrderCollection;

    public EEmployee() {
    }

    public EEmployee(Integer id) {
        this.id = id;
    }

    public EEmployee(Integer id, String empName, String empAddress, String userId, String password) {
        this.id = id;
        this.empName = empName;
        this.empAddress = empAddress;
        this.userId = userId;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpPhoneNo() {
        return empPhoneNo;
    }

    public void setEmpPhoneNo(String empPhoneNo) {
        this.empPhoneNo = empPhoneNo;
    }

    public String getEmpMobileNo() {
        return empMobileNo;
    }

    public void setEmpMobileNo(String empMobileNo) {
        this.empMobileNo = empMobileNo;
    }

    public String getEmpEmailid() {
        return empEmailid;
    }

    public void setEmpEmailid(String empEmailid) {
        this.empEmailid = empEmailid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public EDesignation getEDesignation() {
        return eDesignation;
    }

    public void setEDesignation(EDesignation eDesignation) {
        this.eDesignation = eDesignation;
    }

    public Collection<EIntent> getEIntentCollection() {
        return eIntentCollection;
    }

    public void setEIntentCollection(Collection<EIntent> eIntentCollection) {
        this.eIntentCollection = eIntentCollection;
    }

    public Collection<EOrderQc> getEOrderQcCollection() {
        return eOrderQcCollection;
    }

    public void setEOrderQcCollection(Collection<EOrderQc> eOrderQcCollection) {
        this.eOrderQcCollection = eOrderQcCollection;
    }

    public Collection<EOrder> getEOrderCollection() {
        return eOrderCollection;
    }

    public void setEOrderCollection(Collection<EOrder> eOrderCollection) {
        this.eOrderCollection = eOrderCollection;
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
        if (!(object instanceof EEmployee)) {
            return false;
        }
        EEmployee other = (EEmployee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EEmployee[id=" + id + "]";
    }

}