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
package com.oncore.chhs.web.rest.client;

import com.oncore.chhs.properties.PropertyFileResourceLoader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.client.WebTarget;

/**
 *
 * @author OnCore LLC
 */
public abstract class AbstractRestClient {

    private static final String CLIENT_SERVICE_URLS = "client_service_urls.properties";

    /**
     *
     * @param propertyName The URL property name.
     * @return The WebTarget.
     */
    protected WebTarget getTarget(String propertyName) {
        return this.getTarget(propertyName, false);
    }

    /**
     *
     * @param propertyName The URL property name.
     * @param debug Log debug messages.
     * 
     * @return The WebTarget.
     */
    protected WebTarget getTarget(String propertyName, boolean debug) {
        Client client = ClientBuilder.newClient();
        
//        if ( debug ) {
//            client.register( new LoggingFilter() );
//        }
        
        WebTarget target = client.target( this.getUrl( propertyName ) );

        return target;
    }

    /**
     *
     * @param propertyName The property name.
     * @return The URL.
     */
    private String getUrl(String propertyName) {
        return PropertyFileResourceLoader.getProperty(CLIENT_SERVICE_URLS, propertyName);
    }
}
