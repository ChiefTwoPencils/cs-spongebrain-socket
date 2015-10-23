package model;

import controller.GameOverException;
import controller.LifeLostException;
import utilities.*;

/**
 * Protection proxy for the game map view.
 * @author Robert Wilk
 *         Created on 3/22/2015.
 */
public class MapViewProxy
extends AbstractGameWorldProxy {

    /**
     * Constucts the proxy with the actual game world.
     * @param gameWorld The game world.
     */
    public MapViewProxy(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public int getCurrentPylon() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSoundOn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSoundOn(boolean soundOn) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accelerateCar() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void brakeCar() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void steerCar(ISteerable.Direction direction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void advanceGameTime(int elapsedTime) throws GameOverException, LifeLostException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void generateNewColors() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addFuelCan() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addFuelCan(Location2D location, int size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPylon(int sequenceNumber, Location2D location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getGameStates() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IIterable<IDrawable> getMapViewIterator() {
        return getGameWorld().getMapViewIterator();
    }
}