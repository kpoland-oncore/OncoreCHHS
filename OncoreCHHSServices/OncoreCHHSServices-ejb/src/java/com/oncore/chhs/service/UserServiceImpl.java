/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncore.chhs.service;

import com.oncore.chhs.client.dto.UserDTO;
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
@Remote( UserService.class )
public class UserServiceImpl implements UserService {

    @EJB
    private UserDAO userDAO;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO getUser( Integer userId )
    {
        UserDTO dto = null;
        
        Users user = this.userDAO.findById( userId );
        
        dto = UsersToUserDTOConverter.convert(user);
        
        dto = new UserDTO( "SMITH", "TOM" );
        
        return dto;
    }   

    /**
     * {@inheritDoc} 
     */
    @Override
    public PaginatedResult<UserDTO> searchUsersPaginated( String firstName, String lastName, int firstResult, int numberResults ) { 
        PaginatedResult<UserDTO> paginated = null;
        
        OrderBy orderBy = new OrderBy( Users_.usrLastname );
        orderBy.addSortCriteria( Users_.usrFirstname );
        
        PaginatedResult<Users> users = this.userDAO.getPaginatedSetByName( firstName, lastName, orderBy, firstResult, numberResults );
        
        List<UserDTO> userDTOs = UsersToUserDTOConverter.convert( users.getResults() );
        
        paginated = new PaginatedResult( userDTOs, users.getPaginatedMarker() );
        
        return paginated;
    }    
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public List<UserDTO> searchUsers( String firstName, String lastName ) { 
        List<UserDTO> userDTOs = null;
        
        OrderBy orderBy = new OrderBy( Users_.usrLastname );
        orderBy.addSortCriteria( Users_.usrFirstname );
        
        List<Users> users = this.userDAO.searchByName( firstName, lastName, orderBy );
                
        userDTOs = UsersToUserDTOConverter.convert(users);
        
        return userDTOs;
    }
}
