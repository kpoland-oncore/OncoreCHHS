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
 * @author OnCore LLC
 */
@Entity
@Table(name = "MESSAGES")
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m")})
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MSG_UID")
    private Integer msgUid;
    @Size(max = 45)
    @Column(name = "MSG_FROM")
    private String msgFrom;
    @Size(max = 45)
    @Column(name = "MSG_TO")
    private String msgTo;
    @Size(max = 2048)
    @Column(name = "MSG_TEXT")
    private String msgText;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MSG_TO_USER_IND")
    private boolean msgToUserInd;
    @Column(name = "MSG_CREATED_TS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date msgCreatedTs;
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

    public Messages() {
    }

    public Messages(Integer msgUid) {
        this.msgUid = msgUid;
    }

    public Messages(Integer msgUid, boolean msgToUserInd, String createUserId, Date createTs, String updateUserId, Date updateTs) {
        this.msgUid = msgUid;
        this.msgToUserInd = msgToUserInd;
        this.createUserId = createUserId;
        this.createTs = createTs;
        this.updateUserId = updateUserId;
        this.updateTs = updateTs;
    }

    public Integer getMsgUid() {
        return msgUid;
    }

    public void setMsgUid(Integer msgUid) {
        this.msgUid = msgUid;
    }

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

    public String getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(String msgTo) {
        this.msgTo = msgTo;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public boolean getMsgToUserInd() {
        return msgToUserInd;
    }

    public void setMsgToUserInd(boolean msgToUserInd) {
        this.msgToUserInd = msgToUserInd;
    }

    public Date getMsgCreatedTs() {
        return msgCreatedTs;
    }

    public void setMsgCreatedTs(Date msgCreatedTs) {
        this.msgCreatedTs = msgCreatedTs;
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
        hash += (msgUid != null ? msgUid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messages)) {
            return false;
        }
        Messages other = (Messages) object;
        if ((this.msgUid == null && other.msgUid != null) || (this.msgUid != null && !this.msgUid.equals(other.msgUid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oncore.chhs.persistence.entity.Messages[ msgUid=" + msgUid + " ]";
    }

}
