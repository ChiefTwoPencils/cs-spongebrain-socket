package model;

import utilities.Location2D;

/**
 * An NPCStrategy that moves the npc around the track
 * according to the locations of the pylons which define
 * the track.
 * @author Robert Wilk
 *         Created on 3/6/2015.
 */
public class NPCWinStrategy
extends NPCStrategy {

    /**
     * The locations of the game pylons which define the
     * track.
     */
    private static Location2D[] pylonLocations;
    /**
     * The current pylon target of the npc.
     */
    private int currentTarget;

    /**
     * Constructs the strategy with the associated NPC and pylon
     * locations.
     * @param NPCar The NPC.
     * @param pylonLocations The track's pylon locations.
     */
    public NPCWinStrategy(NPCar NPCar, Location2D[] pylonLocations) {
        super(NPCar);
        currentTarget = NPCar.getPylon();
        NPCWinStrategy.pylonLocations = pylonLocations;
    }

    /**
     * Resets the location of the pylons in the strategy to update
     * the current representation.
     * @param pylonLocations Locations of the pylons.
     */
    public static void resetTrack(Location2D[] pylonLocations) {
        NPCWinStrategy.pylonLocations = pylonLocations;
    }

    @Override
    public void apply(int elapsedTime) {
        if (pylonLocations.length == 0)
            return;
        currentTarget = getNPCPylon();
        if (currentTarget == pylonLocations.length)
            currentTarget = 0;
        setNPCLocation(getTheta(pylonLocations[currentTarget]), elapsedTime);
    }

    @Override
    public String toString() {
        return "Win";
    }
}
