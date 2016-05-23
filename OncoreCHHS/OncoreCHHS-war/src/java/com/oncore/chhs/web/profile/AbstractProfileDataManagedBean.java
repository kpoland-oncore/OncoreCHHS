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
package com.oncore.chhs.web.profile;

import com.oncore.chhs.web.base.AbstractBaseManagedBean;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.exceptions.WebServiceException;

/**
 *
 * @author oncore
 */
public interface AbstractProfileDataManagedBean extends AbstractBaseManagedBean{
    
    /**
     * The <code>findProfileByUserId</code> method retrieves a user profile
     * by userUid
     * 
     * @param userUid a valid users uid
     * @return a populated <code>ProfileBean</code> if a profile is found, 
     * null otherwise
     * @throws WebServiceException 
     */
    public ProfileBean findProfileByUserUid(Integer userUid) throws WebServiceException;
    
    /**
     * The <code>createProfile</code> method creates a new profile. The method
     * expects the Users table entry to have already been created prior to 
     * calling this method.
     * 
     * @param profileBean a fully populated <code>ProfileBean</code> object
     * 
     * @throws WebServiceException 
     */
    public void createProfile(ProfileBean profileBean, Users user) throws WebServiceException;
    
    /**
     * The <code>updateProfile</code> method updates an existing user profile.
     * 
     * @param profileBean a fully populated <code>ProfileBean</code> object
     * 
     * @throws WebServiceException 
     */
    public void updateProfile(ProfileBean profileBean) throws WebServiceException;
}
