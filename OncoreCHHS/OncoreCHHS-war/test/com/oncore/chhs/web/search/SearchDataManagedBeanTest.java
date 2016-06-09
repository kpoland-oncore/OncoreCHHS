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

import com.oncore.chhs.client.dto.locate.FosterFamilyAgency;
import com.oncore.chhs.client.dto.zipcoordinate.Geometry;
import com.oncore.chhs.client.dto.zipcoordinate.Location;
import com.oncore.chhs.client.dto.zipcoordinate.Results;
import com.oncore.chhs.client.dto.zipcoordinate.ZipCoordinate;
import com.oncore.chhs.client.rest.LocateServiceClient;
import com.oncore.chhs.web.global.GlobalManagedBean;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author OnCore LLC
 */
public class SearchDataManagedBeanTest {

    public SearchDataManagedBeanTest() {
    }

    /**
     * Test of search method, of class SearchDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testSearchByZip() throws Exception {

        FosterFamilyAgency testAgency = new FosterFamilyAgency();
        testAgency.setCounty_name("countyName");
        testAgency.setFacility_address("facilityAddress");
        testAgency.setFacility_city("facilityCity");
        testAgency.setFacility_name("facilityName");
        testAgency.setFacility_state("facilityState");
        testAgency.setFacility_zip("facilityZip");

        List<FosterFamilyAgency> testAgencies = new ArrayList<>();
        testAgencies.add(testAgency);

        ZipCoordinate zipCoordinate = new ZipCoordinate();
        Results[] resultsArray = new Results[1];
        Results results = new Results();
        Geometry geometry = new Geometry();
        Location location = new Location();
        location.setLat(1D);
        location.setLng(2D);

        geometry.setLocation(location);
        results.setGeometry(geometry);
        resultsArray[0] = results;
        zipCoordinate.setResults(resultsArray);

        LocateServiceClient mockService = mock(LocateServiceClient.class);
        when(mockService.getZipCodeCoordinate("95608")).thenReturn(zipCoordinate);
        when(mockService.searchFosterFamilyAgencyByCircle(2D, 1D)).thenReturn(testAgencies);

        SearchDataManagedBean instance = spy(new SearchDataManagedBean());
        when(instance.getLocateServiceClient()).thenReturn(mockService);

        instance.setGlobalManagedBean(new GlobalManagedBean());

        //search for an agency in Carmichael, CA
        List<SearchBean> result = instance.search("95608");

        assertNotNull(result);
        assertTrue(result.size() > 0);

        SearchBean first = result.get(0);

        assertEquals("facilityAddress", first.getFacilityAddress());
        assertEquals("facilityCity", first.getFacilityCity());
        assertEquals("facilityState", first.getFacilityState());
        assertEquals("facilityZip", first.getFacilityZip());
        assertEquals("countyName", first.getCounty());
    }

    /**
     * Test of search method, of class SearchDataManagedBean, for a case where
     * no results should be returned.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testSearchArea() throws Exception {

        FosterFamilyAgency testAgency = new FosterFamilyAgency();
        testAgency.setCounty_name("countyName");
        testAgency.setFacility_address("facilityAddress");
        testAgency.setFacility_city("facilityCity");
        testAgency.setFacility_name("facilityName");
        testAgency.setFacility_state("facilityState");
        testAgency.setFacility_zip("facilityZip");

        List<FosterFamilyAgency> testAgencies = new ArrayList<>();
        testAgencies.add(testAgency);

        LocateServiceClient mockService = mock(LocateServiceClient.class);

        Double longitude = 1D;
        Double latitude = 2D;

        when(mockService.searchFosterFamilyAgencyByCircle(longitude, latitude)).thenReturn(testAgencies);

        SearchDataManagedBean instance = spy(new SearchDataManagedBean());
        when(instance.getLocateServiceClient()).thenReturn(mockService);

        instance.setGlobalManagedBean(new GlobalManagedBean());

        //search for an agency in Carmichael, CA
        List<SearchBean> result = instance.searchArea(longitude, latitude);

        assertNotNull(result);
        assertTrue(result.size() > 0);

        SearchBean first = result.get(0);

        assertEquals("facilityAddress", first.getFacilityAddress());
        assertEquals("facilityCity", first.getFacilityCity());
        assertEquals("facilityState", first.getFacilityState());
        assertEquals("facilityZip", first.getFacilityZip());
        assertEquals("countyName", first.getCounty());

    }

}
