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

import com.oncore.chhs.web.entities.Address;
import com.oncore.chhs.web.entities.Contact;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.profile.ProfileBean;
import com.oncore.chhs.web.services.AdrStateCdFacadeREST;
import com.oncore.chhs.web.services.EmcTypeCdFacadeREST;
import com.oncore.chhs.web.utils.ErrorUtils;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utilities clss to support Profile.
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
     * Builds the ProfileBean with users information. Profilebean includes user,
     * address, contact information.
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
     *
     *
     * @param profileBean
     * @param adrStateCdFacadeREST
     *
     * @return
     */
    public static Address convertProfileBeanToAddressEntity(ProfileBean profileBean, AdrStateCdFacadeREST adrStateCdFacadeREST, Users users) {
        Address address = new Address();
        mapProfileBeanToAddressEntity(profileBean, address, adrStateCdFacadeREST, users);

        return address;
    }

    /**
     *
     *
     * @param profileBean
     * @param address
     * @param adrStateCdFacadeREST
     */
    public static void mapProfileBeanToAddressEntity(ProfileBean profileBean, Address address, AdrStateCdFacadeREST adrStateCdFacadeREST, Users users) {
        address.setAdrLine1(profileBean.getAddressLine1());
        address.setAdrLine2(profileBean.getAddressLine2());
        address.setAdrCity(profileBean.getCity());
        address.setAdrStateCd(adrStateCdFacadeREST.findByCode(profileBean.getState()));

        if (StringUtils.isNotBlank(profileBean.getZip())) {
            if (profileBean.getZip().length() > 5) {
                address.setAdrZip5(profileBean.getZip().substring(0, 4));
                address.setAdrZip4(profileBean.getZip().substring(5));
            } else {
                address.setAdrZip5(profileBean.getZip());
            }
        }

        address.setCreateTs(new Date());
        address.setCreateUserId(getFormattedName(users));
        address.setUpdateTs(new Date());
        address.setUpdateUserId(getFormattedName(users));

        if (users != null) {
            address.setUsrUidFk(users);
        }
    }

    /**
     *
     *
     * @param profileBean
     * @param emcTypeCdFacadeREST
     *
     * @return
     */
    public static Contact convertProfileBeanToContactEntity(ProfileBean profileBean, EmcTypeCdFacadeREST emcTypeCdFacadeREST, Users users) {
        Contact contact = new Contact();
        mapProfileBeanToContactEntity(profileBean, contact, emcTypeCdFacadeREST, users);

        return contact;
    }

    /**
     *
     *
     * @param profileBean
     * @param contact
     * @param emcTypeCdFacadeREST
     */
    public static void mapProfileBeanToContactEntity(ProfileBean profileBean, Contact contact, EmcTypeCdFacadeREST emcTypeCdFacadeREST, Users users) {
        contact.setEmcTypeCd(emcTypeCdFacadeREST.findByCode(profileBean.getPhoneType()));
        contact.setEmcValue(profileBean.getPhone());

        contact.setCreateTs(new Date());
        contact.setCreateUserId(getFormattedName(users));
        contact.setUpdateTs(new Date());
        contact.setUpdateUserId(getFormattedName(users));

        if (users != null) {
            contact.setUsrUidFk(users);
        }
    }

    /**
     * Formats the name of the user to: First middle last names.
     *
     * @param user
     *
     * @return formatted name
     */
    public static String getFormattedName(Users user) {
        String formattedName = "";

        if (user != null) {
            if (StringUtils.isNotBlank(user.getUsrFirstname())) {
                formattedName = user.getUsrFirstname();
            }
            if (StringUtils.isNotBlank(user.getUsrMiddlename())) {
                formattedName = " ";
                formattedName = user.getUsrMiddlename();
            }
            if (StringUtils.isNotBlank(user.getUsrLastname())) {
                formattedName = " ";
                formattedName = user.getUsrLastname();
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
                break;
            } else {
                profileBean.setEmail(contact.getEmcValue());
            }
        }
    }
}
