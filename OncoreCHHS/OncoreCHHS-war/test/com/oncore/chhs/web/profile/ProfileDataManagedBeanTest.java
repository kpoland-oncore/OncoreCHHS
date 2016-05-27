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
import com.oncore.chhs.web.services.UsersFacadeREST;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 * Unit test for ProfileDataManagedBean class.
 * 
 * @author oncore
 */
public class ProfileDataManagedBeanTest {

    /**
     * Test of findProfileByUserUid method, of class ProfileDataManagedBean.
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
        contact.setEmcTypeCd(new EmcTypeCd("MPH"));
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
        
        assertEquals( users.getUsrUserId(), result.getUserName() );
        assertEquals( users.getUsrFirstname(), result.getFirstName() );
        assertEquals( users.getUsrLastname(), result.getLastName() );
        
        assertEquals( addy.getAdrLine1(), result.getAddressLine1() );
        assertEquals( addy.getAdrCity(), result.getCity() );
        assertEquals( addy.getAdrStateCd().getCode(), result.getState() );
        assertEquals( addy.getAdrZip5(), result.getZip() );
        
        assertEquals( contact.getEmcValue(), result.getPhone() );
    }

    /**
     * Test of createProfile method, of class ProfileDataManagedBean.
     */
    @Test
    public void testCreateProfile() throws Exception {
        System.out.println("createProfile");
        ProfileBean profileBean = null;
        Users user = null;
        ProfileDataManagedBean instance = new ProfileDataManagedBean();
        instance.createProfile(profileBean, user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProfile method, of class ProfileDataManagedBean.
     */
    @Test
    public void testUpdateProfile() throws Exception {
        System.out.println("updateProfile");
        ProfileBean profileBean = null;
        Users users = null;
        ProfileDataManagedBean instance = new ProfileDataManagedBean();
        instance.updateProfile(profileBean, users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
