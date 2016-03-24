/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.merrimansa.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToOne;
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
@Table(name = "HAZARD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hazard.findAll", query = "SELECT h FROM Hazard h"),
    @NamedQuery(name = "Hazard.findByHazardId", query = "SELECT h FROM Hazard h WHERE h.hazardId = :hazardId"),
    @NamedQuery(name = "Hazard.findByCategory", query = "SELECT h FROM Hazard h WHERE h.category = :category"),
    @NamedQuery(name = "Hazard.findBySubCategory", query = "SELECT h FROM Hazard h WHERE h.subCategory = :subCategory"),
    @NamedQuery(name = "Hazard.findByHow", query = "SELECT h FROM Hazard h WHERE h.how = :how"),
    @NamedQuery(name = "Hazard.findByInjuryType", query = "SELECT h FROM Hazard h WHERE h.injuryType = :injuryType"),
    @NamedQuery(name = "Hazard.findByRoutine", query = "SELECT h FROM Hazard h WHERE h.routine = :routine")})
public class Hazard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "HazardId")
    private Integer hazardId;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Category")
    private String category;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SubCategory")
    private String subCategory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "How")
    private String how;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "InjuryType")
    private String injuryType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Routine")
    private boolean routine;
    @JoinTable(name = "PARTYHAZARD", joinColumns = {
        @JoinColumn(name = "HazardId", referencedColumnName = "HazardId")}, inverseJoinColumns = {
        @JoinColumn(name = "PartyId", referencedColumnName = "PartyId")})
    @ManyToMany
    private Collection<InjuredParty> injuredPartyCollection;
    @JoinColumn(name = "AssetId", referencedColumnName = "AssetId")
    @ManyToOne
    private Asset assetId;
    @JoinColumn(name = "AssessmentId", referencedColumnName = "AssessmentId")
    @ManyToOne(optional = false)
    private ProcessAssessment assessmentId;
    @JoinColumn(name = "ProcessStepId", referencedColumnName = "ProcessStepId")
    @ManyToOne(optional = false)
    private ProcessStep processStepId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hazardId")
    private PrecontrolAssessment precontrolAssessment;
    //private Collection<PrecontrolAssessment> precontrolAssessmentCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hazardId")
    private PostcontrolAssessment postcontrolAssessment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hazardId")
    private Collection<Image> imageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hazardId")
    private Collection<ControlMeasure> controlMeasureCollection;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "hazardId")
    //private Collection<PostcontrolAssessment> postcontrolAssessmentCollection;

    public Hazard() {
    }

    public Hazard(Integer hazardId) {
        this.hazardId = hazardId;
    }

    public Hazard(Integer hazardId, String category, String subCategory, String how, String injuryType, boolean routine) {
        this.hazardId = hazardId;
        this.category = category;
        this.subCategory = subCategory;
        this.how = how;
        this.injuryType = injuryType;
        this.routine = routine;
    }

    public Integer getHazardId() {
        return hazardId;
    }

    public void setHazardId(Integer hazardId) {
        this.hazardId = hazardId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }

    public boolean getRoutine() {
        return routine;
    }

    public void setRoutine(boolean routine) {
        this.routine = routine;
    }

    @XmlTransient
    public Collection<InjuredParty> getInjuredPartyCollection() {
        return injuredPartyCollection;
    }
    @XmlTransient
    public List<InjuredParty> getInjuredPartyList(){
        return (List<InjuredParty>)this.getInjuredPartyCollection();
    }

    public void setInjuredPartyCollection(Collection<InjuredParty> injuredPartyCollection) {
        this.injuredPartyCollection = injuredPartyCollection;
    }

    public Asset getAssetId() {
        return assetId;
    }

    public void setAssetId(Asset assetId) {
        this.assetId = assetId;
    }

    public ProcessAssessment getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(ProcessAssessment assessmentId) {
        this.assessmentId = assessmentId;
    }

    public ProcessStep getProcessStepId() {
        return processStepId;
    }

    public void setProcessStepId(ProcessStep processStepId) {
        this.processStepId = processStepId;
    }

    public PrecontrolAssessment getPrecontrolAssessment() {
        return precontrolAssessment;
    }

    public void setPrecontrolAssessment(PrecontrolAssessment precontrolAssessment) {
        this.precontrolAssessment = precontrolAssessment;
    }

    public PostcontrolAssessment getPostcontrolAssessment() {
        return postcontrolAssessment;
    }

    public void setPostcontrolAssessment(PostcontrolAssessment postcontrolAssessment) {
        this.postcontrolAssessment = postcontrolAssessment;
    }
    /**
    @XmlTransient
    public Collection<PrecontrolAssessment> getPrecontrolAssessmentCollection() {
        return precontrolAssessmentCollection;
    }

    public void setPrecontrolAssessmentCollection(Collection<PrecontrolAssessment> precontrolAssessmentCollection) {
        this.precontrolAssessmentCollection = precontrolAssessmentCollection;
    }
    */
    
    

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    @XmlTransient
    public Collection<ControlMeasure> getControlMeasureCollection() {
        return controlMeasureCollection;
    }

    public void setControlMeasureCollection(Collection<ControlMeasure> controlMeasureCollection) {
        this.controlMeasureCollection = controlMeasureCollection;
    }
    
    
    /**

    @XmlTransient
    public Collection<PostcontrolAssessment> getPostcontrolAssessmentCollection() {
        return postcontrolAssessmentCollection;
    }

    public void setPostcontrolAssessmentCollection(Collection<PostcontrolAssessment> postcontrolAssessmentCollection) {
        this.postcontrolAssessmentCollection = postcontrolAssessmentCollection;
    }
    **/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hazardId != null ? hazardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hazard)) {
            return false;
        }
        Hazard other = (Hazard) object;
        if ((this.hazardId == null && other.hazardId != null) || (this.hazardId != null && !this.hazardId.equals(other.hazardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.Hazard[ hazardId=" + hazardId + " ]";
    }
    
}
