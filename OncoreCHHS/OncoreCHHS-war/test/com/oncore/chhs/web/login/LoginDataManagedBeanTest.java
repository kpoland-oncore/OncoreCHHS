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

import com.oncore.chhs.web.entities.Users;
import com.oncore.chhs.web.profile.ProfileBean;
import com.oncore.chhs.web.services.UsersFacadeREST;
import java.lang.reflect.Method;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 * Unit test for LoginDataManagedBean class.
 * 
 * @author oncore
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
        
        //all these fields should be ignored by this method - 
        //they are handled by other services.
        profile.setAddressLine1("ignore");
        profile.setAddressLine2("ignore");
        profile.setCity("ignore");
        profile.setEmail("ignore");
        profile.setPhone("ignore");
        profile.setPhoneType("ignore");
        profile.setState("ignore");
        profile.setZip("ignore");
        
        LoginDataManagedBean instance = new LoginDataManagedBean();
        
        UsersFacadeREST mockREST = mock(UsersFacadeREST.class);
        instance.setUsersFacadeREST(mockREST);
        instance.createUser(profile);
        
        ArgumentCaptor<Users> actual = ArgumentCaptor.forClass(Users.class);
        verify(mockREST).create(actual.capture());
        
        //the chosen username should get populated into all these fields
        assertEquals( "userName", actual.getValue().getUsrUserId());
        assertEquals( "userName", actual.getValue().getCreateUserId());
        assertEquals( "userName", actual.getValue().getUpdateUserId());
        
        //timestamps should be populated
        assertNotNull( actual.getValue().getCreateTs() );
        assertNotNull( actual.getValue().getUpdateTs() );
        
        //other name fields should be populated
        assertEquals( "firstName", actual.getValue().getUsrFirstname() );
        assertEquals( "middleName", actual.getValue().getUsrMiddlename() );
        assertEquals( "lastName", actual.getValue().getUsrLastname() );
        
        //ensure no ignored fields were updated on the Users object
        Method[] methods = Users.class.getDeclaredMethods();
        for ( Method method : methods )
        {
            if ( String.class.equals(method.getReturnType()))
            {
                String result = (String)method.invoke(actual.getValue(), (Object[])null );
                assertFalse( method.getName() + " should not be populated with any value", 
                    result.equals("ignore"));
            }
        }
    }

    /**
     * Test of authenticateUser method, of class LoginDataManagedBean,
     * when a user is found.
     * 
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testAuthenticateUser() throws Exception {
        
        LoginBean login = new LoginBean();
        login.setUserName("userName");
        
        LoginDataManagedBean instance = new LoginDataManagedBean();
        
        Users expected = new Users();
        expected.setUsrUserId("userName");
        
        UsersFacadeREST mockREST = mock(UsersFacadeREST.class);
        when(mockREST.findByUserId(login.getUserName())).thenReturn(expected);
        
        instance.setUsersFacadeREST(mockREST);
        
        Users answer = instance.authenticateUser(login);
        verify(mockREST).findByUserId(login.getUserName());
        assertEquals(expected, answer);
    }    
    
    /**
     * Test of authenticateUser method, of class LoginDataManagedBean,
     * when no user is found for the given user name.
     * 
     * @throws Exception Any unexpected exception should fail the test.
     */
    @Test
    public void testAuthenticateUserNoUserFound() throws Exception {
        
        LoginBean login = new LoginBean();
        login.setUserName("userName");
        
        LoginDataManagedBean instance = new LoginDataManagedBean();
        
        UsersFacadeREST mockREST = mock(UsersFacadeREST.class);
        when(mockREST.findByUserId(login.getUserName())).thenReturn(null);
        
        instance.setUsersFacadeREST(mockREST);
        
        Users answer = instance.authenticateUser(login);
        verify(mockREST).findByUserId(login.getUserName());
        assertNull(answer );
    }

}
