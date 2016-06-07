/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.dto;

/**
 *
 * @author OnCore LLC
 */
public class CreateMessage extends Message {

    private Integer userUid;

    /**
     * @return the userUid
     */
    public Integer getUserUid() {
        return userUid;
    }

    /**
     * @param userUid the userUid to set
     */
    public void setUserUid(Integer userUid) {
        this.userUid = userUid;
    }
}
