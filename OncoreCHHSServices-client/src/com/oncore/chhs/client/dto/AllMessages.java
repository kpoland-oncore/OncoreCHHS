/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author oncore
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
