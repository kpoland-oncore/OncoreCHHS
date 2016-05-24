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

import com.oncore.chhs.web.search.*;
import com.oncore.chhs.web.base.BaseManagedBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author oncore
 */
@Named("messagesManagedBean")
@ViewScoped
public class MessagesManagedBean extends BaseManagedBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing MessagesManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying MessagesManagedBean: " + this.getClass().hashCode());
    }

    /**
     * @return the messageBean
     */
    public MessageBean getMessageBean() {
        return messageBean;
    }

    /**
     * @param messageBean the messageBean to set
     */
    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    /**
     * @return the messageBeanList
     */
    public List<MessageBean> getMessageBeanList() {
        return messageBeanList;
    }

    /**
     * @param messageBeanList the messageBeanList to set
     */
    public void setMessageBeanList(List<MessageBean> messageBeanList) {
        this.messageBeanList = messageBeanList;
    }

    /**
     * @return the filteredInboxList
     */
    public List<MessageBean> getFilteredInboxList() {
        return filteredInboxList;
    }

    /**
     * @param filteredInboxList the filteredInboxList to set
     */
    public void setFilteredInboxList(List<MessageBean> filteredInboxList) {
        this.filteredInboxList = filteredInboxList;
    }

    /**
     * @return the filteredOutboxList
     */
    public List<MessageBean> getFilteredOutboxList() {
        return filteredOutboxList;
    }

    /**
     * @param filteredOutboxList the filteredOutboxList to set
     */
    public void setFilteredOutboxList(List<MessageBean> filteredOutboxList) {
        this.filteredOutboxList = filteredOutboxList;
    }

    @Inject
    AbstractMessageDataManagedBean messageDataManagedBean;

    @Inject
    MessageValidationBean messageValidationBean;

    private MessageBean messageBean = new MessageBean();

    private List<MessageBean> messageBeanList = new ArrayList<>(1);

    private List<MessageBean> filteredInboxList = new ArrayList<>(1);

    private List<MessageBean> filteredOutboxList = new ArrayList<>(1);

    private final Logger LOG = LogManager.getLogger(MessagesManagedBean.class);

}
