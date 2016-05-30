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
package com.oncore.chhs.web.search;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oncore
 */
public class SearchDataManagedBeanTest {
    
    public SearchDataManagedBeanTest() {
    }



    /**
     * Test of search method, of class SearchDataManagedBean,
     * for a case where results should be returned.
     * 
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testSearchWithResults() throws Exception {
        
        SearchDataManagedBean instance = new SearchDataManagedBean();
        
        //search for an agency in Carmichael, CA
        List<SearchBean> result = instance.search("95608");
        
        assertNotNull(result);
        assertTrue( result.size() > 0 );
        
        SearchBean first = result.get(0);
        
        assertEquals( "CARMICHAEL", first.getFacilityCity() );
        assertEquals( "CA", first.getFacilityState() );
        assertEquals( "SACRAMENTO", first.getCounty() );
    }

    /**
     * Test of search method, of class SearchDataManagedBean,
     * for a case where no results should be returned.
     * 
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testSearchWithBadData() throws Exception {
        
        SearchDataManagedBean instance = new SearchDataManagedBean();
        List<SearchBean> result = instance.search("invalid");
        
        assertNotNull(result);
        assertTrue( result.isEmpty() );
    }
    
}
