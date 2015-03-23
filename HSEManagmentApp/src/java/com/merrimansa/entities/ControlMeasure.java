/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CONTROL MEASURE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlMeasure.findAll", query = "SELECT c FROM ControlMeasure c"),
    @NamedQuery(name = "ControlMeasure.findByControlId", query = "SELECT c FROM ControlMeasure c WHERE c.controlId = :controlId"),
    @NamedQuery(name = "ControlMeasure.findByControlCategory", query = "SELECT c FROM ControlMeasure c WHERE c.controlCategory = :controlCategory"),
    @NamedQuery(name = "ControlMeasure.findByControlDescription", query = "SELECT c FROM ControlMeasure c WHERE c.controlDescription = :controlDescription")})
public class ControlMeasure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ControlId")
    private Integer controlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ControlCategory")
    private String controlCategory;
    @Size(max = 500)
    @Column(name = "ControlDescription")
    private String controlDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "controlId")
    private Collection<Action> actionCollection;
    @JoinColumn(name = "HazardId", referencedColumnName = "HazardId")
    @ManyToOne(optional = false)
    private Hazard hazardId;

    public ControlMeasure() {
    }

    public ControlMeasure(Integer controlId) {
        this.controlId = controlId;
    }

    public ControlMeasure(Integer controlId, String controlCategory) {
        this.controlId = controlId;
        this.controlCategory = controlCategory;
    }

    public Integer getControlId() {
        return controlId;
    }

    public void setControlId(Integer controlId) {
        this.controlId = controlId;
    }

    public String getControlCategory() {
        return controlCategory;
    }

    public void setControlCategory(String controlCategory) {
        this.controlCategory = controlCategory;
    }

    public String getControlDescription() {
        return controlDescription;
    }

    public void setControlDescription(String controlDescription) {
        this.controlDescription = controlDescription;
    }

    @XmlTransient
    public Collection<Action> getActionCollection() {
        return actionCollection;
    }

    public void setActionCollection(Collection<Action> actionCollection) {
        this.actionCollection = actionCollection;
    }

    public Hazard getHazardId() {
        return hazardId;
    }

    public void setHazardId(Hazard hazardId) {
        this.hazardId = hazardId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlId != null ? controlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlMeasure)) {
            return false;
        }
        ControlMeasure other = (ControlMeasure) object;
        if ((this.controlId == null && other.controlId != null) || (this.controlId != null && !this.controlId.equals(other.controlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.ControlMeasure[ controlId=" + controlId + " ]";
    }
    
}
