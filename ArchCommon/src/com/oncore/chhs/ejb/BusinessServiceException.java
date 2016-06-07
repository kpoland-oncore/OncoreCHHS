package com.oncore.chhs.ejb;

import javax.ejb.ApplicationException;

/**
 *
 * @author Kerry O'Brien
 */
@ApplicationException( rollback = true )
public class BusinessServiceException extends RuntimeException {
    /**
     * 
     */
    public BusinessServiceException() {
    }

    /**
     * 
     * @param message The message.
     */
    public BusinessServiceException(String message) {
        super(message);
    }

    /**
     * 
     * @param message The message.
     * @param cause The cause.
     */
    public BusinessServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @cause The cause. 
     */
    public BusinessServiceException(Throwable cause) {
        super(cause);
    }
}
