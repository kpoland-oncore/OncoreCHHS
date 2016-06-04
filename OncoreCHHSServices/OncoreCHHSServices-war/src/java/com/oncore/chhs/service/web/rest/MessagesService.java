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
package com.oncore.chhs.service.web.rest;

import com.oncore.chhs.client.dto.AllMessages;
import com.oncore.chhs.ejb.EJBUtils;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author oncore
 */
@Provider
@Path("Messages")
public class MessagesService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MessagesService
     */
    public MessagesService() {
    }

    /**
     *
     * @param from
     * @param to
     * @param messageTxt
     * @param userUid
     */
    @POST
    @Path("/send/")
    @Consumes({MediaType.APPLICATION_JSON})
    public void sendMessage(@FormParam("from") String from, @FormParam("to") String to,
            @FormParam("message") String messageTxt, @FormParam("id") Integer userUid) {
        this.getEjbMessagesService().sendMessage(from, to, messageTxt, userUid);
    }

    /**
     *
     * @param userUid
     *
     * @return
     */
    @GET
    @Path("/find/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AllMessages getAllMessages(@QueryParam("id") Integer userUid) {
        AllMessages allMessages = this.getEjbMessagesService().getAllMessages(userUid);

        return allMessages;
    }

    /**
     *
     * @return
     */
    private com.oncore.chhs.client.ejb.MessagesService getEjbMessagesService() {
        return EJBUtils.lookupEJB(com.oncore.chhs.client.ejb.MessagesService.class);
    }
}
