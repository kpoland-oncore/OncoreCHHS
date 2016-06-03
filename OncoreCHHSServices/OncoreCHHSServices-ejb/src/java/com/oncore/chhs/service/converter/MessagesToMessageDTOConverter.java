/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service.converter;

import com.oncore.chhs.client.dto.Message;
import com.oncore.chhs.persistence.entity.Messages;

/**
 *
 * @author oncore
 */
public class MessagesToMessageDTOConverter {

    /**
     * Builds the MessageBean to display on the page.
     *
     * @param messages
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
