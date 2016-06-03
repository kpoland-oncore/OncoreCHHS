/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.rest;

import com.oncore.chhs.client.dto.FosterFamilyAgencies;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author oncore
 */
public class LocateServiceClient {

    /**
     *
     *
     * @param zip
     *
     * @return
     */
    public FosterFamilyAgencies searchFosterFamilyAgency(String zip) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("https://chhs.data.ca.gov/resource/mffa-c6z5.json").
                queryParam("facility_status", "LICENSED").queryParam("facility_zip", zip);

        FosterFamilyAgencies results = target.request(MediaType.APPLICATION_JSON)
                .get(FosterFamilyAgencies.class);

        return results;
    }
}
