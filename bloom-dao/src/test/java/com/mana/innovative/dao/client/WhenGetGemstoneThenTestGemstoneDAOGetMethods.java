package com.mana.innovative.dao.client;

import com.mana.innovative.constants.TestConstants;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.client.Gemstone;
import com.mana.innovative.domain.client.Item;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The type WhenGetGemstoneDisThenTestGemstoneDisDAOGetMethods.
 * <p/>
 * Created by Bloom/Rono on 2/13/2016 7:51 PM.
 *
 * @author Bloom Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright
 */
@RunWith( value = SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "/dbConfig-test.xml" } )
@TransactionConfiguration(/* transactionManager = "transactionManager", */defaultRollback = true )
@Transactional
public class WhenGetGemstoneThenTestGemstoneDAOGetMethods {
	
	/**
	 * The Gemstone id.
	 */
	long gemstoneId;
	private Logger logger = LoggerFactory.getLogger( WhenGetGemstoneThenTestGemstoneDAOGetMethods.class );
	/**
	 * The Gemstone dAO.
	 */
	@Resource
	private GemstoneDAO gemstoneDAOImpl;
	
	private RequestParams requestParams;
	private String key;

	private List< String > values;
	
	/**
	 * Sets up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp( ) throws Exception {
		
		logger.debug( TestConstants.setUpMethodLoggerMsg );
		gemstoneId = TestConstants.ZERO;
		
		requestParams = new RequestParams( );
		requestParams.setIsError( TestConstants.IS_ERROR );

		values = new ArrayList<>( );
		values.add( TestConstants.DEFAULT );
		values.add( TestConstants.TEST );

		key = TestConstants.KEY_GEM_NAME;

	}
	
	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown( ) throws Exception {
		
		logger.debug( TestConstants.tearDownMethodLoggerMsg );
	}
	
	/**
	 * Test get gemstone by gemstone id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Transactional( readOnly = true )
	public void testGetGemstoneByGemstoneIdWithErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstoneByGemstoneIdWithErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstoneByGemstoneId( gemstoneId, requestParams );
		
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		// test Error Container
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( gemstoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		
		// test DAOResponse
		Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, gemstoneDAOResponse.getCount( ) );
		Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, gemstoneDAOResponse.getOffset( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( gemstoneDAOResponse.getResults( ).isEmpty( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		
		List< Gemstone > gemstones = gemstoneDAOResponse.getResults( );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, gemstones.size( ) );
		// Test Gemstone
		Gemstone gemstone = gemstones.get( TestConstants.ZERO );
		Assert.assertNotNull( TestConstants.nullMessage, gemstone );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneId, gemstone.getGemstoneId( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_GEM_NAME, gemstone.getGemstoneName( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_GEM_DESCRIPTION, gemstone
				.getGemstoneDescription( ) );

		
		// Test gemstone items
		Assert.assertNotNull( TestConstants.nullMessage, gemstone.getItemList( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstone.getItemList( ).isEmpty( ) );
		
		logger.debug( "Finishing test for GetGemstoneByGemstoneIdWithErrorEnabled" );
	}
	
	/**
	 * Test get gemstones.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Transactional( readOnly = true )
	public void testGetGemstonesWithErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstonesWithErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );
		
		// test DAO Response errorContainer
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( gemstoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		
		// test DAOResponse
		Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, gemstoneDAOResponse.getCount( ) );
		Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, gemstoneDAOResponse.getOffset( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( gemstoneDAOResponse.getResults( ).isEmpty( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		
		
		logger.debug( "Finishing test for GetGemstonesWithErrorEnabled" );
	}
	
	/**
	 * Test get gemstone by gemstone id with error disabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Transactional( readOnly = true )
	public void testGetGemstoneByGemstoneIdWithErrorDisabled( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstoneByGemstoneIdWithErrorDisabled" );
		
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstoneByGemstoneId( gemstoneId, requestParams );
		
		// test errorContainer
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		
		// test DAOResponse
		Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, gemstoneDAOResponse.getCount( ) );
		Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, gemstoneDAOResponse.getOffset( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( gemstoneDAOResponse.getResults( ).isEmpty( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		
		List< Gemstone > gemstones = gemstoneDAOResponse.getResults( );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.ONE, gemstones.size( ) );
		// Test Gemstone
		Gemstone gemstone = gemstones.get( TestConstants.ZERO );
		Assert.assertNotNull( TestConstants.nullMessage, gemstone );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneId, gemstone.getGemstoneId( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_GEM_NAME, gemstone.getGemstoneName( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.DEFAULT_GEM_DESCRIPTION, gemstone.getGemstoneDescription(
		) );
		
		
		// Test gemstone items
		Assert.assertNotNull( TestConstants.nullMessage, gemstone.getItemList( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstone.getItemList( ).isEmpty( ) );
		
		logger.debug( "Finishing test for GetGemstoneByGemstoneIdWithErrorDisabled" );
	}
	
	/**
	 * Test get gemstones with error disabled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Transactional( readOnly = true )
	public void testGetGemstonesWithErrorDisabled( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstonesWithErrorDisabled" );
		
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );
		
		// test errorContainer
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		// test DAOResponse
		Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, gemstoneDAOResponse.getCount( ) );
		Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, gemstoneDAOResponse.getOffset( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( gemstoneDAOResponse.getResults( ).isEmpty( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		
		logger.debug( "Finishing test for GetGemstonesWithErrorDisabled" );
	}

	@Test
	@Transactional( readOnly = true )
	public void testGetGemstonesByParams( ) throws Exception {

		logger.debug( "Starting test for GetGemstonesByParams" );

		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstonesByParams( key, values, requestParams );

		// test errorContainer
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		// test DAOResponse
		Assert.assertNotSame( TestConstants.sameMessage, TestConstants.ZERO, gemstoneDAOResponse.getCount( ) );
		Assert.assertSame( TestConstants.notSameMessage, TestConstants.ZERO, gemstoneDAOResponse.getOffset( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( gemstoneDAOResponse.getResults( ).isEmpty( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ).get( TestConstants.ZERO ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, values.size( ), gemstoneDAOResponse.getResults( ).size( ) );

		logger.info( "******* Data 0 is\n " + gemstoneDAOResponse.getResults( ).get( 0 ).toString( ) );
		logger.info( "******* Data 1 is\n " + gemstoneDAOResponse.getResults( ).get( 1 ).toString( ) );
		logger.debug( "Completing test for GetGemstonesByParams" );
	}
	
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testGetGemstonesWithPagingEnabledNErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstonesWithPagingEnabledNErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		requestParams.setStartLimit( 0L );
		requestParams.setEndLimit( 1L );
		
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );
		
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		// test error container
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		
		List< Gemstone > gemstones = gemstoneDAOResponse.getResults( );
		// gemstone list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, gemstones );
		Assert.assertFalse( TestConstants.trueMessage, gemstones.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDAOResponse.getCount( ), gemstones.size( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TWO, gemstones.size( ) );
//        Assert.assertEquals(27, gemstones.size()); /** Just a guarantee check for making sure new changes are working */
		logger.debug( "Completing test for GetGemstonesWithPagingEnabledNErrorEnabled" );
	}
	
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testGetGemstonesWithPagingSize( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstonesWithPagingSizeNErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		requestParams.setStartLimit( 0L );
		requestParams.setPageSize( 3 );
		
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );
		
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		// test error container
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		
		List< Gemstone > gemstones = gemstoneDAOResponse.getResults( );
		// gemstone list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, gemstones );
		Assert.assertFalse( TestConstants.trueMessage, gemstones.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDAOResponse.getCount( ), gemstones.size( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.THREE, gemstones.size( ) );
		logger.debug( "Completing test for GetGemstonesWithPagingSizeNErrorEnabled" );
	}
	
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testGetGemstonesWithNoPagingNErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstonesWithNoPagingNErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );
		
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		// test error container
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		
		List< Gemstone > gemstones = gemstoneDAOResponse.getResults( );
		// gemstone list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, gemstones );
		Assert.assertFalse( TestConstants.trueMessage, gemstones.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDAOResponse.getCount( ), gemstones.size( ) );
		
		logger.debug( "Completing test for GetGemstonesWithNoPagingNErrorEnabled" );
	}
	
	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testGetGemstonesWithPagingNLimitNErrorEnabled( ) throws Exception {
		
		logger.debug( "Starting test for GetGemstonesWithPagingNLimitNErrorEnabled" );
		
		requestParams.setIsError( TestConstants.IS_ERROR_TRUE );
		
		requestParams.setStartLimit( 0L );
		requestParams.setPageSize( 3 );
		requestParams.setEndLimit( 1L );
		
		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );
		
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		// test error container
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ) );
		Assert.assertTrue( TestConstants.falseMessage, gemstoneDAOResponse.getErrorContainer( ).getErrors( ).isEmpty( ) );
		Assert.assertNull( TestConstants.notNullMessage, gemstoneDAOResponse.getErrorContainer( ).getCurrentError( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );
		
		List< Gemstone > gemstones = gemstoneDAOResponse.getResults( );
		// gemstone list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, gemstones );
		Assert.assertFalse( TestConstants.trueMessage, gemstones.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDAOResponse.getCount( ), gemstones.size( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TWO, gemstones.size( ) );
		
		logger.debug( "Completing test for GetGemstonesWithPagingNLimitNErrorEnabled" );
	}

	@Test
	@Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT )
	public void testGetGemstonesWithPagingNLimitNErrorDisabled( ) throws Exception {

		logger.debug( "Starting test for GetGemstonesWithPagingNLimitNErrorDisabled" );

		requestParams.setIsError( TestConstants.IS_ERROR );

		requestParams.setStartLimit( 0L );
		requestParams.setPageSize( 3 );
		requestParams.setEndLimit( 1L );

		DAOResponse< Gemstone > gemstoneDAOResponse = gemstoneDAOImpl.getGemstones( requestParams );

		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse );
		// test error container
		Assert.assertNull( TestConstants.nullMessage, gemstoneDAOResponse.getErrorContainer( ) );
		// test result object
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneDAOResponse.getResults( ) );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneDAOResponse.getResults( ).isEmpty( ) );

		List< Gemstone > gemstoneList = gemstoneDAOResponse.getResults( );
		// tab list and its size with DAOResponse<T> class count
		Assert.assertNotNull( TestConstants.nullMessage, gemstoneList );
		Assert.assertFalse( TestConstants.trueMessage, gemstoneList.isEmpty( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, gemstoneDAOResponse.getCount( ), gemstoneList.size( ) );
		Assert.assertEquals( TestConstants.notEqualsMessage, TestConstants.TWO, gemstoneList.size( ) );

		logger.debug( "Completing test for GeGemstonesWithPagingNLimitNErrorDisabled" );
	}
}
