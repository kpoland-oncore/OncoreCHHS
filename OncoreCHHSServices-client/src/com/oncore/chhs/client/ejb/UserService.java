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
package com.oncore.chhs.client.ejb;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedResult;
import java.util.List;

/**
 * Business interface.
 *
 * @author OnCore LLC
 */
public interface UserService {

    /**
     * Gets the Users entity from the userId key.
     *
     * @param userId The primary key.
     * @return A User or null if not found.
     */
    public User getUser(Integer userId);

    /**
     * Gets the user with the matching username.
     *
     * @param userName
     *
     * @return User
     */
    public User authenticateUser(String userName);

    /**
     * Creates the user.
     *
     * @param user
     *
     * @return User object with created Users information.
     */
    public User createUser(User user);

    /**
     * Search for all users by name sorted by last/first. Note: Could also user
     * userDAO.findAll()
     *
     * @param firstName A first name.
     * @param lastName A last name.
     *
     * @return A list of matching User.
     */
    public List<User> searchUsers(String firstName, String lastName);

    /**
     * Search for users by name sorted by last/first and paginate the result
     * set.
     *
     * @param firstName A first name.
     * @param lastName A last name.
     * @param firstResult The first result. Note: zero based index.
     * @param numberResults The number of results to return.
     *
     * @return A list of matching User.
     */
    public PaginatedResult<User> searchUsersPaginated(String firstName, String lastName,
            int firstResult, int numberResults);
}
