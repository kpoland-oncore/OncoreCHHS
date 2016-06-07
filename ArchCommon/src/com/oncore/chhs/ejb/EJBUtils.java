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
package com.oncore.chhs.ejb;

import javax.naming.InitialContext;

/**
 * Utility methods for working with EJBs.
 * 
 * @author OnCore LLC
 */
public class EJBUtils {

    /**
     * The lookupEJB method locates a specified EJB class
     * 
     * @param clazz The Class of the EJB to be returned.
     * @return object
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