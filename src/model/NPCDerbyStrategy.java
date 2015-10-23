package model;

import utilities.ISteerable;
import utilities.Location2D;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * An NPCStrategy which moves the npc towards the player's car
 * with intent to collide and damage it.
 * @author Robert Wilk
 *         Created on 3/6/2015.
 */
public class NPCDerbyStrategy
  extends NPCStrategy {

    public static ArrayList<Line2D.Double> lines = new ArrayList<>();
    /**
     * The player's car which the npc targets.
     */
    private Car player;

    /**
     * Constructs the strategy with the NPC and player object.
     * @param NPCar The NPC.
     * @param player The player.
     */
    public NPCDerbyStrategy(NPCar NPCar, Car player) {
        super(NPCar);
        this.player = player;
    }

    @Override
    public void apply(int elapsedTime) {
        /*double ptx = player.getRPos().translation().getTranslateX();
        double pty = player.translation().getTranslateY();*/
        double ntx = npCar.translation().getTranslateX();
        double nty = npCar.translation().getTranslateY();

        /*if (ptx > ntx || pty < nty)
            npCar.steer(ISteerable.Direction.LEFT);
        else if (ptx < ntx)
            npCar.steer(ISteerable.Direction.RIGHT);*/


        Location2D l = player.getLocation();

        setNPCLocation(getTheta(l), elapsedTime);
/*        npCar.rotate(Math
            .toRadians(-npCar.getSteeringDirection()),
            npCar.rx + npCar.translation().getTranslateX(),
            npCar.ry + npCar.translation().getTranslateY()
        );*/
        lines.add(new Line2D.Double(l.getX(), l.getX(), ntx, nty));
    }

    @Override
    public String toString() {
        return "Derby";
    }
}
