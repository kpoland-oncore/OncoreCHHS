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
package com.oncore.chhs.web.search;

import com.oncore.chhs.web.base.BaseManagedBean;
import static com.oncore.chhs.web.base.BaseManagedBean.FORM_NAME;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.utils.FacesUtilities;
import java.util.ArrayList;
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
 * @author oncore
 */
@Named("searchManagedBean")
@ViewScoped
public class SearchManagedBean extends BaseManagedBean {

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing SearchManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying SearchManagedBean: " + this.getClass().hashCode());
    }

    /**
     * The <code>handleLoginButtonClickEvent</code> method handles the click
     * event generated from the login button on the login page.
     *
     * @return a qualified URL or null if an exception occurs
     */
    public String handleSearchButtonClickEvent() {
        String page = null;

        try {

            if (this.searchValidationBean.validateRequiredField(this.getSearchBean().getZipForSearch(), FORM_NAME + "searchTxt:input")) {
                FacesUtilities.createPageLevelValidationError(FacesContext.getCurrentInstance());
            } else {
                this.setSearchBeanList(this.searchDataManagedBean.search(this.getSearchBean().getZipForSearch()));
                this.setFilteredBeanList(this.getSearchBeanList());
            }
        } catch (WebServiceException wx) {
            LOG.error(wx);
            FacesUtilities.createPageLevelFatalError(FacesContext.getCurrentInstance());
        }

        return page;

    }

    /**
     * @return the searchBean
     */
    public SearchBean getSearchBean() {
        return searchBean;
    }

    /**
     * @param searchBean the searchBean to set
     */
    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    /**
     * @return the searchBeanList
     */
    public List<SearchBean> getSearchBeanList() {
        return searchBeanList;
    }

    /**
     * @param searchBeanList the searchBeanList to set
     */
    public void setSearchBeanList(List<SearchBean> searchBeanList) {
        this.searchBeanList = searchBeanList;
    }

    /**
     * @return the filteredBeanList
     */
    public List<SearchBean> getFilteredBeanList() {
        return filteredBeanList;
    }

    /**
     * @param filteredBeanList the filteredBeanList to set
     */
    public void setFilteredBeanList(List<SearchBean> filteredBeanList) {
        this.filteredBeanList = filteredBeanList;
    }

    @Inject
    AbstractSearchDataManagedBean searchDataManagedBean;

    @Inject
    SearchValidationBean searchValidationBean;

    private SearchBean searchBean = new SearchBean();

    private List<SearchBean> searchBeanList = new ArrayList<>(1);

    private List<SearchBean> filteredBeanList = new ArrayList<>(1);

    private final Logger LOG = LogManager.getLogger(SearchManagedBean.class);

}
