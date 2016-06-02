/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.persistence.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author oncore
 */
@Entity
@Table(name = "CHILDREN")
@NamedQueries({
    @NamedQuery(name = "Children.findAll", query = "SELECT c FROM Children c")})
public class Children implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLD_UID")
    private Integer cldUid;
    @Size(max = 45)
    @Column(name = "CLD_FIRSTNAME")
    private String cldFirstname;
    @Size(max = 45)
    @Column(name = "CLD_MIDDLENAME")
    private String cldMiddlename;
    @Size(max = 45)
    @Column(name = "CLD_LASTNAME")
    private String cldLastname;
    @Column(name = "CLD_AGE")
    private Integer cldAge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATE_TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTs;
    @JoinColumn(name = "USR_UID_FK", referencedColumnName = "USR_UID")
    @ManyToOne(optional = false)
    private Users usrUidFk;

    public Children() {
    }

    public Children(Integer cldUid) {
        this.cldUid = cldUid;
    }

    public Children(Integer cldUid, String createUserId, Date createTs, String updateUserId, Date updateTs) {
        this.cldUid = cldUid;
        this.createUserId = createUserId;
        this.createTs = createTs;
        this.updateUserId = updateUserId;
        this.updateTs = updateTs;
    }

    public Integer getCldUid() {
        return cldUid;
    }

    public void setCldUid(Integer cldUid) {
        this.cldUid = cldUid;
    }

    public String getCldFirstname() {
        return cldFirstname;
    }

    public void setCldFirstname(String cldFirstname) {
        this.cldFirstname = cldFirstname;
    }

    public String getCldMiddlename() {
        return cldMiddlename;
    }

    public void setCldMiddlename(String cldMiddlename) {
        this.cldMiddlename = cldMiddlename;
    }

    public String getCldLastname() {
        return cldLastname;
    }

    public void setCldLastname(String cldLastname) {
        this.cldLastname = cldLastname;
    }

    public Integer getCldAge() {
        return cldAge;
    }

    public void setCldAge(Integer cldAge) {
        this.cldAge = cldAge;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public Users getUsrUidFk() {
        return usrUidFk;
    }

    public void setUsrUidFk(Users usrUidFk) {
        this.usrUidFk = usrUidFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cldUid != null ? cldUid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Children)) {
            return false;
        }
        Children other = (Children) object;
        if ((this.cldUid == null && other.cldUid != null) || (this.cldUid != null && !this.cldUid.equals(other.cldUid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oncore.chhs.persistence.entity.Children[ cldUid=" + cldUid + " ]";
    }
    
}