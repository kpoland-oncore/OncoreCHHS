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
package com.oncore.chhs.web.profile;

import com.oncore.chhs.web.base.BaseValidationBean;
import com.oncore.chhs.web.utils.FacesUtilities;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oncore
 */
@Named("profileValidationBean")
@RequestScoped
public class ProfileValidationBean extends BaseValidationBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing LoginValidationBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying LoginValidationBean: " + this.getClass().hashCode());
    }

    public Boolean validateName(String name, String componentId) {
        Boolean isError = Boolean.FALSE;

        if (StringUtils.isBlank(name)) {
            FacesUtilities.requredFieldError(FacesContext.getCurrentInstance(), componentId);
            isError = Boolean.TRUE;
        } else if (!StringUtils.isAlphaSpace(name)) {
            FacesUtilities.invalidAlphaFormatError(FacesContext.getCurrentInstance(), componentId);
            isError = Boolean.TRUE;
        }

        return isError;
    }

    public Boolean validateAddressData(String value, String componentId) {
        Boolean isError = Boolean.FALSE;

        if (StringUtils.isBlank(value)) {
            FacesUtilities.requredFieldError(FacesContext.getCurrentInstance(), componentId);
            isError = Boolean.TRUE;
        } else if (!StringUtils.isAlphanumericSpace(value)) {
            FacesUtilities.invalidFormatError(FacesContext.getCurrentInstance(), componentId);
            isError = Boolean.TRUE;
        }

        return isError;
    }

    private final Logger LOG = LogManager.getLogger(ProfileValidationBean.class);

}
