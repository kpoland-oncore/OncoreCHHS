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
