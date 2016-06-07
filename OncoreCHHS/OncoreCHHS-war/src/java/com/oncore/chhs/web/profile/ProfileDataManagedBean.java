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
import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.client.rest.ProfileServiceClient;
import com.oncore.chhs.web.base.BaseManagedBean;
import com.oncore.chhs.web.utils.helper.ProfileHelper;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class invokes RESTful services to find, create and update profiles.
 *
 * @author OnCore LLC
 */
@Named("profileDataManagedBean")
@RequestScoped
public class ProfileDataManagedBean extends BaseManagedBean implements AbstractProfileDataManagedBean {

    private final Logger LOG = LogManager.getLogger(ProfileDataManagedBean.class);

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing ProfileDataManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying ProfileDataManagedBean: " + this.getClass().hashCode());
    }

    /**
     * Finds the profile information for the user using the userUid.
     *
     * @param userUid
     *
     * @return Profile
     */
    @Override
    public Profile findProfileByUserUid(Integer userUid) {

        return this.getProfileServiceClient().findProfileByUserUid(userUid);
    }

    /**
     * Creates the profile for the user.
     *
     * @param profileBean The profile data collected from the UI.
     * @param user The user to associate the profile with.
     */
    @Override
    public void createProfile(ProfileBean profileBean, User user) {

        Profile profile = ProfileHelper.convertProfileBeanToProfileDTO(profileBean);

        this.getProfileServiceClient().createProfile(profile, user);
    }

    /**
     * Updates the profile information for the user.
     *
     * @param profileBean The profile data collected from the UI.
     * @param user The user the profile is associated with.
     */
    @Override
    public void updateProfile(ProfileBean profileBean, User user) {
        Profile profile = ProfileHelper.convertProfileBeanToProfileDTO(profileBean);

        this.getProfileServiceClient().updateProfile(profile, user);
    }

    /**
     * This gets an instance of the profile service client, which will invoke
     * the RESTful services. Package level getter to allow invocation by unit
     * tests.
     *
     * @return The profile service REST client.
     */
    ProfileServiceClient getProfileServiceClient() {
        return new ProfileServiceClient();
    }
}
