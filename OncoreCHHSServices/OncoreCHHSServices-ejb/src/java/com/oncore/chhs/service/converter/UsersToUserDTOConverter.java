/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service.converter;

import com.oncore.chhs.client.dto.UserDTO;
import com.oncore.chhs.persistence.entity.Users;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kerry O'Brien
 */
public class UsersToUserDTOConverter {
    /**
     * 
     * @param users A list of Users.
     * @return A list of UserDTO.
     */
    public static List<UserDTO> convert( List<Users> users ) {
        List<UserDTO> converted = new ArrayList<>();
        
        if ( users != null ) {
            for ( Users user : users ) {
                converted.add( convert( user ) );
            }
        }
        
        return converted;
    }
    
    /**
     * 
     * @param user A Users.
     * @return A UserDTO.
     */
    public static UserDTO convert( Users user ) {
        UserDTO converted = null;
        
        if ( user != null ) {
            converted = new UserDTO(user.getUsrFirstname(), user.getUsrLastname());
        }
        
        return converted;
    }
}
