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
package com.oncore.chhs.client.ejb;

import com.oncore.chhs.client.dto.profile.CreateOrUpdateProfile;
import com.oncore.chhs.client.dto.profile.Profile;

/**
 * The ProfileService provides methods for retrieving, creating and updating
 * a user's name and contact information.
 * 
 * @author OnCore LLC
 */
public interface ProfileService {
    
    /**
     * Find the user's profile.
     * 
     * @param userUid The user's unique ID.
     * 
     * @return The user's profile.
     */
    public Profile findProfileByUserUid(Integer userUid);
    
    /**
     * Create a new user profile, including address and 
     * contact information.
     * 
     * @param profile The profile data to create.
     */
    public void createProfile( CreateOrUpdateProfile profile );
    
    /**
     * Update a user's profile, including address and contact
     * information.
     * 
     * @param profile The profile data to update.
     */
    public void updateProfile( CreateOrUpdateProfile profile );
    
}
