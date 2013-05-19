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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smp
 */
@Entity
@Table(name = "e_sites")
@NamedQueries({
    @NamedQuery(name = "ESites.findById&Name", query = "SELECT e FROM ESites e WHERE e.id = :id and e.name = :name"),
    @NamedQuery(name = "ESites.findAll", query = "SELECT e FROM ESites e"),
    @NamedQuery(name = "ESites.findById", query = "SELECT e FROM ESites e WHERE e.id = :id"),
    @NamedQuery(name = "ESites.findBySiteName", query = "SELECT e FROM ESites e WHERE e.name = :name"),
    @NamedQuery(name = "ESites.findBySiteLocation", query = "SELECT e FROM ESites e WHERE e.siteLocation = :siteLocation"),
    @NamedQuery(name = "ESites.findBySiteDescription", query = "SELECT e FROM ESites e WHERE e.siteDescription = :siteDescription"),
    @NamedQuery(name = "ESites.findBySiteStockLevelXml", query = "SELECT e FROM ESites e WHERE e.siteStockLevelXml = :siteStockLevelXml")})
public class ESites implements Serializable, ProjectSiteInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "site_name")
    private String name;
    @Basic(optional = false)
    @Column(name = "site_location")
    private String siteLocation;
    @Column(name = "site_description")
    private String siteDescription;
    @Column(name = "site_stock_level_xml")
    private String siteStockLevelXml;
    @JoinColumn(name = "e_project_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EProject eProject;

    public ESites() {
    }

    public ESites(Integer id) {
        this.id = id;
    }

    public ESites(Integer id, String siteName, String siteLocation) {
        this.id = id;
        this.name = siteName;
        this.siteLocation = siteLocation;
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

    public String getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(String siteLocation) {
        this.siteLocation = siteLocation;
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    public String getSiteStockLevelXml() {
        return siteStockLevelXml;
    }

    public void setSiteStockLevelXml(String siteStockLevelXml) {
        this.siteStockLevelXml = siteStockLevelXml;
    }

    public EProject getEProject() {
        return eProject;
    }

    public void setEProject(EProject eProject) {
        this.eProject = eProject;
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
        if (!(object instanceof ESites)) {
            return false;
        }
        ESites other = (ESites) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.ESites[id=" + id + "]";
    }
}