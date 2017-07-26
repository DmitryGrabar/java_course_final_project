package by.bsu.grabar.error;

/**
 * The type Dao exception.
 */
public class DAOException extends Exception {
    /**
     * Instantiates a new Dao exception.
     */
    public DAOException() {
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param cause the cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
