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

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.persistence.dao.UserDAO;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import com.oncore.chhs.persistence.dao.criteria.OrderBy;
import com.oncore.chhs.persistence.entity.Users;
import com.oncore.chhs.persistence.entity.Users_;
import com.oncore.chhs.service.converter.UsersToUserDTOConverter;
import com.oncore.chhs.client.ejb.UserService;
import com.oncore.chhs.ejb.BusinessServiceInterceptor;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedResult;
import java.util.Date;
import javax.interceptor.Interceptors;

/**
 *
 * @author OnCore LLC
 */
@Stateless
@Remote(UserService.class)
@Interceptors({BusinessServiceInterceptor.class})
public class UserServiceImpl implements UserService {

    @EJB
    private UserDAO userDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(Integer userId) {
        User dto = null;

        Users user = this.userDAO.findById(userId);

        dto = UsersToUserDTOConverter.convert(user);

        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(User user) {
        Users userEntity = new Users();

        userEntity.setCreateUserId(user.getUserName());
        userEntity.setCreateTs(new Date());
        userEntity.setUpdateUserId(user.getUserName());
        userEntity.setUpdateTs(new Date());
        userEntity.setUsrFirstname(user.getFirstName());
        userEntity.setUsrMiddlename(user.getMiddleName());
        userEntity.setUsrLastname(user.getLastName());
        userEntity.setUsrUserId(user.getUserName());
        userEntity.setUsrPassword("notused");

        Users createdUsers = this.userDAO.create(userEntity);

        return UsersToUserDTOConverter.convert(createdUsers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User authenticateUser(String userName) {
        User dto = null;

        Users user = this.userDAO.findByUserName(userName);

        if (null != user) {
            dto = UsersToUserDTOConverter.convert(user);
        }

        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaginatedResult<User> searchUsersPaginated(String firstName, String lastName, int firstResult, int numberResults) {
        PaginatedResult<User> paginated = null;

        OrderBy orderBy = new OrderBy(Users_.usrLastname);
        orderBy.addSortCriteria(Users_.usrFirstname);

        PaginatedResult<Users> users = this.userDAO.getPaginatedSetByName(firstName, lastName, orderBy, firstResult, numberResults);

        List<User> userDTOs = UsersToUserDTOConverter.convert(users.getResults());

        paginated = new PaginatedResult(userDTOs, users.getPaginatedMarker());

        return paginated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> searchUsers(String firstName, String lastName) {
        List<User> userDTOs = null;

        OrderBy orderBy = new OrderBy(Users_.usrLastname);
        orderBy.addSortCriteria(Users_.usrFirstname);

        List<Users> users = this.userDAO.searchByName(firstName, lastName, orderBy);

        userDTOs = UsersToUserDTOConverter.convert(users);

        return userDTOs;
    }
}
