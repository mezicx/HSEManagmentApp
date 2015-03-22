/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Steve
 */
@Entity
@Table(name = "ASSET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a"),
    @NamedQuery(name = "Asset.findByAssetId", query = "SELECT a FROM Asset a WHERE a.assetId = :assetId"),
    @NamedQuery(name = "Asset.findByAssetName", query = "SELECT a FROM Asset a WHERE a.assetName = :assetName")})
public class Asset implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AssetId")
    private Integer assetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AssetName")
    private String assetName;
    @OneToMany(mappedBy = "assetId")
    private Collection<Hazard> hazardCollection;
    @JoinColumn(name = "DepartmentId", referencedColumnName = "DepartmentId")
    @ManyToOne
    private Department departmentId;

    public Asset() {
    }

    public Asset(Integer assetId) {
        this.assetId = assetId;
    }

    public Asset(Integer assetId, String assetName) {
        this.assetId = assetId;
        this.assetName = assetName;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @XmlTransient
    public Collection<Hazard> getHazardCollection() {
        return hazardCollection;
    }

    public void setHazardCollection(Collection<Hazard> hazardCollection) {
        this.hazardCollection = hazardCollection;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetId != null ? assetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.assetId == null && other.assetId != null) || (this.assetId != null && !this.assetId.equals(other.assetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.Asset[ assetId=" + assetId + " ]";
    }
    
}
