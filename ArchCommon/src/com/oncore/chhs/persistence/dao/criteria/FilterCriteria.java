/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.persistence.dao.criteria;

import javax.persistence.TypedQuery;

/**
 * 
 * @param <T> The entity type.
 * @param <V> The data value type.
 * 
 * @author Kerry O'Brien
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
