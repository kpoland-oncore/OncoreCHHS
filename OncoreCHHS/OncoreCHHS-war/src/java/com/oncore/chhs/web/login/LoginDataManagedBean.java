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
import com.oncore.chhs.client.rest.UsersServiceClient;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.profile.ProfileBean;
import com.oncore.chhs.web.utils.ErrorUtils;
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
@Named("loginDataManagedBean")
@RequestScoped
public class LoginDataManagedBean implements AbstractLoginDataManagedBean {

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(ProfileBean profileBean) throws WebServiceException {

        User user = new User();
        user.setFirstName(profileBean.getFirstName());
        user.setMiddleName(profileBean.getMiddleName());
        user.setLastName(profileBean.getLastName());
        user.setUserName(profileBean.getUserName());

        try {
            user = this.getUsersServiceClient().createUser(user);
        } catch (Exception ex) {
            throw new WebServiceException(ex.getCause().toString() + "::" + ErrorUtils.getStackTrace(ex));
        }

        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User authenticateUser(LoginBean loginBean) throws WebServiceException {

        User users = null;

        try {
            users = this.getUsersServiceClient().authenticateUser(loginBean.getUserName());
        } catch (Exception ex) {
            throw new WebServiceException(ex.getCause().toString() + "::" + ErrorUtils.getStackTrace(ex));
        }

        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing LoginDataManagedBean: " + this.getClass().hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying LoginDataManagedBean: " + this.getClass().hashCode());
    }

    private final Logger LOG = LogManager.getLogger(LoginDataManagedBean.class);

    /**
     *
     * @return UsersServiceClient
     */
    UsersServiceClient getUsersServiceClient() {

        return new UsersServiceClient();
    }
}
