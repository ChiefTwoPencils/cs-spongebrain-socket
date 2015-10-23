package controller;

/**
 * Exception to be thrown when the game is over.
 * The message should indicate if the user won or lost.
 *
 * @author Robert Wilk
 */
@SuppressWarnings("serial")
public class GameOverException
  extends AbstractGameException {

    /**
     * Constructs the exception with the message to be displayed.
     * @param msg The message.
     */
    public GameOverException(String msg) {
        super("GAME OVER : " + msg);
    }

}
