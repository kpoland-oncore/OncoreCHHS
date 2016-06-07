/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.web.rest.response;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kerry O'Brien
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public abstract class AbstractResponse {
    private String errorMessage;
    private String cause;
    
    /**
     * 
     */
    protected AbstractResponse( ) {
        
    }
    
    /**
     * 
     * @param errorMessage The error message.
     * @param cause The cause.
     */
    protected AbstractResponse( String errorMessage, Throwable cause ) {
        this.errorMessage = errorMessage;
        
        if ( cause != null ) {
            ByteArrayOutputStream os = new ByteArrayOutputStream( 1000 );
            
            PrintWriter pw = new PrintWriter( os );            
            cause.printStackTrace( pw );            
            pw.flush();
            
            this.cause = os.toString();
        }
    }
    
    /**
     * 
     * @return The error message.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    /**
     * The stack trace from the original cause.
     * 
     * @return The cause.
     */
    public String getCause() {
        return this.cause;
    }
    
    /**
     * 
     * @return True if an error occurred during processing.
     */
    public boolean isErrorOccurred() {
        return this.errorMessage != null;
    }
}
