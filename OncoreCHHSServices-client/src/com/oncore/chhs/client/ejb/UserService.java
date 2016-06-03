package com.oncore.chhs.client.ejb;

import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedResult;
import java.util.List;

/**
 * Business interface.
 *
 * @author Kerry O'Brien
 */
public interface UserService {

    /**
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
