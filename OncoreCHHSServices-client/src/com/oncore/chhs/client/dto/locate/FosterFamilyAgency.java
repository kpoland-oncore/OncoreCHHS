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
package com.oncore.chhs.client.dto.locate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OnCore LLC
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FosterFamilyAgency {

    private String county_name;
    private String facility_address;
    private String facility_administrator;
    private String facility_capacity;
    private String facility_city;
    private String facility_name;
    private String facility_number;
    private String facility_state;
    private String facility_telephone_number;
    private String facility_type;
    private String facility_zip;
    private Location location;
    private String location_address;
    private String location_city;
    private String location_zip;
    private String reginal_office;

    /**
     *
     */
    public FosterFamilyAgency() {

    }

    /**
     * @return the county_name
     */
    public String getCounty_name() {
        return county_name;
    }

    /**
     * @param county_name the county_name to set
     */
    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    /**
     * @return the facility_address
     */
    public String getFacility_address() {
        return facility_address;
    }

    /**
     * @param facility_address the facility_address to set
     */
    public void setFacility_address(String facility_address) {
        this.facility_address = facility_address;
    }

    /**
     * @return the facility_administrator
     */
    public String getFacility_administrator() {
        return facility_administrator;
    }

    /**
     * @param facility_administrator the facility_administrator to set
     */
    public void setFacility_administrator(String facility_administrator) {
        this.facility_administrator = facility_administrator;
    }

    /**
     * @return the facility_capacity
     */
    public String getFacility_capacity() {
        return facility_capacity;
    }

    /**
     * @param facility_capacity the facility_capacity to set
     */
    public void setFacility_capacity(String facility_capacity) {
        this.facility_capacity = facility_capacity;
    }

    /**
     * @return the facility_city
     */
    public String getFacility_city() {
        return facility_city;
    }

    /**
     * @param facility_city the facility_city to set
     */
    public void setFacility_city(String facility_city) {
        this.facility_city = facility_city;
    }

    /**
     * @return the facility_name
     */
    public String getFacility_name() {
        return facility_name;
    }

    /**
     * @param facility_name the facility_name to set
     */
    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    /**
     * @return the facility_number
     */
    public String getFacility_number() {
        return facility_number;
    }

    /**
     * @param facility_number the facility_number to set
     */
    public void setFacility_number(String facility_number) {
        this.facility_number = facility_number;
    }

    /**
     * @return the facility_state
     */
    public String getFacility_state() {
        return facility_state;
    }

    /**
     * @param facility_state the facility_state to set
     */
    public void setFacility_state(String facility_state) {
        this.facility_state = facility_state;
    }

    /**
     * @return the facility_telephone_number
     */
    public String getFacility_telephone_number() {
        return facility_telephone_number;
    }

    /**
     * @param facility_telephone_number the facility_telephone_number to set
     */
    public void setFacility_telephone_number(String facility_telephone_number) {
        this.facility_telephone_number = facility_telephone_number;
    }

    /**
     * @return the facility_type
     */
    public String getFacility_type() {
        return facility_type;
    }

    /**
     * @param facility_type the facility_type to set
     */
    public void setFacility_type(String facility_type) {
        this.facility_type = facility_type;
    }

    /**
     * @return the facility_zip
     */
    public String getFacility_zip() {
        return facility_zip;
    }

    /**
     * @param facility_zip the facility_zip to set
     */
    public void setFacility_zip(String facility_zip) {
        this.facility_zip = facility_zip;
    }

//    /**
//     * @return the location
//     */
//    public Location getLocation() {
//        return location;
//    }
//
//    /**
//     * @param location the location to set
//     */
//    public void setLocation(Location location) {
//        this.location = location;
//    }
    /**
     * @return the location_address
     */
    public String getLocation_address() {
        return location_address;
    }

    /**
     * @param location_address the location_address to set
     */
    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    /**
     * @return the location_city
     */
    public String getLocation_city() {
        return location_city;
    }

    /**
     * @param location_city the location_city to set
     */
    public void setLocation_city(String location_city) {
        this.location_city = location_city;
    }

    /**
     * @return the location_zip
     */
    public String getLocation_zip() {
        return location_zip;
    }

    /**
     * @param location_zip the location_zip to set
     */
    public void setLocation_zip(String location_zip) {
        this.location_zip = location_zip;
    }

    /**
     * @return the reginal_office
     */
    public String getReginal_office() {
        return reginal_office;
    }

    /**
     * @param reginal_office the reginal_office to set
     */
    public void setReginal_office(String reginal_office) {
        this.reginal_office = reginal_office;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
