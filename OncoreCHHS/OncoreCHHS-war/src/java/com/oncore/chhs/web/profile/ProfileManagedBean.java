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

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.web.base.BaseManagedBean;
import static com.oncore.chhs.web.base.BaseManagedBean.FORM_NAME;
import com.oncore.chhs.web.enums.ContactTypeEnum;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.global.GlobalManagedBean;
import com.oncore.chhs.web.login.AbstractLoginDataManagedBean;
import com.oncore.chhs.web.login.LoginBean;
import com.oncore.chhs.web.utils.FacesUtilities;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author OnCore LLC
 */
@Named("profileManagedBean")
@ViewScoped
public class ProfileManagedBean extends BaseManagedBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing ProfileManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying ProfileManagedBean: " + this.getClass().hashCode());
    }

    /**
     * The handleRegisterButtonClickEvent method handles the click
     * event from the register button on the register screen.
     */
    public void handleRegisterButtonClickEvent() {

        try {

            FacesUtilities.removeMessages();
            this.getProfileBean().reset();

            Boolean isError = validateFields();

            if (!isError) {
                // determine if user already exists
                LoginBean loginBean = new LoginBean();
                loginBean.setUserName(this.getProfileBean().getUserName());

                User user = this.loginDataManagedBean.authenticateUser(loginBean);

                if (user == null) {

                    user = this.loginDataManagedBean.createUser(profileBean);

                    this.getProfileBean().setPhoneType(ContactTypeEnum.HOME_PHONE.getValue());

                    this.profileDataManagedBean.createProfile(profileBean, user);

                    this.globalManagedBean.setAuthenticated(Boolean.TRUE);
                    this.globalManagedBean.setLoginText("Welcome " + user.getFirstName() + " " + user.getLastName());
                    this.globalManagedBean.setAuthenticatedUser(user);

                    this.setIsAddMode(Boolean.FALSE);

                    FacesUtilities.createPageLevelCustomInfo(FacesContext.getCurrentInstance(), FacesUtilities.THANK_YOU_PROFILE_MESSAGE);

                } else {
                    FacesUtilities.createPageLevelCustomError(FacesContext.getCurrentInstance(), FacesUtilities.USER_ALREADY_TAKEN_MESSAGE);
                }
            } else {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            }

        } catch (WebServiceException wx) {
            LOG.error(wx);
            FacesUtilities.createPageLevelFatalError(FacesContext.getCurrentInstance());
        }
    }

    /**
     * The validateFields method validates the input fields on the
     * register page.
     *
     * @param isError true if an error is detected, false otherwise
     *
     * @return
     */
    private Boolean validateFields() {
        Boolean isError = Boolean.FALSE;
        String error;

        if ((error = this.profileValidationBean.validateUserName(this.getProfileBean().getUserName(), FORM_NAME + "userNameTxt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setUserNameError(error);
        }

        if ((error = this.profileValidationBean.validateName(this.getProfileBean().getFirstName(), Boolean.TRUE, FORM_NAME + "firstNameTxt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setFirstNameError(error);
        }
        if ((error = this.profileValidationBean.validateName(this.getProfileBean().getLastName(), Boolean.TRUE, FORM_NAME + "lastNameTxt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setLastNameError(error);
        }
        if ((error = this.profileValidationBean.validateName(this.getProfileBean().getMiddleName(), Boolean.FALSE, FORM_NAME + "middleNameTxt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setMiddleNameError(error);
        }
        if ((error = this.profileValidationBean.validateAddressData(this.getProfileBean().getAddressLine1(), Boolean.TRUE, FORM_NAME + "addressLine1Txt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setAddressLine1Error(error);
        }
        if ((error = this.profileValidationBean.validateAddressData(this.getProfileBean().getAddressLine2(), Boolean.FALSE, FORM_NAME + "addressLine2Txt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setAddressLine2Error(error);
        }
        if ((error = this.profileValidationBean.validateAddressData(this.getProfileBean().getCity(), Boolean.TRUE, FORM_NAME + "cityTxt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setCityError(error);
        }
        if ((error = this.profileValidationBean.validateRequiredDropDownField(this.getProfileBean().getState(), FORM_NAME + "statesList:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setStateError(error);
        }
        if ((error = this.profileValidationBean.validateRequiredField(this.getProfileBean().getZip(), FORM_NAME + "zipMsk:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setZipError(error);
        }
        if ((error = this.profileValidationBean.validateRequiredField(this.getProfileBean().getPhone(), FORM_NAME + "phoneMsk:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setPhoneError(error);
        }
        if ((error = this.profileValidationBean.validateEmailAddress(this.getProfileBean().getEmail(), FORM_NAME + "emailTxt:input")) != null) {
            isError = Boolean.TRUE;
            this.getProfileBean().setEmailError(error);
        }
        return isError;
    }

    /**
     * @return the profileBean
     */
    public ProfileBean getProfileBean() {
        return profileBean;
    }

    /**
     * @param profileBean the profileBean to set
     */
    public void setProfileBean(ProfileBean profileBean) {
        this.profileBean = profileBean;
    }

    /**
     * @return the isAddMode
     */
    public Boolean getIsAddMode() {
        return isAddMode;
    }

    /**
     * @param isAddMode the isAddMode to set
     */
    public void setIsAddMode(Boolean isAddMode) {
        this.isAddMode = isAddMode;
    }

    @Inject
    AbstractProfileDataManagedBean profileDataManagedBean;

    @Inject
    AbstractLoginDataManagedBean loginDataManagedBean;

    @Inject
    ProfileValidationBean profileValidationBean;

    @Inject
    protected GlobalManagedBean globalManagedBean;

    private ProfileBean profileBean = new ProfileBean();

    private Boolean isAddMode = Boolean.TRUE;

    private final Logger LOG = LogManager.getLogger(ProfileManagedBean.class);

}
