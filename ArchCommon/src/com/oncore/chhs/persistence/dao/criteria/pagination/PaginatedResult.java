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
package com.oncore.chhs.persistence.dao.criteria.pagination;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OnCore LLC
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class PaginatedResult<T> {
    @XmlElement( name="results" )
    private List<T> results;
    private PaginatedMarker marker;
    
    /**
     * 
     */
    public PaginatedResult( ) {
        
    }
    
    /**
     * 
     * @param results The results.
     * @param marker The PaginatedMarker used to retrieved the next or previous
     * result set if available.
     */
    public PaginatedResult( List<T> results, PaginatedMarker marker ) {
        this.results = results;
        this.marker = marker;
    }
    
    /**
     * 
     * @return The results.
     */
    public List<T> getResults( ) {
        return this.results;
    }
    
    /**
     * 
     * @return The PaginatedMarker.
     */
    public PaginatedMarker getPaginatedMarker() {
        return this.marker;
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder( 100 );
        
        int size = 0;
        
        if ( this.results != null ) {
            size = this.results.size();
        }
        
        builder.append( "NumberResults: " );
        builder.append( size );
        
        if ( this.marker != null ) {
            builder.append( " " );
            builder.append( this.marker );
        }
        
        return builder.toString();
    }
}
