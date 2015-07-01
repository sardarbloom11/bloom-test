package com.mana.innovative.service.client;

import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * The interface Items service.
 *
 * @author Rono, AB, Vadim Servetnik
 * @email arkoghosh @hotmail.com, ma@gmail.com, vsssadik@gmail.com
 * @Copyright
 */
@Service
public interface ItemsService {

    /**
     * Gets items.
     *
     * @param isError the is error
     * @return the items
     */
    Response getItems( boolean isError );

    /**
     * Delete all items.
     *
     * @param isError the is error
     * @param deleteAllItems the delete all items
     * @return the response
     */
    Response deleteAllItems( boolean isError, boolean deleteAllItems );
}
