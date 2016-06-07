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
import com.oncore.chhs.web.base.BaseManagedBean;
import static com.oncore.chhs.web.base.BaseManagedBean.FORM_NAME;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.global.GlobalManagedBean;
import com.oncore.chhs.web.utils.ErrorUtils;
import com.oncore.chhs.web.utils.FacesUtilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author OnCore LLC
 */
@Named("messagesManagedBean")
@ViewScoped
public class MessagesManagedBean extends BaseManagedBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing MessagesManagedBean: " + this.getClass().hashCode());

        if (this.globalManagedBean.getAuthenticatedUser() == null) {
            try {
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login_redirect");
            } catch (IllegalStateException ix) {
                LOG.warn(ErrorUtils.getStackTrace(ix));
            }
        } else {
            populateBoxes();
        }
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying MessagesManagedBean: " + this.getClass().hashCode());
    }

    /**
     * The handleSendButtonClickEvent method handles the click
     * event generated from the send button on the message page.
     */
    public void handleSendButtonClickEvent() {

        Boolean isError = Boolean.FALSE;
        String error = null;

        try {
            if ((error = this.messageValidationBean.validateTextArea(this.getMessageBean().getMessage(), Boolean.TRUE, FORM_NAME + "messageTxt:input")) != null) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
                this.getMessageBean().setMessageError(error);
            } else {

                this.getMessageBean().setFrom(this.globalManagedBean.getCalculatedUserFullName());
                this.getMessageBean().setSentDate(new Date());
                this.getMessageBean().setTo("CHHS");
                this.messageDataManagedBean.sendMessage(this.getMessageBean(), this.globalManagedBean.getAuthenticatedUser());

                this.populateBoxes();
                
                this.setMessageBean(new MessageBean());
            }
        } catch (WebServiceException wx) {
            LOG.error(wx);
            FacesUtilities.createPageLevelFatalError(FacesContext.getCurrentInstance());
        }

    }

    private void populateBoxes() {
        try {
            FacesUtilities.removeMessages();

            AllMessages allMessages = this.messageDataManagedBean.fetchMessages(this.globalManagedBean.getAuthenticatedUser().getUserUid().intValue());

            if (allMessages != null) {
                this.setInboxList(allMessages.getInboundMessages());
                this.setOutboxList(allMessages.getOutboundMessages());

            }

        } catch (WebServiceException wx) {
            LOG.error(wx);
            FacesUtilities.createPageLevelFatalError(FacesContext.getCurrentInstance());
        }
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
     * @return the inboxList
     */
    public List<Message> getInboxList() {
        return inboxList;
    }

    /**
     * @param inboxList the inboxList to set
     */
    public void setInboxList(List<Message> inboxList) {
        this.inboxList = inboxList;
    }

    /**
     * @return the outboxList
     */
    public List<Message> getOutboxList() {
        return outboxList;
    }

    /**
     * @param outboxList the outboxList to set
     */
    public void setOutboxList(List<Message> outboxList) {
        this.outboxList = outboxList;
    }

    /**
     * @return the filteredInboxList
     */
    public List<Message> getFilteredInboxList() {
        return filteredInboxList;
    }

    /**
     * @param filteredInboxList the filteredInboxList to set
     */
    public void setFilteredInboxList(List<Message> filteredInboxList) {
        this.filteredInboxList = filteredInboxList;
    }

    /**
     * @return the filteredOutboxList
     */
    public List<Message> getFilteredOutboxList() {
        return filteredOutboxList;
    }

    /**
     * @param filteredOutboxList the filteredOutboxList to set
     */
    public void setFilteredOutboxList(List<Message> filteredOutboxList) {
        this.filteredOutboxList = filteredOutboxList;
    }

    @Inject
    AbstractMessageDataManagedBean messageDataManagedBean;

    @Inject
    MessageValidationBean messageValidationBean;

    @Inject
    protected GlobalManagedBean globalManagedBean;

    private MessageBean messageBean = new MessageBean();

    private List<Message> inboxList = new ArrayList<>(1);

    private List<Message> outboxList = new ArrayList<>(1);

    private List<Message> filteredInboxList = new ArrayList<>(1);

    private List<Message> filteredOutboxList = new ArrayList<>(1);

    private final Logger LOG = LogManager.getLogger(MessagesManagedBean.class);

}
