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
package com.oncore.chhs.web.login;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.base.AbstractBaseManagedBean;
import com.oncore.chhs.web.profile.ProfileBean;

/**
 *
 * @author OnCore LLC
 */
public interface AbstractLoginDataManagedBean extends AbstractBaseManagedBean {

    /**
     * The createUser method creates a new user record
     *
     * @param profileBean a populated profileBean object
     * @throws WebServiceException
     * @return a populated Users object if found, null otherwise
     */
    public User createUser(ProfileBean profileBean) throws WebServiceException;

    /**
     * The authenticateUser method determines if a user exists
     * matching the user name.
     *
     * @param loginBean a populated LoginBean object
     * @throws WebServiceException
     * @return a populated UserDTO object if found, null otherwise
     */
    public User authenticateUser(LoginBean loginBean) throws WebServiceException;

}
