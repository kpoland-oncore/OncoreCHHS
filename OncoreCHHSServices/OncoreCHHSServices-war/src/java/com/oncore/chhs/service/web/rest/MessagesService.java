/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
