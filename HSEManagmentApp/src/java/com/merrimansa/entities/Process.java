/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Table(name = "PROCESS")
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Process.findAll", query = "SELECT p FROM Process p"),
    @NamedQuery(name = "Process.findByProcessId", query = "SELECT p FROM Process p WHERE p.processId = :processId"),
    @NamedQuery(name = "Process.findByProcessName", query = "SELECT p FROM Process p WHERE p.processName = :processName"),
    @NamedQuery(name = "Process.findByProcessDecription", query = "SELECT p FROM Process p WHERE p.processDecription = :processDecription"),
    @NamedQuery(name = "Process.findByArchived", query = "SELECT p FROM Process p WHERE p.archived = :archived")})
public class Process implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProcessId")
    private Integer processId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ProcessName")
    private String processName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ProcessDecription")
    private String processDecription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Archived")
    private boolean archived;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processId")
    private Collection<ProcessStep> processStepCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processId")
    private Collection<ProcessAssessment> processAssessmentCollection;
    @JoinColumn(name = "DepartmentId", referencedColumnName = "DepartmentId")
    @ManyToOne(optional = false)
    private Department departmentId;

    public Process() {
    }

    public Process(Integer processId) {
        this.processId = processId;
    }

    public Process(Integer processId, String processName, String processDecription, boolean archived) {
        this.processId = processId;
        this.processName = processName;
        this.processDecription = processDecription;
        this.archived = archived;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessDecription() {
        return processDecription;
    }

    public void setProcessDecription(String processDecription) {
        this.processDecription = processDecription;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @XmlTransient
    public Collection<ProcessStep> getProcessStepCollection() {
        return processStepCollection;
    }

    public void setProcessStepCollection(Collection<ProcessStep> processStepCollection) {
        this.processStepCollection = processStepCollection;
    }

    @XmlTransient
    public Collection<ProcessAssessment> getProcessAssessmentCollection() {
        return processAssessmentCollection;
    }

    public void setProcessAssessmentCollection(Collection<ProcessAssessment> processAssessmentCollection) {
        this.processAssessmentCollection = processAssessmentCollection;
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
        hash += (processId != null ? processId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Process)) {
            return false;
        }
        Process other = (Process) object;
        if ((this.processId == null && other.processId != null) || (this.processId != null && !this.processId.equals(other.processId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.Process[ processId=" + processId + " ]";
    }
    
}
