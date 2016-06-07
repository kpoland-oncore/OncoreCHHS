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
import com.oncore.chhs.web.utils.ErrorUtils;
import com.oncore.chhs.web.utils.FacesUtilities;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
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
 * @author OnCore LLC
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

    /**
     * The validateName method validates the contents of a name
     * field.
     *
     * @param name the value of the input field
     * @param isRequired true if the field is required, false otherwise
     * @param componentId the identifier assigned to the JSF component
     * @return null if there is no error, a populated error message if there is
     * an error detected
     */
    public String validateName(String name, Boolean isRequired, String componentId) {
        String error = null;

        if (isRequired && StringUtils.isBlank(name)) {
            FacesUtilities.requredFieldError(FacesContext.getCurrentInstance(), componentId);
            error = FacesUtilities.REQUIRED_ERROR;
        } else if (!StringUtils.isAlphaSpace(name)) {
            error = FacesUtilities.INVALID_ALPHA_FORMAT_ERROR;
            FacesUtilities.invalidAlphaFormatError(FacesContext.getCurrentInstance(), componentId);
        }

        return error;
    }

    /**
     * The validateAddressData method validates the contents of an
     * address field.
     *
     * @param value the value of the inputTextArea
     * @param isRequired true if the field is required, false otherwise
     * @param componentId the identifier assigned to the JSF component
     * @return null if there is no error, a populated error message if there is
     * an error detected
     */
    public String validateAddressData(String value, Boolean isRequired, String componentId) {
        String error = null;

        if (isRequired && StringUtils.isBlank(value)) {
            FacesUtilities.requredFieldError(FacesContext.getCurrentInstance(), componentId);
            error = FacesUtilities.REQUIRED_ERROR;
        } else if (!StringUtils.isAlphanumericSpace(value)) {
            FacesUtilities.invalidFormatError(FacesContext.getCurrentInstance(), componentId);
            error = FacesUtilities.INVALID_FORMAT_ERROR;
        }

        return error;
    }

    /**
     * The validateEmailAddress method validates the contents of
     * an email field.
     *
     * @param value the value of the inputTextArea
     * @param componentId the identifier assigned to the JSF component
     * @return null if there is no error, a populated error message if there is
     * an error detected
     */
    public String validateEmailAddress(String value, String componentId) {
        String error = null;

        if (StringUtils.isNotBlank(value)) {

            try {
                if (!Pattern.matches("[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}", value)) {
                    FacesUtilities.invalidEmailFormatError(FacesContext.getCurrentInstance(), componentId);
                    error = FacesUtilities.INVALID_EMAIL_ERROR;
                }
            } catch (PatternSyntaxException px) {
                LOG.warn(ErrorUtils.getStackTrace(px));
                error = FacesUtilities.INVALID_EMAIL_ERROR;
            } catch (Exception ex) {
                LOG.warn(ErrorUtils.getStackTrace(ex));
                error = FacesUtilities.INVALID_EMAIL_ERROR;
            }
        } else {
            FacesUtilities.requredFieldError(FacesContext.getCurrentInstance(), componentId);
            error = FacesUtilities.REQUIRED_ERROR;
        }

        return error;
    }

    private final Logger LOG = LogManager.getLogger(ProfileValidationBean.class);

}
