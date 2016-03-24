/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Steve
 */
@Entity
@Table(name = "PROCESS ASSESSMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcessAssessment.findAll", query = "SELECT p FROM ProcessAssessment p"),
    @NamedQuery(name = "ProcessAssessment.findByAssessmentId", query = "SELECT p FROM ProcessAssessment p WHERE p.assessmentId = :assessmentId"),
    @NamedQuery(name = "ProcessAssessment.findByTitle", query = "SELECT p FROM ProcessAssessment p WHERE p.title = :title"),
    @NamedQuery(name = "ProcessAssessment.findByReferenceNumber", query = "SELECT p FROM ProcessAssessment p WHERE p.referenceNumber = :referenceNumber"),
    @NamedQuery(name = "ProcessAssessment.findByAssessmentDate", query = "SELECT p FROM ProcessAssessment p WHERE p.assessmentDate = :assessmentDate"),
    @NamedQuery(name = "ProcessAssessment.findByPreamble", query = "SELECT p FROM ProcessAssessment p WHERE p.preamble = :preamble"),
    @NamedQuery(name = "ProcessAssessment.findByApproved", query = "SELECT p FROM ProcessAssessment p WHERE p.approved = :approved"),
    @NamedQuery(name = "ProcessAssessment.findBySubmitted", query = "SELECT p FROM ProcessAssessment p WHERE p.submitted = :submitted")})
public class ProcessAssessment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "AssessmentId")
    private Integer assessmentId;
    @Size(max = 100)
    @Column(name = "Title")
    private String title;
    @Size(max = 20)
    @Column(name = "ReferenceNumber")
    private String referenceNumber;
    @Column(name = "AssessmentDate")
    @Temporal(TemporalType.DATE)
    private Date assessmentDate;
    @Size(max = 500)
    @Column(name = "Preamble")
    private String preamble;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Approved")
    private boolean approved;
    @Column(name = "Submitted")
    private Boolean submitted;
    @JoinTable(name = "ASSESSMENT TEAM MEMBER", joinColumns = {
        @JoinColumn(name = "AssessmentId", referencedColumnName = "AssessmentId")}, inverseJoinColumns = {
        @JoinColumn(name = "UserId", referencedColumnName = "UserId")})
    @ManyToMany
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assessmentId")
    private Collection<Hazard> hazardCollection;
    @JoinColumn(name = "ProcessId", referencedColumnName = "ProcessId")
    @ManyToOne(optional = false)
    private Process processId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assessmentId")
    private Collection<Note> noteCollection;

    public ProcessAssessment() {
    }

    public ProcessAssessment(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public ProcessAssessment(Integer assessmentId, boolean approved) {
        this.assessmentId = assessmentId;
        this.approved = approved;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Date getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getPreamble() {
        return preamble;
    }

    public void setPreamble(String preamble) {
        this.preamble = preamble;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }
    
    @XmlTransient
    public List<User> getUserCollectionList(){
        return (List)getUserCollection();
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public List<Hazard> getHazardList() {
        return (List)hazardCollection;
    }
    
    @XmlTransient
    public Collection<Hazard> getHazardCollection() {
        return hazardCollection;
    }

    public void setHazardCollection(Collection<Hazard> hazardCollection) {
        this.hazardCollection = hazardCollection;
    }

    public Process getProcessId() {
        return processId;
    }

    public void setProcessId(Process processId) {
        this.processId = processId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assessmentId != null ? assessmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcessAssessment)) {
            return false;
        }
        ProcessAssessment other = (ProcessAssessment) object;
        if ((this.assessmentId == null && other.assessmentId != null) || (this.assessmentId != null && !this.assessmentId.equals(other.assessmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.ProcessAssessment[ assessmentId=" + assessmentId + " ]";
    }
    
}
