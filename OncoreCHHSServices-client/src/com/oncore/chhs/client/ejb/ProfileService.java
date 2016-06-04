/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.client.ejb;

import com.oncore.chhs.client.dto.profile.Profile;

/**
 * The ProfileService provides methods for retrieving, creating and updating
 * a user's name and contact information.
 * 
 * @author oncore
 */
public interface ProfileService {
    
    public Profile findProfileByUserUid(Integer userUid);
    
    
    
}
