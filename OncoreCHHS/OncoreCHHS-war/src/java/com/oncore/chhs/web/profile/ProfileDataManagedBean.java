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

import com.oncore.chhs.web.base.BaseManagedBean;
import com.oncore.chhs.web.entities.Address;
import com.oncore.chhs.web.entities.Contact;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.exceptions.WebServiceException;
import com.oncore.chhs.web.login.LoginDataManagedBean;
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

    @Override
    public void createProfile(ProfileBean profileBean, Users user) throws WebServiceException {
        try {
            boolean isUpdateUser = false;

            if (StringUtils.isNotBlank(profileBean.getAddressLine1())) {
                if (null == user.getAddressList()) {
                    user.setAddressList(new ArrayList<>());
                }

                Address address = ProfileHelper.convertProfileBeanToAddressEntity(profileBean, this.adrStateCdFacadeREST, user);
                address.setUsrUidFk(user);

                user.getAddressList().add(address);

                this.addressFacadeREST.create(address);
            }

            if (StringUtils.isNotBlank(profileBean.getPhone())) {
                if (null == user.getContactList()) {
                    user.setContactList(new ArrayList<>());
                }

                Contact contact = ProfileHelper.convertProfileBeanToContactEntity(profileBean, this.emcTypeCdFacadeREST, user);
                contact.setUsrUidFk(user);

                user.getContactList().add(contact);

                this.contactFacadeREST.create(contact);
            }
            
                        if (StringUtils.isNotBlank(profileBean.getEmail())) {
                if (null == user.getContactList()) {
                    user.setContactList(new ArrayList<>());
                }

                Contact contact = ProfileHelper.convertProfileBeanToContactEntity(profileBean, this.emcTypeCdFacadeREST, user);
                contact.setUsrUidFk(user);

                user.getContactList().add(contact);

                this.contactFacadeREST.create(contact);
            }

            if (isUpdateUser) {
                this.usersFacadeREST.edit(user.getUsrUid(), user);
            }

        } catch (Exception ex) {
            throw new WebServiceException(ErrorUtils.getStackTrace(ex));
        }
    }

    @Override
    public void updateProfile(ProfileBean profileBean, Users users) throws WebServiceException {
        try {
            if (null != profileBean) {

                if (users != null) {
                    this.updateAddress(profileBean, users.getAddressList(), users);
                    this.updateContact(profileBean, users.getContactList(), users);
                }
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
    private void updateAddress(ProfileBean profileBean, List<Address> addresses, Users users) {
        if (StringUtils.isNotBlank(profileBean.getAddressLine1())) {
            if (CollectionUtils.isNotEmpty(addresses)) {

                Address address = addresses.get(0);

                ProfileHelper.mapProfileBeanToAddressEntity(profileBean, address, this.adrStateCdFacadeREST, users);
                this.addressFacadeREST.edit(address);
            } else {
                Address address = new Address();

                ProfileHelper.mapProfileBeanToAddressEntity(profileBean, address, this.adrStateCdFacadeREST, users);
                this.addressFacadeREST.create(address);
            }
        }
    }

    /**
     * Updates the Contact for the user.
     *
     * @param profileBean
     * @param contacts
     */
    private void updateContact(ProfileBean profileBean, List<Contact> contacts, Users users) {
        if (CollectionUtils.isNotEmpty(contacts)) {
            for (Contact contactToUpdate : contacts) {
                if (StringUtils.isNotBlank(profileBean.getPhone())) {
                    if (StringUtils.equals(profileBean.getPhoneType(), contactToUpdate.getEmcTypeCd().getCode()) && StringUtils.equals(profileBean.getPhone(), contactToUpdate.getEmcValue())) {
                        ProfileHelper.mapProfileBeanToContactEntity(profileBean, contactToUpdate, this.emcTypeCdFacadeREST, users);
                        this.contactFacadeREST.edit(contactToUpdate);
                    }
                }

                if (StringUtils.isNotBlank(profileBean.getEmail())) {
                    ProfileHelper.mapProfileBeanToContactEntity(profileBean, contactToUpdate, this.emcTypeCdFacadeREST, users);
                    this.contactFacadeREST.edit(contactToUpdate);
                }
            }
        } else {
            Contact newContact = new Contact();

            ProfileHelper.mapProfileBeanToContactEntity(profileBean, newContact, this.emcTypeCdFacadeREST, users);
            this.contactFacadeREST.create(newContact);
        }
    }
}
