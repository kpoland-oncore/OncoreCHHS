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
package com.oncore.chhs.web.profile;

import com.oncore.chhs.web.base.BaseBean;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author OnCore LLC
 */
public class ProfileBean extends BaseBean {

    // some early example properties
    //TODO: add all properties needed for the profile page
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String phoneType;
    private String phone;
    private String email;

    private String userNameError;
    private String firstNameError;
    private String middleNameError;
    private String lastNameError;
    private String addressLine1Error;
    private String addressLine2Error;
    private String cityError;
    private String stateError;
    private String zipError;
    private String phoneTypeError;
    private String phoneError;
    private String emailError;

    public void reset() {

        userNameError = StringUtils.EMPTY;
        firstNameError = StringUtils.EMPTY;
        middleNameError = StringUtils.EMPTY;
        lastNameError = StringUtils.EMPTY;
        addressLine1Error = StringUtils.EMPTY;
        addressLine2Error = StringUtils.EMPTY;
        cityError = StringUtils.EMPTY;
        stateError = StringUtils.EMPTY;
        zipError = StringUtils.EMPTY;
        phoneTypeError = StringUtils.EMPTY;
        phoneError = StringUtils.EMPTY;
        emailError = StringUtils.EMPTY;

    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the phoneType
     */
    public String getPhoneType() {
        return this.phoneType;
    }

    /**
     * @param phoneType the phoneType to set
     */
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the userNameError
     */
    public String getUserNameError() {
        return userNameError;
    }

    /**
     * @param userNameError the userNameError to set
     */
    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    /**
     * @return the firstNameError
     */
    public String getFirstNameError() {
        return firstNameError;
    }

    /**
     * @param firstNameError the firstNameError to set
     */
    public void setFirstNameError(String firstNameError) {
        this.firstNameError = firstNameError;
    }

    /**
     * @return the middleNameError
     */
    public String getMiddleNameError() {
        return middleNameError;
    }

    /**
     * @param middleNameError the middleNameError to set
     */
    public void setMiddleNameError(String middleNameError) {
        this.middleNameError = middleNameError;
    }

    /**
     * @return the lastNameError
     */
    public String getLastNameError() {
        return lastNameError;
    }

    /**
     * @param lastNameError the lastNameError to set
     */
    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    /**
     * @return the addressLine1Error
     */
    public String getAddressLine1Error() {
        return addressLine1Error;
    }

    /**
     * @param addressLine1Error the addressLine1Error to set
     */
    public void setAddressLine1Error(String addressLine1Error) {
        this.addressLine1Error = addressLine1Error;
    }

    /**
     * @return the addressLine2Error
     */
    public String getAddressLine2Error() {
        return addressLine2Error;
    }

    /**
     * @param addressLine2Error the addressLine2Error to set
     */
    public void setAddressLine2Error(String addressLine2Error) {
        this.addressLine2Error = addressLine2Error;
    }

    /**
     * @return the cityError
     */
    public String getCityError() {
        return cityError;
    }

    /**
     * @param cityError the cityError to set
     */
    public void setCityError(String cityError) {
        this.cityError = cityError;
    }

    /**
     * @return the stateError
     */
    public String getStateError() {
        return stateError;
    }

    /**
     * @param stateError the stateError to set
     */
    public void setStateError(String stateError) {
        this.stateError = stateError;
    }

    /**
     * @return the zipError
     */
    public String getZipError() {
        return zipError;
    }

    /**
     * @param zipError the zipError to set
     */
    public void setZipError(String zipError) {
        this.zipError = zipError;
    }

    /**
     * @return the phoneTypeError
     */
    public String getPhoneTypeError() {
        return phoneTypeError;
    }

    /**
     * @param phoneTypeError the phoneTypeError to set
     */
    public void setPhoneTypeError(String phoneTypeError) {
        this.phoneTypeError = phoneTypeError;
    }

    /**
     * @return the phoneError
     */
    public String getPhoneError() {
        return phoneError;
    }

    /**
     * @param phoneError the phoneError to set
     */
    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    /**
     * @return the emailError
     */
    public String getEmailError() {
        return emailError;
    }

    /**
     * @param emailError the emailError to set
     */
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

}
