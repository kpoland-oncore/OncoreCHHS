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
package com.oncore.chhs.persistence.dao;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import com.oncore.chhs.persistence.dao.criteria.OrderBy;
import com.oncore.chhs.persistence.dao.criteria.Predicate;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedResult;
import com.oncore.chhs.persistence.entity.Users;
import com.oncore.chhs.persistence.entity.Users_;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author oncore
 */
@Stateless
@LocalBean
public class UserDAO extends AbstractDAO<Users> {

    @PersistenceContext(name = "OncoreCHHSServices-persistencePU")
    private EntityManager entityManager;

    /**
     *
     * @param username User name.
     *
     * @return A matching User.
     */
    public Users findByUserName(String userName) {
        Users user = null;

        List<Predicate<Users, String>> predicates = new ArrayList<>();

        Predicate pred = new Predicate();
        pred.addSimpleFilter(Users_.usrUserId, Arrays.asList(userName));

        predicates.add(pred);

        List<Users> users = this.search(predicates, null);

        if (null != users && !users.isEmpty()) {
            user = users.get(0);
        }

        return user;
    }

    /**
     *
     * @param lastName User last name.
     * @param firstName User first name.
     * @param orderBy Order the result set. Default value is
     * OrderBy.Direction.ASC.
     * @return A list of matching Users.
     */
    public List<Users> searchByName(String firstName, String lastName, OrderBy orderBy) {
        List<Users> users;

        List<Predicate<Users, String>> predicates = new ArrayList<>();

        Predicate pred = new Predicate();
        pred.addSimpleFilter(Users_.usrFirstname, Arrays.asList(firstName));
        pred.addSimpleFilter(Users_.usrLastname, Arrays.asList(lastName));

        predicates.add(pred);

        users = this.search(predicates, orderBy);

        return users;
    }

    /**
     *
     * @param firstName The user first name.
     * @param lastName The user last name.
     * @param orderBy An order by.
     * @param firstResult The first result.
     * @param numberResults The number of results to be retrieved.
     *
     * @return A list of Users.
     */
    public PaginatedResult<Users> getPaginatedSetByName(String firstName, String lastName, OrderBy orderBy,
            int firstResult, int numberResults) {
        PaginatedResult<Users> users;

        Predicate<Users, String> pred = new Predicate();
        pred.addSimpleFilter(Users_.usrFirstname, Arrays.asList(firstName));
        pred.addSimpleFilter(Users_.usrLastname, Arrays.asList(lastName));

        users = this.getPaginatedSet(firstResult, numberResults, orderBy, pred);

        return users;
    }

    /**
     *
     * @param userIds One or more userId primary keys.
     * @param orderBy An order by.
     * @param firstResult The first result.
     * @param numberResults The number of results to be retrieved.
     *
     * @return A list of Users.
     */
    public PaginatedResult<Users> getPaginatedSetByNumber(List<? extends Number> userIds, OrderBy orderBy,
            int firstResult, int numberResults) {
        PaginatedResult<Users> users;

        Predicate<Users, String> pred = new Predicate();
        pred.addSimpleNumericFilter(Users_.usrUid, userIds);

        users = this.getPaginatedSet(firstResult, numberResults, orderBy, pred);

        return users;
    }

    /**
     * @{inherited}
     */
    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    //TODO: Do indirect datasource lookup as well?
}
