/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service.web.rest;

import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.ejb.EJBUtils;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 * This class provides RESTful services for Profile functionality.
 * 
 * @author oncore
 */
@Provider
@Path("Profile")
public class ProfileService {
    
    @Context
    private UriInfo context;
    
    /**
     * Default constructor.
     */
    public ProfileService() {
        
    }
    
    @GET
    @Path("/findByUserUid/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})    
    public Profile findByUserUid( @QueryParam("id") Integer userUid)
    {
        return this.getEjbProfileService().findProfileByUserUid(userUid);
    }
    
    /**
     * Get the EJB service that will handle the request.
     * Package level to allow access by unit tests.
     * 
     * @return The EJB ProfileService.
     */
    com.oncore.chhs.client.ejb.ProfileService getEjbProfileService()
    {
        return EJBUtils.lookupEJB(com.oncore.chhs.client.ejb.ProfileService.class );
    }
}
