/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTOs need to be page specific which means that they should be created within
 * an identifying package.
 *
 * @author Kerry O'Brien
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    private Long userUid;
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;

    /**
     *
     */
    public User() {

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName The first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName The last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the userUid
     */
    public Long getUserUid() {
        return userUid;
    }

    /**
     * @param userUid the userUid to set
     */
    public void setUserUid(Long userUid) {
        this.userUid = userUid;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
