package controller;

/**
 * Abstract super class for all game exceptions.
 * @author Robert Wilk
 */
@SuppressWarnings("serial")
public class AbstractGameException
  extends Exception {

    /**
     * The message to be sent with the exception.
     */
    private final String msg;

    /**
     * Constructs the exception with the message.
     * @param msg The message to be displayed.
     */
    AbstractGameException(String msg) {
        this.msg = msg;
    }

    /**
     * Returns the exception message.
     * @return The message.
     */
    @Override
    public String getMessage() {
        return msg;
    }
}
