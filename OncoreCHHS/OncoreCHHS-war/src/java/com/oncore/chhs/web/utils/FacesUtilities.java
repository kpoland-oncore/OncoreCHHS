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
package com.oncore.chhs.web.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author OnCore LLC
 */
public final class FacesUtilities {

    /**
     * The requredFieldError method returns a required field error for a
     * component.
     *
     * @param context a valid FacesContext
     * @param componentId a valid JSF component identifier
     */
    public static void requredFieldError(FacesContext context, String componentId) {
        FacesMessage message = new FacesMessage();
        message.setSummary(REQUIRED_ERROR);
        message.setDetail(REQUIRED_ERROR);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(componentId, message);
        context.renderResponse();
    }

    /**
     * The invalidFormatError method returns an invalid format field error for a
     * component.
     *
     * @param context a valid FacesContext
     * @param componentId a valid JSF component identifier
     */
    public static void invalidFormatError(FacesContext context, String componentId) {
        FacesMessage message = new FacesMessage();
        message.setSummary(INVALID_FORMAT_ERROR);
        message.setDetail(INVALID_FORMAT_ERROR);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(componentId, message);
        context.renderResponse();
    }

    /**
     * The invalidTextAreaFormatError method returns an invalid format field
     * error for an input text area component.
     *
     * @param context a valid FacesContext
     * @param componentId a valid JSF component identifier
     */
    public static void invalidTextAreaFormatError(FacesContext context, String componentId) {
        FacesMessage message = new FacesMessage();
        message.setSummary(INVALID_TEXT_AREA_ERROR);
        message.setDetail(INVALID_TEXT_AREA_ERROR);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(componentId, message);
        context.renderResponse();
    }

    /**
     * The invalidAlphaFormatError method returns an invalid alpha format field
     * error for an input component.
     *
     * @param context a valid FacesContext
     * @param componentId a valid JSF component identifier
     */
    public static void invalidAlphaFormatError(FacesContext context, String componentId) {
        FacesMessage message = new FacesMessage();
        message.setSummary(INVALID_ALPHA_FORMAT_ERROR);
        message.setDetail(INVALID_ALPHA_FORMAT_ERROR);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(componentId, message);
        context.renderResponse();
    }

    /**
     * The createPageLevelValidationError method creates a common validation
     * error message for top of the page display.
     *
     * @param context a valid FacesContext
     */
    public static void createPageLevelValidationError(FacesContext context) {
        FacesMessage message = new FacesMessage();
        message.setSummary(PAGE_LEVEL_VALIDATION_MESSAGE);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
        context.renderResponse();
    }

    /**
     * The createPageLevelFatalError method creates a common fatal error message
     * for top of the page display.
     *
     * @param context a valid FacesContext
     */
    public static void createPageLevelFatalError(FacesContext context) {
        FacesMessage message = new FacesMessage();
        message.setSummary(FATAL_EXCEPTION_MESSAGE);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
        context.renderResponse();
    }

    /**
     * The createPageLevelSaveSuccess method creates a common save message for
     * top of the page display.
     *
     * @param context a valid FacesContext
     */
    public static void createPageLevelSaveSuccess(FacesContext context) {
        FacesMessage message = new FacesMessage();
        message.setSummary(SAVE_SUCCESSFUL_MESSAGE);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, message);
        context.renderResponse();
    }

    /**
     * The createPageLevelCustomError method creates a custom error message for
     * top of the page display.
     *
     * @param context a valid FacesContext
     */
    public static void createPageLevelCustomError(FacesContext context, String errorMessage) {
        FacesMessage message = new FacesMessage();
        message.setSummary(errorMessage);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
        context.renderResponse();
    }

    /**
     * The createPageLevelCustomInfo method creates a custom info message for
     * top of the page display.
     *
     * @param context a valid FacesContext
     */
    public static void createPageLevelCustomInfo(FacesContext context, String errorMessage) {
        FacesMessage message = new FacesMessage();
        message.setSummary(errorMessage);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, message);
        context.renderResponse();
    }

    /**
     * The invalidEmailFormatError method creates a common email validation
     * error message for top of the page display.
     *
     * @param context a valid FacesContext
     */
    public static void invalidEmailFormatError(FacesContext context, String componentId) {
        FacesMessage message = new FacesMessage();
        message.setSummary(INVALID_EMAIL_ERROR);
        message.setDetail(INVALID_EMAIL_ERROR);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(componentId, message);
        context.renderResponse();
    }

    /**
     * The removeMessages method clears out any JSF page errors that may still
     * be in the message buffer.
     */
    public static void removeMessages() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (CollectionUtils.isNotEmpty(context.getMessageList())) {
            context.getMessageList().clear();
        }
    }

    /**
     * The runJavaScript method executes JavaScript against the external
     * context.
     *
     * @param script script to execute
     */
    public static void runJavaScript(String script) {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        if (requestContext != null) {
            requestContext.execute(script);
        }
    }

    public static final String REQUIRED_ERROR = "Field validation error, field is Required";
    public static final String INVALID_FORMAT_ERROR = "Field validation error, value entered must be letters, numbers, spaces, or punctuation";
    public static final String INVALID_EMAIL_ERROR = "Field validation error, the email addressed entered is not a valid format";
    public static final String INVALID_TEXT_AREA_ERROR = "Field validation error, value entered must be letters, numbers, spaces, or punctuation";
    public static final String INVALID_ALPHA_FORMAT_ERROR = "Field validation error, value entered must be letters or spaces.";
    public static final String PAGE_LEVEL_VALIDATION_MESSAGE = "There are validation errors. Please correct the errors before proceeding.";
    public static final String FATAL_EXCEPTION_MESSAGE = "A fatal exception has occurred. Please notify support services at 800.111.2222";
    public static final String SAVE_SUCCESSFUL_MESSAGE = "Save successful.";
    public static final String THANK_YOU_PROFILE_MESSAGE = "Thank you, your profile has been created";
    public static final String THANK_YOU_PROFILE_UPDATED_MESSAGE = "Thank you, your profile has been updated";
    public static final String USER_ALREADY_TAKEN_MESSAGE = "The user name provided has already been taken. Please try a different user name.";

}
