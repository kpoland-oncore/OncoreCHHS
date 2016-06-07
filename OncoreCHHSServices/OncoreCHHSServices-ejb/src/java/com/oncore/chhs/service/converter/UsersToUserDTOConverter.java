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
package com.oncore.chhs.service.converter;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.persistence.entity.Users;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OnCore LLC
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
