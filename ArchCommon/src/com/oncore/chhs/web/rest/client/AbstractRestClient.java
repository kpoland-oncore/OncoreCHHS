/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.web.rest.client;

import com.oncore.chhs.properties.PropertyFileResourceLoader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Kerry O'Brien
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
