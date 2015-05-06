package com.mana.innovative.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Rono, Ankur Bhardwaj
 * @email arkoghosh @hotmail.com, meankur1@gmail.com
 * @Copyright Date : 10/3/12 Time: 11:14 AM
 * @since: jdk 1.7
 */
public class IllegalArgumentValueException extends RuntimeException {

    /**
     * The Message.
     */
    private String message = "   \nArgument value is Invalid or Null ";

    /**
     * Instantiates a new Illegal argument value exception.
     */
    public IllegalArgumentValueException( ) {
        super( );
    }

    /**
     * Instantiates a new Illegal argument value exception.
     *
     * @param message the message
     */
    public IllegalArgumentValueException( String message ) {

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
