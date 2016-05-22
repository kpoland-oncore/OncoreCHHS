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
package com.oncore.chhs.web.global;

import com.oncore.chhs.web.base.BaseManagedBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oncore
 */
@Named("applicationManagedBean")
//@Singleton
//@LocalBean
//@Startup
@SessionScoped
public class ApplicationManagedBean extends BaseManagedBean{

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing ApplicationManagedBean: " + this.getClass().hashCode());
        
        this.loadStates();
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying ApplicationManagedBean: " + this.getClass().hashCode());
    }

    private void loadStates() {

        SelectItem item = new SelectItem();
        item.setValue("-1");
        item.setLabel("<Select>");
        item.setDescription("<Select>");

        this.states.add(item);

        item = new SelectItem();
        item.setValue("AK");
        item.setLabel("AK");
        item.setDescription("Alaska");

        this.states.add(item);

        item = new SelectItem();
        item.setValue("AL");
        item.setLabel("AL");
        item.setDescription("Alabama");

        this.states.add(item);

    }

    /**
     * @return the states
     */
    public List<SelectItem> getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(List<SelectItem> states) {
        this.states = states;
    }

    /**
     * @return the countries
     */
    public List<SelectItem> getCountries() {
        return countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(List<SelectItem> countries) {
        this.countries = countries;
    }

    /**
     * @return the contactTypes
     */
    public List<SelectItem> getContactTypes() {
        return contactTypes;
    }

    /**
     * @param contactTypes the contactTypes to set
     */
    public void setContactTypes(List<SelectItem> contactTypes) {
        this.contactTypes = contactTypes;
    }

    private List<SelectItem> states = new ArrayList<>(1);
    private List<SelectItem> countries = new ArrayList<>(1);
    private List<SelectItem> contactTypes = new ArrayList<>(1);

    private final Logger LOG = LogManager.getLogger(ApplicationManagedBean.class);

}
