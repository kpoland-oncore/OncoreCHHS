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

import com.oncore.chhs.web.base.BaseManagedBean;
import static com.oncore.chhs.web.base.BaseManagedBean.FORM_NAME;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.enums.ContactTypeEnum;
import com.oncore.chhs.web.exceptions.WebServiceException;
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
 * @author oncore
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
     * The <code>handleRegisterButtonClickEvent</code> method handles the click
     * event from the register button on the register screen.
     *
     * @return null if there is an exception, index if the operation is
     * successful
     */
    public String handleRegisterButtonClickEvent() {
        String page = null;
        Boolean isError = Boolean.FALSE;

        try {
            FacesUtilities.removeMessages();

            if (this.profileValidationBean.validateUserName(this.getProfileBean().getUserName(), FORM_NAME + "userNameTxt:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateName(this.getProfileBean().getFirstName(), Boolean.TRUE, FORM_NAME + "firstNameTxt:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateName(this.getProfileBean().getLastName(), Boolean.TRUE, FORM_NAME + "lastNameTxt:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateName(this.getProfileBean().getMiddleName(), Boolean.FALSE, FORM_NAME + "middleNameTxt:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateAddressData(this.getProfileBean().getAddressLine1(), Boolean.TRUE, FORM_NAME + "addressLine1Txt:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateAddressData(this.getProfileBean().getAddressLine2(), Boolean.FALSE, FORM_NAME + "addressLine2Txt:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateAddressData(this.getProfileBean().getCity(), Boolean.TRUE, FORM_NAME + "cityTxt:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateRequiredField(this.getProfileBean().getState(), FORM_NAME + "statesList:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateRequiredField(this.getProfileBean().getZip(), FORM_NAME + "zipMsk:input")) {
                isError = Boolean.TRUE;
            }
            if (this.profileValidationBean.validateEmailAddress(this.getProfileBean().getEmail(), FORM_NAME + "emailTxt:input")) {
                isError = Boolean.TRUE;
            }

            if (!isError) {
                // determine if user already exists
                LoginBean loginBean = new LoginBean();
                loginBean.setUserName(this.getProfileBean().getUserName());
                Users users = this.loginDataManagedBean.authenticateUser(loginBean);

                if (users == null) {
                    users = this.loginDataManagedBean.createUser(profileBean);

                    this.getProfileBean().setPhoneType(ContactTypeEnum.HOME_PHONE.getValue());

                    this.profileDataManagedBean.createProfile(profileBean, users);

                    this.globalManagedBean.setAuthenticated(Boolean.TRUE);
                    this.globalManagedBean.setLoginText("Welcome " + users.getUsrFirstname() + " " + users.getUsrLastname());
                    this.globalManagedBean.setAuthenticatedUser(users);

                   // FacesUtilities.runJavaScript("PF('saveDlgWdg').show();");

                    // page = this.navigationManagedBean.navigateToLink("index", Boolean.FALSE);
                    
                    FacesUtilities.createPageLevelSaveSuccess(FacesContext.getCurrentInstance());
                    
                } else {
                    FacesUtilities.createPageLevelCustomError(FacesContext.getCurrentInstance(), "The user name provided has already been taken. Please try a different user name.");
                }
            } else {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            }

        } catch (WebServiceException wx) {
            LOG.error(wx);
            FacesUtilities.createPageLevelFatalError(FacesContext.getCurrentInstance());
        }

        return page;
    }

    public String handleUpdateButtonClickEvent() {
        String page = null;

        try {
            FacesUtilities.removeMessages();

            if (this.profileValidationBean.validateName(this.getProfileBean().getFirstName(), Boolean.TRUE, FORM_NAME + "firstNameTxt")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateName(this.getProfileBean().getLastName(), Boolean.TRUE, FORM_NAME + "lastNameTxt")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateName(this.getProfileBean().getMiddleName(), Boolean.FALSE, FORM_NAME + "middleNameTxt")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateAddressData(this.getProfileBean().getAddressLine1(), Boolean.TRUE, FORM_NAME + "addressLine1Txt")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateAddressData(this.getProfileBean().getAddressLine2(), Boolean.FALSE, FORM_NAME + "addressLine2Txt")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateAddressData(this.getProfileBean().getCity(), Boolean.TRUE, FORM_NAME + "cityTxt")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateRequiredField(this.getProfileBean().getState(), FORM_NAME + "statesList")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateRequiredField(this.getProfileBean().getZip(), FORM_NAME + "zipMsk")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else if (this.profileValidationBean.validateEmailAddress(this.getProfileBean().getEmail(), FORM_NAME + "emailTxt")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else {
                // determine if user already exists
                LoginBean loginBean = new LoginBean();
                loginBean.setUserName(this.getProfileBean().getUserName());
                Users users = this.loginDataManagedBean.authenticateUser(loginBean);

                if (users == null) {
                    users = this.loginDataManagedBean.createUser(profileBean);

                    this.getProfileBean().setPhoneType(ContactTypeEnum.HOME_PHONE.getValue());

                    this.profileDataManagedBean.createProfile(profileBean, users);

                    this.globalManagedBean.setAuthenticated(Boolean.TRUE);
                    this.globalManagedBean.setLoginText("Welcome " + users.getUsrFirstname() + " " + users.getUsrLastname());
                    this.globalManagedBean.setAuthenticatedUser(users);

                    FacesUtilities.runJavaScript("PF('saveDlgWdg').show();");

                    // page = this.navigationManagedBean.navigateToLink("index", Boolean.FALSE);
                }
            }
        } catch (WebServiceException wx) {
            LOG.error(wx);
            FacesUtilities.createPageLevelFatalError(FacesContext.getCurrentInstance());
        }

        return page;
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

    @Inject
    AbstractProfileDataManagedBean profileDataManagedBean;

    @Inject
    AbstractLoginDataManagedBean loginDataManagedBean;

    @Inject
    ProfileValidationBean profileValidationBean;

    private ProfileBean profileBean = new ProfileBean();

    private final Logger LOG = LogManager.getLogger(ProfileManagedBean.class);

}
