/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.web.rest.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kerry O'Brien
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public abstract class AbstractUpdateResponse extends AbstractResponse {
    private int numberItemsAffected;
    
    /**
     * 
     */
    protected AbstractUpdateResponse( ) {
        
    }
    
    /**
     * 
     * @param numberItemsAffected The number items affected.
     */
    protected AbstractUpdateResponse( int numberItemsAffected ) {
        this.numberItemsAffected = numberItemsAffected;
    }
    
    /**
     * 
     * @param errorMessage The error message.
     */
    protected AbstractUpdateResponse( String errorMessage, Throwable cause ) {
       super( errorMessage, cause );
    }
    
    /**
     * 
     * @return The number of items affected.
     */
    public int getNumberItemsAffected() {
        return this.numberItemsAffected;
    }
}
