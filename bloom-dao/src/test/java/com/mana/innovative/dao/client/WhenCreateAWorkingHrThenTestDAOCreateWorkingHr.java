package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Shop;
import com.mana.innovative.domain.client.WorkingHour;
import com.mana.innovative.dto.request.RequestParams;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bloom/Rono on 3/19/2015. This class is for .. ToDo
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration( defaultRollback = true )
@Transactional
public class WhenCreateAWorkingHrThenTestDAOCreateWorkingHr {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( WhenCreateAWorkingHrThenTestDAOCreateWorkingHr.class );

    /**
     * The Dummy working hour.
     */
    private WorkingHour dummyWorkingHour;

    /**
     * The Working hour dAO impl.
     */
    @Resource
    private WorkingHourDAO workingHourDAOImpl;
    /**
     * The Shop dAO impl.
     */
    @Resource
    private ShopDAO shopDAOImpl;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    @Transactional( propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED )
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );
        RequestParams requestParams = new RequestParams( );
        requestParams.setIsError( TestConstants.IS_ERROR_TRUE );

        DAOResponse< Shop > shopDAOResponse = shopDAOImpl.getShopByShopId( TestConstants.TEST_ID, requestParams );
        Assert.assertNotNull( TestConstants.nullMessage, shopDAOResponse.getResults( ) );
        Assert.assertFalse( TestConstants.trueMessage, shopDAOResponse.getResults( ).isEmpty( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, shopDAOResponse.getResults( ).size( ) );
        Shop shop = shopDAOResponse.getResults( ).get( TestConstants.ZERO );
        Assert.assertNotNull( TestConstants.nullMessage, shop );

        logger.debug( "Loaded Shop for which WorkingHour is being created" );

        dummyWorkingHour = new WorkingHour( );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_TIME_FORMAT );
        Date time = simpleDateFormat.parse( TestConstants.TEST_START_TIME );
        dummyWorkingHour.setStartTime( time );
        time = simpleDateFormat.parse( TestConstants.TEST_END_TIME );
        dummyWorkingHour.setEndTime( time );
        dummyWorkingHour.setDay( TestConstants.TEST_DAY );
        dummyWorkingHour.setHoliday( Boolean.TRUE );
        dummyWorkingHour.setOffline( Boolean.FALSE );
        dummyWorkingHour.setWeekend( Boolean.TRUE );
        dummyWorkingHour.setShopWorkingHour( shop );
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
    }

    /**
     * Test create a working hour with error enabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateAWorkingHourWithErrorEnabled( ) throws Exception {

        logger.debug( "Starting test for CreateAWorkingHourWithErrorEnabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        workingHourDAOResponse = workingHourDAOImpl.createWorkingHour( dummyWorkingHour, TestConstants.IS_ERROR_TRUE );

        // Test Error Container
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ) );
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ).getCurrentError( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.getErrorContainer( ).getErrors( )
                .isEmpty( ) );

        //Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ).get( TestConstants.ZERO
        ) );
        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, workingHourDAOResponse.getCount( ) );

        WorkingHour workingHour = workingHourDAOResponse.getResults( ).get( TestConstants.ZERO );
        dummyWorkingHour.setWorkingHourId( workingHour.getWorkingHourId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyWorkingHour, workingHour );

        logger.debug( "Finishing test for CreateAWorkingHourWithErrorEnabled" );
    }

    /**
     * Test create a working hour with error disabled.
     *
     * @throws Exception the exception
     */
    @Test
    @Rollback
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testCreateAWorkingHourWithErrorDisabled( ) throws Exception {

        logger.debug( "Starting test for CreateAWorkingHourWithErrorDisabled" );

        DAOResponse< WorkingHour > workingHourDAOResponse = new DAOResponse<>( );
        workingHourDAOResponse = workingHourDAOImpl.createWorkingHour( dummyWorkingHour, TestConstants.IS_ERROR );

        // Test Error Container
        Assert.assertNull( TestConstants.notNullMessage, workingHourDAOResponse.getErrorContainer( ) );

        //Test WorkingHourDAOResponse
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isCreate( ) );
        Assert.assertTrue( TestConstants.falseMessage, workingHourDAOResponse.isRequestSuccess( ) );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDAOResponse.isUpdate( ) );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDAOResponse.isDelete( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDAOResponse.getResults( ).get( TestConstants.ZERO
        ) );
        Assert.assertEquals( "Value of Count is not One", TestConstants.ONE, workingHourDAOResponse.getCount( ) );

        WorkingHour workingHour = workingHourDAOResponse.getResults( ).get( TestConstants.ZERO );
        dummyWorkingHour.setWorkingHourId( workingHour.getWorkingHourId( ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, dummyWorkingHour, workingHour );

        logger.debug( "Finishing test for CreateAWorkingHourWithErrorDisabled" );
    }
}
