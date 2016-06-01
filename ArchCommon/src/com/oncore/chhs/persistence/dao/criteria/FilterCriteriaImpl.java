/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.persistence.dao.criteria;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Filter criteria for String value types.
 * 
 * @param <T> The entity type.
 *
 * @author Kerry O'Brien
 * 
 * @see FilterCriteria
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class FilterCriteriaImpl<T> extends AbstractFilterCriteria<T, String> {
    private Comparator comparator = Comparator.EQUALS;
    
    /**
     * 
     */
    public FilterCriteriaImpl( ) {
        
    }
    
    /**
     * 
     * @param attribute A SingularAttribute.
     * @param value A value.
     */
    public FilterCriteriaImpl( SingularAttribute<T, ?> attribute, String value ) {
        super( attribute, value );
    }
    
    /**
     * 
     * @param attribute A SingularAttribute.
     * @param value A value.
     * @param comparator The comparator.
     */
    public FilterCriteriaImpl( SingularAttribute<T, ?> attribute, String value, 
            Comparator comparator ) {
        super( attribute, value );
        
        this.comparator = comparator;
    }

    /**
     * 
     * @param attribute A SingularAttribute.
     * @param values Values.
     */
    public FilterCriteriaImpl( SingularAttribute<T, ?> attribute, List<String> values ) {
        super( attribute, values );
    }
    
    /**
     * 
     * @param attribute A SingularAttribute.
     * @param values Values.
     * @param comparator The comparator.
     */
    public FilterCriteriaImpl( SingularAttribute<T, ?> attribute, List<String> values, 
            Comparator comparator ) {
        super( attribute, values );
        
        this.comparator = comparator;
    }
            
    /**
     * {@inheritDoc} 
     */
    @Override
    public void addJPQL( StringBuilder builder, int filterCriteriaIndex ) {
        if ( this.getValues().size() > 1 ) {
            builder.append( "(" );
            for (int i = 0;i < this.getValues().size();i++) {
                if ( i > 0 ) {
                    builder.append( " or");
                }

                builder.append( " " );
                builder.append( this.getAttributeName() );

                this.comparator.addToken( builder, this.getValues().get( i ) );

                this.addMultiValueParameterName( builder, filterCriteriaIndex, i );
            }

            builder.append( ")" );
        } else {
            builder.append( " " );
            builder.append( this.getAttributeName() );        
            this.comparator.addToken( builder, this.getValue() );
            
            this.addParameterName( builder, filterCriteriaIndex );
        }
    }

    /**
     * Set the parameter on the query.
     * 
     * @param typedQuery The typed query.
     * @param filterCriteriaIndex Unique number to keep parameter names unique.
     */
    @Override
    public void setParameterValue( TypedQuery<T> typedQuery, int filterCriteriaIndex ) {
        if ( this.getValues().size() > 1 ) {
            for (int i = 0;i < this.getValues().size();i++) {
                typedQuery.setParameter( this.getMultiValueParameterNameForSet( filterCriteriaIndex, i ), 
                        this.getValues().get( i ));
            }
        } else {
            typedQuery.setParameter( this.getParameterNameForSet( filterCriteriaIndex ), this.getValue() );
        }
    }
    
    /**
     * Comparators for string values.
     */
    @XmlEnum
    public enum Comparator {
        @XmlEnumValue( value = " = " )
        EQUALS( " = ", " like " ), 
        NOT_EQUAL( " != ", " not like " );
        
        private String token;
        private String likeToken;
        
        /**
         * 
         * @param token The token.
         * @param likeToken The like token. 
         */
        private Comparator( String token, String likeToken ) {
            this.token = token;
            this.likeToken = likeToken;
        }
        
        /**
         * 
         * @param builder A StringBuilder.
         * @param value The current value.
         */
        public void addToken( StringBuilder builder, String value ) {
            if ( value != null && value.contains( "%" ) ) {                
               builder.append( this.likeToken );
            } else {
                builder.append( this.token );
            }
        }
    }       
}
