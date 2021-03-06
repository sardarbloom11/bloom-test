/**
 * @author Bloom This Class UserService.java is for Created at Aug 28, 2012 4:31:34 PM
 */
package com.mana.innovative.rest.consumer;

import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.service.consumer.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The type Users rest web service.
 * <p/>
 * Created by Bloom/Rono on $date $time.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@Component
@Path( "/{users: (?i)users}" )
public class UsersRestWebService {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( UsersRestWebService.class );

    /**
     * The Users service.
     */
    @Resource
    private UsersService usersService;

    /**
     * This method is for HTTP GET response for getting
     *
     * @param isError the is error
     * @return A response object
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response getAllUsers( @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #getAllUsers()" );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );
        return usersService.getAllUsers( requestParams );
    }

    /**
     * Delete users.
     *
     * @param isError the is error
     * @return the response
     */
    @DELETE
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response deleteUsers( @QueryParam( "is_error" ) @DefaultValue( "false" ) Boolean isError ) {

        logger.debug( "Starting #deleteAllUsers()" );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( isError );

        return usersService.deleteAllUsers( requestParams );
    }

}
