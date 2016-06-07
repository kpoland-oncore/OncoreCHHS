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
package com.oncore.chhs.web.global;

import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.utils.ErrorUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author OnCore LLC
 */
@Named("referenceDataManagedBean")
@RequestScoped
public class ReferenceDataManagedBean implements AbstractReferenceDataManagedBean {

    /**
     * {@inheritDoc }
     */
    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing ReferenceDataManagedBean: " + this.getClass().hashCode());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying ReferenceDataManagedBean: " + this.getClass().hashCode());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<String> fetchStateCodes() throws WebServiceException {
        List<String> stateCodes = new ArrayList<>(1);

        try {
// FUTURE ENHANCEMENT, TIE INTO REFERENCE DATA TABLE(S)
//            stateCodes = this.adrStateCdREST.findAll();
        } catch (Exception ex) {
            throw new WebServiceException(ErrorUtils.getStackTrace(ex));
        }

        return stateCodes;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<String> fetchContactCodes() throws WebServiceException {
        List<String> contactCodes = new ArrayList<>(1);

        try {
// FUTURE ENHANCEMENT, TIE INTO REFERENCE DATA TABLE(S)
//            contactCodes = this.emcTypeCdREST.findAll();
        } catch (Exception ex) {
            throw new WebServiceException(ErrorUtils.getStackTrace(ex));
        }

        return contactCodes;
    }

    private final Logger LOG = LogManager.getLogger(ReferenceDataManagedBean.class);
}
