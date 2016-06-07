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

import com.oncore.chhs.persistence.dao.criteria.OrderBy.Direction;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * oncore
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
