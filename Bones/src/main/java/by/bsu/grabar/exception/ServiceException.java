package by.bsu.grabar.exception;

/**
 * The type Service exception.
 */
public class ServiceException extends Exception {
    /**
     * Instantiates a new Service exception.
     */
    public ServiceException() {
        super();
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param cause the cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
