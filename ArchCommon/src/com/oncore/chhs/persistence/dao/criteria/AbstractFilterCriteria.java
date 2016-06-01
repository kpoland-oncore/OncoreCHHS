/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Kerry O'Brien
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
