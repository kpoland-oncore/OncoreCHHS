/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oncore
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FosterFamilyAgencies {

    @XmlElement(name = "fosterFamilyAgencies")
    private List<FosterFamilyAgency> fosterFamilyAgencies;

    /**
     * @return the fosterFamilyAgencies
     */
    public List<FosterFamilyAgency> getFosterFamilyAgencies() {
        if (null == this.fosterFamilyAgencies) {
            this.fosterFamilyAgencies = new ArrayList<>();
        }

        return fosterFamilyAgencies;
    }

    /**
     * @param fosterFamilyAgencies the fosterFamilyAgencies to set
     */
    public void setFosterFamilyAgencies(List<FosterFamilyAgency> fosterFamilyAgencies) {
        this.fosterFamilyAgencies = fosterFamilyAgencies;
    }
}
