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

import com.oncore.chhs.web.entities.Messages;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.services.MessagesFacadeREST;
import com.oncore.chhs.web.services.UsersFacadeREST;
import com.oncore.chhs.web.utils.helper.MessagesHelper;
import static com.oncore.chhs.web.utils.helper.ProfileHelper.getFormattedName;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oncore
 */
@Named("messageDataManagedBean")
@RequestScoped
public class MessageDataManagedBean implements AbstractMessageDataManagedBean {

    private final Logger LOG = LogManager.getLogger(MessageDataManagedBean.class);

    @EJB
    private MessagesFacadeREST messagesFacadeREST;

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

    @Override
    public List<MessageBean> fetchInbox(Users users, Date oldestDate) throws WebServiceException {
        return this.getInbounds(users.getMessagesList());
    }

    @Override
    public List<MessageBean> fetchOutbox(Users users, Date oldestDate) throws WebServiceException {
        return this.getOutbounds(users.getMessagesList());
    }

    @Override
    public void sendMessage(MessageBean messageBean, Users users) throws WebServiceException {
        this.createMessages(messageBean.getFrom(), messageBean.getTo(), messageBean.getMessage(), true, users);
        this.createMessages(messageBean.getTo(), messageBean.getFrom(), MessagesHelper.getRandomResponse(), false, users);
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
        messages.setCreateUserId(getFormattedName(users));
        messages.setUpdateTs(new Date());
        messages.setUpdateUserId(getFormattedName(users));

        if (null == users.getMessagesList()) {
            users.setMessagesList(new ArrayList<>());
        }

        users.getMessagesList().add(messages);

        this.messagesFacadeREST.create(messages);
    }

    /**
     *
     *
     * @param msgs
     *
     * @return
     */
    private List<MessageBean> getInbounds(List<Messages> msgs) {
        List<MessageBean> msgBeans = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(msgs)) {
            for (Messages msg : msgs) {
                if (msg.getMsgToUserInd()) {
                    msgBeans.add(MessagesHelper.buildMessageBeansFromMessages(msg));
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
    private List<MessageBean> getOutbounds(List<Messages> msgs) {
        List<MessageBean> msgBeans = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(msgs)) {
            for (Messages msg : msgs) {
                if (!msg.getMsgToUserInd()) {
                    msgBeans.add(MessagesHelper.buildMessageBeansFromMessages(msg));
                }
            }
        }

        return msgBeans;
    }
}
