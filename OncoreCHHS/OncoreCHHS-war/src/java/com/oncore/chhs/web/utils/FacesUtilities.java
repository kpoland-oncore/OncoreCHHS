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

/**
 *
 * @author oncore
 */
public final class FacesUtilities {

    public static void requredFieldError(FacesContext context, String componentId) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Field is Required");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(componentId, message);
        context.renderResponse();
    }

    public static void createPageLevelValidationError(FacesContext context) {
        FacesMessage message = new FacesMessage();
        message.setSummary("There are validation errors. Please correct the errors before proceeding.");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
        context.renderResponse();
    }

    public static void createPageLevelFatalError(FacesContext context) {
        FacesMessage message = new FacesMessage();
        message.setSummary("A fatal exception has occurred. Please notify support services at 800.111.2222");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
        context.renderResponse();
    }

    public static void createPageLevelCustomError(FacesContext context, String errorMessage) {
        FacesMessage message = new FacesMessage();
        message.setSummary(errorMessage);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
        context.renderResponse();
    }
}
