package controller;

/**
 * Exception to be thrown when the player loses a life. The user
 * should pass in the number of lives the player has remaining.
 *
 * @author Robert Wilk
 */
@SuppressWarnings("serial")
public class LifeLostException extends
  AbstractGameException {
    /**
     * Constructs the exception with the default "life-lost" message
     * and the number of lives left passed in.
     *
     * @param lives The number of lives remaining.
     */
    public LifeLostException(int lives, String reason) {
        super("You lost a life! " + reason + " Your game has been reset!\nRemaining lives: " + lives);
    }
}
