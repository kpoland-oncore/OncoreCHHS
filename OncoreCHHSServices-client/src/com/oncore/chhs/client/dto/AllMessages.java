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
package com.oncore.chhs.client.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OnCore LLC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AllMessages {

    @XmlElement(name = "inboundMessages")
    private List<Message> inboundMessages;
    @XmlElement(name = "outboundMessages")
    private List<Message> outboundMessages;

    /**
     * @return the inboundMessages
     */
    public List<Message> getInboundMessages() {

        if (this.inboundMessages == null) {
            this.inboundMessages = new ArrayList<>();
        }

        return this.inboundMessages;
    }

    /**
     * @param inboundMessages the inboundMessages to set
     */
    public void setInboundMessages(List<Message> inboundMessages) {
        this.inboundMessages = inboundMessages;
    }

    /**
     * @return the outboundMessages
     */
    public List<Message> getOutboundMessages() {

        if (this.outboundMessages == null) {
            this.outboundMessages = new ArrayList<>();
        }

        return this.outboundMessages;
    }

    /**
     * @param outboundMessages the outboundMessages to set
     */
    public void setOutboundMessages(List<Message> outboundMessages) {
        this.outboundMessages = outboundMessages;
    }
}
