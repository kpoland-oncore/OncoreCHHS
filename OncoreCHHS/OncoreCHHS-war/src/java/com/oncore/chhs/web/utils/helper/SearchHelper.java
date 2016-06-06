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

import com.oncore.chhs.web.clients.objects.FosterFamilyAgency.FosterFamilyAgency;
import com.oncore.chhs.web.search.SearchBean;

/**
 *
 * @author oncore
 */
public class SearchHelper {

    /**
     *
     *
     * @param agency
     *
     * @return
     */
    public static SearchBean convertFosterFamilyAgencyToSearchBean(FosterFamilyAgency agency) {
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
        }

        return searchBean;
    }
}
