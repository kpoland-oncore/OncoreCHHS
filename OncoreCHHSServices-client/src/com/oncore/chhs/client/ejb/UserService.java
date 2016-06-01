package com.oncore.chhs.client.ejb;

import com.oncore.chhs.client.dto.UserDTO;
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
     * @return A UserDTO or null if not found.
     */
    public UserDTO getUser( Integer userId );
    
    /**
     * Search for all users by name sorted by last/first.
     * Note: Could also user userDAO.findAll()
     * 
     * @param firstName A first name.
     * @param lastName A last name.
     * 
     * @return A list of matching UserDTO.
     */
    public List<UserDTO> searchUsers( String firstName, String lastName );    
    
    /**
     * Search for users by name sorted by last/first and paginate the result set.
     * 
     * @param firstName A first name.
     * @param lastName A last name.
     * @param firstResult The first result. Note: zero based index.
     * @param numberResults The number of results to return.
     * 
     * @return A list of matching UserDTO.
     */
    public PaginatedResult<UserDTO> searchUsersPaginated( String firstName, String lastName, 
                int firstResult, int numberResults );
}
