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
@XmlAccessorType( XmlAccessType.FIELD )
public class UserDTO {
    private String firstName;
    private String lastName;

    /**
     * 
     */
    public UserDTO( ) {
        
    }
    
    public UserDTO( String firstName, String lastName ) {
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
}
