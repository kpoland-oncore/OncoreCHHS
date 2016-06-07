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
package com.oncore.chhs.service.web.rest;

import com.oncore.chhs.client.dto.profile.CreateOrUpdateProfile;
import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.ejb.EJBUtils;
import com.oncore.chhs.web.rest.response.InsertResponse;
import com.oncore.chhs.web.rest.response.SelectResponse;
import com.oncore.chhs.web.rest.response.UpdateResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.Logger;

/**
 * This class provides RESTful services for Profile functionality.
 *
 * @author OnCore LLC
 */
@Provider
@Path("Profile")
public class ProfileService {

    private static final Logger LOGGER = Logger.getLogger(ProfileService.class);

    @Context
    private UriInfo context;

    /**
     * Default constructor.
     */
    public ProfileService() {

    }

    /**
     * Find the profile by the user's unique ID.
     *
     * @param userUid The user ID.
     *
     * @return The user's profile.
     */
    @GET
    @Path("/find/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SelectResponse<Profile> findByUserUid(@QueryParam("id") Integer userUid) {
        SelectResponse<Profile> response = null;

        try {
            Profile profile = this.getEjbProfileService().findProfileByUserUid(userUid);

            response = new SelectResponse<>(profile);
        } catch (Throwable t) {
            LOGGER.error("Error loading profile for user ID " + userUid, t);
            response = new SelectResponse<>("Error loading profile.", t);
        }

        return response;
    }
    
    @POST
    @Path( "/create/")
    @Consumes({MediaType.APPLICATION_JSON})
    public InsertResponse<Long> createProfile( CreateOrUpdateProfile newProfile )
    {
        InsertResponse<Long> response = null;
        
        try
        {
            this.getEjbProfileService().createProfile(newProfile);
            
            response = new InsertResponse(1);
        }
        catch (Throwable t )
        {
            String error = "Error creating profile.";
            
            LOGGER.error( error, t);
            response = new InsertResponse( error, t );
        }
        
        return response;
    }
    
    @POST
    @Path( "/update/")
    @Consumes({MediaType.APPLICATION_JSON})
    public UpdateResponse updateProfile( CreateOrUpdateProfile updatedProfile )
    {
        UpdateResponse response = null;
        
        try
        {
            this.getEjbProfileService().updateProfile(updatedProfile);
            
            response = new UpdateResponse(1);
        }
        catch (Throwable t )
        {
            String error = "Error updating profile.";
            
            LOGGER.error( error, t);
            response = new UpdateResponse( error, t );
        }
        
        return response;
    }

    /**
     * Get the EJB service that will handle the request. Package level to allow
     * access by unit tests.
     *
     * @return The EJB ProfileService.
     */
    com.oncore.chhs.client.ejb.ProfileService getEjbProfileService() {
        return EJBUtils.lookupEJB(com.oncore.chhs.client.ejb.ProfileService.class);
    }
}
