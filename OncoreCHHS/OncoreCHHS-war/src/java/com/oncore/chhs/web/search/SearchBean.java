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
package com.oncore.chhs.web.search;

import com.oncore.chhs.web.base.BaseBean;
import java.util.Date;

/**
 *
 * @author oncore
 */
public class SearchBean extends BaseBean {
    
    private String facilityType;
    private String facilityNumber;
    private String facilityName;
    private String licensee;
    private String facilityAdministrator;
    private String facilityTelephone;
    private String facilityAddress;
    private String facilityCity;
    private String facilityState;
    private String facilityZip;
    private String county;
    private String regionalOffice;
    private String facilityCapacity;
    private String facilityStatus;
    private Date licenseFirstDate;
    private Date closeDate;
    private String location;
    private String latitude;
    private String zipForSearch;

    /**
     * @return the facilityType
     */
    public String getFacilityType() {
        return facilityType;
    }

    /**
     * @param facilityType the facilityType to set
     */
    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    /**
     * @return the facilityNumber
     */
    public String getFacilityNumber() {
        return facilityNumber;
    }

    /**
     * @param facilityNumber the facilityNumber to set
     */
    public void setFacilityNumber(String facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    /**
     * @return the facilityName
     */
    public String getFacilityName() {
        return facilityName;
    }

    /**
     * @param facilityName the facilityName to set
     */
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    /**
     * @return the licensee
     */
    public String getLicensee() {
        return licensee;
    }

    /**
     * @param licensee the licensee to set
     */
    public void setLicensee(String licensee) {
        this.licensee = licensee;
    }

    /**
     * @return the facilityAdministrator
     */
    public String getFacilityAdministrator() {
        return facilityAdministrator;
    }

    /**
     * @param facilityAdministrator the facilityAdministrator to set
     */
    public void setFacilityAdministrator(String facilityAdministrator) {
        this.facilityAdministrator = facilityAdministrator;
    }

    /**
     * @return the facilityTelephone
     */
    public String getFacilityTelephone() {
        return facilityTelephone;
    }

    /**
     * @param facilityTelephone the facilityTelephone to set
     */
    public void setFacilityTelephone(String facilityTelephone) {
        this.facilityTelephone = facilityTelephone;
    }

    /**
     * @return the facilityAddress
     */
    public String getFacilityAddress() {
        return facilityAddress;
    }

    /**
     * @param facilityAddress the facilityAddress to set
     */
    public void setFacilityAddress(String facilityAddress) {
        this.facilityAddress = facilityAddress;
    }

    /**
     * @return the facilityCity
     */
    public String getFacilityCity() {
        return facilityCity;
    }

    /**
     * @param facilityCity the facilityCity to set
     */
    public void setFacilityCity(String facilityCity) {
        this.facilityCity = facilityCity;
    }

    /**
     * @return the facilityState
     */
    public String getFacilityState() {
        return facilityState;
    }

    /**
     * @param facilityState the facilityState to set
     */
    public void setFacilityState(String facilityState) {
        this.facilityState = facilityState;
    }

    /**
     * @return the facilityZip
     */
    public String getFacilityZip() {
        return facilityZip;
    }

    /**
     * @param facilityZip the facilityZip to set
     */
    public void setFacilityZip(String facilityZip) {
        this.facilityZip = facilityZip;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the regionalOffice
     */
    public String getRegionalOffice() {
        return regionalOffice;
    }

    /**
     * @param regionalOffice the regionalOffice to set
     */
    public void setRegionalOffice(String regionalOffice) {
        this.regionalOffice = regionalOffice;
    }

    /**
     * @return the facilityCapacity
     */
    public String getFacilityCapacity() {
        return facilityCapacity;
    }

    /**
     * @param facilityCapacity the facilityCapacity to set
     */
    public void setFacilityCapacity(String facilityCapacity) {
        this.facilityCapacity = facilityCapacity;
    }

    /**
     * @return the facilityStatus
     */
    public String getFacilityStatus() {
        return facilityStatus;
    }

    /**
     * @param facilityStatus the facilityStatus to set
     */
    public void setFacilityStatus(String facilityStatus) {
        this.facilityStatus = facilityStatus;
    }

    /**
     * @return the licenseFirstDate
     */
    public Date getLicenseFirstDate() {
        return licenseFirstDate;
    }

    /**
     * @param licenseFirstDate the licenseFirstDate to set
     */
    public void setLicenseFirstDate(Date licenseFirstDate) {
        this.licenseFirstDate = licenseFirstDate;
    }

    /**
     * @return the closeDate
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate the closeDate to set
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the zipForSearch
     */
    public String getZipForSearch() {
        return zipForSearch;
    }

    /**
     * @param zipForSearch the zipForSearch to set
     */
    public void setZipForSearch(String zipForSearch) {
        this.zipForSearch = zipForSearch;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    
    
    
}
