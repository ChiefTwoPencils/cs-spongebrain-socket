package utilities;

import java.util.Date;
import java.util.Random;

/**
 * Random number singleton.
 * @author Robert Wilk
 */
public class RandNumGen {
    /**
     * The instance of the single random number generator.
     */
    private static RandNumGen instance = null;
    /**
     * A Java Random 'generator'.
     */
    private final Random generator;

    /**
     * Private default constructor for the random
     * number generator.
     */
    private RandNumGen() {
        generator = new Random();
        // Make sure the generator is uniquely seeded.
        seed(new Date().getTime());
    }

    /**
     * Method to get the instance of the singleton.
     * @return The single instance.
     */
    public static RandNumGen getInstance() {
        synchronized (RandNumGen.class) {
            if (instance == null) {
                instance = new RandNumGen();
            }
            return instance;
        }
    }

    /**
     * Method to seed the generator.
     * @param seed The seed for the random number generator.
     */
    public void seed(long seed) {
        generator.setSeed((long) (seed * Math.pow(2, 8)));
    }

    /**
     * Method to get a random integer.
     * @return The next random integer.
     */
    public int nextInt() {
        return Math.abs(generator.nextInt());
    }

    /**
     * Gets a random double.
     * @return The random double.
     */
    public double nextDouble() { return Math.abs(generator.nextDouble()); }
}
