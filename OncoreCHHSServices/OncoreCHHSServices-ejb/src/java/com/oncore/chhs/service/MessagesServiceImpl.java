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
package com.oncore.chhs.service;

import com.oncore.chhs.client.dto.AllMessages;
import com.oncore.chhs.client.dto.Message;
import com.oncore.chhs.client.ejb.MessagesService;
import com.oncore.chhs.ejb.BusinessServiceInterceptor;
import com.oncore.chhs.persistence.dao.MessagesDAO;
import com.oncore.chhs.persistence.dao.UserDAO;
import com.oncore.chhs.persistence.entity.Messages;
import com.oncore.chhs.persistence.entity.Users;
import com.oncore.chhs.service.converter.MessagesToMessageDTOConverter;
import com.oncore.chhs.service.helper.MessagesHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author OnCore LLC
 */
@Stateless
@Remote(MessagesService.class)
@Interceptors({BusinessServiceInterceptor.class})
public class MessagesServiceImpl implements MessagesService {

    public static List<String> MESSAGES_RESPONSE = Arrays.asList(
            "Thank you for your question. We will get back to you within 5 business days.");

    @EJB
    private UserDAO userDAO;

    @EJB
    private MessagesDAO messagesDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(String from, String to, String messageTxt, Integer userUid) {

        Users users = this.userDAO.findById(userUid);
        this.createMessages(from, to, messageTxt, false, users);
        this.createMessages(to, from, this.getRandomResponse(), true, users);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AllMessages getAllMessages(Integer userUid) {
        AllMessages allMessages = new AllMessages();

        Users users = this.userDAO.findById(userUid);

        List<Message> inboundMsgs = this.getInbounds(users.getMessagesSet());

        if (CollectionUtils.isNotEmpty(inboundMsgs)) {
            allMessages.getInboundMessages().addAll(inboundMsgs);
        }

        List<Message> outboundMsgs = this.getOutbounds(users.getMessagesSet());

        if (CollectionUtils.isNotEmpty(outboundMsgs)) {
            allMessages.getOutboundMessages().addAll(outboundMsgs);
        }

        return allMessages;
    }

    /**
     * Gets the random responses from predefined texts.
     *
     * @return response
     */
    private String getRandomResponse() {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(MESSAGES_RESPONSE.size());

        return MESSAGES_RESPONSE.get(index);
    }

    /**
     * Creates the messages from using MessageBean.
     *
     * @param from
     * @param to
     * @param messageTxt
     * @param isInbound
     * @param users
     *
     */
    private void createMessages(String from, String to, String messageTxt, boolean isInbound, Users users) {
        Messages messages = new Messages();

        messages.setMsgFrom(from);
        messages.setMsgTo(to);
        messages.setMsgText(messageTxt);
        messages.setMsgToUserInd(isInbound);
        messages.setMsgCreatedTs(new Date());
        messages.setUsrUidFk(users);
        messages.setCreateTs(new Date());
        messages.setCreateUserId(MessagesHelper.getFormattedName(users));
        messages.setUpdateTs(new Date());
        messages.setUpdateUserId(MessagesHelper.getFormattedName(users));

        if (null == users.getMessagesSet()) {
            users.setMessagesSet(new HashSet<>());
        }

        Messages createdMessages = this.messagesDAO.create(messages);

        users.getMessagesSet().add(createdMessages);
    }

    /**
     *
     *
     * @param msgs
     *
     * @return
     */
    private List<Message> getInbounds(Set<Messages> msgs) {
        List<Message> msgBeans = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(msgs)) {
            for (Messages msg : msgs) {
                if (msg.getMsgToUserInd()) {
                    msgBeans.add(MessagesToMessageDTOConverter.convertMessageBeansFromMessages(msg));
                }
            }
        }

        return msgBeans;
    }

    /**
     *
     *
     * @param msgs
     *
     * @return
     */
    private List<Message> getOutbounds(Set<Messages> msgs) {
        List<Message> msgBeans = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(msgs)) {
            for (Messages msg : msgs) {
                if (!msg.getMsgToUserInd()) {
                    msgBeans.add(MessagesToMessageDTOConverter.convertMessageBeansFromMessages(msg));
                }
            }
        }

        return msgBeans;
    }
}
