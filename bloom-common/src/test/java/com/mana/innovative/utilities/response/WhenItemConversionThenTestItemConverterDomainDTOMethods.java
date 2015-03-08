package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dto.Item;
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

import java.util.ArrayList;
import java.util.List;

/**
 * The type When item conversion then test item converter domain dTO methods.
 */
@RunWith( value = BlockJUnit4ClassRunner.class )
public class WhenItemConversionThenTestItemConverterDomainDTOMethods {

    private static final Logger logger = Logger.getLogger( WhenItemConversionThenTestItemConverterDomainDTOMethods.class );

    private Item itemDTO, itemDTO2;
    private com.mana.innovative.domain.Item itemDomain, itemDomain2;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp( ) throws Exception {

        logger.debug( TestConstants.setUpMethodLoggerMsg );

        itemDomain2 = new com.mana.innovative.domain.Item( );
        itemDTO2 = new Item( );

        // Set Values for tempValues
        itemDTO = TestDummyDTOObjectGenerator.getTestItemDTOObject( new Item( ) );
        itemDomain = TestDummyDomainObjectGenerator.getTestItemDomainObject( new com.mana.innovative.domain.Item( ) );
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
        if ( itemDomain.getItemId( ) != 1 ) {
            itemDomain = TestDummyDomainObjectGenerator.getTestItemDomainObject( itemDomain );
        }
        itemDTO2 = ItemDomainDTOConverter.getConvertedDTOFromDomain( itemDTO2, itemDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDTO2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDTO, itemDTO2 );

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

        List< Item > itemDTOList;
        List< com.mana.innovative.domain.Item > itemDomainList = new ArrayList<>( );
        itemDomainList.add( itemDomain );
        if ( itemDomain.getItemId( ) != TestConstants.TEST_ID ) {
            TestDummyDomainObjectGenerator.getTestItemDomainZEROIDObject( itemDomain );
        }
        itemDTOList = ItemDomainDTOConverter.getConvertedListDTOFromDomain( itemDomainList );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDTOList );
        Assert.assertFalse( TestConstants.trueMessage, itemDTOList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDTOList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDTO, itemDTOList.get( TestConstants.ZERO ) );

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

        itemDomain2 = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain2, itemDTO );
        TestDummyDomainObjectGenerator.getTestItemDomainZEROIDObject( itemDomain );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDomain2 );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDomain, itemDomain2 );

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

        List< Item > itemDTOList = new ArrayList<>( );
        List< com.mana.innovative.domain.Item > itemDomainList;
        itemDTOList.add( itemDTO );
        TestDummyDomainObjectGenerator.getTestItemDomainZEROIDObject( itemDomain );

        itemDomainList = ItemDomainDTOConverter.getConvertedListDomainFromDTO( itemDTOList );

        Assert.assertNotNull( TestConstants.notNullMessage, itemDomainList );
        Assert.assertFalse( TestConstants.trueMessage, itemDomainList.isEmpty( ) );
        Assert.assertNotNull( TestConstants.notNullMessage, itemDomainList.get( TestConstants.ZERO ) );
        Assert.assertEquals( TestConstants.notEqualsMessage, itemDomain, itemDomainList.get( TestConstants.ZERO ) );

        logger.debug( "Finishing GetConvertedListDomainFromDTO" );
    }

    /**
     * Test get converted domain from dTO for error.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetConvertedDomainFromDTOForError( ) throws Exception {

        logger.debug( "Starting  testGetConvertedDomainFromDTOForError" );
        Item item = new Item( );
        IllegalArgumentValueException illegalArgumentValueException = null;
        NullPointerException nullPointerException = null;
        try {
            itemDomain2 = ItemDomainDTOConverter.getConvertedDomainFromDTO( itemDomain2, item );
        } catch ( IllegalArgumentValueException e ) {
            illegalArgumentValueException = e;
        }
        Assert.assertNotNull( TestConstants.notNullMessage, illegalArgumentValueException );
        try {
            itemDomain2 = ItemDomainDTOConverter.getConvertedDomainFromDTO( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing testGetConvertedDomainFromDTOForError" );
    }

    @Test
    public void testGetConvertedDTOFromDomainForError( ) throws Exception {

        logger.debug( "Starting  test GetConvertedDTOFromDomainForError" );
        NullPointerException nullPointerException = null;
        try {
            itemDTO2 = ItemDomainDTOConverter.getConvertedDTOFromDomain( null, null );
        } catch ( NullPointerException n ) {
            nullPointerException = n;
        }
        Assert.assertNotNull( TestConstants.nullMessage, nullPointerException );
        logger.debug( "Finishing test GetConvertedDTOFromDomainForError" );
    }
}