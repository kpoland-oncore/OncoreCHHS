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
package com.oncore.chhs.service;

import com.oncore.chhs.client.dto.profile.Profile;
import com.oncore.chhs.client.ejb.ProfileService;
import com.oncore.chhs.persistence.dao.UserDAO;
import com.oncore.chhs.persistence.entity.Users;
import com.oncore.chhs.service.converter.UsersToProfileDTOConverter;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author oncore
 */
@Stateless
@Remote(ProfileService.class)
public class ProfileServiceImpl implements ProfileService {

    @EJB
    private UserDAO userDAO;

    @Override
    public Profile findProfileByUserUid(Integer userUid) {

        Users users = this.userDAO.findById(userUid);

        Profile profile = UsersToProfileDTOConverter.convert(users);

        return profile;
    }

}
