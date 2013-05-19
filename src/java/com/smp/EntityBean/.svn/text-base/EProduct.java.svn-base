/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.EntityBean;

import com.smp.ManagedBean.ProjectSiteInterface;
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
@Table(name = "e_product")
@NamedQueries({
    @NamedQuery(name = "EProduct.findAll", query = "SELECT e FROM EProduct e"),
    @NamedQuery(name = "EProduct.findById", query = "SELECT e FROM EProduct e WHERE e.id = :id ORDER BY e.id"),
    @NamedQuery(name = "EProduct.findByProduct", query = "SELECT e FROM EProduct e WHERE e.name = :name"),
    @NamedQuery(name = "EProduct.findByUnit", query = "SELECT e FROM EProduct e WHERE e.unit = :unit"),
    @NamedQuery(name = "EProduct.findByRate", query = "SELECT e FROM EProduct e WHERE e.rate = :rate"),
    @NamedQuery(name = "EProduct.findByMxId", query = "SELECT MAX(e.id) FROM EProduct e ")
})
public class EProduct implements Serializable,ProjectSiteInterface{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "product")
    private String name;
    @Column(name = "unit")
    private String unit;
    @Column(name = "rate")
    private Long rate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eProduct")
    private Collection<ETransaction> eTransactionCollection;

    public EProduct() {
    }

    public EProduct(Integer id) {
        this.id = id;
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


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
    

    public Collection<ETransaction> getETransactionCollection() {
        return eTransactionCollection;
    }

    public void setETransactionCollection(Collection<ETransaction> eTransactionCollection) {
        this.eTransactionCollection = eTransactionCollection;
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
        if (!(object instanceof EProduct)) {
            return false;
        }
        EProduct other = (EProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EProduct[id=" + id + "]";
    }

}
