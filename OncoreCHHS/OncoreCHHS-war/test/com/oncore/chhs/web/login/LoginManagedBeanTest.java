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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oncore
 */
public class LoginManagedBeanTest {
    
    public LoginManagedBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class LoginManagedBean.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        LoginManagedBean instance = new LoginManagedBean();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class LoginManagedBean.
     */
    @Test
    public void testDestroy() {
        System.out.println("destroy");
        LoginManagedBean instance = new LoginManagedBean();
        instance.destroy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleLoginButtonClickEvent method, of class LoginManagedBean.
     */
    @Test
    public void testHandleLoginButtonClickEvent() {
        System.out.println("handleLoginButtonClickEvent");
        LoginManagedBean instance = new LoginManagedBean();
        String expResult = "";
        String result = instance.handleLoginButtonClickEvent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLoginBean method, of class LoginManagedBean.
     */
    @Test
    public void testGetLoginBean() {
        System.out.println("getLoginBean");
        LoginManagedBean instance = new LoginManagedBean();
        LoginBean expResult = null;
        LoginBean result = instance.getLoginBean();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoginBean method, of class LoginManagedBean.
     */
    @Test
    public void testSetLoginBean() {
        System.out.println("setLoginBean");
        LoginBean loginBean = null;
        LoginManagedBean instance = new LoginManagedBean();
        instance.setLoginBean(loginBean);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}