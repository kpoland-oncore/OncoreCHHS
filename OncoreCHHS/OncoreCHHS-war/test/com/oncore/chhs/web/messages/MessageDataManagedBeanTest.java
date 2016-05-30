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
import com.oncore.chhs.web.services.MessagesFacadeREST;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 *
 * @author oncore
 */
public class MessageDataManagedBeanTest {
    
    private Users testUser = null;
    
    public MessageDataManagedBeanTest() {
        
        this.testUser = new Users();
        
        List<Messages> messages = new ArrayList<>();
        this.testUser.setMessagesList(messages);
        
        Messages firstIn = new Messages();
        firstIn.setMsgFrom("first in message from");
        firstIn.setMsgText("first in message text");
        firstIn.setMsgToUserInd(true);
        firstIn.setUsrUidFk(this.testUser);
        messages.add(firstIn);
        
        Messages firstOut = new Messages();
        firstOut.setMsgTo("first out message to");
        firstOut.setMsgText("first out message text");
        firstOut.setMsgToUserInd(false);
        firstOut.setUsrUidFk(this.testUser);
        messages.add(firstOut);
        
        Messages secondIn = new Messages();
        secondIn.setMsgFrom("second in message from");
        secondIn.setMsgText("second in message text");
        secondIn.setMsgToUserInd(true);
        secondIn.setUsrUidFk(this.testUser);
        messages.add(secondIn);
        
        Messages secondOut = new Messages();
        secondOut.setMsgTo("second out message to");
        secondOut.setMsgText("second out message text");
        secondOut.setMsgToUserInd(false);
        secondOut.setUsrUidFk(this.testUser);
        messages.add(secondOut);
    }
    
    

    /**
     * Test of fetchInbox method, of class MessageDataManagedBean.
     * 
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testFetchInbox() throws Exception {
        
        MessageDataManagedBean instance = new MessageDataManagedBean();
        List<MessageBean> result = instance.fetchInbox(this.testUser, new Date() );
        
        assertNotNull( result );
        assertEquals( 2, result.size() );
        
        MessageBean firstIn = result.get(0);
        assertEquals( "first in message from", firstIn.getFrom() );
        assertEquals( "first in message text", firstIn.getMessage() );
        
        MessageBean secondIn = result.get(1);
        assertEquals( "second in message from", secondIn.getFrom() );
        assertEquals( "second in message text", secondIn.getMessage() );
    }

    /**
     * Test of fetchOutbox method, of class MessageDataManagedBean.
     * 
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testFetchOutbox() throws Exception {
        MessageDataManagedBean instance = new MessageDataManagedBean();
        List<MessageBean> result = instance.fetchOutbox(this.testUser, new Date() );
        
        assertNotNull( result );
        assertEquals( 2, result.size() );
        
        MessageBean firstOut = result.get(0);
        assertEquals("first out message to", firstOut.getTo() );
        assertEquals("first out message text", firstOut.getMessage() );
        
        MessageBean secondOut = result.get(1);
        assertEquals("second out message to", secondOut.getTo() );
        assertEquals("second out message text", secondOut.getMessage() );
    }

    /**
     * Test of sendMessage method, of class MessageDataManagedBean.
     * 
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testSendMessage() throws Exception {
        
        
        MessageBean thirdOut = new MessageBean();
        thirdOut.setTo("third out message to");
        thirdOut.setMessage("third out message text");

        MessageDataManagedBean instance = new MessageDataManagedBean();
        
        MessagesFacadeREST mockREST = mock(MessagesFacadeREST.class);
        instance.setMessagesFacadeREST(mockREST);
        
        instance.sendMessage(thirdOut, this.testUser);
        
        ArgumentCaptor<Messages> captor = ArgumentCaptor.forClass(Messages.class);
        verify(mockREST).create(captor.capture());
        
        Messages createdMessage = captor.getValue();
        assertEquals( false, createdMessage.getMsgToUserInd() );
        assertEquals( "third out message to", createdMessage.getMsgTo() );
        assertEquals( "third out message text", createdMessage.getMsgText() );
        assertNull( createdMessage.getMsgFrom() );
        
        assertEquals( 5, this.testUser.getMessagesList().size() );
        assertEquals( createdMessage, this.testUser.getMessagesList().get(4));
        
    }
    
}
