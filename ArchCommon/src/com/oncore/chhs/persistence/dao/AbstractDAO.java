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
package com.oncore.chhs.persistence.dao;

import com.oncore.chhs.persistence.dao.criteria.OrderBy;
import com.oncore.chhs.persistence.dao.criteria.Predicate;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedMarker;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedResult;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Abstract DAO which will generate JPQL and provide basic find, search, create and
 * delete operations. In addition pagination is provided as well.
 * 
 * @author OnCore LLC
 */
public abstract class AbstractDAO<T> {
    private Class<T> type;
    
    /**
     * 
     */
    protected AbstractDAO()
    {
        Type t = getClass().getGenericSuperclass();

        while (!(t instanceof ParameterizedType) || ((ParameterizedType) t).getRawType() != AbstractDAO.class) {
            if (t instanceof ParameterizedType) {
                t = ((Class<?>) ((ParameterizedType) t).getRawType()).getGenericSuperclass();
            } else {
                t = ((Class<?>) t).getGenericSuperclass();
            }
        }

        this.type = (Class<T>) ((ParameterizedType) t).getActualTypeArguments()[0];    
    }
    
    /**
     * 
     * @param id Primary key.
     * 
     * @return An entity instance.
     */
    public T findById( Object id )
    {
        Object obj;
        
        obj = this.getEntityManager().find( this.type, id );
        
        return type.cast( obj );
    }
    
    /**
     * Warning: Use with caution as this method is now bounded.
     * 
     * @return All of the T instances.
     */
    public List<T> findAll( )
    {
        return this.getResultList( -1, -1, (List<Predicate<T, ?>>)null, (OrderBy)null );
    }
    
    /**
     * Warning: Use with caution as this method is not bounded.
     * 
     * @param orderBy Order the result set.
     * 
     * @return All of the T instances.
     */
    public List<T> findAll( OrderBy<T> orderBy )
    {
        return this.getResultList( -1, -1, (List<Predicate<T, ?>>)null, orderBy );
    }
    
    /**
     * Retrieve a paginated set of data.
     * 
     * @param firstResult The first result, starting at 0.
     * @param numberResults Number results to be returned.
     * @param predicates A list of optional predicates to filter the result list.
     * @param orderBy Operation order by columns using OrderBy.Direction.
     * 
     * @return A PaginatedResult
     */
    protected PaginatedResult getPaginatedSet( int firstResult, int numberResults, List<Predicate<T, ?>> predicates, OrderBy orderBy )
    {
        PaginatedResult paginated = null;
        
        List<T> results = this.getResultList( firstResult, numberResults + 1, predicates, orderBy );
        
        boolean moreRowsAvailable = results.size() > numberResults;
        
        if ( moreRowsAvailable ) {
            results.remove( results.size() - 1 );
        }
        
        paginated = new PaginatedResult( results, new PaginatedMarker( firstResult, 
                numberResults, moreRowsAvailable ) );
        
        return paginated;
    }
    
    /**
     * Retrieve a paginated set of results.
     * 
     * @param firstResult The first result, starting at 0. -1 indicates indicates
     * that the parameter is not used.
     * @param numberResults Number results to be returned. -1 indicates that the
     * parameter is not used.
     * @param orderBy Operation order by columns using OrderBy.Direction.
     * @param predicates A list of optional predicates to filter the result list.
     * 
     * @return A PaginatedResult
     */
    protected PaginatedResult getPaginatedSet( int firstResult, int numberResults, OrderBy orderBy, 
            Predicate... predicates ) {
        return this.getPaginatedSet( firstResult, numberResults, Arrays.asList( predicates ), orderBy );
    }
    
    /**
     * Get the next set of data for a paginated set.
     * 
     * @param paginatedMarker A PaginatedMarker.
     * @return A PaginatedResult.
     */
    public PaginatedResult getNextPaginatedSet( PaginatedMarker paginatedMarker ) {
        PaginatedResult result = null;
        
        if ( paginatedMarker == null || !paginatedMarker.isMoreRowsAvailable() ) {
            throw new IllegalArgumentException( 
                    "A PaginatedMarker must be available and more rows "
                            + "must be available in the set :" + paginatedMarker );
        }
        
        int firstResult = paginatedMarker.getStartingIndexForNextSet();
        
        result = this.getPaginatedSet( firstResult, paginatedMarker.getNumberRowsInSet(),
                paginatedMarker.getPredicates(), paginatedMarker.getOrderBy() );
        
        return result;
    }
    
