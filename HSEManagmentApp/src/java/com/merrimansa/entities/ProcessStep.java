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
@Table(name = "PROCESS STEP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessStep.findAll", query = "SELECT p FROM ProcessStep p"),
    @NamedQuery(name = "ProcessStep.findByProcessStepId", query = "SELECT p FROM ProcessStep p WHERE p.processStepId = :processStepId"),
    @NamedQuery(name = "ProcessStep.findByProcessStepName", query = "SELECT p FROM ProcessStep p WHERE p.processStepName = :processStepName"),
    @NamedQuery(name = "ProcessStep.findByStepDescription", query = "SELECT p FROM ProcessStep p WHERE p.stepDescription = :stepDescription")})
public class ProcessStep implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProcessStepId")
    private Integer processStepId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ProcessStepName")
    private String processStepName;
    @Size(max = 500)
    @Column(name = "StepDescription")
    private String stepDescription;
    @JoinColumn(name = "ProcessId", referencedColumnName = "ProcessId")
    @ManyToOne(optional = false)
    private Process processId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processStepId")
    private Collection<Hazard> hazardCollection;

    public ProcessStep() {
    }

    public ProcessStep(Integer processStepId) {
        this.processStepId = processStepId;
    }

    public ProcessStep(Integer processStepId, String processStepName) {
        this.processStepId = processStepId;
        this.processStepName = processStepName;
    }

    public Integer getProcessStepId() {
        return processStepId;
    }

    public void setProcessStepId(Integer processStepId) {
        this.processStepId = processStepId;
    }

    public String getProcessStepName() {
        return processStepName;
    }

    public void setProcessStepName(String processStepName) {
        this.processStepName = processStepName;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public Process getProcessId() {
        return processId;
    }

    public void setProcessId(Process processId) {
        this.processId = processId;
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
        hash += (processStepId != null ? processStepId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcessStep)) {
            return false;
        }
        ProcessStep other = (ProcessStep) object;
        if ((this.processStepId == null && other.processStepId != null) || (this.processStepId != null && !this.processStepId.equals(other.processStepId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.ProcessStep[ processStepId=" + processStepId + " ]";
    }
    
}
