package com.mana.innovative.dao.consumer;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.UserRole;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bloom/Rono on 5/15/2015 12:16 PM. This class WhenGetUserRoleThenTestUserRoleDAOMethods is a test class
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } ) // "" <- <add location file>
//@TransactionConfiguration // If required
@Transactional   // If required
public class WhenGetUserRoleThenTestUserRoleDAOGetMethods {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenGetUserRoleThenTestUserRoleDAOGetMethods.class );

    /**
     * The UserRole dAO.
     */
    @Resource
    private UserRoleDAO userRoleDAO;
    /**
     * The Request params.
     */
    private RequestParams requestParams;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @BeforeTransaction
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        requestParams = new RequestParams( );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    @AfterTransaction
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.tearDownMethodLoggerMsg );
    }

    /**
     * Test get userRoles with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUserRolesWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetUserRolesWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO.getUserRoles( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, userRoleDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );
        // userRole list and its size with DAOResponse<T> class count
        List< UserRole > userRoles = userRoleDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, userRoles );
        Assert.assertFalse( TestConstants.trueMessage, userRoles.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDAOResponse.getCount( ), userRoles.size( ) );

        for ( UserRole userRole : userRoles ) {
            Assert.assertNotNull( TestConstants.nullMessage, userRole );
            Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleName( ) );
            Assert.assertFalse( TestConstants.trueMessage, userRole.getUserRoleId( ) < 0 );

            if ( userRole.getUserRoleName( ).equals( "default" ) ) {
                Assert.assertFalse( TestConstants.trueMessage, userRole.isActive( ) );
            }
            Assert.assertFalse( TestConstants.trueMessage, userRole.isLocked( ) );
            Assert.assertFalse( TestConstants.trueMessage, userRole.isExpired( ) );
        }

        logger.debug( "Finishing test for GetUserRolesWithErrorDisabled" );
    }

    /**
     * Test get userRole with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void GetUserRoleByUserRoleIdWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetUserRoleByUserRoleIdWithErrorDisabled" );

        requestParams.setIsError( TestConstants.IS_ERROR );
        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO.getUserRoleByUserRoleId( TestConstants.ONE,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse );
        // test error container
        Assert.assertNull( TestConstants.notNullMessage, userRoleDAOResponse.getErrorContainer( ) );
        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );
        // userRole list and its size with DAOResponse<T> class count
        List< UserRole > userRoles = userRoleDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, userRoles );
        Assert.assertFalse( TestConstants.trueMessage, userRoles.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDAOResponse.getCount( ), userRoles.size( ) );
        Assert.assertEquals( TestConstants.ONE, userRoles.size( ) );
        // test userRole
        UserRole userRole = userRoles.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, userRole );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleName( ) );

        Assert.assertTrue( TestConstants.falseMessage, userRole.isActive( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isLocked( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isExpired( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getPrivileges( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.getPrivileges( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetUserRoleByUserRoleIdWithErrorDisabled" );
    }

    /**
     * Test get userRoles with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUserRolesWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetUserRolesWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO.getUserRoles( requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );

        // userRole list and its size with DAOResponse<T> class count
        List< UserRole > userRoles = userRoleDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, userRoles );
        Assert.assertFalse( TestConstants.trueMessage, userRoles.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDAOResponse.getCount( ), userRoles.size( ) );

        for ( UserRole userRole : userRoles ) {

            Assert.assertNotNull( TestConstants.nullMessage, userRole );
            Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleName( ) );
            Assert.assertFalse( TestConstants.trueMessage, userRole.getUserRoleId( ) < 0 );

            if ( userRole.getUserRoleName( ).equals( "default" ) ) {
                Assert.assertFalse( TestConstants.trueMessage, userRole.isActive( ) );
            }
            Assert.assertFalse( TestConstants.trueMessage, userRole.isLocked( ) );
            Assert.assertFalse( TestConstants.trueMessage, userRole.isExpired( ) );

        }

        logger.debug( "Finishing test for GetUserRolesWithErrorEnabled" );
    }

    /**
     * Test get userRole with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUserRoleByUserRoleIdWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetUserRoleByUserRoleIdWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO.getUserRoleByUserRoleId( TestConstants.ONE,
                requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );

        // userRole list and its size with DAOResponse<T> class count
        List< UserRole > userRoles = userRoleDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, userRoles );
        Assert.assertFalse( TestConstants.trueMessage, userRoles.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDAOResponse.getCount( ), userRoles.size( ) );
        Assert.assertEquals( TestConstants.ONE, userRoles.size( ) );

        // test userRole
        UserRole userRole = userRoles.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, userRole );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleName( ) );

        Assert.assertTrue( TestConstants.falseMessage, userRole.isActive( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isLocked( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isExpired( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getPrivileges( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.getPrivileges( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetUserRoleByUserRoleIdWithErrorEnabled" );
    }

    /**
     * Test get userRole with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUserRoleByUserRoleNameWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for GetUserRoleByUserRoleNameWithErrorDisabled" );

        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO
                .getUserRoleByUserRoleName( TestConstants.DEFAULT_USER_ROLE_NAME, requestParams );

        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse );

        // test error container
        Assert.assertNull( TestConstants.notNullMessage, userRoleDAOResponse.getErrorContainer( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );

        // userRole list and its size with DAOResponse<T> class count
        List< UserRole > userRoles = userRoleDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, userRoles );
        Assert.assertFalse( TestConstants.trueMessage, userRoles.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDAOResponse.getCount( ), userRoles.size( ) );
        Assert.assertEquals( TestConstants.ONE, userRoles.size( ) );

        // test userRole
        UserRole userRole = userRoles.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, userRole );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleName( ) );

        Assert.assertTrue( TestConstants.falseMessage, !userRole.isActive( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isLocked( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isExpired( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getPrivileges( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.getPrivileges( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetUserRoleByUserRoleNameWithErrorDisabled" );
    }

    /**
     * Test get userRole with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT )
    public void testGetUserRoleByUserRoleNameWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for GetUserRoleByUserRoleIdWithErrorEnabled" );

        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );

        DAOResponse< UserRole > userRoleDAOResponse = userRoleDAO
                .getUserRoleByUserRoleName( TestConstants.DEFAULT_USER_ROLE_NAME, requestParams );


        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse );

        // test error container
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getErrorContainer( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, userRoleDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );

        // test result object
        Assert.assertNotNull( TestConstants.nullMessage, userRoleDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRoleDAOResponse.getResults( ).isEmpty( ) );

        // userRole list and its size with DAOResponse<T> class count
        List< UserRole > userRoles = userRoleDAOResponse.getResults( );

        Assert.assertNotNull( TestConstants.nullMessage, userRoles );
        Assert.assertFalse( TestConstants.trueMessage, userRoles.isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, userRoleDAOResponse.getCount( ), userRoles.size( ) );
        Assert.assertEquals( TestConstants.ONE, userRoles.size( ) );

        // test userRole
        UserRole userRole = userRoles.get( TestConstants.ZERO );

        Assert.assertNotNull( TestConstants.nullMessage, userRole );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleId( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUserRoleName( ) );

        Assert.assertTrue( TestConstants.falseMessage, !userRole.isActive( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isLocked( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.isExpired( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getPrivileges( ) );
        Assert.assertFalse( TestConstants.trueMessage, userRole.getPrivileges( ).isEmpty( ) );

        Assert.assertNotNull( TestConstants.nullMessage, userRole.getCreatedDate( ) );
        Assert.assertNotNull( TestConstants.nullMessage, userRole.getUpdatedDate( ) );

        logger.debug( "Finishing test for GetUserRoleByUserRoleIdWithErrorEnabled" );
    }
}