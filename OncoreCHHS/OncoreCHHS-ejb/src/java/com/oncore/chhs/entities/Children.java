/*
 * The MIT License
 *
 * Copyright 2016 oncore.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.oncore.chhs.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oncore
 */
@Cacheable(false)
@Entity
@Table(name = "CHILDREN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Children.findAll", query = "SELECT c FROM Children c"),
    @NamedQuery(name = "Children.findByCldUid", query = "SELECT c FROM Children c WHERE c.cldUid = :cldUid"),
    @NamedQuery(name = "Children.findByCldFirstname", query = "SELECT c FROM Children c WHERE c.cldFirstname = :cldFirstname"),
    @NamedQuery(name = "Children.findByCldMiddlename", query = "SELECT c FROM Children c WHERE c.cldMiddlename = :cldMiddlename"),
    @NamedQuery(name = "Children.findByCldLastname", query = "SELECT c FROM Children c WHERE c.cldLastname = :cldLastname"),
    @NamedQuery(name = "Children.findByCldAge", query = "SELECT c FROM Children c WHERE c.cldAge = :cldAge"),
    @NamedQuery(name = "Children.findByCreateUserId", query = "SELECT c FROM Children c WHERE c.createUserId = :createUserId"),
    @NamedQuery(name = "Children.findByCreateTs", query = "SELECT c FROM Children c WHERE c.createTs = :createTs"),
    @NamedQuery(name = "Children.findByUpdateUserId", query = "SELECT c FROM Children c WHERE c.updateUserId = :updateUserId"),
    @NamedQuery(name = "Children.findByUpdateTs", query = "SELECT c FROM Children c WHERE c.updateTs = :updateTs")})
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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
        return "com.oncore.chhs.entities.Children[ cldUid=" + cldUid + " ]";
    }
    
}
