package com.mana.innovative.dao.consumer;

import com.mana.innovative.dao.response.DAOResponse;
import com.mana.innovative.domain.consumer.CreditCard;
import com.mana.innovative.dto.request.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bloom/Rono on 5/2/2015 5:27 PM. This class is CreditCardDAO
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Repository
public interface CreditCardDAO {

    /**
     * Create credit card.
     *
     * @param creditCard the credit card
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CreditCard > createCreditCard( CreditCard creditCard, RequestParams requestParams );

    /**
     * Gets credit cards.
     *
     * @param requestParams the request params
     * @return the credit cards
     */
    DAOResponse< CreditCard > getCreditCards( RequestParams requestParams );

    /**
     * Gets credit card.
     *
     * @param cardId the credit card id
     * @param requestParams the request params
     * @return the credit card
     */
    DAOResponse< CreditCard > getCreditCardByCardId( long cardId, RequestParams requestParams );

    /**
     * Update credit card.
     *
     * @param creditCard the credit card
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CreditCard > updateCreditCard( CreditCard creditCard, RequestParams requestParams );

    /**
     * Delete credit card by credit card id.
     *
     * @param cardId the credit card id
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CreditCard > deleteCreditCardByCardId( long cardId, RequestParams requestParams );

    /**
     * Delete credit cards by card ids.
     *
     * @param cardIds the card ids
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CreditCard > deleteCreditCardsByCardIds( List< Long > cardIds, RequestParams requestParams );

    /**
     * Delete all credit cards.
     *
     * @param requestParams the request params
     * @return the dAO response
     */
    DAOResponse< CreditCard > deleteAllCreditCards( RequestParams requestParams );
}
