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

import com.oncore.chhs.web.base.AbstractBaseManagedBean;
import com.oncore.chhs.web.exceptions.WebServiceException;
import java.util.List;

/**
 *
 * @author OnCore LLC
 */
public interface AbstractSearchDataManagedBean extends AbstractBaseManagedBean {

    /**
     * The search method hits the CHHS Facility search web service
     * attempting to locate facilities in the provided zip code
     *
     * @param zip a populated zip code
     * @return a list of populated SearchBean objects if found,
     * empty list otherwise
     * @throws WebServiceException
     */
    public List<SearchBean> search(String zip) throws WebServiceException;

    
    /**
     * The searchArea method hits the CHHS Facility search web
     * service attempting to locate facilities in the provided coordinate.
     *
     * @param logitude a logitude of coordinate
     * @param latitude a latitude of coordinate
     * @return a list of populated SearchBean objects if found,
     * empty list otherwise
     * @throws WebServiceException
     */
    public List<SearchBean> searchArea(Double logitude, Double latitude) throws WebServiceException;
}
