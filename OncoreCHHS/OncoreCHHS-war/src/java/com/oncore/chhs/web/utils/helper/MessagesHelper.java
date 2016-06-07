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
package com.oncore.chhs.web.utils.helper;

import com.oncore.chhs.client.dto.AllMessages;
import com.oncore.chhs.client.dto.Message;
import com.oncore.chhs.web.messages.MessageBean;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author OnCore LLC
 */
public class MessagesHelper {

    /**
     * The getInbouds method uses the data fetched from the underlying datastore
     * for a user and iterates through it preparing a list of messages for
     * return to the UI level.
     *
     * @param allMsgs an AllMessages object containing
     * @return a populated List of messages
     */
    public static List<MessageBean> getInbounds(AllMessages allMsgs) {
        List<MessageBean> msgBeans = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(allMsgs.getInboundMessages())) {
            for (Message msg : allMsgs.getInboundMessages()) {
                msgBeans.add(buildMessageBeansFromMessages(msg));
            }
        }

        return msgBeans;
    }

    /**
     * The getOutbounds method uses the data fetched from the underlying
     * datastore for a user and iterates through it preparing a list of messages
     * for return to the UI level.
     *
     * @param allMsgs an AllMessages object containing
     * @return a populated List of messages
     */
    public static List<MessageBean> getOutbounds(AllMessages allMsgs) {
        List<MessageBean> msgBeans = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(allMsgs.getOutboundMessages())) {
            for (Message msg : allMsgs.getOutboundMessages()) {
                msgBeans.add(buildMessageBeansFromMessages(msg));
            }
        }

        return msgBeans;
    }

    /**
     * Builds the MessageBean to display on the page.
     *
     * @param messages
     *
     * @return all messages.
     */
    private static MessageBean buildMessageBeansFromMessages(Message msgDto) {

        MessageBean msgBean = new MessageBean();
        msgBean.setFrom(msgDto.getFrom());
        msgBean.setTo(msgDto.getTo());
        msgBean.setMessage(msgDto.getMessage());
        msgBean.setReceivedDate(msgDto.getReceivedDate());
        msgBean.setSentDate(msgDto.getSentDate());

        return msgBean;
    }
}
