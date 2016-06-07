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
package com.oncore.chhs.service.converter;

import com.oncore.chhs.client.dto.Message;
import com.oncore.chhs.persistence.entity.Messages;

/**
 *
 * @author OnCore LLC
 */
public class MessagesToMessageDTOConverter {

    /**
     * Builds the MessageBean to display on the page.
     *
     * @param msg
     *
     * @return all messages.
     */
    public static Message convertMessageBeansFromMessages(Messages msg) {

        Message msgDto = new Message();
        msgDto.setFrom(msg.getMsgFrom());
        msgDto.setTo(msg.getMsgTo());
        msgDto.setMessage(msg.getMsgText());
        msgDto.setReceivedDate(msg.getMsgCreatedTs());
        msgDto.setSentDate(msg.getMsgCreatedTs());

        return msgDto;
    }
}
