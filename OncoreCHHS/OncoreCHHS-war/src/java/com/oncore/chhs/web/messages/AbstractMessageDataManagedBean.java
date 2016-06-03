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
import com.oncore.chhs.web.base.AbstractBaseManagedBean;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.exceptions.WebServiceException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oncore
 */
public interface AbstractMessageDataManagedBean extends AbstractBaseManagedBean {

    /**
     * The <code>fetchMessages</code> method fetches all messages from the inbox
     * and outbox for the user.
     *
     * @param userUid the entity representing the user
     *
     * @return a map containing list of populated inbox and outbox
     * <code>MessageBean</code> objects if found, empty list otherwise
     * @throws WebServiceException
     */
    public AllMessages fetchMessages(Integer userUid) throws WebServiceException;

    /**
     * The <code>sendMessage</code> method sends a message.
     *
     * @param messageBean a message to send
     * @param users the entity representing the user
     *
     * @throws WebServiceException
     */
    public void sendMessage(MessageBean messageBean, Users users) throws WebServiceException;
}
