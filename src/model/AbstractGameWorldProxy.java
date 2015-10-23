package model;

import utilities.IGameWorld;
import utilities.Observable;

/**
 * @author Robert Wilk
 *         Created on 3/22/2015.
 */
public abstract class AbstractGameWorldProxy
extends Observable
implements IGameWorld {

    private IGameWorld gameWorld;

    protected AbstractGameWorldProxy(IGameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    protected IGameWorld getGameWorld() { return gameWorld; }
}
