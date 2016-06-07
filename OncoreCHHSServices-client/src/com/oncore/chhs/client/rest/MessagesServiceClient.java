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
import com.oncore.chhs.client.dto.CreateMessage;
import com.oncore.chhs.web.rest.client.AbstractRestClient;
import com.oncore.chhs.web.rest.response.InsertResponse;
import com.oncore.chhs.web.rest.response.SelectResponse;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author OnCore LLC
 */
public class MessagesServiceClient extends AbstractRestClient {

    private static final String MESSAGES_URL = "messages.rest.url.json";

    /**
     * The sendMessage method sends a message.
     *
     * @param from message from
     * @param to message to
     * @param message a message to send
     * @param userUid the userUid
     */
    public void sendMessage(String from, String to, String message, Integer userUid) {
        InsertResponse<Long> insertResponse = null;

        WebTarget target = this.getTarget(MESSAGES_URL).
                path("Messages").path("send");

        CreateMessage createMessage = new CreateMessage();
        createMessage.setFrom(from);
        createMessage.setTo(to);
        createMessage.setMessage(message);
        createMessage.setUserUid(userUid);

        try {
            insertResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(createMessage, MediaType.APPLICATION_JSON),
                            new GenericType<InsertResponse<Long>>() {
                    });

            if (insertResponse.isErrorOccurred()) {
                throw new WebServiceException(insertResponse.getErrorMessage());
            }
        } catch (Throwable t) {
            throw new WebServiceException("Error occurred creating the message.", t);
        }
    }

    /**
     * The fetchMessages method fetches all messages from the inbox
     * and outbox for the user.
     *
     * @param userUid the entity representing the user
     *
     * @return a map containing list of populated inbox and outbox
     * MessageDTO objects if found, empty list otherwise
     */
    public AllMessages getAllMessages(Integer userUid) {

        AllMessages allmsgs = null;

        WebTarget target = this.getTarget(MESSAGES_URL).
                path("Messages").path("find").queryParam("id", userUid);
        try {
            SelectResponse<AllMessages> response = target.request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<SelectResponse<AllMessages>>() {
                    });

            if (response.isErrorOccurred()) {
                throw new WebServiceException(response.getErrorMessage());
            }

            allmsgs = response.getResult();

        } catch (Throwable t) {
            throw new WebServiceException("Error occurred getting all messages.", t);
        }

        return allmsgs;
    }
}
