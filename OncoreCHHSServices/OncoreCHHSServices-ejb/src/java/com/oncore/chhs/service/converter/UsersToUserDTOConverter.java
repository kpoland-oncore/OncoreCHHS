/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service.converter;

import com.oncore.chhs.client.dto.User;
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
    public static List<User> convert(List<Users> users) {
        List<User> converted = new ArrayList<>();

        if (users != null) {
            for (Users user : users) {
                converted.add(convert(user));
            }
        }

        return converted;
    }

    /**
     *
     * @param user A Users.
     * @return A UserDTO.
     */
    public static User convert(Users user) {
        User converted = null;

        if (user != null) {
            converted = new User(user.getUsrFirstname(), user.getUsrLastname());
            converted.setMiddleName(user.getUsrMiddlename());
            converted.setUserName(user.getUsrUserId());

            if (null != user.getUsrUid()) {
                converted.setUserUid(user.getUsrUid().longValue());
            }
        }

        return converted;
    }
}
