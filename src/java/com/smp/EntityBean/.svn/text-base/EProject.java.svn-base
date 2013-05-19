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
@Table(name = "e_project")
@NamedQueries({
    @NamedQuery(name = "EProject.findByMaxId", query = "SELECT MAX(e.id) FROM EProject e"),
    @NamedQuery(name = "EProject.findById&Name", query = "SELECT e FROM EProject e WHERE e.id = :id and e.name = :name"),
    @NamedQuery(name = "EProject.findAll", query = "SELECT e FROM EProject e"),
    @NamedQuery(name = "EProject.findById", query = "SELECT e FROM EProject e WHERE e.id = :id"),
    @NamedQuery(name = "EProject.findByProjName", query = "SELECT e FROM EProject e WHERE e.name = :name"),
    @NamedQuery(name = "EProject.findByProjLocation", query = "SELECT e FROM EProject e WHERE e.projLocation = :projLocation"),
    @NamedQuery(name = "EProject.findByProjDescription", query = "SELECT e FROM EProject e WHERE e.projDescription = :projDescription"),
    @NamedQuery(name = "EProject.findByOrgBoqXml", query = "SELECT e FROM EProject e WHERE e.orgBoqXml = :orgBoqXml"),
    @NamedQuery(name = "EProject.findByMainBoqXml", query = "SELECT e FROM EProject e WHERE e.mainBoqXml = :mainBoqXml")})
public class EProject implements Serializable, ProjectSiteInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "proj_name")
    private String name;
    @Basic(optional = false)
    @Column(name = "proj_location")
    private String projLocation;
    @Column(name = "proj_description")
    private String projDescription;
    @Column(name = "org_boq_xml")
    private String orgBoqXml;
    @Column(name = "main_boq_xml")
    private String mainBoqXml;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eProject")
    private Collection<ESites> eSitesCollection;

    public EProject() {
    }

    public EProject(Integer id) {
        this.id = id;
    }

    public EProject(Integer id, String projName, String projLocation) {
        this.id = id;
        this.name = projName;
        this.projLocation = projLocation;
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

    public String getProjLocation() {
        return projLocation;
    }

    public void setProjLocation(String projLocation) {
        this.projLocation = projLocation;
    }

    public String getProjDescription() {
        return projDescription;
    }

    public void setProjDescription(String projDescription) {
        this.projDescription = projDescription;
    }

    public String getOrgBoqXml() {
        return orgBoqXml;
    }

    public void setOrgBoqXml(String orgBoqXml) {
        this.orgBoqXml = orgBoqXml;
    }

    public String getMainBoqXml() {
        return mainBoqXml;
    }

    public void setMainBoqXml(String mainBoqXml) {
        this.mainBoqXml = mainBoqXml;
    }

    public Collection<ESites> getESitesCollection() {
        return eSitesCollection;
    }

    public void setESitesCollection(Collection<ESites> eSitesCollection) {
        this.eSitesCollection = eSitesCollection;
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
        if (!(object instanceof EProject)) {
            return false;
        }
        EProject other = (EProject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smp.EntityBean.EProject[id=" + id + "]";
    }
}