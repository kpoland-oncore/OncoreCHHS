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
package com.oncore.chhs.service;

import com.oncore.chhs.client.dto.profile.CreateOrUpdateProfile;
import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.client.ejb.ProfileService;
import com.oncore.chhs.persistence.dao.AddressDAO;
import com.oncore.chhs.persistence.dao.AdrStateCdDAO;
import com.oncore.chhs.persistence.dao.ContactDAO;
import com.oncore.chhs.persistence.dao.EmcTypeCdDAO;
import com.oncore.chhs.persistence.dao.UserDAO;
import com.oncore.chhs.persistence.entity.Address;
import com.oncore.chhs.persistence.entity.AdrStateCd;
import com.oncore.chhs.persistence.entity.Contact;
import com.oncore.chhs.persistence.entity.EmcTypeCd;
import com.oncore.chhs.persistence.entity.Users;
import com.oncore.chhs.service.converter.UsersToProfileDTOConverter;
import java.util.Date;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author oncore
 */
@Stateless
@Remote(ProfileService.class)
public class ProfileServiceImpl implements ProfileService {

    @EJB
    private AddressDAO addressDAO;

    @EJB
    private AdrStateCdDAO adrStateCdDAO;

    @EJB
    private ContactDAO contactDAO;

    @EJB
    private EmcTypeCdDAO emcTypeCdDAO;

    @EJB
    private UserDAO userDAO;

    @Override
    public Profile findProfileByUserUid(Integer userUid) {

        Users users = this.userDAO.findById(userUid);

        Profile profile = UsersToProfileDTOConverter.convert(users);

        return profile;
    }

    @Override
    public void createProfile(CreateOrUpdateProfile profile) {

        Users users = this.userDAO.findById(profile.getUserUid());

        if (StringUtils.isNotBlank(profile.getAddressLine1())) {
            createAddress(users, profile);
        }

        if (StringUtils.isNotBlank(profile.getPhone())) {
            createContactPhone(users, profile);
        }

        if (StringUtils.isNotBlank(profile.getEmail())) {
            createContactEmail(users, profile);
        }
    }

    /**
     * Create a new address for the user.
     * 
     * @param users The Users entity, which the new Address will be added to.
     * @param profile The Profile DTO, containing the new address info.
     */
    private void createAddress(Users users, Profile profile) {

        if (users.getAddressSet() == null) {
            users.setAddressSet(new HashSet<>());
        }

        Address address = new Address();
        address.setUsrUidFk(users);

        address.setAdrLine1(profile.getAddressLine1());
        address.setAdrLine2(profile.getAddressLine2());
        address.setAdrCity(profile.getCity());

        AdrStateCd adrStateCd = this.adrStateCdDAO.findById(profile.getState());
        address.setAdrStateCd(adrStateCd);

        if (StringUtils.isNotBlank(profile.getZip())) {
            if (profile.getZip().length() > 5) {
                address.setAdrZip5(profile.getZip().substring(0, 4));
                address.setAdrZip4(profile.getZip().substring(5));
            } else {
                address.setAdrZip5(profile.getZip());
            }
        }

        address.setCreateTs(new Date());
        address.setCreateUserId(getFormattedName(users));
        address.setUpdateTs(new Date());
        address.setUpdateUserId(getFormattedName(users));

        users.getAddressSet().add(address);

        this.addressDAO.create(address);
    }

    /**
     * Create a new Contact entity for the user's phone.
     *
     * @param users The Users entity, which the new Contact will be added to.
     * @param profile The Profile DTO, containing the new phone info.
     */
    private void createContactPhone(Users users, Profile profile) {

        if (users.getContactSet() == null) {
            users.setContactSet(new HashSet<>());
        }

        Contact contact = new Contact();
        contact.setUsrUidFk(users);

        EmcTypeCd emcTypeCd = this.emcTypeCdDAO.findById(profile.getPhoneType());
        contact.setEmcTypeCd(emcTypeCd);
        contact.setEmcValue(profile.getPhone());
        contact.setCreateTs(new Date());
        contact.setCreateUserId(getFormattedName(users));
        contact.setUpdateTs(new Date());
        contact.setUpdateUserId(getFormattedName(users));

        users.getContactSet().add(contact);

        this.contactDAO.create(contact);
    }

    /**
     * Create a new email Contact for the user.
     * 
     * @param users The Users entity, which the new Contact will be added to.
     * @param profile The Profile DTO, containing the new email info.
     */
    private void createContactEmail(Users users, Profile profile) {

        if (users.getContactSet() == null) {
            users.setContactSet(new HashSet<>());
        }

        Contact contact = new Contact();
        contact.setUsrUidFk(users);

        EmcTypeCd emcTypeCd = this.emcTypeCdDAO.findById("EML");
        contact.setEmcTypeCd(emcTypeCd);
        contact.setEmcValue(profile.getEmail());
        contact.setCreateTs(new Date());
        contact.setCreateUserId(getFormattedName(users));
        contact.setUpdateTs(new Date());
        contact.setUpdateUserId(getFormattedName(users));

        users.getContactSet().add(contact);

        this.contactDAO.create(contact);
    }

    @Override
    public void updateProfile(CreateOrUpdateProfile profile) {
        throw new RuntimeException( "Update Profile is not implemented yet");
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

}
