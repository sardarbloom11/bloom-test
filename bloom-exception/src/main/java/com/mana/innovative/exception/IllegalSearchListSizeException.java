package com.mana.innovative.exception;

import com.mana.innovative.constants.DAOConstants;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright Date : 10/2/12 Time: 3:42 PM
 * @since: jdk 1.7
 */
public class IllegalSearchListSizeException extends RuntimeException {

    /**
     * The Message.
     */
    private String message = "Size of Search String keywords cannot be Greater than "
            + DAOConstants.THREE + " OR Less " + "Than " + DAOConstants.ONE;

    /**
     * Instantiates a new Illegal search list size exception.
     */
    public IllegalSearchListSizeException( ) {
        super( );
    }

    /**
     * Instantiates a new Illegal search list size exception.
     *
     * @param message the message
     */
    public IllegalSearchListSizeException( String message ) {

        super( message );
        this.message = message + this.message;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public String getError( ) {
        return message;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString( ) {
        return message;
    }
}
