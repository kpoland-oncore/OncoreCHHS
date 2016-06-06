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
package com.oncore.chhs.client.rest;

import com.oncore.chhs.client.dto.locate.FosterFamilyAgency;
import com.oncore.chhs.web.rest.client.AbstractRestClient;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author oncore
 */
public class LocateServiceClient extends AbstractRestClient {

    private static final String LOCATE_SERVICE = "locate.rest.url.json";
    private static final String APP_TOKEN = "98mLn8GscN2Q78YuzLWkp9Z7m";

    /**
     * Retrieves the licensed foster family agencies by the zip code.
     *
     * @param zipCode
     *
     * @return Foster Family Agencies
     *
     * @throws Exception
     */
    public List<FosterFamilyAgency> searchFosterFamilyAgency(String zip) {

        List<FosterFamilyAgency> familyAgencyList = null;

        try {
            WebTarget target = this.getTarget(LOCATE_SERVICE).
                    queryParam("facility_status", "LICENSED").queryParam("$$app_token", APP_TOKEN).
                    queryParam("facility_zip", zip);

            familyAgencyList = target.request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<FosterFamilyAgency>>() {
                    });
        } catch (Throwable t) {
            throw new WebServiceException("Unable to retreive foster family agencies for zip " + zip, t);
        }

        return familyAgencyList;
    }

    /**
     * Retrieves the licensed foster family agencies by coordinate of the user.
     * It will get the agencies in the circle of 15000 meters.
     *
     * @param logitude
     * @param latitude
     *
     * @return Foster Family Agencies
     *
     * @throws Exception
     */
    public List<FosterFamilyAgency> searchFosterFamilyAgencyByCircle(
            Double logitude, Double latitude) throws Exception {
        List<FosterFamilyAgency> familyAgencyList = null;

        try {
            if (logitude != null && latitude != null) {

                String whereQueryParam = "within_circle(location, " + logitude.toString() + "," + latitude.toString() + ", 15000)";

                WebTarget target = this.getTarget(LOCATE_SERVICE).
                        queryParam("facility_status", "LICENSED").queryParam("$where", whereQueryParam).
                        queryParam("$$app_token", APP_TOKEN);

                familyAgencyList = target.request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<FosterFamilyAgency>>() {
                        });
            }

            return familyAgencyList;
        } catch (Throwable t) {
            throw new WebServiceException("Unable to retreive foster family agencies for coodinates " + logitude + "," + latitude, t);
        }
    }
}
