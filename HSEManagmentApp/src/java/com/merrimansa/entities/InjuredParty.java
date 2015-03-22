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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "INJURED PARTY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InjuredParty.findAll", query = "SELECT i FROM InjuredParty i"),
    @NamedQuery(name = "InjuredParty.findByPartyId", query = "SELECT i FROM InjuredParty i WHERE i.partyId = :partyId"),
    @NamedQuery(name = "InjuredParty.findByPartyType", query = "SELECT i FROM InjuredParty i WHERE i.partyType = :partyType")})
public class InjuredParty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PartyId")
    private Integer partyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PartyType")
    private String partyType;
    @ManyToMany(mappedBy = "injuredPartyCollection")
    private Collection<Hazard> hazardCollection;

    public InjuredParty() {
    }

    public InjuredParty(Integer partyId) {
        this.partyId = partyId;
    }

    public InjuredParty(Integer partyId, String partyType) {
        this.partyId = partyId;
        this.partyType = partyType;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    @XmlTransient
    public Collection<Hazard> getHazardCollection() {
        return hazardCollection;
    }

    public void setHazardCollection(Collection<Hazard> hazardCollection) {
        this.hazardCollection = hazardCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partyId != null ? partyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InjuredParty)) {
            return false;
        }
        InjuredParty other = (InjuredParty) object;
        if ((this.partyId == null && other.partyId != null) || (this.partyId != null && !this.partyId.equals(other.partyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.InjuredParty[ partyId=" + partyId + " ]";
    }
    
}
