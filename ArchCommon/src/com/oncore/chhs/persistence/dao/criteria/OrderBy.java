/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Kerry O'Brien
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
