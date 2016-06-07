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
import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.client.rest.ProfileServiceClient;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 * Unit test for ProfileDataManagedBean class.
 *
 * @author OnCore LLC
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

        ProfileDataManagedBean instance = spy(new ProfileDataManagedBean());
        when(instance.getProfileServiceClient()).thenReturn(mockService);

        User user = new User();
        
        ProfileBean profileBean = new ProfileBean();
        
        ArgumentCaptor<Profile> profileCaptor = ArgumentCaptor.forClass(Profile.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        
        instance.createProfile(profileBean, user );
        
        verify( mockService ).createProfile(profileCaptor.capture(), userCaptor.capture());
    }

    /**
     * Test of updateProfile method, of class ProfileDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testUpdateProfile() throws Exception {

        ProfileServiceClient mockService = mock(ProfileServiceClient.class);

        ProfileDataManagedBean instance = spy(new ProfileDataManagedBean());
        when(instance.getProfileServiceClient()).thenReturn(mockService);

        User user = new User();
        user.setUserUid(1L);
        
        ProfileBean profileBean = new ProfileBean();
        profileBean.setFirstName("firstName");
        profileBean.setAddressLine1("line1");
        profileBean.setEmail("email");
        profileBean.setPhone("phone");
        profileBean.setPhoneType("phoneType");
        
        ArgumentCaptor<Profile> profileCaptor = ArgumentCaptor.forClass(Profile.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        
        instance.updateProfile(profileBean, user );
        
        verify( mockService ).updateProfile(profileCaptor.capture(), userCaptor.capture());
        
        Profile actualProfile = profileCaptor.getValue();
        assertEquals( "firstName", actualProfile.getFirstName() );
        assertEquals( "line1", actualProfile.getAddressLine1() );
        assertEquals( "email", actualProfile.getEmail());
        assertEquals( "phone", actualProfile.getPhone() );
        assertEquals( "phoneType", actualProfile.getPhoneType() );
        
        User actualUser = userCaptor.getValue();
        assertEquals( (Long)1L, actualUser.getUserUid() );

    }

}
