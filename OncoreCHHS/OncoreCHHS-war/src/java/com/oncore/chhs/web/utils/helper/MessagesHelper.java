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

import com.oncore.chhs.web.entities.Messages;
import com.oncore.chhs.web.messages.MessageBean;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author oncore
 */
public class MessagesHelper {

    public static List<String> MESSAGES_RESPONSE = Arrays.asList("Thank you for your question.",
            "Thank you for your question. We will get back to you within 5 business days.");

    /**
     * Gets the random responses from predefined texts.
     *
     * @return response
     */
    public static String getRandomResponse() {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(MESSAGES_RESPONSE.size());

        return MESSAGES_RESPONSE.get(index);

    }

    /**
     * Builds the MessageBean to display on the page.
     *
     * @param messages
     *
     * @return all messages.
     */
    public static MessageBean buildMessageBeansFromMessages(Messages msg) {

        MessageBean msgBean = new MessageBean();
        msgBean.setFrom(msg.getMsgFrom());
        msgBean.setTo(msg.getMsgTo());
        msgBean.setMessage(msg.getMsgText());
        msgBean.setReceivedDate(msg.getMsgCreatedTs());
        msgBean.setSentDate(msg.getMsgCreatedTs());

        return msgBean;
    }
}
