package model;

import utilities.Location2D;

/**
 * Super class for all NPCStrategies.
 * @author Robert Wilk
 *         Created on 3/6/2015.
 */
abstract class NPCStrategy {

    /**
     * The car which uses the strategy.
     */
    protected final NPCar npCar;

    /**
     * Constructs the strategy with the NPC.
     * @param npCar The NPC.
     */
    NPCStrategy(NPCar npCar) {
        this.npCar = npCar;
    }

    /**
     * Applies the strategy to the NPC.
     * @param elapsedTime Time elapsed since last application.
     */
    public abstract void apply(int elapsedTime);

    /**
     * Sets the location of the npc based on the angle of
     * the heading required to head in the direction of
     * its target.
     * @param theta The angle of heading from 0 deg.
     */
    void setNPCLocation(double theta, int elapsedTime) {
        double distance =
          ((double) (npCar.getSpeed() * elapsedTime)) / 1000.0;
        double dx = Math.cos(theta) * distance;
        double dy = Math.sin(theta) * distance;
        npCar.setHeading(npCar.getSteeringDirection());
        npCar.translate(dx, dy);
    }

    /**
     * Determines the angle <i>theta</i> of the heading based
     * on the target location.
     * @param target The location of the target.
     * @return The angle of the heading from 0 deg.
     */
    double getTheta(Location2D target) {
        double tx = target.getX();
        double ty = target.getY();
        Location2D npcLocation = getNPCLocation();
        double dx = tx - npcLocation.getX();
        double dy = ty - npcLocation.getY();
        return Math.atan2(dy, dx);
    }

    /**
     * Gets the location of the NPC implementing the strategy.
     * @return The location.
     */
    Location2D getNPCLocation() {
        Location2D l = new Location2D(
          npCar.translation().getTranslateX(),
          npCar.translation().getTranslateY()
        );
        return l;
    }

    /**
     * Gets the sequence number of the last pylon reached.
     * @return The sequence number.
     */
    int getNPCPylon() {
        return npCar.getPylon();
    }


}
