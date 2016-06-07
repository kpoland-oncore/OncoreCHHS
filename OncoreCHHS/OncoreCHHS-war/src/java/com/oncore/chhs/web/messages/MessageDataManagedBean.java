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
package com.oncore.chhs.web.messages;

import com.oncore.chhs.client.dto.AllMessages;
import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.client.rest.MessagesServiceClient;
import com.oncore.chhs.web.exceptions.WebServiceException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author OnCore LLC
 */
@Named("messageDataManagedBean")
@RequestScoped
public class MessageDataManagedBean implements AbstractMessageDataManagedBean {

    private final Logger LOG = LogManager.getLogger(MessageDataManagedBean.class);

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing MessageDataManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying MessageDataManagedBean: " + this.getClass().hashCode());
    }

    /**
     * The fetchMessages method returns all messages for the 
     * specified user.
     * 
     * @param userUid a valid user identifier
     *
     * @return a populated AllMessages object
     *
     * @throws WebServiceException
     */
    @Override
    public AllMessages fetchMessages(Integer userUid) throws WebServiceException {
        return this.getMessagesServiceClient().getAllMessages(userUid);
    }

    /**
     * The sendMessage method sends a message from a user
     * to a case worker.
     *
     * @param messageBean a populated MessageBean object
     * @param users a populated User object
     * @throws WebServiceException
     */
    @Override
    public void sendMessage(MessageBean messageBean, User users) throws WebServiceException {
        this.getMessagesServiceClient().sendMessage(messageBean.getFrom(), messageBean.getTo(), messageBean.getMessage(), users.getUserUid().intValue());
    }

    /**
     * The getMessagesServiceClient method returns an instance of
     * MessagesServiceClient
     * 
     * @return MessagesServiceClient
     */
    public MessagesServiceClient getMessagesServiceClient() {

        return new MessagesServiceClient();
    }
}
