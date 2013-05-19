/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.EntityBean;

import com.smp.ManagedBean.ProjectSiteInterface;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_product_category")
@NamedQueries({
     @NamedQuery(name = "EProductCategory.findByMxId", query = "SELECT MAX(e.id) FROM EProductCategory e "),
    @NamedQuery(name = "EProductCategory.findId&Name", query = "SELECT e FROM EProductCategory e where e.id = :id AND e.name= :name "),
    @NamedQuery(name = "EProductCategory.findAll", query = "SELECT e FROM EProductCategory e"),
    @NamedQuery(name = "EProductCategory.findById", query = "SELECT e FROM EProductCategory e WHERE e.id = :id"),
    @NamedQuery(name = "EProductCategory.findByProductCategory", query = "SELECT e FROM EProductCategory e WHERE e.name= :name")})
public class EProductCategory implements Serializable, ProjectSiteInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "product_category")
    private String name;

    public EProductCategory() {
    }

    public EProductCategory(Integer id) {
        this.id = id;
    }

    public EProductCategory(Integer id, String productCategory) {
        this.id = id;
        this.name = productCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof EProductCategory)) {
            return false;
        }
        EProductCategory other = (EProductCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EProductCategory[id=" + id + "]";
    }
}
