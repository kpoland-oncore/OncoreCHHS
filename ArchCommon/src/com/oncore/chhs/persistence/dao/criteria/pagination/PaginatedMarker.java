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

import com.oncore.chhs.persistence.dao.criteria.OrderBy;
import com.oncore.chhs.persistence.dao.criteria.Predicate;
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
public class PaginatedMarker<T> {
    private int firstResult;
    private int numberRowsInSet;
    private boolean moreRowsAvailable;
    private OrderBy orderBy;
    @XmlElement(name="predicates")
    private List<Predicate<T,?>> predicates;
    
    /**
     * 
     */
    public PaginatedMarker( ) {
        
    }
    
    /**
     * 
     * @param firstResult The first result.
     * @param numberRowsInSet The number rows in the set.
     * @param moreRowsAvailable  True if more rows are available.
     */
    public PaginatedMarker( int firstResult, int numberRowsInSet, boolean moreRowsAvailable ) {
        this.firstResult = firstResult;
        this.numberRowsInSet = numberRowsInSet;
        this.moreRowsAvailable = moreRowsAvailable;
    }
    
    /**
     * 
     * @param firstResult The first result.
     * @param numberRowsInSet The number rows in the set.
     * @param moreRowsAvailable  True if more rows are available.
     * @param orderBy The OrderBy
     * @param predicates The Predicate list.
     */
    public PaginatedMarker( int firstResult, int numberRowsInSet, boolean moreRowsAvailable,
            OrderBy orderBy, List<Predicate<T,?>> predicates ) {
        this.firstResult = firstResult;
        this.numberRowsInSet = numberRowsInSet;
        this.moreRowsAvailable = moreRowsAvailable;
        this.orderBy = orderBy;
        this.predicates = predicates;
    }
    
    /**
     * 
     * @return The index of the first result.
     */
    public int getFirstResult() {
        return this.firstResult;
    }
    
    /**
     * 
     * @return The number of rows to select.
     */
    public int getNumberRowsInSet() {
        return this.numberRowsInSet;
    }
    
    /**
     * 
     * @return True if more rows are available.
     */
    public boolean isMoreRowsAvailable() {
        return this.moreRowsAvailable;
    }    
    
    /**
     * 
     * @return True if previous results are available.
     */
    public boolean isPreviousRowsAvailable() {
        return this.firstResult > 0;
    }
    
    /**
     * 
     * @return The previous starting index.
     */
    public int getStartingIndexForNextSet() {
        return this.numberRowsInSet + this.firstResult;
    }
    
    /**
     * 
     * @return The next starting index.
     */
    public int getStartingIndexForPreviousSet() {
        int starting = 0;
        
        if ( this.firstResult > this.numberRowsInSet ) {
            starting = this.firstResult - this.numberRowsInSet;
        }
        
        return starting;
    }
    
    /**
     * 
     * @return The OrderBy.
     */
    public OrderBy getOrderBy() {
        return this.orderBy;
    }
    
    /**
     * 
     * @return The Predicate list.
     */
    public List<Predicate<T,?>> getPredicates() {
        return this.predicates;
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder( 100 );
        builder.append( "FirstResult: " );
        builder.append( this.firstResult );
        builder.append( " NumberRowsInSet: " );
        builder.append( this.numberRowsInSet );
        builder.append( " MoreRowsAvailable: " );
        builder.append( this.moreRowsAvailable );
        
        return builder.toString();
    }
}
