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
package com.oncore.chhs.web.rest.response;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OnCore LLC
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
