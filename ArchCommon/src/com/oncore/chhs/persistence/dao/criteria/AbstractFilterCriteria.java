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
package com.oncore.chhs.persistence.dao.criteria;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @param <T> The entity type.
 * @param <V> The data value type.
 * 
 * @author OnCore LLC
 */
public abstract class AbstractFilterCriteria<T,V> implements FilterCriteria<T,V> {
    private String attribute;
    @XmlElement( name="values" )
    private List<V> values;
    //TODO: Implement null checks.
    
    /**
     * 
     */
    public AbstractFilterCriteria( ) {
        
    }
    
    /**
     * 
     * @param attribute The attribute.
     * @param value The comparison value.
     */
    protected AbstractFilterCriteria( SingularAttribute<T, ?> attribute, V value ) {
        this.attribute = attribute.getName();
        this.values = new ArrayList<>();
        this.values.add( value );
    }
    
    /**
     * 
     * @param attribute The attribute.
     * @param values The comparison values.
     */
    protected AbstractFilterCriteria( SingularAttribute<T, ?> attribute, List<V> values ) {
        this.attribute = attribute.getName();
        this.values = values;
    }
    
    /**
     * 
     * @return The attribute name.
     */
    protected String getAttributeName() {
        return "e." + this.getUnqualifiedAttributeName();
    }
    
    /**
     * 
     * @return Unqualified
     */
    private String getUnqualifiedAttributeName() {
        return this.attribute;
    }
    
    /**
     * 
     * @return The comparison value.
     */
    protected V getValue() {
        if ( this.values.size() > 1 ) {
            throw new IllegalArgumentException( "Only valid when a list of values is present." );
        }
        
        return this.values.get( 0 );
    }
    
        /**
     * 
     * @return The comparison value.
     */
    protected List<V> getValues() {
        return this.values;
    }
    
    /**
     * 
     * @param value A value.
     */
    public void addValue( V value ) {
        if ( this.values == null ) {
            this.values = new ArrayList<V>();
        }
        
        this.values.add( value );
    }
    
    /**
     * @param filterCriteriaIndex Index to keep parameter names unique across generations.
     * 
     * @return The parameter name.
     */
    protected String getParameterName( int filterCriteriaIndex ) {
        return ":" + this.getParameterNameForSet( filterCriteriaIndex );
    }
    
    /**
     * @param filterCriteriaIndex Index to keep parameter names unique across generations.
     * 
     * @return The parameter name.
     */
    protected String getParameterNameForSet( int filterCriteriaIndex ) {
        return this.getUnqualifiedAttributeName() + "_" + filterCriteriaIndex;
    }
    
    /**
     * 
     * @param filterCriteriaIndex Index to keep parameter names unique across generations.
     * @param parameter The parameter index.
     * @return  The multi-value parameter for setting a parameter.
     */
    protected String getMultiValueParameterNameForSet( int filterCriteriaIndex, int parameter )
    {
        return this.getParameterNameForSet( filterCriteriaIndex ) + "_" + parameter;
    }
    
    /**
     * @param builder A StringBuilder.
     * @param filterCriteriaIndex Index to keep parameter names unique across generations.
     * @param parameter The parameter index.
     */
    protected void addMultiValueParameterName( StringBuilder builder, 
                            int filterCriteriaIndex, int parameter ) {
        this.addParameterName( builder, filterCriteriaIndex );
        builder.append( "_" );
        builder.append( parameter );
    }
    
    /**
     * Add the parameter name to the builder.
     * 
     * @param builder A StringBuilder.
     * @param filterCriteriaIndex Index to keep parameter names unique across generations.
     */
    protected void addParameterName( StringBuilder builder, int filterCriteriaIndex ) {
        builder.append( ":" );
        builder.append( this.getUnqualifiedAttributeName() );
        builder.append( "_" );
        builder.append( filterCriteriaIndex );
    }
}
