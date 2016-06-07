/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author kerry
 */
public class BusinessServiceInterceptor {

    /**
     * 
     * @param ctx The InovcationContext.
     * @return The return value.
     * @throws Exception 
     */
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception
    {
        Object result = null;
        
        try {
            result = ctx.proceed();
        } catch ( Throwable t ) {
            if ( t instanceof BusinessServiceException ) {
                throw t;
            } else {
                throw new BusinessServiceException( t.getMessage(), t );
            }
        }
        
       return result;
    }
}
