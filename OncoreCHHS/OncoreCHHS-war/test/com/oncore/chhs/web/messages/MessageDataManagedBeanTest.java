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
import com.oncore.chhs.client.dto.Message;
import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.client.rest.MessagesServiceClient;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author OnCore LLC
 */
public class MessageDataManagedBeanTest {

    /**
     * Test of fetchMessages method, of class MessageDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testFetchMessages() throws Exception {

        AllMessages expected = new AllMessages();
        List<Message> inbound = new ArrayList<>();
        expected.setInboundMessages(inbound);

        MessagesServiceClient mockService = mock(MessagesServiceClient.class);
        when(mockService.getAllMessages(1)).thenReturn(expected);

        MessageDataManagedBean instance = spy(new MessageDataManagedBean());
        when(instance.getMessagesServiceClient()).thenReturn(mockService);

        AllMessages actual = instance.fetchMessages(1);

        assertEquals(expected, actual);
    }

    /**
     * Test of sendMessage method, of class MessageDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testSendMessage() throws Exception {

        MessagesServiceClient mockService = mock(MessagesServiceClient.class);

        MessageDataManagedBean instance = spy(new MessageDataManagedBean());
        when(instance.getMessagesServiceClient()).thenReturn(mockService);

        MessageBean messageBean = new MessageBean();
        messageBean.setFrom("from");
        messageBean.setTo("to");
        messageBean.setMessage("message");
        
        User user = new User();
        user.setUserUid(1L);
        
        instance.sendMessage(messageBean, user);
        verify( mockService ).sendMessage("from", "to", "message", 1 );
    }
}
