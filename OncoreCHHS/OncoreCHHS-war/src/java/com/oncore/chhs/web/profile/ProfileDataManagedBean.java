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
import com.oncore.chhs.web.base.BaseManagedBean;
import com.oncore.chhs.web.entities.Address;
import com.oncore.chhs.web.entities.Contact;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.enums.ContactTypeEnum;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.services.AddressFacadeREST;
import com.oncore.chhs.web.services.AdrStateCdFacadeREST;
import com.oncore.chhs.web.services.ContactFacadeREST;
import com.oncore.chhs.web.services.EmcTypeCdFacadeREST;
import com.oncore.chhs.web.services.UsersFacadeREST;
import com.oncore.chhs.web.utils.ErrorUtils;
import com.oncore.chhs.web.utils.helper.ProfileHelper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oncore
 */
@Named("profileDataManagedBean")
@RequestScoped
public class ProfileDataManagedBean extends BaseManagedBean implements AbstractProfileDataManagedBean {

    private final Logger LOG = LogManager.getLogger(ProfileDataManagedBean.class);
    @EJB
    private UsersFacadeREST usersFacadeREST;

    @EJB
    private AddressFacadeREST addressFacadeREST;

    @EJB
    private ContactFacadeREST contactFacadeREST;

    @EJB
    private AdrStateCdFacadeREST adrStateCdFacadeREST;

    @EJB
    private EmcTypeCdFacadeREST emcTypeCdFacadeREST;

    /**
     * Package level setter used for passing in mock objects for unit tests.
     *
     * @param mockObject The mock EJB to use for testing.
     */
    void setUsersFacadeREST(UsersFacadeREST mockObject) {
        this.usersFacadeREST = mockObject;
    }

    /**
     * Package level setter used for passing in mock objects for unit tests.
     *
     * @param mockObject The mock EJB to use for testing.
     */
    void setAddressFacadeREST(AddressFacadeREST mockObject) {
        this.addressFacadeREST = mockObject;
    }

    /**
     * Package level setter used for passing in mock objects for unit tests.
     *
     * @param mockObject The mock EJB to use for testing.
     */
    void setContactFacadeREST(ContactFacadeREST mockObject) {
        this.contactFacadeREST = mockObject;
    }

    /**
     * Package level setter used for passing in mock objects for unit tests.
     *
     * @param mockObject The mock EJB to use for testing.
     */
    void setAdrStateCdFacadeREST(AdrStateCdFacadeREST mockObject) {
        this.adrStateCdFacadeREST = mockObject;
    }

    /**
     * Package level setter used for passing in mock objects for unit tests.
     *
     * @param mockObject The mock EJB to use for testing.
     */
    void setEmcTypeCdFacadeREST(EmcTypeCdFacadeREST mockObject) {
        this.emcTypeCdFacadeREST = mockObject;
    }

    @Override
    @PostConstruct
    public void initialize() {
        LOG.debug("Initializing ProfileDataManagedBean: " + this.getClass().hashCode());
    }

    @Override
    @PreDestroy
    public void destroy() {
        LOG.debug("Destroying ProfileDataManagedBean: " + this.getClass().hashCode());
    }

    /**
     * Finds the profile information for the user using the userUid.
     *
     * @param userUid
     *
     * @return <code>ProfileBean</code>
     *
     * @throws WebServiceException
     */
    @Override
    public ProfileBean findProfileByUserUid(Integer userUid) throws WebServiceException {

        ProfileBean profileBean = null;

        try {
            Users users = this.usersFacadeREST.find(userUid);

            if (users != null) {
                profileBean = ProfileHelper.buildProfile(users);
            }

        } catch (Exception ex) {
            throw new WebServiceException(ErrorUtils.getStackTrace(ex));
        }

        return profileBean;
    }

    /**
     * Creates the profile for the user.
     *
     * @param profileBean
     * @param user
     * @throws WebServiceException
     */
    @Override
    public void createProfile(ProfileBean profileBean, User user) throws WebServiceException {
        try {
            this.createAddress(profileBean, user);
//TODO:  UPDATE TO USE NEW WEB SERVICE CLIENT
//            if (StringUtils.isNotBlank(profileBean.getPhone())) {
//                if (null == user.getContactList()) {
//                    user.setContactList(new ArrayList<>());
//                }
//
//                Contact contact = ProfileHelper.convertPhoneNumberToContactEntity(profileBean, this.emcTypeCdFacadeREST, user);
//                contact.setUsrUidFk(user);
//
//                user.getContactList().add(contact);
//
//                this.contactFacadeREST.create(contact);
//            }
//
//            if (StringUtils.isNotBlank(profileBean.getEmail())) {
//                if (null == user.getContactList()) {
//                    user.setContactList(new ArrayList<>());
//                }
//
//                Contact contact = ProfileHelper.convertEmailToContactEntity(profileBean, this.emcTypeCdFacadeREST, user);
//                contact.setUsrUidFk(user);
//
//                user.getContactList().add(contact);
//
//                this.contactFacadeREST.create(contact);
//            }
        } catch (Exception ex) {
            throw new WebServiceException(ErrorUtils.getStackTrace(ex));
        }
    }

