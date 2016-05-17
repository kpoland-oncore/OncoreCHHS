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
package com.oncore.chhs.navigation;

import com.oncore.chss.web.base.BaseManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 

/**
 *
 * @author oncore
 */
@Named("navigationManagedBean")
@SessionScoped
public class NavigationManagedBean extends BaseManagedBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing NavigationManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
       LOG.debug("Destroying NavigationManagedBean: " + this.getClass().hashCode());
    }

    /**
     * The <code>navigateToLink</code> method passes control from one page to
     * another using JSF's built in navigation management.
     *
     * @param target a target JSF page or external address
     * @param isExternal true if the target is an external address, false
     * otherwise
     * @return a fully qualified address
     */
    public String navigateToLink(String target, boolean isExternal) {
        String address = null;

        // This is were we would normally implement some advanced features
        // like bread crumb tracking
        if (isExternal) {
            // perhaps close out the session or perform other security checks
            // or cleanup
            ;
        } else {
            address = target + "?faces-redirect=true";
        }

        return address;
    }

   private final Logger LOG = LogManager.getLogger(NavigationManagedBean.class);
}
