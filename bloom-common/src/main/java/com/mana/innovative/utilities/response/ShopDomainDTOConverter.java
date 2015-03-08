package com.mana.innovative.utilities.response;

import com.mana.innovative.constants.DAOConstants;
import com.mana.innovative.domain.Address;
import com.mana.innovative.domain.Item;
import com.mana.innovative.domain.WorkingHour;
import com.mana.innovative.dto.Shop;
import com.mana.innovative.exception.IllegalArgumentValueException;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Shop domain dTO converter.
 */
public class ShopDomainDTOConverter {

    private static final Logger logger = Logger.getLogger( ShopDomainDTOConverter.class );
    private static final int ZERO = DAOConstants.ZERO;
    private static final int ONE = DAOConstants.ONE;

    /**
     * Gets converted shop dTO from shop domain.
     *
     * @param shopDTO    the shop dTO
     * @param shopDomain the shop
     *
     * @return the converted shop dTO from shop domain
     */
    public static Shop getConvertedDTOFromDomain( Shop shopDTO, com.mana.innovative.domain.Shop shopDomain ) {

        if ( shopDomain == null ) {
            String message = "Parameter shopDomain is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( shopDTO == null ) {
            shopDTO = new Shop( );
            logger.warn( "Creating shopDTO for conversion as was null " );
        }

        if ( shopDomain.getShopId( ) > ZERO ) {
            shopDTO.setShopId( shopDomain.getShopId( ) );
        }
        if ( !StringUtils.isEmpty( shopDomain.getShopName( ) ) ) {
            shopDTO.setShopName( shopDomain.getShopName( ) );
        }
        if ( shopDomain.getShopOwnId( ) > ONE )
            shopDTO.setShopOwnId( shopDomain.getShopOwnId( ) );
        if ( !StringUtils.isEmpty( shopDomain.getShopWebLink( ) ) ) {
            shopDTO.setShopWebLink( shopDomain.getShopWebLink( ) );
        }
        /** {@link WorkingHour} List */
        if ( shopDomain.getWorkingHours( ) != null && !shopDomain.getWorkingHours( ).isEmpty( ) ) {
            shopDTO.setWorkingHours( WorkingHourDomainDTOConverter.getConvertedListDTOFromDomain( shopDomain.getWorkingHours( ) ) );
        }
        /** {@link Address,com.mana.innovative.dto.Address}*/
        if ( shopDomain.getAddress( ) != null ) {
            shopDTO.setAddress( AddressDomainDTOConverter.getConvertedDTOFromDomain( shopDomain.getAddress( ) ) );
        }
        /**{@link Item,com.mana.innovative.dto.Item} */
        if ( shopDomain.getItems( ) != null ) {
            shopDTO.setItems( ItemDomainDTOConverter.getConvertedListDTOFromDomain( shopDomain.getItems( ) ) );
        }
        return shopDTO;
    }

    /**
     * Gets converted shop dTO list.
     *
     * @param shops the shops
     *
     * @return the converted shop dTO list
     */
    public static List< Shop > getConvertedListDTOFromDomain( List< com.mana.innovative.domain.Shop > shops ) {

        List< Shop > shopDTOList = new ArrayList<>( );
        for ( com.mana.innovative.domain.Shop shop : shops ) {

            Shop shopDTO = new Shop( );
            shopDTO = getConvertedDTOFromDomain( shopDTO, shop );
            shopDTOList.add( shopDTO );
        }
        return shopDTOList;
    }

    /**
     * Gets converted shop domain from shop dTO.
     *
     * @param shopDomain the shop domain
     * @param shopDTO    the shop dTO
     *
     * @return the converted shop domain from shop dTO
     */
    public static com.mana.innovative.domain.Shop getConvertedDomainFromDTO( com.mana.innovative.domain.Shop shopDomain, Shop shopDTO ) {

        if ( shopDTO == null ) {
            String message = "Parameter shopDTO is required for conversion";
            logger.error( message );
            throw new NullPointerException( message );
        }
        if ( shopDomain == null ) {
            shopDomain = new com.mana.innovative.domain.Shop( );
            logger.warn( "Creating shopDomain for conversion as was null " );
        }
        boolean flag = false;
        StringBuilder stringBuilder = new StringBuilder( " Value must not be null for " );
        if ( shopDTO.getShopOwnId( ) > ZERO ) {
            shopDomain.setShopOwnId( shopDTO.getShopOwnId( ) );
        } else {
            flag = true;
            stringBuilder.append( " ShopOwnID," );
        }
        //shopDomain.setShopId(shopDTO.getShopId());
        if ( shopDTO.getShopName( ) != null && !shopDTO.getShopName( ).isEmpty( ) ) {
            shopDomain.setShopName( shopDTO.getShopName( ) );
        } else {
            flag = true;
            stringBuilder.append( " ShopName," );
        }
        if ( !StringUtils.isEmpty( shopDTO.getShopWebLink( ) ) ) {
            shopDomain.setShopWebLink( shopDTO.getShopWebLink( ) );
        } else {
            flag = true;
            stringBuilder.append( " ShopWebLink," );
        }
        // check Address
        if ( shopDTO.getAddress( ) != null ) {
            shopDomain.setAddress( AddressDomainDTOConverter.getConvertedDomainFromDTO( shopDTO.getAddress( ) ) );
        } else {
            stringBuilder.append( " Address," );
        }
        // check Working Hours
        if ( shopDTO.getWorkingHours( ) != null && !shopDTO.getWorkingHours( ).isEmpty( ) ) {
            shopDomain.setWorkingHours( WorkingHourDomainDTOConverter.getConvertedListDomainFromDTO( shopDTO.getWorkingHours( ) ) );
        } else {
            flag = true;
            stringBuilder.append( " Working Hours," );
        }
        // check Items
        if ( shopDTO.getItems( ) != null && !shopDTO.getItems( ).isEmpty( ) ) {
            shopDomain.setItems( ItemDomainDTOConverter.getConvertedListDomainFromDTO( shopDTO.getItems( ) ) );
        } else {
            stringBuilder.append( " Items" );
        }
        //            shop.setShopShop();
        if ( flag ) {
            logger.error( stringBuilder );
            throw new IllegalArgumentValueException( );
        }
        logger.info( stringBuilder.toString( ) );
        return shopDomain;
    }

    /**
     * Gets converted shop domain list from shop dTO list.
     *
     * @param shopDTOList the shop dTO list
     *
     * @return the converted shop domain list from shop dTO list
     */
    public static List< com.mana.innovative.domain.Shop > getConvertedListDomainFromDTO( List< Shop > shopDTOList ) {

        List< com.mana.innovative.domain.Shop > shopDomainList = new ArrayList<>( );
        for ( Shop shopDTO : shopDTOList ) {
            com.mana.innovative.domain.Shop shopDomain = new com.mana.innovative.domain.Shop( );
            shopDomain = getConvertedDomainFromDTO( shopDomain, shopDTO );
            shopDomainList.add( shopDomain );
        }
        return shopDomainList;
    }
}