/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.persistence.dao.criteria.pagination;

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
