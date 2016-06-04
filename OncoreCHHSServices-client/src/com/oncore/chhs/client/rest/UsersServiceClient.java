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

import com.oncore.chhs.client.dto.Summaries;
import com.oncore.chhs.client.dto.User;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Client;

/**
 *
 * @author oncore
 */
public class UsersServiceClient {

    public Summaries searchUsers(String lastName, String firstName) {
        // Complex - not sure yet.
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/OncoreCHHSServices-war/rest").
                path("Users").path("search").queryParam("lastName", lastName).
                queryParam("firstName", firstName);

        Object results
                = target.request(MediaType.APPLICATION_XML)
                .get(Summaries.class);

        return (Summaries) results;
    }

    public User getUser(Integer id) {
        // Complex - not sure yet.
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/OncoreCHHSServices-war/rest").
                path("Users").path("find").queryParam("id", id);

        User results
                = target.request(MediaType.APPLICATION_XML)
                .get(User.class);

        return results;
    }

    /**
     *
     * @param userName
     *
     * @return
     */
    public User authenticateUser(String userName) {

        User result = null;
        try {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/OncoreCHHSServices-war/rest").
                    path("Users").path("authenticate").queryParam("userName", userName);

            result = target.request(MediaType.APPLICATION_JSON)
                    .get(User.class);
        } catch (Exception e) {
//TODO have proper handling.
        }

        return result;
    }
}
