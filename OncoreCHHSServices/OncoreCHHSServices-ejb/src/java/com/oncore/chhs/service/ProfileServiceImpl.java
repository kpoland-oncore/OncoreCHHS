/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service;

import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.client.ejb.ProfileService;
import com.oncore.chhs.persistence.dao.UserDAO;
import com.oncore.chhs.persistence.entity.Address;
import com.oncore.chhs.persistence.entity.Contact;
import com.oncore.chhs.persistence.entity.Users;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.apache.commons.collections4.CollectionUtils;

/**
 *
 * @author oncore
 */
@Stateless
@Remote(ProfileService.class)
public class ProfileServiceImpl implements ProfileService {

    private static final List<String> CONTACT_PHONE_TYPES
            = Arrays.asList("HPH", "MPH", "SMS", "WPH");

    @EJB
    private UserDAO userDAO;

    @Override
    public Profile findProfileByUserUid(Integer userUid) {

        Users users = this.userDAO.findById(userUid);

        Profile profile = new Profile();

        if (users != null) {
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
