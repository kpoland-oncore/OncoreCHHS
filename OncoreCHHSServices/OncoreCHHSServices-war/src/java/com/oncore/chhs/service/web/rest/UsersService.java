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
package com.oncore.chhs.service.web.rest;

import com.oncore.chhs.client.dto.Summaries;
import com.oncore.chhs.client.dto.User;
import com.oncore.chhs.ejb.EJBUtils;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import com.oncore.chhs.client.ejb.UserService;
import com.oncore.chhs.persistence.dao.criteria.FilterCriteriaImpl;
import com.oncore.chhs.persistence.dao.criteria.NumericFilterCriteriaImpl;
import com.oncore.chhs.persistence.dao.criteria.OrderBy;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedMarker;
import com.oncore.chhs.persistence.dao.criteria.pagination.PaginatedResult;
import com.oncore.chhs.web.rest.response.InsertResponse;
import com.oncore.chhs.web.rest.response.SelectResponse;
import java.util.function.Predicate;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author oncore
 */
@Provider
@Path("Users")
public class UsersService {

    private static final Logger LOGGER = Logger.getLogger(UsersService.class);
    private static final String MESSAGES_URL = "messages.rest.url.json";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public UsersService() {
    }

    /**
     *
     * @param id The user id.
     * @return A list of matching UserDTO.
     */
    @GET
    @Path("/find/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findById(@QueryParam("id") Integer id) {
        User response = null;

        UserService userService = EJBUtils.lookupEJB(UserService.class);

        response = userService.getUser(id);

        return response;
    }

    /**
     *
     * @param userName The user id.
     * @return A list of matching UserDTO.
     */
    @GET
    @Path("/authenticate/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SelectResponse<User> authenticateUser(@QueryParam("userName") String userName) {

        SelectResponse<User> response = null;

        try {
            UserService userService = EJBUtils.lookupEJB(UserService.class);

            User user = userService.authenticateUser(userName);

            response = new SelectResponse<>(user);
        } catch (Throwable t) {
            String errorMsg = "Error authenticating user.";

            LOGGER.error(errorMsg, t);
            response = new SelectResponse<>(errorMsg, t);
        }

        return response;
    }

    /**
     *
     * @param message The message.
     *
     * @return InsertResponse.
     */
    @POST
    @Path("/createUser/")
    @Consumes({MediaType.APPLICATION_JSON})
    public InsertResponse createUser(User user) {
        InsertResponse insertResponse = null;

        try {
            EJBUtils.lookupEJB(UserService.class).createUser(user);

            insertResponse = new InsertResponse(1);
        } catch (Throwable t) {
            String errorMsg = "Error creating message.";

            LOGGER.error(errorMsg, t);
            insertResponse = new InsertResponse(errorMsg, t);
        }

        return insertResponse;
    }

    /**
     *
     * @param lastName The last name.
     * @param firstName The first name.
     * @return A list of matching UserDTO.
     */
    @GET
    @Path("/search/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Summaries search(@QueryParam("lastName") String lastName,
            @QueryParam("firstName") String firstName) {
        List<User> response = null;

        UserService userService = EJBUtils.lookupEJB(UserService.class);

        response = userService.searchUsers(firstName, lastName);

        try {
            JAXBContext context = JAXBContext.newInstance(Summaries.class, User.class, PaginatedResult.class,
                    PaginatedMarker.class, Predicate.class, OrderBy.class, NumericFilterCriteriaImpl.class,
                    FilterCriteriaImpl.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(response, System.out);
        } catch (Throwable t) {
            System.out.println("Error marshalling: " + t.getMessage());
            t.printStackTrace();;
        }

        return new Summaries(response);
    }

    /**
     *
     * @param lastName The last name.
     * @param firstName The first name.
     * @param firstResult Default starts at index 0. Default = 0.
     * @param numberResults The number of results to retrieve. Default = 25.
     *
     * @return Zero of more UserDTO instances.
     */
    @GET
    @Path("/searchPaginated/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PaginatedResult searchPaginated(@QueryParam("lastName") String lastName,
            @QueryParam("firstName") String firstName,
            @DefaultValue("0") @QueryParam("firstResult") int firstResult,
            @DefaultValue("25") @QueryParam("numberResults") int numberResults) {
        PaginatedResult response = null;

        UserService userService = EJBUtils.lookupEJB(UserService.class);

        response = userService.searchUsersPaginated(lastName, firstName, firstResult, numberResults);

        return response;
    }

//    @GET
//    @Path("/name/{name}/id/{id}")
//    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//    public CompanyDTO find( @PathParam( "name" ) String name, @PathParam( "id" ) String id ) {
//        CompanyDTO response = EJBUtils.lookupEJB( CompanySession.class ).test();
//        
//        response.setName("Name + Id " + name + ":" + id);
//        return response;
//    }
//    @GET
//    @Path("/company")
//    @Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//    @QueryParam( "{company}" )
//    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
//    public CompanyDTO find( @QueryParam( "company" ) CompanyDTO company ) {
//        CompanyDTO response = EJBUtils.lookupEJB( CompanySession.class ).getCompany( companyName);
//        
//        response.setName( "name only " + company.getName() + ":" + company.getId() );
//        
//        return response;
//    }
}
