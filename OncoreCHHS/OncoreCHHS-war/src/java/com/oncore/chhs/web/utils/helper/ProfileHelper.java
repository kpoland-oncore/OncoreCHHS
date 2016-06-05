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
package com.oncore.chhs.web.utils.helper;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.web.entities.Address;
import com.oncore.chhs.web.entities.Contact;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.profile.ProfileBean;
import com.oncore.chhs.web.utils.ErrorUtils;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utilities class to support Profile.
 *
 * @author oncore
 */
public class ProfileHelper {

    public static final String CONTACT_TYPE_HOME_PHONE = "HPH";
    public static final String CONTACT_TYPE_MOBILE_PHONE = "MPH";
    public static final String CONTACT_TYPE_SMS_TEXT = "SMS";
    public static final String CONTACT_TYPE_WORK_PHONE = "WPH";
    public static final String CONTACT_TYPE_EMAIL = "EML";
    private final static Logger LOG = LogManager.getLogger(ProfileHelper.class);

    /**
     * Builds the ProfileBean with users information. Profile bean includes
     * user, address, contact information.
     *
     * @param users
     *
     * @return profile bean
     */
    public static ProfileBean buildProfile(Users users) {
        ProfileBean profileBean = new ProfileBean();

        try {
            populateProfileUsers(profileBean, users);
        } catch (Exception ex) {
            LOG.warn(ErrorUtils.getStackTrace(ex));
        }

        if (CollectionUtils.isNotEmpty(users.getAddressList()));
        {
            try {
                populateProfileAddress(profileBean, users.getAddressList().get(0));
            } catch (Exception ex) {
                LOG.warn(ErrorUtils.getStackTrace(ex));
            }
        }

        if (CollectionUtils.isNotEmpty(users.getContactList())) {
            try {
                populateProfileContact(profileBean, users.getContactList());
            } catch (Exception ex) {
                LOG.warn(ErrorUtils.getStackTrace(ex));
            }
        }

        return profileBean;
    }

    /**
     * Convert a ProfileBean UI object to an Profile data transfer object.
     * 
     * @param profileBean The ProfileBean to convert.
     * 
     * @return The DTO.
     */
    public static Profile convertProfileBeanToProfileDTO(ProfileBean profileBean) {
        Profile profile = new Profile();
        profile.setAddressLine1(profileBean.getAddressLine1());
        profile.setAddressLine2(profileBean.getAddressLine2());
        profile.setCity(profileBean.getCity());
        profile.setEmail(profileBean.getEmail());
        profile.setFirstName(profileBean.getFirstName());
        profile.setLastName(profileBean.getLastName());
        profile.setMiddleName(profileBean.getMiddleName());
        profile.setPhone(profileBean.getPhone());
        profile.setPhoneType(profileBean.getPhoneType());
        profile.setState(profileBean.getState());
        profile.setUserName(profileBean.getUserName());
        profile.setZip(profileBean.getZip());
        
        return profile;
    }

    /**
     * Formats the name of the user to: First middle last names.
     *
     * @param user
     *
     * @return formatted name
     */
    public static String getFormattedName(User user) {
        String formattedName = "";

        if (user != null) {
            if (StringUtils.isNotBlank(user.getFirstName())) {
                formattedName = user.getFirstName();
            }
            if (StringUtils.isNotBlank(user.getMiddleName())) {
                formattedName = " ";
                formattedName = user.getMiddleName();
            }
            if (StringUtils.isNotBlank(user.getLastName())) {
                formattedName = " ";
                formattedName = user.getLastName();
            }
        }

        return formattedName;
    }

    /**
     * Populates the user information in ProfileBean.
     *
     * @param profileBean
     * @param users
     */
    private static void populateProfileUsers(ProfileBean profileBean, Users users) {
        profileBean.setUserName(users.getUsrUserId());
        profileBean.setFirstName(users.getUsrFirstname());
        profileBean.setMiddleName(users.getUsrMiddlename());
        profileBean.setLastName(users.getUsrLastname());
    }

    /**
     * Populates the address information in ProfileBean.
     *
     * @param profileBean
     * @param address
     */
    private static void populateProfileAddress(ProfileBean profileBean, Address address) {
        profileBean.setAddressLine1(address.getAdrLine1());
        profileBean.setAddressLine2(address.getAdrLine2());
        profileBean.setCity(address.getAdrCity());
        profileBean.setState(address.getAdrStateCd().getCode());
        profileBean.setZip(address.getAdrZip5() + address.getAdrZip4());
    }

    /**
     * Populates the contact information in ProfileBean.
     *
     * @param profileBean
     * @param contacts
     */
    private static void populateProfileContact(ProfileBean profileBean, List<Contact> contacts) {
        for (Contact contact : contacts) {
            if (StringUtils.equals(CONTACT_TYPE_HOME_PHONE, contact.getEmcTypeCd().getCode())
                    || StringUtils.equals(CONTACT_TYPE_MOBILE_PHONE, contact.getEmcTypeCd().getCode())
                    || StringUtils.equals(CONTACT_TYPE_SMS_TEXT, contact.getEmcTypeCd().getCode())
                    || StringUtils.equals(CONTACT_TYPE_HOME_PHONE, contact.getEmcTypeCd().getCode())) {
                profileBean.setPhone(contact.getEmcValue());
            } else {
                profileBean.setEmail(contact.getEmcValue());
            }
        }
    }
}
