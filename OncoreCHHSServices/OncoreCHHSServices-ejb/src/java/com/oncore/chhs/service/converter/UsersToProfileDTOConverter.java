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
package com.oncore.chhs.service.converter;

import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.persistence.entity.Address;
import com.oncore.chhs.persistence.entity.Contact;
import com.oncore.chhs.persistence.entity.Users;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Convert a Users entity to a Profile DTO.
 * 
 * @author OnCore LLC
 */
public class UsersToProfileDTOConverter {

    private static final List<String> CONTACT_PHONE_TYPES
            = Arrays.asList("HPH", "MPH", "SMS", "WPH");

    /**
     * Convert a Users entity to a Profile DTO.
     * 
     * @param users The entity.
     * 
     * @return The DTO.
     */
    public static Profile convert(Users users) {
        Profile profile = null;

        if (users != null) {
            profile = new Profile();

            profile.setUserName(users.getUsrUserId());
            profile.setFirstName(users.getUsrFirstname());
            profile.setMiddleName(users.getUsrMiddlename());
            profile.setLastName(users.getUsrLastname());

            if (CollectionUtils.isNotEmpty(users.getAddressSet())) {
                Address address = users.getAddressSet().iterator().next();

                profile.setAddressLine1(address.getAdrLine1());
                profile.setAddressLine2(address.getAdrLine2());
                profile.setCity(address.getAdrCity());
                profile.setState(address.getAdrStateCd().getCode());
                profile.setZip(address.getAdrZip5() + address.getAdrZip4());
            }

            if (CollectionUtils.isNotEmpty(users.getContactSet())) {
                for (Contact contact : users.getContactSet()) {
                    if (CONTACT_PHONE_TYPES.contains(contact.getEmcTypeCd().getCode())) {
                        profile.setPhone(contact.getEmcValue());
                    } else {
                        profile.setEmail(contact.getEmcValue());
                    }
                }
            }
        }

        return profile;
    }

}
