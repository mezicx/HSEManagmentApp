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
@Table(name = "ACTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a"),
    @NamedQuery(name = "Action.findByActionId", query = "SELECT a FROM Action a WHERE a.actionId = :actionId"),
    @NamedQuery(name = "Action.findByActionDescription", query = "SELECT a FROM Action a WHERE a.actionDescription = :actionDescription"),
    @NamedQuery(name = "Action.findByControlId", query = "SELECT a FROM Action a WHERE a.controlId = :controlId")})
public class Action implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ActionId")
    private Integer actionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ActionDescription")
    private String actionDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ControlId")
    private int controlId;
    @JoinColumn(name = "UserId", referencedColumnName = "ControlId")
    @ManyToOne(optional = false)
    private ControlMeasure userId;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private User userId1;

    public Action() {
    }

    public Action(Integer actionId) {
        this.actionId = actionId;
    }

    public Action(Integer actionId, String actionDescription, int controlId) {
        this.actionId = actionId;
        this.actionDescription = actionDescription;
        this.controlId = controlId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public int getControlId() {
        return controlId;
    }

    public void setControlId(int controlId) {
        this.controlId = controlId;
    }

    public ControlMeasure getUserId() {
        return userId;
    }

    public void setUserId(ControlMeasure userId) {
        this.userId = userId;
    }

    public User getUserId1() {
        return userId1;
    }

    public void setUserId1(User userId1) {
        this.userId1 = userId1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionId != null ? actionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.actionId == null && other.actionId != null) || (this.actionId != null && !this.actionId.equals(other.actionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.merrimansa.entities.Action[ actionId=" + actionId + " ]";
    }
    
}
