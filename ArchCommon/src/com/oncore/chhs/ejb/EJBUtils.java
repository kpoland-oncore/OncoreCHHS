/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.ejb;

import javax.naming.InitialContext;

/**
 * Utility methods for working with EJBs.
 * 
 * @author Kerry O'Brien
 */
public class EJBUtils {

    /**
     * 
     * @param clazz The Class of the EJB to be returned.
     * @return 
     */
    public static <T> T lookupEJB( Class<T> clazz ) {
        Object obj = null;
        try {
            InitialContext ic = new InitialContext();

            obj = ic.lookup(clazz.getName());
        } catch (Throwable t) {
            throw new RuntimeException("Error looking up EJB " + clazz.getName(), t);
        }

        return clazz.cast(obj);
    }
}