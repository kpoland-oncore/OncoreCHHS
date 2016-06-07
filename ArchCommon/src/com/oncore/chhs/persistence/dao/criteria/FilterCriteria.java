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

import javax.persistence.TypedQuery;

/**
 * 
 * @param <T> The entity type.
 * @param <V> The data value type.
 * 
 * @author OnCore LLC
 */
public interface FilterCriteria<T, V> {

    /**
     * Add an additional value to the filter values list. If multiple values are
     * present, a fragment will be created such as ( a = 1 or a = 2 or a = 3 ).
     * 
     * @param value A value.
     */
    public void addValue( V value );
    
    /**
     * Create the JPQL statement for the provided values and add it to the
     * the JPQL statement.
     * 
     * @param builder A StringBuilder.
     * @param filterIndex Id to keep parameters unique.
     */
    public void addJPQL( StringBuilder builder, int filterIndex );
    
    /**
     * Set the value for each of the named paremeters created in the #addJPQL method.
     * 
     * @param typedQuery A TypedQuery.
     * @param filterCriteriaIndex The same index value used to generate the JPQL
     * fragment.
     */
    public void setParameterValue( TypedQuery<T> typedQuery, int filterCriteriaIndex );    
}
