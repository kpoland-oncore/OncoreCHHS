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
package com.oncore.chss.web.login;

import com.oncore.chhs.utils.FacesUtilities;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chss.web.base.BaseManagedBean;
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
     * The <code>handleLoginButtonClickEvent</code> method handles the click
     * event generated from the login button on the login page.
     *
     * @return a qualified URL or null if an exception occurs
     */
    public String handleLoginButtonClickEvent() {
        String page = null;

        if (this.loginValidationBean.validateUserName(this.getLoginBean().getUserName(), FORM_NAME + "userNameTxt")) {
            FacesUtilities.createPageLevelError(FacesContext.getCurrentInstance());
        } else {
            Users users = this.loginDataManagedBean.authenticateUser(loginBean);

            if (users != null) {
                this.globalMangedBean.setAuthenticated(Boolean.TRUE);
                this.globalMangedBean.setLoginText("Welcome " + users.getUsrFirstname() + " " + users.getUsrLastname());
                page = this.navigationManagedBean.navigateToLink("index", Boolean.FALSE);
            }
            else
            {
                 FacesUtilities.createPageLevelError(FacesContext.getCurrentInstance());
            }
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
    LoginDataManagedBean loginDataManagedBean;

    @Inject
    LoginValidationBean loginValidationBean;

    private LoginBean loginBean = new LoginBean();

    private final Logger LOG = LogManager.getLogger(LoginManagedBean.class);

}
