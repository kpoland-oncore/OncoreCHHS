/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.rest;

import com.oncore.chhs.client.dto.AllMessages;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author oncore
 */
public class MessagesServiceClient {

    /**
     *
     *
     * @param from
     * @param to
     * @param message
     * @param userUid
     */
    public void sendMessage(String from, String to, String message, Integer userUid) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/OncoreCHHSServices-war/rest").
                path("Messages").path("send");

        Form form = new Form();
        form.param("from", from);
        form.param("to", to);
        form.param("message", message);
        form.param("id", userUid.toString());

        target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
    }

    /**
     *
     *
     * @param userUid
     *
     * @return
     */
    public AllMessages getAllMessages(Integer userUid) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/OncoreCHHSServices-war/rest").
                path("Messages").path("find").queryParam("id", userUid);

        AllMessages results
                = target.request(MediaType.APPLICATION_JSON)
                .get(AllMessages.class);

        return results;
    }
}
