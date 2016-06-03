/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Kerry O'Brien
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
