/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kerry O'Brien
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Summaries {
    @XmlElement(name="items")
    private List<UserDTO> items;
    
    /**
     * 
     */
    public Summaries() {
        
    }
    
    /**
     * 
     * @param items The items.
     */
    public Summaries( List<UserDTO> items ) {
        this.items = items;
    }
    
    /**
     * 
     * @return The items.
     */
    public List<UserDTO> getItems() {
        return this.items;
    }
    
    /**
     * 
     * @param index The index.
     * @return An item.
     */
    public UserDTO getItem( int index ) {
        return this.items.get(index);
    }
}
