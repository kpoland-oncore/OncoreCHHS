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
package com.oncore.chhs.web.login;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.web.utils.FacesUtilities;
import com.oncore.chhs.web.base.BaseManagedBean;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.global.GlobalManagedBean;
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
@Named("loginManagedBean")
@ViewScoped
public class LoginManagedBean extends BaseManagedBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing LoginManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying LoginManagedBean: " + this.getClass().hashCode());
    }

    /**
     * The handleLoginButtonClickEvent method handles the click
     * event generated from the login button on the login page.
     *
     * @return a qualified URL or null if an exception occurs
     */
    public String handleLoginButtonClickEvent() {
        String page = null;
        Boolean isError = Boolean.FALSE;
        String error = null;

        try {
            FacesUtilities.removeMessages();
            this.getLoginBean().reset();

            if ((error = this.loginValidationBean.validateUserName(this.getLoginBean().getUserName(), FORM_NAME + "userNameTxt:input")) != null) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
                this.getLoginBean().setUserNameError(error);
            } else {
                User user = this.loginDataManagedBean.authenticateUser(loginBean);

                if (user != null) {

                    User users = new User();
                    users.setFirstName(user.getFirstName());
                    users.setMiddleName(user.getMiddleName());
                    users.setLastName(user.getLastName());
                    users.setUserName(user.getUserName());
                    users.setUserUid(user.getUserUid());

                    this.globalManagedBean.setAuthenticated(Boolean.TRUE);
                    this.globalManagedBean.setLoginText("Welcome " + user.getFirstName() + " " + user.getLastName());
                    this.globalManagedBean.setAuthenticatedUser(users);
                    page = this.navigationManagedBean.navigateToLink("index", Boolean.FALSE);
                } else {
                    FacesUtilities.createPageLevelCustomError(FacesContext.getCurrentInstance(), "The user name provided was not found.");
                }
            }
        } catch (WebServiceException wx) {
            LOG.error(wx);
            FacesUtilities.createPageLevelFatalError(FacesContext.getCurrentInstance());
        }

        return page;

    }

    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }

    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    @Inject
    AbstractLoginDataManagedBean loginDataManagedBean;

    @Inject
    LoginValidationBean loginValidationBean;
 
    @Inject
    protected GlobalManagedBean globalManagedBean;

    private LoginBean loginBean = new LoginBean();

    private final Logger LOG = LogManager.getLogger(LoginManagedBean.class);

}
