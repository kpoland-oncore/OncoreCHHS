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
package com.oncore.chhs.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author OnCore LLC
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USR_UID")
    private Integer usrUid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "USR_USER_ID")
    private String usrUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "USR_PASSWORD")
    private String usrPassword;
    @Size(max = 45)
    @Column(name = "USR_FIRSTNAME")
    private String usrFirstname;
    @Size(max = 45)
    @Column(name = "USR_MIDDLENAME")
    private String usrMiddlename;
    @Size(max = 45)
    @Column(name = "USR_LASTNAME")
    private String usrLastname;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrUidFk")
    private Set<Contact> contactSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrUidFk")
    private Set<Messages> messagesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrUidFk")
    private Set<Address> addressSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrUidFk")
    private Set<Children> childrenSet;

    public Users() {
    }

    public Users(Integer usrUid) {
        this.usrUid = usrUid;
    }

    public Users(Integer usrUid, String usrUserId, String usrPassword, String createUserId, Date createTs, String updateUserId, Date updateTs) {
        this.usrUid = usrUid;
        this.usrUserId = usrUserId;
        this.usrPassword = usrPassword;
        this.createUserId = createUserId;
        this.createTs = createTs;
        this.updateUserId = updateUserId;
        this.updateTs = updateTs;
    }

    public Integer getUsrUid() {
        return usrUid;
    }

    public void setUsrUid(Integer usrUid) {
        this.usrUid = usrUid;
    }

    public String getUsrUserId() {
        return usrUserId;
    }

    public void setUsrUserId(String usrUserId) {
        this.usrUserId = usrUserId;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrFirstname() {
        return usrFirstname;
    }

    public void setUsrFirstname(String usrFirstname) {
        this.usrFirstname = usrFirstname;
    }

    public String getUsrMiddlename() {
        return usrMiddlename;
    }

    public void setUsrMiddlename(String usrMiddlename) {
        this.usrMiddlename = usrMiddlename;
    }

    public String getUsrLastname() {
        return usrLastname;
    }

    public void setUsrLastname(String usrLastname) {
        this.usrLastname = usrLastname;
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

    public Set<Contact> getContactSet() {
        return contactSet;
    }

    public void setContactSet(Set<Contact> contactSet) {
        this.contactSet = contactSet;
    }

    public Set<Messages> getMessagesSet() {
        return messagesSet;
    }

    public void setMessagesSet(Set<Messages> messagesSet) {
        this.messagesSet = messagesSet;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    public Set<Children> getChildrenSet() {
        return childrenSet;
    }

    public void setChildrenSet(Set<Children> childrenSet) {
        this.childrenSet = childrenSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrUid != null ? usrUid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usrUid == null && other.usrUid != null) || (this.usrUid != null && !this.usrUid.equals(other.usrUid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oncore.chhs.persistence.entity.Users[ usrUid=" + usrUid + " ]";
    }
    
}
