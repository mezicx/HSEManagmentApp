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
@Table(name = "NOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findByNoteId", query = "SELECT n FROM Note n WHERE n.noteId = :noteId"),
    @NamedQuery(name = "Note.findByNoteText", query = "SELECT n FROM Note n WHERE n.noteText = :noteText")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoteId")
    private Integer noteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NoteText")
    private String noteText;
    @JoinColumn(name = "AssessmentId", referencedColumnName = "AssessmentId")
    @ManyToOne(optional = false)
    private ProcessAssessment assessmentId;

    public Note() {
    }

    public Note(Integer noteId) {
        this.noteId = noteId;
    }

    public Note(Integer noteId, String noteText) {
        this.noteId = noteId;
        this.noteText = noteText;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public ProcessAssessment getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(ProcessAssessment assessmentId) {
        this.assessmentId = assessmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteId != null ? noteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.noteId == null && other.noteId != null) || (this.noteId != null && !this.noteId.equals(other.noteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.Note[ noteId=" + noteId + " ]";
    }
    
}
