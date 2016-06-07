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
package com.oncore.chhs.web.base;

import com.oncore.chhs.web.navigation.NavigationManagedBean;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author OnCore LLC
 */
public abstract class BaseManagedBean implements AbstractBaseManagedBean, Serializable {

    @Override
    public abstract void initialize();

    @Override
    public abstract void destroy();

    /**
     * The handleHeaderNavigationClickEvent method handles the
     * click event on the header navigation links such as Home, MyProfile,
     * Locate Services, and Messages.
     *
     * @param target the source link identifier
     *
     * @return a target URL
     */
    public String handleHeaderNavigationClickEvent(String target) {
        return this.navigationManagedBean.navigateToLink(target, Boolean.FALSE);
    }

    @Inject
    protected NavigationManagedBean navigationManagedBean;

    public static final String FORM_NAME = "chhsForm:";

}
