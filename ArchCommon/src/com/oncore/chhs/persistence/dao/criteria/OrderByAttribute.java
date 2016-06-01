/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.persistence.dao.criteria;

import com.oncore.chhs.persistence.dao.criteria.OrderBy.Direction;
import static javafx.scene.input.KeyCode.T;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Kerry O'Brien
 */
@XmlRootElement
public class OrderByAttribute<T> {
    private String attribute;
    private Direction direction = Direction.ASC;

    /**
     * 
     */
    public OrderByAttribute( ) {
        
    }
    
    /**
     * 
     * @param attribute A SingularAttribute.
     * @param direction The sort Direction.
     */
    public OrderByAttribute( SingularAttribute<T,?> attribute, Direction direction ) {
        this.attribute = attribute.getName();
        this.direction = direction;
    }

    /**
     * 
     * @return The attribute.
     */
    public String getAttribute() {
        return this.attribute;
    }

    public Direction getDirection( ) {
        return this.direction;
    }
}
