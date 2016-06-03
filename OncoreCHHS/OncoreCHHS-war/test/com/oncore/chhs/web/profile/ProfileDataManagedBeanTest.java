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

import com.oncore.chhs.web.entities.Address;
import com.oncore.chhs.web.entities.AdrStateCd;
import com.oncore.chhs.web.entities.Contact;
import com.oncore.chhs.web.entities.EmcTypeCd;
import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.enums.ContactTypeEnum;
import com.oncore.chhs.web.services.AddressFacadeREST;
import com.oncore.chhs.web.services.AdrStateCdFacadeREST;
import com.oncore.chhs.web.services.ContactFacadeREST;
import com.oncore.chhs.web.services.EmcTypeCdFacadeREST;
import com.oncore.chhs.web.services.UsersFacadeREST;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for ProfileDataManagedBean class.
 *
 * @author oncore
 */
public class ProfileDataManagedBeanTest {

    /**
     * Test of findProfileByUserUid method, of class ProfileDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testFindProfileByUserUid() throws Exception {

        Users users = new Users();
        users.setUsrUid(1);
        users.setUsrUserId("userName");
        users.setUsrFirstname("firstName");
        users.setUsrLastname("lastName");

        Address addy = new Address();
        addy.setAdrLine1("addressLine1");
        addy.setAdrCity("city");
        addy.setAdrStateCd(new AdrStateCd("CA"));
        addy.setAdrZip5("12345");
        addy.setAdrZip4("");

        List<Address> addys = new ArrayList<>();
        addys.add(addy);
        users.setAddressList(addys);

        Contact contact = new Contact();
        contact.setEmcTypeCd(new EmcTypeCd("HPH"));
        contact.setEmcValue("123-456-7890");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);
        users.setContactList(contacts);

        ProfileDataManagedBean instance = new ProfileDataManagedBean();
        UsersFacadeREST mockREST = mock(UsersFacadeREST.class);
        when(mockREST.find(1)).thenReturn(users);

        instance.setUsersFacadeREST(mockREST);

        ProfileBean result = instance.findProfileByUserUid(1);
        assertNotNull(result);

        assertEquals(users.getUsrUserId(), result.getUserName());
        assertEquals(users.getUsrFirstname(), result.getFirstName());
        assertEquals(users.getUsrLastname(), result.getLastName());

        assertEquals(addy.getAdrLine1(), result.getAddressLine1());
        assertEquals(addy.getAdrCity(), result.getCity());
        assertEquals(addy.getAdrStateCd().getCode(), result.getState());
        assertEquals(addy.getAdrZip5(), result.getZip());

        assertEquals(contact.getEmcValue(), result.getPhone());
    }

    /**
     * Test of createProfile method, of class ProfileDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testCreateProfile() throws Exception {

        ProfileBean profile = new ProfileBean();

        profile.setAddressLine1("addressLine1");
        profile.setCity("city");
        profile.setState("CA");
        profile.setZip("12345");

        profile.setPhone("123-456-7890");
        profile.setPhoneType("HPH");

        profile.setEmail("me@somewhere.org");

        Users users = new Users();
        users.setUsrUid(100);

        ProfileDataManagedBean instance = new ProfileDataManagedBean();

        AddressFacadeREST mockAddressREST = mock(AddressFacadeREST.class);
        instance.setAddressFacadeREST(mockAddressREST);

        AdrStateCdFacadeREST mockAdrStateCdREST = mock(AdrStateCdFacadeREST.class);
        when(mockAdrStateCdREST.findByCode("CA")).thenReturn(new AdrStateCd("CA"));
        instance.setAdrStateCdFacadeREST(mockAdrStateCdREST);

        ContactFacadeREST mockContactREST = mock(ContactFacadeREST.class);
        instance.setContactFacadeREST(mockContactREST);

        EmcTypeCdFacadeREST mockEmcTypeCdREST = mock(EmcTypeCdFacadeREST.class);
        when(mockEmcTypeCdREST.findByCode(ContactTypeEnum.HOME_PHONE.getValue())).
                thenReturn(new EmcTypeCd(ContactTypeEnum.HOME_PHONE.getValue()));
        instance.setEmcTypeCdFacadeREST(mockEmcTypeCdREST);

//        instance.createProfile(profile, users);

        verify(mockAddressREST).create(new Address());
        verify(mockContactREST, times(2)).create(new Contact());

        List<Address> addyList = users.getAddressList();
        assertEquals(addyList.size(), 1);
        Address addy = addyList.get(0);
        assertEquals(users, addy.getUsrUidFk());
        assertEquals(profile.getAddressLine1(), addy.getAdrLine1());
        assertEquals(profile.getCity(), addy.getAdrCity());
        assertEquals(profile.getState(), addy.getAdrStateCd().getCode());
        assertEquals(profile.getZip(), addy.getAdrZip5());

        List<Contact> contactList = users.getContactList();
        assertEquals(contactList.size(), 2);

        Contact phone = contactList.get(0);
        assertEquals(users, phone.getUsrUidFk());
        assertEquals(profile.getPhoneType(), phone.getEmcTypeCd().getCode());
        assertEquals(profile.getPhone(), phone.getEmcValue());

        Contact email = contactList.get(1);
        assertEquals(users, email.getUsrUidFk());
        assertEquals(profile.getEmail(), email.getEmcValue());

    }

    /**
     * Test of updateProfile method, of class ProfileDataManagedBean. In this
     * test we will check that the address and phone get updated, and a new
     * email address gets added.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testUpdateProfileAddNewEmail() throws Exception {

        ProfileBean profile = new ProfileBean();

        profile.setAddressLine1("addressLine1");
        profile.setCity("city");
        profile.setState("CA");
        profile.setZip("12345");

        profile.setPhone("123-456-7890");
        profile.setPhoneType(ContactTypeEnum.HOME_PHONE.getValue());

        profile.setEmail("me@somewhere.org");

        Users users = new Users();
        users.setUsrUid(100);

        Address existingAddy = new Address();
        existingAddy.setUsrUidFk(users);
        existingAddy.setAdrLine1("something different");
        existingAddy.setAdrCity("different city");
        existingAddy.setAdrStateCd(new AdrStateCd("WA"));
        existingAddy.setAdrZip5("11111");

        List<Address> existingAddys = new ArrayList<>();
        existingAddys.add(existingAddy);
        users.setAddressList(existingAddys);

        Contact existingPhone = new Contact();
        existingPhone.setUsrUidFk(users);
        existingPhone.setEmcTypeCd(new EmcTypeCd(ContactTypeEnum.HOME_PHONE.getValue()));
        existingPhone.setEmcValue("444-333-2222");

        List<Contact> existingContacts = new ArrayList<>();
        existingContacts.add(existingPhone);
        users.setContactList(existingContacts);

        ProfileDataManagedBean instance = new ProfileDataManagedBean();

        AddressFacadeREST mockAddressREST = mock(AddressFacadeREST.class);
        instance.setAddressFacadeREST(mockAddressREST);

        AdrStateCdFacadeREST mockAdrStateCdREST = mock(AdrStateCdFacadeREST.class);
        when(mockAdrStateCdREST.findByCode("CA")).thenReturn(new AdrStateCd("CA"));
        instance.setAdrStateCdFacadeREST(mockAdrStateCdREST);

        ContactFacadeREST mockContactREST = mock(ContactFacadeREST.class);
        instance.setContactFacadeREST(mockContactREST);

        EmcTypeCdFacadeREST mockEmcTypeCdREST = mock(EmcTypeCdFacadeREST.class);
        when(mockEmcTypeCdREST.findByCode(ContactTypeEnum.HOME_PHONE.getValue())).
                thenReturn(new EmcTypeCd(ContactTypeEnum.HOME_PHONE.getValue()));
        instance.setEmcTypeCdFacadeREST(mockEmcTypeCdREST);

//        instance.updateProfile(profile, users);

        verify(mockAddressREST).edit(existingAddy);
        verify(mockContactREST).edit(existingPhone);
        verify(mockContactREST).create(new Contact());

        List<Address> addyList = users.getAddressList();
        assertEquals(addyList.size(), 1);
        Address addy = addyList.get(0);
        assertEquals(users, addy.getUsrUidFk());
        assertEquals(profile.getAddressLine1(), addy.getAdrLine1());
        assertEquals(profile.getCity(), addy.getAdrCity());
        assertEquals(profile.getState(), addy.getAdrStateCd().getCode());
        assertEquals(profile.getZip(), addy.getAdrZip5());

        List<Contact> contactList = users.getContactList();
        assertEquals(contactList.size(), 2);

        Contact phone = contactList.get(0);
        assertEquals(users, phone.getUsrUidFk());
        assertEquals(profile.getPhoneType(), phone.getEmcTypeCd().getCode());
        assertEquals(profile.getPhone(), phone.getEmcValue());

        Contact email = contactList.get(1);
        assertEquals(users, email.getUsrUidFk());
        assertEquals(profile.getEmail(), email.getEmcValue());

    }

    /**
     * Test of updateProfile method, of class ProfileDataManagedBean. In this
     * test we will check that the address and email get updated, and a new
     * phone gets added.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testUpdateProfileAddNewPhone() throws Exception {

        ProfileBean profile = new ProfileBean();

        profile.setAddressLine1("addressLine1");
        profile.setCity("city");
        profile.setState("CA");
        profile.setZip("12345");

        profile.setPhone("123-456-7890");
        profile.setPhoneType(ContactTypeEnum.HOME_PHONE.getValue());

        profile.setEmail("me@somewhere.org");

        Users users = new Users();
        users.setUsrUid(100);

        Address existingAddy = new Address();
        existingAddy.setUsrUidFk(users);
        existingAddy.setAdrLine1("something different");
        existingAddy.setAdrCity("different city");
        existingAddy.setAdrStateCd(new AdrStateCd("WA"));
        existingAddy.setAdrZip5("11111");

        List<Address> existingAddys = new ArrayList<>();
        existingAddys.add(existingAddy);
        users.setAddressList(existingAddys);

        Contact existingEmail = new Contact();
        existingEmail.setUsrUidFk(users);
        existingEmail.setEmcTypeCd(new EmcTypeCd(ContactTypeEnum.EMAIL_ADDRESS.getValue()));
        existingEmail.setEmcValue("different@somewhere-else.org");

        List<Contact> existingContacts = new ArrayList<>();
        existingContacts.add(existingEmail);
        users.setContactList(existingContacts);

        ProfileDataManagedBean instance = new ProfileDataManagedBean();

        AddressFacadeREST mockAddressREST = mock(AddressFacadeREST.class);
        instance.setAddressFacadeREST(mockAddressREST);

        AdrStateCdFacadeREST mockAdrStateCdREST = mock(AdrStateCdFacadeREST.class);
        when(mockAdrStateCdREST.findByCode("CA")).thenReturn(new AdrStateCd("CA"));
        instance.setAdrStateCdFacadeREST(mockAdrStateCdREST);

        ContactFacadeREST mockContactREST = mock(ContactFacadeREST.class);
        instance.setContactFacadeREST(mockContactREST);

        EmcTypeCdFacadeREST mockEmcTypeCdREST = mock(EmcTypeCdFacadeREST.class);
        when(mockEmcTypeCdREST.findByCode(ContactTypeEnum.HOME_PHONE.getValue())).
                thenReturn(new EmcTypeCd(ContactTypeEnum.HOME_PHONE.getValue()));
        instance.setEmcTypeCdFacadeREST(mockEmcTypeCdREST);

//        instance.updateProfile(profile, users);

        verify(mockAddressREST).edit(existingAddy);
        verify(mockContactREST).edit(existingEmail);
        verify(mockContactREST).create(new Contact());

        List<Address> addyList = users.getAddressList();
        assertEquals(addyList.size(), 1);
        Address addy = addyList.get(0);
        assertEquals(users, addy.getUsrUidFk());
        assertEquals(profile.getAddressLine1(), addy.getAdrLine1());
        assertEquals(profile.getCity(), addy.getAdrCity());
        assertEquals(profile.getState(), addy.getAdrStateCd().getCode());
        assertEquals(profile.getZip(), addy.getAdrZip5());

        List<Contact> contactList = users.getContactList();
        assertEquals(contactList.size(), 2);

        Contact phone = contactList.get(1);
        assertEquals(users, phone.getUsrUidFk());
        assertEquals(profile.getPhoneType(), phone.getEmcTypeCd().getCode());
        assertEquals(profile.getPhone(), phone.getEmcValue());

        Contact email = contactList.get(0);
        assertEquals(users, email.getUsrUidFk());
        assertEquals(profile.getEmail(), email.getEmcValue());

    }

}
