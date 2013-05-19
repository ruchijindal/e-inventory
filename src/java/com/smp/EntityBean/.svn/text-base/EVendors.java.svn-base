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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_vendors")
@NamedQueries({
    @NamedQuery(name = "EVendors.findByMaxId", query = "SELECT MAX(e.id) FROM EVendors e"),
    @NamedQuery(name = "EVendors.findAll", query = "SELECT e FROM EVendors e"),
    @NamedQuery(name = "EVendors.findById", query = "SELECT e FROM EVendors e WHERE e.id = :id"),
    @NamedQuery(name = "EVendors.findByVenName", query = "SELECT e FROM EVendors e WHERE e.venName = :venName"),
    @NamedQuery(name = "EVendors.findByContactPerson", query = "SELECT e FROM EVendors e WHERE e.contactPerson = :contactPerson"),
    @NamedQuery(name = "EVendors.findByVenAddress", query = "SELECT e FROM EVendors e WHERE e.venAddress = :venAddress"),
    @NamedQuery(name = "EVendors.findByVenPhNo", query = "SELECT e FROM EVendors e WHERE e.venPhNo = :venPhNo"),
    @NamedQuery(name = "EVendors.findByVenMobNo", query = "SELECT e FROM EVendors e WHERE e.venMobNo = :venMobNo"),
    @NamedQuery(name = "EVendors.findByVenEmail", query = "SELECT e FROM EVendors e WHERE e.venEmail = :venEmail"),
    @NamedQuery(name = "EVendors.findByRemark", query = "SELECT e FROM EVendors e WHERE e.remark = :remark")})
public class EVendors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ven_name")
    private String venName;
    @Column(name = "contact_person")
    private String contactPerson;
    @Basic(optional = false)
    @Column(name = "ven_address")
    private String venAddress;
    @Column(name = "ven_ph_no")
    private String venPhNo;
    @Column(name = "ven_mob_no")
    private String venMobNo;
    @Column(name = "ven_email")
    private String venEmail;
    @Column(name = "remark")
    private String remark;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eVendors")
    private Collection<EQuotation> eQuotationCollection;

    public EVendors() {
    }

    public EVendors(Integer id) {
        this.id = id;
    }

    public EVendors(Integer id, String venName, String venAddress) {
        this.id = id;
        this.venName = venName;
        this.venAddress = venAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getVenAddress() {
        return venAddress;
    }

    public void setVenAddress(String venAddress) {
        this.venAddress = venAddress;
    }

    public String getVenPhNo() {
        return venPhNo;
    }

    public void setVenPhNo(String venPhNo) {
        this.venPhNo = venPhNo;
    }

    public String getVenMobNo() {
        return venMobNo;
    }

    public void setVenMobNo(String venMobNo) {
        this.venMobNo = venMobNo;
    }

    public String getVenEmail() {
        return venEmail;
    }

    public void setVenEmail(String venEmail) {
        this.venEmail = venEmail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (!(object instanceof EVendors)) {
            return false;
        }
        EVendors other = (EVendors) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EVendors[id=" + id + "]";
    }
}