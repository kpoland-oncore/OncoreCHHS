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
package com.oncore.chhs.web.clients;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oncore.chhs.web.clients.objects.FosterFamilyAgency.FosterFamilyAgency;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.utils.ErrorUtils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author oncore
 */
public class FosterFamilyAgencyJsonClient {

    private static final String DEFAULT_URL = "https://chhs.data.ca.gov/resource/mffa-c6z5.json?facility_status=LICENSED";
    private static final String URL_ZIPCODE = "&facility_zip=";

    public static List<FosterFamilyAgency> getFosterFamilyAgency(String zipCode) throws Exception {

        try {
            String url = DEFAULT_URL;

            if (StringUtils.isNotBlank(zipCode)) {
                url = DEFAULT_URL + URL_ZIPCODE + zipCode;
            }

            String json = IOUtils.toString(new URL(url));

            List<FosterFamilyAgency> familyAgencyList = new Gson().fromJson(json, new TypeToken<ArrayList<FosterFamilyAgency>>() {
            }.getType());

            return familyAgencyList;
        } catch (Throwable t) {
            throw new WebServiceException(ErrorUtils.getStackTrace(t));
        }
    }
}
