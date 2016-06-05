/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.web.rest.response;

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
public class SelectResponse<T> extends AbstractResponse {
    
    @XmlElement( name="result" )
    private T result;
    
    /**
     * 
     */
    public SelectResponse( ) {
        
    }
    
    /**
     * 
     * @param result The result.
     */
    public SelectResponse( T result ) {
        this.result = result;
    }
    
    /**
     * 
     * @param errorMessage The error message.
     * @param cause The cause.
     */
    public SelectResponse( String errorMessage, Throwable cause ) {
       super( errorMessage, cause );
    }
    
    /**
     * 
     * @return The result.
     */
    public T getResult() {
        return this.result;
    }    
}
