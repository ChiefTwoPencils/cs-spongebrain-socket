package model;

import utilities.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A game representation of a car.
 * @author Robert Wilk
 */
public class Car
extends AbstractMovableGameObject
implements ISteerable, IColorable, IDamage {

    /**
     * The key used to determine a collision response by
     * the Collider.
     */
    public static final String COLLISION_KEY = "Car";
    /**
     * Maximum fuel the car can have.
     */
    private static final double MAX_FUEL = 100;
    /**
     * Continuous running fuel cost.
     */
    private static final double FUEL_SPENT = 0.10;
    /**
     * Single steering instance increment.
     */
    private static final int STEERING_INC = 5;
    /**
     * Increment for calls to accelerate.
     */
    private static final int SPEED_INC = 20;
    /**
     * Increment for calls to brake.
     */
    private static final int BRAKE_INC = 10;
    /**
     * Maximum +/- heading value.
     */
    private static final int MAX_OFFSET = 40;
    /**
     * Does the car have traction?
     */
    private boolean traction = true;
    /**
     * The width of the car.
     */
    private final int width;
    /**
     * The length of the car.
     */
    private final int length;
    /**
     * The steering direction of the car.
     */
    private int steeringDirection = 0;
    /**
     * The maximum speed of the car.
     */
    private int maximumSpeed;
    /**
     * Maximum damage the car can sustain.
     */
    int MAX_DAMAGE;
    /**
     * The fuel level of the car.
     */
    private double fuelLevel = 100;
    /**
     * The damage level of the car.
     */
    private double damageLevel = 0;
    /**
     * Amount of damage done in a collision.
     */
    private final int damage;
    /**
     * The last pylon reached.
     */
    private int currentPylon;

    protected double rx;
    protected double ry;

    /**
     * In addition to the data taken by the super
     * class this constructor accepts the width and
     * length of the car.
     *
     * @param width  The width of the car.
     * @param length The length of the car.
     */
    public Car(int width, int length, Color color, Location2D location,
               int speed, int heading, int maximumSpeed, int damage) {
        super(color,
          location,
          speed,
          heading);

        this.width = width;
        this.length = length;
        this.maximumSpeed = maximumSpeed;
        this.MAX_DAMAGE = 100;
        this.damage = damage;
        rx = .5 * width;
        ry = .5 * length;

        /*
         * Transforms the origin to be the center of the car.
         */
        translate(-rx, -ry);
        translate(location.getX(), location.getY());
    }

    /**
     * Method to get the car's width.
     *
     * @return the width
     */
    int getWidth() {
        return width;
    }

    /**
     * Method to get the length of the car.
     *
     * @return the length
     */
    int getLength() {
        return length;
    }

    /**
     * Method to get the steering direction of the car.
     *
     * @return the steeringDirection
     */
    int getSteeringDirection() {
        return steeringDirection;
    }

    /**
     * Method to get the maximum speed of the car.
     *
     * @return the maximumSpeed
     */
    int getMaximumSpeed() {
        return maximumSpeed;
    }

    /**
     * Method to get the fuel level of the car.
     *
     * @return the fuelLevel
     */
    public double getFuelLevel() {
        return fuelLevel;
    }

    /**
     * Method to get the damage level of the car.
     *
     * @return the damageLevel
     */
    public double getDamageLevel() {
        return damageLevel;
    }

    /**
     * @return The last pylon reached.
     */
    public int getPylon() {
        return currentPylon;
    }

    /**
     * Advance the last pylon reached.
     */
    public void advance() {
        ++currentPylon;
    }

    /**
     * Getter for the traction field.
     *
     * @return Does the car currently have traction?
     */
    boolean hasTraction() {
        return traction;
    }

    /**
     * Setter for the traction field.
     *
     * @param traction Does the car currently have traction?
     */
    public void hasTraction(boolean traction) {
        this.traction = traction;
    }

    @Override
    public void move(int elapsedTime) {
        if (movable()) {
            fuelLevel -= FUEL_SPENT;
            super.move(elapsedTime);
            if (hasTraction()) {
                setHeading(getSteeringDirection());
                rotate(Math
                  .toRadians(-getSteeringDirection()),
                  rx + translation().getTranslateX(),
                  ry + translation().getTranslateY()
                );
            }
        }
    }

    /**
     * Accelerate the car if not already at it's relative
     * max speed and has traction.
     */
    public void accelerate() {
        if (hasTraction() && (getSpeed() < maximumSpeed))
            setSpeed(getSpeed() + SPEED_INC);
    }

    /**
     * Brake the car if not already stopped and has traction.
     */
    public void brake() {
        if (hasTraction() && (getSpeed() > 0))
            setSpeed(getSpeed() - BRAKE_INC);
    }

    /**
     * Add fuel to the car.
     */
    public void fuelUp(double fuel) {
        double f = fuelLevel + fuel;
        fuelLevel = f > MAX_FUEL ? MAX_FUEL : f;
    }

    /**
     * Adds some damage to the car.
     * @param damage The amount of damage.
     */
    public void damaged(int damage) {
        damageLevel += damage;
        if (damageLevel > MAX_DAMAGE)
            damageLevel = MAX_DAMAGE;
        maximumSpeed -= damage;
        updateSpeed();
    }

    /**
     * Checks the car for minimum requirements for
     * movement.
     * @return Is the car movable?
     */
    public boolean movable() {
        return (fuelLevel > 0) &&
          (damageLevel != MAX_DAMAGE);
    }

    protected void spendFuel() {
        fuelLevel -= FUEL_SPENT;
    }

    /**
     * A car steering mechanism is limited by a max offset of
     * 40 degrees either side of "straight" ahead.
     * @see utilities.ISteerable#steer(utilities.ISteerable.Direction)
     */
    @Override
    public void steer(Direction direction) {
        switch (direction) {
            case LEFT:
                if (steeringDirection > -MAX_OFFSET)
                    steeringDirection -= STEERING_INC;
                break;
            case RIGHT:
                if (steeringDirection < MAX_OFFSET)
                    steeringDirection += STEERING_INC;
                break;
        }
    }

    /**
     * Update the cars speed after the maximum speed is
     * changed.
     */
    private void updateSpeed() {
        if (getSpeed() > maximumSpeed)
            setSpeed(maximumSpeed);
        if (getSpeed() < 0)
            setSpeed(0);
    }

    @Override
    public Location2D getLocation() {
        Point2D op = new Point2D.Double(0, 0);
        Point2D dp = new Point2D.Double(0, 0);

        translation().deltaTransform(op, dp);
        rotation().deltaTransform(op, dp);
        return new Location2D(
          dp.getX(),
          dp.getY()
        );
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        return new StringBuilder("Car: loc=")
          .append(df.format(getLocation().getX()))
          .append(",")
          .append(df.format(getLocation().getY()))
          .append(" color=[")
          .append(getColor().getRed())
          .append(",")
          .append(getColor().getBlue())
          .append(",")
          .append(getColor().getGreen())
          .append("] heading=")
          .append(getHeading())
          .append(" speed=")
          .append(getSpeed())
          .append(" width=")
          .append(getWidth())
          .append(" length=")
          .append(getLength())
          .append("\n\tmaxSpeed=")
          .append(getMaximumSpeed())
          .append(" steeringDirection=")
          .append(getSteeringDirection())
          .append(" fuelLevel=")
          .append(df.format(getFuelLevel()))
          .append(" damageLevel=")
          .append(df.format(getDamageLevel()))
          .toString();
    }

    @Override
    public void changeColor(Color color) {
        super.setColor(color);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform saved = g.getTransform();
        g.transform(rotation());
        g.transform(translation());
        g.setColor(getColor());
        g.fillRect(0, 0, getWidth(), getLength());
        g.setColor(Color.RED);
        g.drawRect(0, 0, getWidth(), getLength());
        // g.draw(boundingShape());
        g.setTransform(saved);
    }

    @Override
    public void handleCollisions() {
        ArrayList<ICollider> c = collisions.get(CURRENT);
        ArrayList<ICollider> p = collisions.get(PREVIOUS);
        ArrayList<Pylon> pylons = new ArrayList<>();

        for (ICollider e : c) {
            if (p.contains(e)) {
                p.remove(e);
            } else {
                handleCollision(e);
                if (e instanceof Pylon) {
                    pylons.add((Pylon) e);
                }
            }
        }
        for (ICollider e : p) {
            if (e instanceof OilSlick) {
                hasTraction(true);
                break;
            }
        }
        for (Pylon pylon : pylons) {
            c.remove(pylon);
        }
        collisions.clear();
        p.clear();
        collisions.add(CURRENT, p);
        collisions.add(PREVIOUS, c);
    }

    @Override
    public Shape boundingShape() {
        Point2D op = new Point2D.Double(0, 0);
        Point2D dp = new Point2D.Double(0, 0);

        translation().deltaTransform(op, dp);
        rotation().deltaTransform(op, dp);
        return new Rectangle(
          (int) dp.getX(),
          (int) dp.getY(), //(int) translation().getTranslateY(),
          getWidth(),
          getLength()
        );
    }

    @Override
    public String getCollisionKey() { return COLLISION_KEY; }

    @Override
    public int damage() {
        return damage;
    }
}
