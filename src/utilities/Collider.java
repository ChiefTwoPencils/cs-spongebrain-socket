package utilities;

import commands.CollideWithBirdAction;
import commands.CollideWithCarAction;
import commands.CollideWithFuelCanAction;
import commands.CollideWithPylonAction;
import model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A system for processing collision between IColliders.
 * @author Robert Wilk
 *         Created on 4/20/2015.
 */
public class Collider {

    /**
     * A map of the response functions for the collisions.
     */
    private static Map<String, BiConsumer<ICollider, ICollider>> funcs;
    /**
     * The function used to add an oil slick to the game in the event
     * one is created from a collision.
     */
    private static Consumer<Location2D> addOilSlick;

    /**
     * Constructs the collider with a special function for oil slick
     * additions.
     * @param consumer The oil slick addition functino.
     */
    public static void addSlickHandler(Consumer<Location2D> consumer) {
        addOilSlick = consumer;
    }

    /**
     * The function which handles a collision of two cars.
     */
    private static BiConsumer<ICollider, ICollider> carAgainstCar = (a, b) -> {
        Car ac = (Car) a;
        Car bc = (Car) b;
        ac.damaged(bc.damage());
        bc.damaged(ac.damage());
        CollideWithCarAction.getInstance()
          .actionPerformed(null);
        if (!(ac instanceof NPCar && bc instanceof NPCar)) {
            if (RandNumGen.getInstance().nextDouble() < .20) {
                addOilSlick.accept(new Location2D(ac.getLocation().getX(), ac.getLocation().getY()));
            }
        }
    };

    /**
     * The function which handles a collision between a car and a bird.
     */
    private static BiConsumer<ICollider, ICollider> carAgainstBird = (a, b) -> {
        Car ac = (Car) a;
        Bird bb = (Bird) b;
        ac.damaged(bb.damage());
        CollideWithBirdAction.getInstance()
          .actionPerformed(null);
    };

    /**
     * The function which handles a collision between a bird and a car.
     */
    private static BiConsumer<ICollider, ICollider> birdAgainstCar = (a, b) -> carAgainstBird.accept(b, a);

    /**
     * The function which handles a collision between a car and fuel can.
     */
    private static BiConsumer<ICollider, ICollider> carAgainstFuelCan = (a, b) -> {
        Car ac = (Car) a;
        FuelCan bf = (FuelCan) b;
        ac.fuelUp(bf.getSize());
        bf.collided(true);
        CollideWithFuelCanAction.getInstance()
          .actionPerformed(null);
    };

    // private static BiConsumer<ICollider, ICollider> fuelCanAgainstCar = (a, b) -> carAgainstFuelCan.accept(b, a);

    /**
     * The function which handles a collision between a car and an oils slick.
     */
    private static BiConsumer<ICollider, ICollider> carAgainstOilSlick = (a, b) -> {
        Car ac = (Car) a;
        ac.hasTraction(false);
    };

/**
 * The function that handles a collision between a car and a pylon.
 */
private static BiConsumer<ICollider, ICollider> carAgainstPylon = (a, b) -> {
        Car ac = (Car) a;
        Pylon bp = (Pylon) b;
        if (ac.getPylon() + 1 == bp.getSequenceNumber()) {
            ac.advance();
        }
        CollideWithPylonAction.getInstance()
          .actionPerformed(null);
    };

    /**
     * Carries out the response for the given collision determined by the collision key created
     * from the concatenation of their collision keys.
     * @param a The first collider.
     * @param b The second collider.
     */
    public static void collide(ICollider a, ICollider b) {
        try {
            funcs.get(ICollider.getCollisionString(a, b)).accept(a, b);
            reportCollision(a, b);
        } catch (Exception e) {
            // return;
        }
    }

    public static void reportCollision(ICollider a, ICollider b) {
        System.out.println(
          a + "\nhas collided with\n" + b
        );
    }

    /**
     * Static block which builds the response map.
     */
    static {
        funcs = new HashMap<>();
        funcs.put(Car.COLLISION_KEY + Car.COLLISION_KEY, carAgainstCar);
        funcs.put(Car.COLLISION_KEY + Bird.COLLISION_KEY, carAgainstBird);
        funcs.put(Bird.COLLISION_KEY + Car.COLLISION_KEY, birdAgainstCar);
        funcs.put(Car.COLLISION_KEY + FuelCan.COLLISION_KEY, carAgainstFuelCan);
        funcs.put(Car.COLLISION_KEY + OilSlick.COLLISION_KEY, carAgainstOilSlick);
        funcs.put(Car.COLLISION_KEY + Pylon.COLLISION_KEY, carAgainstPylon);
    }
}
