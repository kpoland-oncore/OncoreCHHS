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
package com.oncore.chhs.client.rest;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.client.dto.profile.CreateOrUpdateProfile;
import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.web.rest.client.AbstractRestClient;
import com.oncore.chhs.web.rest.response.InsertResponse;
import com.oncore.chhs.web.rest.response.SelectResponse;
import com.oncore.chhs.web.rest.response.UpdateResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceException;

/**
 * This service client invokes RESTful services for the Profile functionality.
 *
 * @author OnCore LLC
 */
public class ProfileServiceClient extends AbstractRestClient {

    private static final String PROFILE_URL = "profile.rest.url.json";

    /**
     * Find the user's profile.
     *
     * @param userUid The user's unique ID.
     *
     * @return The user's profile.
     */
    public Profile findProfileByUserUid(Integer userUid) {

        Profile profile = null;

        WebTarget target = this.getTarget(PROFILE_URL).
                path("Profile").path("find").queryParam("id", userUid);

        try {
            SelectResponse<Profile> response = target.request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<SelectResponse<Profile>>() {
                    });

            if (response.isErrorOccurred()) {
                throw new WebServiceException(response.getErrorMessage());
            }

            profile = response.getResult();

        } catch (Throwable t) {
            throw new WebServiceException("Error occurred finding the user's profile.", t);
        }

        return profile;
    }

    /**
     * Create a new user profile, including address and contact information.
     *
     * @param profile The profile data.
     * @param user The user to add the profile to.
     */
    public void createProfile(Profile profile, User user) {
        InsertResponse response = null;

        WebTarget target = this.getTarget(PROFILE_URL).path("Profile").path("create");

        CreateOrUpdateProfile newProfile = new CreateOrUpdateProfile();
        newProfile.addProfile(profile);
        newProfile.setUserUid(user.getUserUid().intValue());

        try {
            response = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(newProfile, MediaType.APPLICATION_JSON),
                            new GenericType<InsertResponse<Integer>>() {
                    });

            if (response.isErrorOccurred()) {
                throw new WebServiceException(response.getErrorMessage());
            }
        } catch (Throwable t) {
            throw new WebServiceException("Error occurred creating the profile.", t);
        }
    }

    /**
     * Update a user profile, including address and contact information.
     *
     * @param profile The profile data.
     * @param user The user whose profile needs updating.
     */
    public void updateProfile(Profile profile, User user) {
        UpdateResponse response = null;

        WebTarget target = this.getTarget(PROFILE_URL).path("Profile").path("update");

        CreateOrUpdateProfile updatedProfile = new CreateOrUpdateProfile();
        updatedProfile.addProfile(profile);
        updatedProfile.setUserUid(user.getUserUid().intValue());

        try {
            response = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(updatedProfile, MediaType.APPLICATION_JSON),
                            UpdateResponse.class);

            if (response.isErrorOccurred()) {
                throw new WebServiceException(response.getErrorMessage());
            }
        } catch (Throwable t) {
            throw new WebServiceException("Error occurred updating the profile.", t);
        }
    }

}
