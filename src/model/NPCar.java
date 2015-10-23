package model;

import utilities.IDamage;
import utilities.Location2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;

/**
 * A non player controlled car. The car relies on NPCStrategies for
 * its movement behaviour.
 * @author Robert Wilk
 *         Created on 3/6/2015.
 */
public class NPCar
extends Car
implements IDamage {

    /**
     * The movement strategy of the NPCar.
     */
    private NPCStrategy strategy;
    /**
     * A coefficient to "armor" the NPCar.
     */
    private static final int DAMAGE_MULTIPLIER = 2;
    /**
     * The amount of damage caused be a collision
     * with an NPC.
     */
    private int damage;

    /**
     * In addition to the data taken by the super
     *
     * class this constructor accepts the width and length of the car.
     *
     * @param width        The width of the car.
     * @param length       The length of the car.
     * @param color        The color of the car.
     * @param location     The initial location of the car.
     * @param speed        The initial speed of the car.
     * @param heading      The initial heading of the car.
     * @param maximumSpeed The maximum speed of the car.
     */
    public NPCar(int width,
                 int length,
                 Color color,
                 Location2D location,
                 int speed,
                 int heading,
                 int maximumSpeed,
                 int damage) {
        super(width, length, color, location, speed, heading, maximumSpeed, damage);
        MAX_DAMAGE *= DAMAGE_MULTIPLIER;
        this.damage = damage;
    }

    /**
     * Sets the strategy of the NPC.
     * @param strategy The strategy.
     */
    public void setStrategy(NPCStrategy strategy) {
        this.strategy = strategy;
    }

    public NPCStrategy getStrategy() {
        return strategy;
    }
    /**
     * An NPCar moves according to the rules of its strategy.
     */
    @Override
    public void move(int elapsedTime) {
        if (movable()) {
            spendFuel();
            strategy.apply(elapsedTime);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform saved = g.getTransform();
        //g.transform(rotation());
        g.transform(translation());
        g.setColor(getColor());
        g.drawRect(0, 0, getWidth(), getLength());
        g.setColor(Color.white);
        g.setTransform(saved);
    }

    @Override
    public int damage() { return damage; }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        return new StringBuilder("NPC: loc=")
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
          .append(" strategy=")
          .append(strategy)
          .toString();
    }
}
