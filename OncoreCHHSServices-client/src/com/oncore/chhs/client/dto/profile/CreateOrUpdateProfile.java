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
package com.oncore.chhs.client.dto.profile;

/**
 *
 * @author oncore
 */
public class CreateOrUpdateProfile extends Profile {

    private Long userUid;

    public CreateOrUpdateProfile(Profile profile) {
        this.setAddressLine1(profile.getAddressLine1());
        this.setAddressLine2(profile.getAddressLine2());
        this.setCity(profile.getCity());
        this.setEmail(profile.getEmail());
        this.setFirstName(profile.getFirstName());
        this.setLastName(profile.getLastName());
        this.setMiddleName(profile.getMiddleName());
        this.setPhone(profile.getPhone());
        this.setPhoneType(profile.getPhoneType());
        this.setState(profile.getState());
        this.setUserName(profile.getUserName());
        this.setZip(profile.getZip());
    }

    /**
     * @return the userUid
     */
    public Long getUserUid() {
        return userUid;
    }

    /**
     * @param userUid the userUid to set
     */
    public void setUserUid(Long userUid) {
        this.userUid = userUid;
    }

}