    /**
     * Updates the profile information for the user.
     *
     * @param profileBean
     * @param user
     * @throws WebServiceException
     */
    @Override
    public void updateProfile(ProfileBean profileBean, User user) throws WebServiceException {
        try {
            if (null != profileBean) {
//TODO: UPDATE TO USE NEW CLIENT
//                if (user != null) {
//                    this.updateAddress(profileBean, user.getAddressList(), user);
//                    this.updateContact(profileBean, user.getContactList(), user);
//                }
            }
        } catch (Exception ex) {
            throw new WebServiceException(ErrorUtils.getStackTrace(ex));
        }
    }

    /**
     * Updates the Address for the user.
     *
     * @param profileBean
     * @param addresses
     */
    private void updateAddress(ProfileBean profileBean, List<Address> addresses, User user) {
        if (StringUtils.isNotBlank(profileBean.getAddressLine1())) {
            if (CollectionUtils.isNotEmpty(addresses)) {

                Address address = addresses.get(0);

                ProfileHelper.mapProfileBeanToAddressEntity(profileBean, address, this.adrStateCdFacadeREST, user);
                this.addressFacadeREST.edit(address);
            } else {
                this.createAddress(profileBean, user);
            }
        }
    }

    /**
     * Updates the Contact for the user.
     *
     * @param profileBean
     * @param contacts
     */
    private void updateContact(ProfileBean profileBean, List<Contact> contacts, User user) {
        if (CollectionUtils.isNotEmpty(contacts)) {

            boolean isHomePhoneContactExists = false;
            boolean isEmailContactExists = false;

            for (Contact contactToUpdate : contacts) {
                if (StringUtils.equals(ContactTypeEnum.HOME_PHONE.getValue(), contactToUpdate.getEmcTypeCd().getCode())) {
                    ProfileHelper.mapProfileBeanToContactEntity(profileBean.getPhoneType(), profileBean.getPhone(), contactToUpdate, this.emcTypeCdFacadeREST, user);
                    this.contactFacadeREST.edit(contactToUpdate);

                    isHomePhoneContactExists = true;
                }

                if (StringUtils.equals(ContactTypeEnum.EMAIL_ADDRESS.getValue(), contactToUpdate.getEmcTypeCd().getCode())) {
                    ProfileHelper.mapProfileBeanToContactEntity(ContactTypeEnum.EMAIL_ADDRESS.getValue(), profileBean.getEmail(), contactToUpdate, this.emcTypeCdFacadeREST, user);
                    this.contactFacadeREST.edit(contactToUpdate);

                    isEmailContactExists = true;
                }
            }

            if (!isHomePhoneContactExists) {
                this.createHomePhoneContact(profileBean, user);
            }

            if (!isEmailContactExists) {
                this.createEmailContact(profileBean, user);
            }
        } else {
            this.createContactInformation(profileBean, user);
        }
    }

    /**
     * Creates the address information into the contact table.
     *
     * @param profileBean
     * @param user
     */
    private void createAddress(ProfileBean profileBean, User user) {
        if (StringUtils.isNotBlank(profileBean.getAddressLine1())) {
            
//TODO: UPDATE TO USE NEW WEB SERVICE CLIENT
//            if (null == user.getAddressList()) {
//                user.setAddressList(new ArrayList<>());
//            }
//
//            Address address = ProfileHelper.convertProfileBeanToAddressEntity(profileBean, this.adrStateCdFacadeREST, user);
//            address.setUsrUidFk(user);
//
//            user.getAddressList().add(address);

//            this.addressFacadeREST.create(address);
        }
    }

    /**
     * Creates the phone number and/or email information into the contact table.
     *
     * @param profileBean
     * @param user
     */
    private void createContactInformation(ProfileBean profileBean, User user) {
        
        
//TODO: REFACTOR TO USE NEW WEB SERVICE CLIENT      
//        this.createHomePhoneContact(profileBean, user);
//        this.createEmailContact(profileBean, user);
    }

    /**
     * Creates a new home phone contact.
     *
     * @param profileBean
     * @param user
     */
    private void createHomePhoneContact(ProfileBean profileBean, User user) {
        
//TODO: REFACTOR TO USE NEW WEB SERVICE CLIENT
//        if (StringUtils.isNotBlank(profileBean.getPhone())) {
//            if (null == user.getContactList()) {
//                user.setContactList(new ArrayList<>());
//            }
//
//            Contact contact = ProfileHelper.convertPhoneNumberToContactEntity(profileBean, this.emcTypeCdFacadeREST, user);
//            contact.setUsrUidFk(user);
//
//            user.getContactList().add(contact);
//
//            this.contactFacadeREST.create(contact);
//        }
    }

    /**
     * Creates a new email contact.
     *
     * @param profileBean
     * @param user
     */
    private void createEmailContact(ProfileBean profileBean, User user) {
        
//TODO: REFACTOR TO USE NEW WEB SERVICE CLIENT
//        if (StringUtils.isNotBlank(profileBean.getEmail())) {
//            if (null == user.getContactList()) {
//                user.setContactList(new ArrayList<>());
//            }
//
//            Contact contact = ProfileHelper.convertEmailToContactEntity(profileBean, this.emcTypeCdFacadeREST, user);
//            contact.setUsrUidFk(user);
//
//            user.getContactList().add(contact);
//
//            this.contactFacadeREST.create(contact);
//        }
    }
}
