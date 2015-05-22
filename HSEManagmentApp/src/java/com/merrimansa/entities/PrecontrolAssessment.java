/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steve
 */
@Entity
@Table(name = "PRECONTROL ASSESSMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrecontrolAssessment.findAll", query = "SELECT p FROM PrecontrolAssessment p"),
    @NamedQuery(name = "PrecontrolAssessment.findByAssessmentId", query = "SELECT p FROM PrecontrolAssessment p WHERE p.assessmentId = :assessmentId"),
    @NamedQuery(name = "PrecontrolAssessment.findByConsequence", query = "SELECT p FROM PrecontrolAssessment p WHERE p.consequence = :consequence"),
    @NamedQuery(name = "PrecontrolAssessment.findByLikelihood", query = "SELECT p FROM PrecontrolAssessment p WHERE p.likelihood = :likelihood")})
public class PrecontrolAssessment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AssessmentId")
    private Integer assessmentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Consequence")
    private int consequence;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Likelihood")
    private int likelihood;
    @JoinColumn(name = "HazardId", referencedColumnName = "HazardId")
    @OneToOne(optional = false)
    private Hazard hazardId;

    public PrecontrolAssessment() {
    }

    public PrecontrolAssessment(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public PrecontrolAssessment(Integer assessmentId, int consequence, int likelihood) {
        this.assessmentId = assessmentId;
        this.consequence = consequence;
        this.likelihood = likelihood;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getConsequence() {
        return consequence;
    }

    public void setConsequence(int consequence) {
        this.consequence = consequence;
    }

    public int getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(int likelihood) {
        this.likelihood = likelihood;
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
        hash += (assessmentId != null ? assessmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecontrolAssessment)) {
            return false;
        }
        PrecontrolAssessment other = (PrecontrolAssessment) object;
        if ((this.assessmentId == null && other.assessmentId != null) || (this.assessmentId != null && !this.assessmentId.equals(other.assessmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.PrecontrolAssessment[ assessmentId=" + assessmentId + " ]";
    }
    
}
