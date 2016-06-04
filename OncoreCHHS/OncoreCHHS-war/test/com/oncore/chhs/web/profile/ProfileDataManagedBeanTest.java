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

import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.client.rest.ProfileServiceClient;
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

        Profile expected = new Profile();
        expected.setUserName("userName");

        ProfileServiceClient mockService = mock(ProfileServiceClient.class);
        when(mockService.findProfileByUserUid(1)).thenReturn(expected);

        ProfileDataManagedBean instance = spy(new ProfileDataManagedBean());
        when(instance.getProfileServiceClient()).thenReturn(mockService);

        Profile answer = instance.findProfileByUserUid(1);

        assertEquals(expected, answer);

    }

    /**
     * Test of createProfile method, of class ProfileDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testCreateProfile() throws Exception {

        ProfileServiceClient mockService = mock(ProfileServiceClient.class);
        //when(mockService()).thenReturn(expected);

        ProfileDataManagedBean instance = spy(new ProfileDataManagedBean());
        when(instance.getProfileServiceClient()).thenReturn(mockService);

        fail("new REST API still needs to be implemented.");

    }

    /**
     * Test of updateProfile method, of class ProfileDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testUpdateProfile() throws Exception {

        ProfileServiceClient mockService = mock(ProfileServiceClient.class);
        //when(mockService()).thenReturn(expected);

        ProfileDataManagedBean instance = spy(new ProfileDataManagedBean());
        when(instance.getProfileServiceClient()).thenReturn(mockService);

        fail("new REST API still needs to be implemented.");

    }

}
