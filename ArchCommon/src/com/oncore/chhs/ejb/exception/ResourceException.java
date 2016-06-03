/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.ejb.exception;

/**
 *
 * @author Kerry O'Brien
 */
public class ResourceException extends RuntimeException {

    /**
     * 
     * @param message The message.
     */
    public ResourceException(String message) {
        super(message);
    }

    /**
     * 
     * @param message The message.
     * @param cause The cause.
     */    
    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 
     * @param cause The cause.
     */    
    public ResourceException(Throwable cause) {
        super(cause);
    }

    /**
     * 
     * @param message The message.
     * @param cause The cause.
     * @param enableSuppression Enable suppression.
     * @param writableStackTrace Should a writable stack trace be created.
     */    
    public ResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
