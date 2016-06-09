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
package com.oncore.chhs.web.utils.helper;

import com.oncore.chhs.client.dto.locate.FosterFamilyAgency;
import com.oncore.chhs.web.search.SearchBean;
import com.oncore.chhs.web.utils.GpsUtils;

/**
 *
 * @author OnCore LLC
 */
public class SearchHelper {

    /**
     *
     *
     * @param agency
     *
     * @return
     */
    public static SearchBean convertFosterFamilyAgencyToSearchBean(FosterFamilyAgency agency, Double zipLatitude, Double zipLongitude) {
        SearchBean searchBean = new SearchBean();

        searchBean.setCounty(agency.getCounty_name());
        searchBean.setFacilityAddress(agency.getFacility_address());
        searchBean.setFacilityAdministrator(agency.getFacility_administrator());
        searchBean.setFacilityCity(agency.getFacility_city());
        searchBean.setFacilityName(agency.getFacility_name());
        searchBean.setFacilityNumber(agency.getFacility_number());
        searchBean.setFacilityState(agency.getFacility_state());
        searchBean.setFacilityTelephone(agency.getFacility_telephone_number());
        searchBean.setFacilityType(agency.getFacility_type());
        searchBean.setFacilityZip(agency.getFacility_zip());
        searchBean.setRegionalOffice(agency.getReginal_office());

        if (null != agency.getLocation()) {
            searchBean.setLogitude(agency.getLocation().getCoordinates()[0]);
            searchBean.setLatitude(agency.getLocation().getCoordinates()[1]);

            if (null != zipLatitude && null != zipLongitude) {
                searchBean.setDistance(GpsUtils.calculateDistance(zipLatitude, zipLongitude, searchBean.getLatitude(), searchBean.getLogitude()));
            }

        }

        return searchBean;
    }

//    /**
//     * This routine calculates the distance between two points (given the
//     * latitude/longitude of those points). It is being used to calculate the
//     * distance between two locations.
//     *
//     * @param lat1 - latitude point 1
//     * @param lon1 - longitude point 1
//     * @param lat2 - latitude point 2
//     * @param lon2 - longitude point 2
//     *
//     * @return the distance between the two points with precision to the .1.
//     */
//    public static final double getDistance(double lat1, double lon1, double lat2, double lon2) {
//        double theta = lon1 - lon2;
//        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
//        dist = Math.acos(dist);
//        dist = rad2deg(dist);
//        dist = dist * 60 * 1.1515;
//
//        return new BigDecimal(dist)
//                .setScale(1, BigDecimal.ROUND_HALF_UP)
//                .doubleValue();
//    }
//
//    /**
//     * This function converts decimal degrees to radians.
//     *
//     * @param deg - the decimal to convert to radians
//     * @return the decimal converted to radians
//     */
//    private static final double deg2rad(double deg) {
//        return (deg * Math.PI / 180.0);
//    }
//
//    /**
//     * This function converts radians to decimal degrees.
//     *
//     * @param rad - the radian to convert
//     * @return the radian converted to decimal degrees
//     */
//    private static final double rad2deg(double rad) {
//        return (rad * 180 / Math.PI);
//    }
}
