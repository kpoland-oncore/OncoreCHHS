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

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Filter for Numeric types.
 * 
 * @param <T> The entity type.
 * 
 * @author OnCore LLC
 * 
 * @see FilterCriteria
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class NumericFilterCriteriaImpl<T> extends AbstractFilterCriteria<T,Number> {
    private Number highNumber;
    private Comparator comparator = Comparator.EQUALS;
    
    /**
     * 
     */
    public NumericFilterCriteriaImpl( ) {
        
    }
    
    /**
     * 
     * @param attribute A SingularAttribute.
     * @param value A value. 
     */
    public NumericFilterCriteriaImpl( SingularAttribute<T, ?> attribute, 
            Number value ) {
        super( attribute, value );
    }
    
   /**
     * 
     * @param attribute A SingularAttribute.
     * @param values Values. 
     */
    public NumericFilterCriteriaImpl( SingularAttribute<T, ?> attribute, 
            List<Number> values ) {
        super( attribute, values );
    }    
    
    /**
     * 
     * @param attribute A SingularAttribute.
     * @param value A value. 
     * @param comparator The Comparator.
     */
    public NumericFilterCriteriaImpl( SingularAttribute<T, ?> attribute, 
            Number value, Comparator comparator ) {
        super( attribute, value );
        
        this.comparator = comparator;
    }
    
        /**
     * 
     * @param attribute A SingularAttribute.
     * @param values Values. 
     * @param comparator The Comparator.
     */
    public NumericFilterCriteriaImpl( SingularAttribute<T, ?> attribute, 
            List<Number> values, Comparator comparator ) {
        super( attribute, values );
        
        this.comparator = comparator;
    }
    
    /**
     * Create a between filter. Note that with a between filter, only one set
     * of values is allowed.
     * 
     * @param attribute A SingularAttribute.
     * @param value A value.
     * @param highNumber The high number used for between.
     * @param comparator The Comparator.
     */
    public NumericFilterCriteriaImpl( SingularAttribute<T, ?> attribute, 
                    Number value, Number highNumber, Comparator comparator ) {
        this( attribute, value, comparator );
        
        this.highNumber = highNumber;
        this.comparator = comparator;
    }

    /**
     * {@inheritDoc} 
     */    
    @Override
    public void setParameterValue( TypedQuery<T> typedQuery, int filterCriteriaIndex ) {
        //TODO: Handle multiple values.
        if ( Comparator.BETWEEN.equals( this.comparator ) ) {
            typedQuery.setParameter( this.getParameterNameForSet( filterCriteriaIndex ), this.getValue() );
            typedQuery.setParameter( this.getParameterNameForSet( filterCriteriaIndex ) + "_high", this.highNumber );
        } else {
            if ( this.getValues().size() > 1 ) {
                for (int i = 0;i < this.getValues().size();i++) {
                    typedQuery.setParameter( this.getMultiValueParameterNameForSet( filterCriteriaIndex, i ), 
                            this.getValues().get( i ));
                }
            } else {
                typedQuery.setParameter( this.getParameterNameForSet( filterCriteriaIndex ), this.getValue() );
            }            
        }
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void addJPQL( StringBuilder builder, int filterCriteriaIndex ) {
        if ( this.getValues().size() > 1 ) {
            if ( Comparator.BETWEEN.equals( this.comparator ) ) {
                throw new IllegalArgumentException( "Between is not valid with multiple values." );
            }
            
            builder.append( "(" );
            for (int i = 0;i < this.getValues().size();i++) {
                if ( i > 0 ) {
                    builder.append( " or");
                }

                builder.append( " " );
                builder.append( this.getAttributeName() );

                this.comparator.appendToken(builder);

                this.addMultiValueParameterName( builder, filterCriteriaIndex, i );
            }

            builder.append( ")" );            
        } else {
            builder.append( " " );
            builder.append( this.getAttributeName() );  

            this.comparator.appendToken( builder );

            this.addParameterName( builder, filterCriteriaIndex );

            if ( Comparator.BETWEEN.equals( this.comparator ) ) {
                builder.append( " and " );
                this.addParameterName( builder, filterCriteriaIndex );
                builder.append( "_high " );
            }      
        }
    }
    
    /**
     * Comparators for numeric values.
     */
    @XmlEnum
    public enum Comparator {
        @XmlEnumValue( value=" = " )
        EQUALS(" = "), 
        @XmlEnumValue( value=" != " )
        NOT_EQUAL(" != "), 
        @XmlEnumValue( value=" >= " )
        GT_EQUAL(" >= "), 
        @XmlEnumValue( value=" <= ")
        LT_EQUAL(" <= "),
        @XmlEnumValue( value=" between " )
        BETWEEN(" between ");
        
        private String token;
        
        /**
         * 
         * @param token The token.
         */
        private Comparator( String token ) {
            this.token = token;
        }
        
        /**
         * @param builder A StringBuilder.
         */
        public void appendToken( StringBuilder builder ) {
            builder.append( this.token );
        }
    }     
}
