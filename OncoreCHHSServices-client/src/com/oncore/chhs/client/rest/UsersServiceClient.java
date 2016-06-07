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
import com.oncore.chhs.web.rest.client.AbstractRestClient;
import com.oncore.chhs.web.rest.response.InsertResponse;
import com.oncore.chhs.web.rest.response.SelectResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author OnCore LLC
 */
public class UsersServiceClient extends AbstractRestClient {

    private static final String USER_URL = "users.rest.url.json";

    /**
     * Gets the Users entity from the userId key.
     *
     * @param id The primary key.
     *
     * @return A User or null if not found.
     */
    public User getUser(Integer id) throws WebServiceException {
        User user = null;

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(USER_URL).
                    path("Users").path("find").queryParam("id", id);

            user = target.request(MediaType.APPLICATION_JSON)
                    .get(User.class);

        } catch (Throwable t) {
            throw new WebServiceException("Error occurred getting user.", t);
        }

        return user;
    }

    /**
     * Gets the user with the matching username.
     *
     * @param userName
     * @return a populated User object if found, null otherwise
     * @throws WebServiceException
     */
    public User authenticateUser(String userName) throws WebServiceException {

        User user = null;

        try {
            WebTarget target = this.getTarget(USER_URL).
                    path("Users").path("authenticate").queryParam("userName", userName);

            SelectResponse<User> response = target.request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<SelectResponse<User>>() {
                    });

            if (response.isErrorOccurred()) {
                throw new WebServiceException(response.getErrorMessage());
            }

            user = response.getResult();
        } catch (Throwable t) {
            throw new WebServiceException("Error occurred authenticating user.", t);
        }

        return user;
    }

    /**
     * Creates the user.
     *
     * @param user
     *
     * @return User object with created Users information.
     */
    public User createUser(User user) throws WebServiceException {

        User result = null;

        WebTarget target = this.getTarget(USER_URL).path("Users").path("createUser");

        try {
            InsertResponse<User> insertResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(user, MediaType.APPLICATION_JSON),
                            new GenericType<InsertResponse<User>>() {
                    });

            if (insertResponse.isErrorOccurred()) {
                throw new WebServiceException(insertResponse.getErrorMessage());
            }

            result = insertResponse.getResult();
        } catch (Throwable t) {
            throw new WebServiceException("Error occurred create user.", t);
        }

        return result;
    }
}
