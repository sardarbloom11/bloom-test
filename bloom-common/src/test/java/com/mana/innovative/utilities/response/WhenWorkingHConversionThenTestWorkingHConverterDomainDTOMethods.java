package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.WorkingHour;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.utilities.TestDummyDTOObjectGenerator;
import com.mana.innovative.utilities.TestDummyDomainObjectGenerator;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is a test class for testing class todo...
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenWorkingHConversionThenTestWorkingHConverterDomainDTOMethods {

    private static final Logger logger = Logger.getLogger( WhenWorkingHConversionThenTestWorkingHConverterDomainDTOMethods.class );

    private WorkingHour workingHourDTO, workingHourDTO2;
    private com.mana.innovative.domain.WorkingHour workingHourDomain, workingHourDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {
        logger.debug( TestConstants.setUpMethodLoggerMsg );
        workingHourDomain2 = new com.mana.innovative.domain.WorkingHour( );
        workingHourDTO2 = new WorkingHour( );

        // Set Values for tempValues
        workingHourDTO = TestDummyDTOObjectGenerator.getTestWorkingHourDTOObject( new WorkingHour( ) );
        workingHourDomain = TestDummyDomainObjectGenerator.getTestWorkingHourDomainObject( new com.mana.innovative.domain.WorkingHour( ) );

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
     * Test get converted dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDTOFromDomain( ) throws Exception {

        logger.debug( "Starting  GetConvertedDTOFromDomain" );
        if ( workingHourDomain.getWorkingHourId( ) != 1 ) {
            workingHourDomain = TestDummyDomainObjectGenerator.getTestWorkingHourDomainObject( workingHourDomain );
        }
        workingHourDTO2 = WorkingHourDomainDTOConverter.getConvertedDTOFromDomain( workingHourDomain );

        Assert.assertNotNull( TestConstants.nullMessage, workingHourDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDTO, workingHourDTO2 );

        logger.debug( "Finishing GetConvertedDTOFromDomain" );
    }

    /**
     * Test get converted list dTO from domain.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDTOFromDomain( ) throws Exception {

        logger.debug( "Starting  GetConvertedListDTOFromDomain" );

        List< WorkingHour > workingHourDTOList;
        List< com.mana.innovative.domain.WorkingHour > workingHourDomainList = new ArrayList<>( );
        workingHourDomainList.add( workingHourDomain );
        if ( workingHourDomain.getWorkingHourId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.getTestWorkingHourDomainZEROIDObject( workingHourDomain );
        }
        workingHourDTOList = WorkingHourDomainDTOConverter.getConvertedListDTOFromDomain( workingHourDomainList );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDTOList );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDTO, workingHourDTOList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing GetConvertedListDTOFromDomain" );
    }

    /**
     * Test get converted domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  GetConvertedDomainFromDTO" );

        workingHourDomain2 = WorkingHourDomainDTOConverter.getConvertedDomainFromDTO( workingHourDTO );
        TestDummyDomainObjectGenerator.getTestWorkingHourDomainZEROIDObject( workingHourDomain );

        Assert.assertNotNull( TestConstants.nullMessage, workingHourDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDomain, workingHourDomain2 );

        logger.debug( "Finishing GetConvertedDomainFromDTO" );
    }

    /**
     * Test get converted list domain from dTO.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedListDomainFromDTO( ) throws Exception {

        logger.debug( "Starting  GetConvertedListDomainFromDTO" );

        List< WorkingHour > workingHourDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.WorkingHour > workingHourDomainList;
        workingHourDTOList.add( workingHourDTO );
        TestDummyDomainObjectGenerator.getTestWorkingHourDomainZEROIDObject( workingHourDomain );

        workingHourDomainList = WorkingHourDomainDTOConverter.getConvertedListDomainFromDTO( workingHourDTOList );

        Assert.assertNotNull( TestConstants.nullMessage, workingHourDomainList );
        Assert.assertFalse( TestConstants.trueMessage, workingHourDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.nullMessage, workingHourDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, workingHourDomain, workingHourDomainList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing GetConvertedListDomainFromDTO" );
    }

    @Test
    public void testToStringTimeFromDate( ) throws Exception {

        logger.debug( "Starting  ToStringTimeFromDate" );
        String testTimeString = "11:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( TestConstants.TEST_TIME_FORMAT );
        Date testDate = simpleDateFormat.parse( testTimeString );
        Assert.assertEquals( TestConstants.notEqualsMessage, testTimeString, WorkingHourDomainDTOConverter.toStringTimeFromDate(
                testDate ) );

        logger.debug( "Finishing ToStringTimeFromDate" );
    }

    @Test
    public void testGetConvertedDomainFromDTOForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDomainFromDTOForError" );
        WorkingHour workingHour = new WorkingHour( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            workingHourDomain2 = WorkingHourDomainDTOConverter.getConvertedDomainFromDTO( workingHour );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.nullMessage, illegalArgumentValueException );

        try {
            workingHourDomain2 = WorkingHourDomainDTOConverter.getConvertedDomainFromDTO( null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDomainFromDTOForError" );
    }

    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
        NullPointerException nullPointerException = null;
        try {
            workingHourDTO2 = WorkingHourDomainDTOConverter.getConvertedDTOFromDomain( null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}