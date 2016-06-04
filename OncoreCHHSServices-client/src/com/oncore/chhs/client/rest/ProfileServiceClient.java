/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.rest;

import com.oncore.chhs.client.dto.profile.Profile;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * This service client invokes RESTful services for the Profile functionality.
 * 
 * @author oncore
 */
public class ProfileServiceClient {
    
    public Profile findProfileByUserUid(Integer userUid)
    {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:8080/OncoreCHHSServices-war/rest").
                path("Profile").path("findProfileByUserUid").queryParam("userUid", userUid);

        Profile results
                = target.request(MediaType.APPLICATION_JSON).get(Profile.class);
        
        return results;
    }
    
}
