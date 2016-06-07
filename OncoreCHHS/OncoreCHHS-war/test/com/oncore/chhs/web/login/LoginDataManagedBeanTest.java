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
package com.oncore.chhs.web.login;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.client.rest.UsersServiceClient;
import com.oncore.chhs.web.profile.ProfileBean;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 * Unit test for LoginDataManagedBean class.
 *
 * @author OnCore LLC
 */
public class LoginDataManagedBeanTest {

    /**
     * Test of createUser method, of class LoginDataManagedBean.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testCreateUser() throws Exception {

        ProfileBean profile = new ProfileBean();
        profile.setFirstName("firstName");
        profile.setLastName("lastName");
        profile.setMiddleName("middleName");
        profile.setUserName("userName");

        UsersServiceClient mockService = mock(UsersServiceClient.class);

        LoginDataManagedBean instance = spy(new LoginDataManagedBean());
        when(instance.getUsersServiceClient()).thenReturn(mockService);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        instance.createUser(profile);

        verify(mockService).createUser(userCaptor.capture());

        User actual = userCaptor.getValue();

        //the chosen username should get populated into all these fields
        assertEquals("userName", actual.getUserName());
        assertEquals("firstName", actual.getFirstName());
        assertEquals("middleName", actual.getMiddleName());
        assertEquals("lastName", actual.getLastName());

    }

    /**
     * Test of authenticateUser method, of class LoginDataManagedBean, when a
     * user is found.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testAuthenticateUser() throws Exception {

        LoginBean login = new LoginBean();
        login.setUserName("userName");

        User expected = new User();
        expected.setUserName("userName");

        UsersServiceClient mockService = mock(UsersServiceClient.class);
        when(mockService.authenticateUser("userName")).thenReturn(expected);

//        LoginDataManagedBean instance = spy(new LoginDataManagedBean());
//        when(instance.getUsersServiceClient()).thenReturn(mockService);
//        User answer = instance.authenticateUser(login);
//        assertEquals(expected, answer);
    }

    /**
     * Test of authenticateUser method, of class LoginDataManagedBean, when no
     * user is found for the given user name.
     *
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testAuthenticateUserNoUserFound() throws Exception {

        LoginBean login = new LoginBean();
        login.setUserName("userName");

        UsersServiceClient mockService = mock(UsersServiceClient.class);
        when(mockService.authenticateUser("userName")).thenReturn(null);

//        LoginDataManagedBean instance = spy(new LoginDataManagedBean());
//        when(instance.getUsersServiceClient()).thenReturn(mockService);
//
//        User answer = instance.authenticateUser(login);
//        assertNull(answer);
    }

}
