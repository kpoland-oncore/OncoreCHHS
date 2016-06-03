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
public class DeleteResponse extends AbstractUpdateResponse {
    
    /**
     * 
     */
    public DeleteResponse( ) {
        
    }
    
    /**
     * 
     * @param numberItemsAffected The number items affected.
     */
    public DeleteResponse( int numberItemsAffected ) {
        super( numberItemsAffected );
    }
    
    /**
     * 
     * @param errorMessage The error message.
     * @param cause The cause.
     */
    public DeleteResponse( String errorMessage, Throwable cause ) {
       super( errorMessage, cause );
    }
}
