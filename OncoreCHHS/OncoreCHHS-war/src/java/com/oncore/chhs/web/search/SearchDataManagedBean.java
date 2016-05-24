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

import com.oncore.chhs.web.clients.FosterFamilyAgencyJsonClient;
import com.oncore.chhs.web.clients.objects.FosterFamilyAgency.FosterFamilyAgency;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.services.UsersFacadeREST;
import com.oncore.chhs.web.utils.ErrorUtils;
import com.oncore.chhs.web.utils.helper.SearchHelper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oncore
 */
@Named("searchDataManagedBean")
@RequestScoped
public class SearchDataManagedBean implements AbstractSearchDataManagedBean {

    @EJB
    private UsersFacadeREST usersFacadeREST;

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing SearchDataManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying SearchDataManagedBean: " + this.getClass().hashCode());
    }

    /**
     *
     *
     * @param zip
     *
     * @return
     *
     * @throws WebServiceException
     */
    @Override
    public List<SearchBean> search(String zip) throws WebServiceException {
        List<SearchBean> agencies = new ArrayList<>();

        try {
            List<FosterFamilyAgency> fosterFamilyAgencyList = FosterFamilyAgencyJsonClient.getFosterFamilyAgency(zip);

            if (null != null) {
                for (FosterFamilyAgency agency : fosterFamilyAgencyList) {
                    agencies.add(SearchHelper.convertFosterFamilyAgencyToSearchBean(agency));
                }
            }
        } catch (Exception ex) {
            throw new WebServiceException(ErrorUtils.getStackTrace(ex));
        }

        return agencies;
    }

    private final Logger LOG = LogManager.getLogger(SearchDataManagedBean.class);
}
