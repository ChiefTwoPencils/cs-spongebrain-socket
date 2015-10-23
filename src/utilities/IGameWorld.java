package utilities;

import controller.GameOverException;
import controller.LifeLostException;

/**
 * Interface for game worlds and their respective
 * proxies.
 * @author Robert Wilk
 *         Created on 3/22/2015.
 */
public interface IGameWorld {

    /**
     * Gets the last pylon reached by the player's car.
     *
     * @return The current progress on the track.
     */
    public int getCurrentPylon();

    /**
     * Informs if the game sound is on.
     * @return Is the sound on?
     */
    boolean isSoundOn();

    /**
     * Sets the sound to be on or off.
     * @param soundOn Is the sound on.
     */
    public void setSoundOn(boolean soundOn);

    /**
     * Accelerate the player's car.
     */
    public void accelerateCar();

    /**
     * Apply the brakes on the player's car.
     */
    public void brakeCar();

 /*   *//**
     * Simulates a collision with a bird flying over the track.
     *//*
    public void collideWithBird();

    *//**
     * Simulates a collision with another car on the track.
     *//*
    public void collideWithCar();

    *//**
     * Simulates a collision with a fuel can.
     *//*
    public void collideWithFuelCan();

    *//**
     * Simulates a collision with an oil slick. A collision can be considered
     * the car's enter or exit of an oil slick.
     *//*
    public void collideWithOilSlick(boolean exiting);

    *//**
     * Simulates a collision between the player's car and a pylon.
     *//*
    public void collideWithPylon(int pylon);*/

    /**
     * Sends a steer command to the player's car.
     *
     * @param direction The direction to head.
     */
    public void steerCar(ISteerable.Direction direction);

    /**
     * Advances the game clock by a single "tick" and updates all game
     * objects.
     *
     * @throws controller.GameOverException
     * @throws controller.LifeLostException
     */
    public void advanceGameTime(int elapsedTime)
      throws GameOverException, LifeLostException;

    /**
     * Generates new random colors for the objects in the game world.
     */
    public void generateNewColors();

    /**
     * Adds a fuel can to the game world of a random size and location.
     */
    public void addFuelCan();

    /**
     * Adds a fuel can to the game world.
     * @param location The can's location.
     * @param size The can's size.
     */
    public void addFuelCan(Location2D location, int size);

    /**
     * Adds a pylon to the game world.
     * @param sequenceNumber The pylons number.
     * @param location The pylon's location.
     */
    public void addPylon(int sequenceNumber, Location2D location);

    /**
     * Gets the various states the world tracks.
     * @return An array of strings representing the world's state.
     */
    public String[] getGameStates();

    /**
     * An iterator of drawables for the map view to use.
     * @return The IDrawable iterator.
     */
    public IIterable<IDrawable> getMapViewIterator();
}
