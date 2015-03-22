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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steve
 */
@Entity
@Table(name = "IMAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findByImageId", query = "SELECT i FROM Image i WHERE i.imageId = :imageId"),
    @NamedQuery(name = "Image.findByImageName", query = "SELECT i FROM Image i WHERE i.imageName = :imageName"),
    @NamedQuery(name = "Image.findByImageData", query = "SELECT i FROM Image i WHERE i.imageData = :imageData")})
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ImageId")
    private Integer imageId;
    @Size(max = 50)
    @Column(name = "ImageName")
    private String imageName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ImageData")
    private String imageData;
    @JoinColumn(name = "HazardId", referencedColumnName = "HazardId")
    @ManyToOne(optional = false)
    private Hazard hazardId;

    public Image() {
    }

    public Image(Integer imageId) {
        this.imageId = imageId;
    }

    public Image(Integer imageId, String imageData) {
        this.imageId = imageId;
        this.imageData = imageData;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
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
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.Image[ imageId=" + imageId + " ]";
    }
    
}
