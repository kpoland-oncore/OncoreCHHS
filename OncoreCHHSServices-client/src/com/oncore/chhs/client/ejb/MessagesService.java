/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.ejb;

import com.oncore.chhs.client.dto.AllMessages;

/**
 *
 * @author oncore
 */
public interface MessagesService {

    /**
     * The <code>sendMessage</code> method sends a message.
     *
     * @param from message from
     * @param to message to
     * @param messageTxt a message to send
     * @param userUid the userUid
     */
    public void sendMessage(String from, String to, String messageTxt, Integer userUid);

    /**
     * The <code>fetchMessages</code> method fetches all messages from the inbox
     * and outbox for the user.
     *
     * @param userUid the entity representing the user
     *
     * @return a map containing list of populated inbox and outbox
     * <code>MessageDTO</code> objects if found, empty list otherwise
     */
    public AllMessages getAllMessages(Integer userUid);
}