    /**
     * Get the previous set of data for a paginated set.
     * 
     * @param paginatedMarker A PaginatedMarker.
     * @return A PaginatedResult.
     */
    public PaginatedResult getPreviousPaginatedSet( PaginatedMarker paginatedMarker ) {
        PaginatedResult result = null;
        
        if ( paginatedMarker == null ) {
            throw new IllegalArgumentException( 
                    "A PaginatedMarker must be available." );
        }
        
        int firstResult = paginatedMarker.getStartingIndexForPreviousSet();
        
        result = this.getPaginatedSet( firstResult, paginatedMarker.getNumberRowsInSet(),
                paginatedMarker.getPredicates(), paginatedMarker.getOrderBy() );
        
        return result;
    }    

    /**
     * Use this method when creating multiple simple predicates using the
     * Predicate.addSimpleFilter method.
     * 
     * @param orderBy Operation order by columns using OrderBy.Direction.
     * @param predicates A list of optional predicates to filter the result list.
     * 
     * @return A list of instances.
     */
    protected List<T> search( OrderBy orderBy, 
            Predicate... predicates ) {
        return this.getResultList( -1, -1, Arrays.asList( predicates ), orderBy );
    }

    /**
     * Retrieve entities.
     * 
     * @param predicates A list of optional predicates to filter the result list.
     * @param orderBy Operation order by columns using OrderBy.Direction.
     * 
     * @return Zero or more T instances.
     */
    protected List<T> search( List predicates, OrderBy orderBy )
    {
        return this.getResultList( -1, -1, predicates, orderBy );
    }
    
    /**
     * Retrieve entities.
     * 
     * @param firstResult The first result, starting at 0. -1 indicates indicates
     * that the parameter is not used.
     * @param numberResults Number results to be returned. -1 indicates that the
     * parameter is not used.
     * @param predicates A list of optional predicates to filter the result list.
     * @param orderBy Operation order by columns using OrderBy.Direction.
     * 
     * @return Zero or more T instances.
     */
    private List<T> getResultList( int firstResult, int numberResults, List predicates, OrderBy orderBy )
    {
        StringBuilder jpql = new StringBuilder( 300 );
        jpql.append( "select e from " );
        jpql.append( this.type.getSimpleName() );
        jpql.append( " e " );

        this.setJPQLFilterFragments( jpql, predicates );
        
        if ( orderBy != null ) {            
            orderBy.addJPQL( jpql );
        }
        
        System.out.println( this.getClass().getSimpleName() + ": Generated JPQL: " + jpql.toString() );
        
        TypedQuery<T> query = this.getEntityManager().createQuery( jpql.toString(), this.type );
        
        this.setParameters(query, predicates);
        
        if ( firstResult > -1 ) {
            query.setFirstResult( firstResult );
        }
        
        if ( numberResults > -1 ) {
            query.setMaxResults( numberResults );
        }
        
        //TODO: Optimize
        // query.setHint("eclipselink.batch", "e.address");
        // query.setHint("eclipselink.batch.type", "EXISTS");
        // query.setHint("eclipselink.batch.type", "IN");

        List<T> instances = query.getResultList();
        
        PaginatedMarker marker = null;
        PaginatedResult<T> result = new PaginatedResult<T>( instances, marker );
        
        return instances;
    }
    
    /**
     * 
     * @param jpql The current JPQL statement.
     * @param predicates Predicates to be added to the where clause.
     */
    private void setJPQLFilterFragments( StringBuilder jpql, List<Predicate<T, ?>> predicates ) {
        Predicate p = null;
        for (int i = 0;predicates != null && i < predicates.size();i++) {
            if ( i == 0 ) {
                jpql.append( "where " );                
            } else {
                jpql.append( " and " );
            } 
            
            p = predicates.get( i );
            
            p.addFilters( jpql, i );
        }      
    }
    
    /**
     * 
     * @param typedQuery The TypedQuery.
     * @param predicates Predicates which need parameters set.
     */
    private void setParameters( TypedQuery<T> typedQuery, List<Predicate<T, ?>> predicates ) {
        Predicate p = null;
        for (int i = 0;predicates != null && i < predicates.size();i++) {
            p = predicates.get( i );
            
            p.setParameters( typedQuery, i );
        }        
    }  
    
    /**
     * A flush is performed to guarantee that the primary key is generated
     * for use in the following methods.
     * 
     * @param newInstance An instance of T.
     * 
     * @return An entity instance.
     */
    public T create( T newInstance )
    {
        this.getEntityManager().persist( newInstance );
        this.getEntityManager().flush();
        
        return type.cast( newInstance );
    }
    
    /**
     * Delete an entity.
     * 
     * @param instance An instance of T.
     */
    public void delete( T instance )
    {
        this.getEntityManager().remove( instance );
    }
    
    /**
     * 
     * @return The injected EntityManager instance.
     */
    protected abstract EntityManager getEntityManager();
}
