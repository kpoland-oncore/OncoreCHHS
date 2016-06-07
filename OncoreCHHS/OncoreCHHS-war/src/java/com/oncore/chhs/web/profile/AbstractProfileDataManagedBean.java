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

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.web.base.AbstractBaseManagedBean;
import com.oncore.chhs.web.exceptions.WebServiceException;

/**
 *
 * @author OnCore LLC
 */
public interface AbstractProfileDataManagedBean extends AbstractBaseManagedBean{
    
    /**
     * The findProfileByUserId method retrieves a user profile
     * by userUid
     * 
     * @param userUid a valid users uid
     * @return a populated Profile if a profile is found, 
     * null otherwise
     * @throws WebServiceException 
     */
    public Profile findProfileByUserUid(Integer userUid) throws WebServiceException;
    
    /**
     * The createProfile method creates a new profile. The method
     * expects the Users table entry to have already been created prior to 
     * calling this method.
     * 
     * @param profileBean a fully populated ProfileBean object
     * @param user a fully populated users entity
     * 
     * @throws WebServiceException 
     */
    public void createProfile(ProfileBean profileBean, User user) throws WebServiceException;
    
    /**
     * The updateProfile method updates an existing user profile.
     * 
     * @param profileBean a fully populated ProfileBean object
     * @param user a fully populated users entity
     * 
     * @throws WebServiceException 
     */
    public void updateProfile(ProfileBean profileBean, User user) throws WebServiceException;
}
