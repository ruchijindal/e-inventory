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
@Table(name = "e_designation")
@NamedQueries({
    @NamedQuery(name = "EDesignation.findAll", query = "SELECT e FROM EDesignation e"),
    @NamedQuery(name = "EDesignation.findById", query = "SELECT e FROM EDesignation e WHERE e.id = :id"),
    @NamedQuery(name = "EDesignation.findByDesignation", query = "SELECT e FROM EDesignation e WHERE e.designation = :designation")})
public class EDesignation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "designation")
    private String designation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eDesignation")
    private Collection<EEmployee> eEmployeeCollection;

    public EDesignation() {
    }

    public EDesignation(Integer id) {
        this.id = id;
    }

    public EDesignation(Integer id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Collection<EEmployee> getEEmployeeCollection() {
        return eEmployeeCollection;
    }

    public void setEEmployeeCollection(Collection<EEmployee> eEmployeeCollection) {
        this.eEmployeeCollection = eEmployeeCollection;
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
        if (!(object instanceof EDesignation)) {
            return false;
        }
        EDesignation other = (EDesignation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EDesignation[id=" + id + "]";
    }

}
