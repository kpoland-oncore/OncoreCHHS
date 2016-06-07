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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Ad additional sort criteria using the #addSortCriteria method.
 *
 * @param <T> The entity type.
 * 
 * @author OnCore LLC
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class OrderBy<T> {
    @XmlElement( name="attributes" )
    private List<OrderByAttribute> attributes = new ArrayList<>();
    
    /**
     * 
     */
    public OrderBy() {
        
    }
    
    /**
     * 
     * @param attribute The attribute;
     */
    public OrderBy( SingularAttribute<T, ?> attribute ) {
        this.attributes.add( new OrderByAttribute(attribute, Direction.ASC));
    }    
    /**
     * 
     * @param attribute The attribute;
     * @param direction The Direction.
     */
    public OrderBy( SingularAttribute<T, ?> attribute, Direction direction ) {
        this.attributes.add( new OrderByAttribute(attribute, direction));
    }
    
    /**
     * Add an additional column sort.
     * 
     * @param attribute An attribute.
     */
    public void addSortCriteria( SingularAttribute<T,?> attribute ) {
        this.attributes.add( new OrderByAttribute( attribute, Direction.ASC ) );
    }
    
    /**
     * Add an additional column sort.
     * 
     * @param attribute An attribute.
     * @param direction The direction.
     */
    public void addSortCriteria( SingularAttribute<T,?> attribute, Direction direction ) {
        this.attributes.add( new OrderByAttribute( attribute, direction ) );
    }
    
    /**
     * @param builder A SringBuilder.
     */
    public void addJPQL( StringBuilder builder ) {
        builder.append( "order by " );
        
        OrderByAttribute attribute = null;
        for ( int i = 0;i < this.attributes.size();i++ ) {
            attribute = this.attributes.get( i );
            
            if ( i > 0 ) {
                builder.append( ", " );
            }
            
            builder.append( " e." );
            builder.append( attribute.getAttribute() );

            if ( attribute.getDirection() != null ) {
                builder.append( " " );
                builder.append( attribute.getDirection() );
            }            
        }
    }
    
    /**
     * The sort direction.
     */
    @XmlEnum
    public enum Direction {
        ASC, DESC;
    }    
}
