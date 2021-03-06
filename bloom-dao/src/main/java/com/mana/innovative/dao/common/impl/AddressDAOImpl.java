package com.mana.innovative.dao.common.impl;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.dao.BasicDAO;
import com.mana.innovative.dao.common.AddressDAO;
import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.common.Address;
import com.mana.innovative.dto.request.RequestParams;
import com.mana.innovative.exception.IllegalArgumentValueException;
import com.mana.innovative.exception.IllegalSearchListSizeException;
import com.mana.innovative.exception.response.ErrorContainer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Address dAO impl.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
@Transactional( propagation = Propagation.MANDATORY, isolation = Isolation.DEFAULT )
public class AddressDAOImpl extends BasicDAO implements AddressDAO {

    /**
     * The constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger( AddressDAOImpl.class );

    /**
     * Gets address by address id.
     *
     * @param addressId the address id
     * @param requestParams the request params
     * @return the address by address id
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Address > getAddressByAddressId( long addressId, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getAddressByAddressId()";
        logger.debug( "Starting " + location );

        List< Address > address = null;
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from Address where addressId" + " = :address_id" );
            query.setLong( "address_id", addressId );
//            transaction.commit();
            address = query.list( );
            this.closeDBTransaction( );

            if ( !address.isEmpty( ) && address.size( ) > DAOConstants.ONE ) {
                throw new IllegalSearchListSizeException( " Address Size exceeded maximum value " +
                        "of " + DAOConstants.ONE );
            }
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Error occurred while trying to fetch data from address table for " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        addressDAOResponse.setCount( address == null ? DAOConstants.ZERO : address.size( ) );
        addressDAOResponse.setResults( address );
        addressDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return addressDAOResponse;
    }

    /**
     * This method is to retrieve all the address values from the DB
     *
     * @param requestParams the request params
     * @return List<Address>    </> Return a list of
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED )
    public DAOResponse< Address > getAddress( RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#getAddress()";
        logger.debug( "Starting " + location );

        List< Address > address = null;
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            address = session.createQuery( " from Address" ).list( );
            this.closeDBTransaction( );

        } catch ( HibernateException exception ) {
            this.handleExceptions( exception );
            logger.error( "Error occurred while trying to fetch data from address table for " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        addressDAOResponse.setCount( address == null ? DAOConstants.ZERO : address.size( ) );
        addressDAOResponse.setResults( address );
        addressDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );

        return addressDAOResponse;
    }

    /**
     * Delete working hour by working hr ids.
     *
     * @param addressId the working hour id
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED )
    public DAOResponse< Address > deleteAddressByAddressId( final long addressId, final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAddressByAddressId()";
        logger.debug( "Starting " + location );

        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from Address where addressId=:addressId" );
            query.setParameter( "addressId", addressId );
            addressDAOResponse.setCount( query.executeUpdate( ) );
            this.closeDBTransaction( );
            addressDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed to delete address", exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        addressDAOResponse.setResults( null );
        addressDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return addressDAOResponse;
    }

    /**
     * Delete working hours by working hr ids.
     *
     * @param addressIds the working hour ids
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Address > deleteAddressesByAddressIds( List< Long > addressIds, RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + "#deleteAddressesByAddressIds()";
        logger.debug( "Starting " + location );
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse.setDelete( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " delete from Address where addressId in (:addressIds)" );
            query.setParameterList( "addressIds", addressIds );
            addressDAOResponse.setCount( query.executeUpdate( ) );
            this.closeDBTransaction( );
            addressDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed to delete address with given ids " + location, exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
            addressDAOResponse.setRequestSuccess( false );
        }
        addressDAOResponse.setResults( null );
        addressDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return addressDAOResponse;
    }

    /**
     * Delete all address.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Address > deleteAllAddress( final RequestParams requestParams ) {

        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "deleteAllAddresses()";
        logger.debug( "Starting " + location );
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse.setDelete( true );
        addressDAOResponse.setCount( DAOConstants.ZERO );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );

        if ( requestParams.isDeleteAll( ) ) {
            try {
                this.openDBTransaction( );
                // Note: Will delete all Address class rows stored in DB also will delete anything from address class
                // Note: and they are one-to-one mapped which means if no address then no address can exist there
                Query query = session.createQuery( "delete from Address" );
                addressDAOResponse.setCount( query.executeUpdate( ) );
                this.closeDBTransaction( );
                addressDAOResponse.setRequestSuccess( true );
//            transaction.commit();
            } catch ( Exception exception ) {
                if ( exception instanceof HibernateException ) {
                    this.handleExceptions( ( HibernateException ) exception );
                }
                logger.error( "Error occurred while trying to clear address table " + location, exception );
                if ( requestParams.isError( ) ) {
                    errorContainer = this.fillErrorContainer( location, exception );
                }
                addressDAOResponse.setRequestSuccess( false );
            }
        }
        addressDAOResponse.setResults( null );
        addressDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return addressDAOResponse;
    }

    /**
     * Create address.
     *
     * @param address the address
     * @param requestParams the request params
     * @return the dAO response
     */
    @Override
    @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED )
    public DAOResponse< Address > createAddress( final Address address, final RequestParams requestParams ) {
        if ( address == null ) {
            throw new NullPointerException( "Creation object Address is null" );
        }
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "createAddress()";
        logger.debug( "Starting " + location );
        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse.setCreate( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
        List< Address > addresses = new ArrayList<>( );
        try {
            this.openDBTransaction( );
            // Note need to use saveOrUpdate here since Address, or other stuff of address object might have been reused
            session.save( address );
            this.closeDBTransaction( );
            addressDAOResponse.setCount( DAOConstants.ONE );
            addresses.add( address );
            addressDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed to create address", exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }
        addressDAOResponse.setResults( addresses );
        addressDAOResponse.setErrorContainer( errorContainer );

        logger.debug( "Finishing " + location );
        return addressDAOResponse;
    }

    /**
     * Update address.
     *
     * @param address the address
     * @param requestParams the request params
     * @return the dAO response
     */
    @SuppressWarnings( "unchecked" )
    @Override
    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.REPEATABLE_READ )
    public DAOResponse< Address > updateAddress( final Address address, RequestParams requestParams ) {

        if ( address == null ) {
            throw new NullPointerException( "Update object Address is null" );
        }
        String location = this.getClass( ).getCanonicalName( ) + DAOConstants.HASH + "updateAddress()";
        logger.debug( "Starting " + location );

        DAOResponse< Address > addressDAOResponse = new DAOResponse<>( );
        addressDAOResponse.setUpdate( true );
        ErrorContainer errorContainer = !requestParams.isError( ) ? null : new ErrorContainer( );
        List< Address > addresses = null;

        try {
            this.openDBTransaction( );
            Query query = session.createQuery( " from Address where addressId=:addressId" );
            query.setParameter( "addressId", address.getAddressId( ) );
            addresses = query.list( );
            if ( addresses.size( ) != DAOConstants.ONE ) {
                throw new IllegalArgumentValueException( "Address List Size is different to expected Size" );
            }
            Address dbAddress = addresses.get( DAOConstants.ZERO );
            this.updateShopAddress( address, dbAddress );
            this.closeDBTransaction( );
            this.openDBTransaction( );
            session.update( address );
            this.closeDBTransaction( );
            addressDAOResponse.setCount( DAOConstants.ONE );
            addresses = new ArrayList<>( );
            addresses.add( address );
            addressDAOResponse.setCount( DAOConstants.ONE );
            addressDAOResponse.setRequestSuccess( true );
        } catch ( Exception exception ) {
            if ( exception instanceof HibernateException ) {
                this.handleExceptions( ( HibernateException ) exception );
            }
            logger.error( "Failed to update address", exception );
            if ( requestParams.isError( ) ) {
                errorContainer = this.fillErrorContainer( location, exception );
            }
        }

        addressDAOResponse.setResults( addresses );
        addressDAOResponse.setErrorContainer( errorContainer );
        logger.debug( "Finishing " + location );
        return addressDAOResponse;
    }

}
