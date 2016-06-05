/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service.web.rest;

import com.oncore.chhs.client.dto.AllMessages;
import com.oncore.chhs.client.dto.CreateMessage;
import com.oncore.chhs.ejb.EJBUtils;
import com.oncore.chhs.web.rest.response.InsertResponse;
import com.oncore.chhs.web.rest.response.SelectResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/**
 *
 * @author oncore
 */
@Provider
@Path("Messages")
public class MessagesService {

    private static final Logger LOGGER = Logger.getLogger(MessagesService.class);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MessagesService
     */
    public MessagesService() {
    }

    /**
     *
     * @param message The message.
     *
     * @return InsertResponse.
     */
    @POST
    @Path("/send/")
    @Consumes({MediaType.APPLICATION_JSON})
    public InsertResponse sendMessage(CreateMessage createMessage) {
        InsertResponse insertResponse = null;

        try {
            this.getEjbMessagesService().sendMessage(createMessage.getFrom(), createMessage.getTo(),
                    createMessage.getMessage(), createMessage.getUserUid());

            insertResponse = new InsertResponse(1);
        } catch (Throwable t) {
            String errorMsg = "Error creating message.";

            LOGGER.error(errorMsg, t);
            insertResponse = new InsertResponse(errorMsg, t);
        }

        return insertResponse;
    }

    /**
     *
     *
     * @param userUid
     *
     * @return
     */
    @GET
    @Path("/find/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SelectResponse<AllMessages> getAllMessages(@QueryParam("id") Integer userUid) {

        SelectResponse<AllMessages> response = null;

        try {
            AllMessages allMessages = this.getEjbMessagesService().getAllMessages(userUid);

            response = new SelectResponse<>(allMessages);

        } catch (Throwable t) {
            String errorMsg = "Error getting all messages.";

            LOGGER.error(errorMsg, t);
            response = new SelectResponse<>(errorMsg, t);
        }

        return response;
    }

    /**
     *
     * @return
     */
    private com.oncore.chhs.client.ejb.MessagesService getEjbMessagesService() {
        return EJBUtils.lookupEJB(com.oncore.chhs.client.ejb.MessagesService.class);
    }
}
