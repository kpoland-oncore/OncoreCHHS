/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedResult;

/**
 *
 * @author Kerry O'Brien
 */
@Stateless
@Remote(UserService.class)
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
