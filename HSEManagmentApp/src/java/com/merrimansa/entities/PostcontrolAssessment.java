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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "POSTCONTROL ASSESSMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostcontrolAssessment.findAll", query = "SELECT p FROM PostcontrolAssessment p"),
    @NamedQuery(name = "PostcontrolAssessment.findByAssessmentId", query = "SELECT p FROM PostcontrolAssessment p WHERE p.assessmentId = :assessmentId"),
    @NamedQuery(name = "PostcontrolAssessment.findByConsequence", query = "SELECT p FROM PostcontrolAssessment p WHERE p.consequence = :consequence"),
    @NamedQuery(name = "PostcontrolAssessment.findByLikelihood", query = "SELECT p FROM PostcontrolAssessment p WHERE p.likelihood = :likelihood")})
public class PostcontrolAssessment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "AssessmentId")
    private Integer assessmentId;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "Consequence")
    private int consequence;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "Likelihood")
    private int likelihood;
    @JoinColumn(name = "HazardId", referencedColumnName = "HazardId")
    @OneToOne(optional = false)
    private Hazard hazardId;

    public PostcontrolAssessment() {
    }

    public PostcontrolAssessment(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public PostcontrolAssessment(Integer assessmentId, int consequence, int likelihood) {
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
        if (!(object instanceof PostcontrolAssessment)) {
            return false;
        }
        PostcontrolAssessment other = (PostcontrolAssessment) object;
        if ((this.assessmentId == null && other.assessmentId != null) || (this.assessmentId != null && !this.assessmentId.equals(other.assessmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.PostcontrolAssessment[ assessmentId=" + assessmentId + " ]";
    }
    
}
