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

import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.base.BaseManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oncore
 */
@Named("globalManagedBean")
@SessionScoped
public class GlobalManagedBean extends BaseManagedBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing GlobalManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying GlobalManagedBean: " + this.getClass().hashCode());
    }

    /**
     * @return the authenticated
     */
    public Boolean getAuthenticated() {
        return authenticated;
    }

    /**
     * @param authenticated the authenticated to set
     */
    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    /**
     * @return the loginText
     */
    public String getLoginText() {
        return loginText;
    }

    /**
     * @param loginText the loginText to set
     */
    public void setLoginText(String loginText) {
        this.loginText = loginText;
    }

    /**
     * @return the authenticatedUser
     */
    public Users getAuthenticatedUser() {
        return authenticatedUser;
    }

    /**
     * @param authenticatedUser the authenticatedUser to set
     */
    public void setAuthenticatedUser(Users authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    /**
     * @return the calculatedUserFullName
     */
    public String getCalculatedUserFullName() {

        if (this.getAuthenticatedUser() != null) {
            this.calculatedUserFullName = StringUtils.EMPTY;

            if (StringUtils.isNotBlank(this.getAuthenticatedUser().getUsrFirstname())) {
                this.calculatedUserFullName += this.getAuthenticatedUser().getUsrFirstname();
            }

            if (StringUtils.isNotBlank(this.getAuthenticatedUser().getUsrLastname())) {
                this.calculatedUserFullName += " " + this.getAuthenticatedUser().getUsrLastname();
            }

        }
        
        return calculatedUserFullName;
    }

    /**
     * @param calculatedUserFullName the calculatedUserFullName to set
     */
    public void setCalculatedUserFullName(String calculatedUserFullName) {
        this.calculatedUserFullName = calculatedUserFullName;
    }

    private Boolean authenticated = Boolean.FALSE;
    private String loginText = "Login";
    private Users authenticatedUser;
    private String calculatedUserFullName;

    private final Logger LOG = LogManager.getLogger(GlobalManagedBean.class);

}
