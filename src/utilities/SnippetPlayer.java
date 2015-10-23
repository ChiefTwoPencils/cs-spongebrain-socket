package utilities;

import controller.Game;
import model.Bird;
import model.Car;
import model.FuelCan;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Audio snippet player for all aspects of the game.
 * @author Robert Wilk
 *         Created on 4/21/2015.
 */
public class SnippetPlayer {

    /*
    The various file paths for the game sounds.
     */
    private static final String SOUND_PATH = "." + File.separator + "sounds" + File.separator;
    private static final String CAR_SOUND = SOUND_PATH + "crunch.wav";
    private static final String BIRD_SOUND = SOUND_PATH + "prettyBird.wav";
    private static final String BACKGROUND = SOUND_PATH + "background.wav";
    private static final String FUEL_SOUND = SOUND_PATH + "slurp.wav";
    private static final String DIE = SOUND_PATH + "youDied.wav";

    /**
     * The library of sounds for the game.
     */
    private static Map<String, GameSound> soundLibrary;

    /**
     * Constructs the player with a predefined sound library.
     */
    public SnippetPlayer() {
        soundLibrary = new HashMap<>();
        soundLibrary.put(Game.GAME_BACKGROUND, new GameSound(BACKGROUND));
        soundLibrary.put(Car.COLLISION_KEY, new GameSound(CAR_SOUND));
        soundLibrary.put(FuelCan.COLLISION_KEY, new GameSound(FUEL_SOUND));
        soundLibrary.put(Bird.COLLISION_KEY, new GameSound(BIRD_SOUND));
        soundLibrary.put(Game.YOU_DIED, new GameSound(DIE));
    }

    /**
     * Plays a sound from the sound library.
     * @param key The key to the mapped sound.
     */
    public static void play(String key) {
        soundLibrary.get(key).play();
    }

    /**
     * Plays a sound from the sound library with the option
     * to loop it.
     * @param key The key to the mapped sound.
     * @param loop Should it loop?
     */
    public static void play(String key, boolean loop) {
        if (loop)
            soundLibrary.get(key).loop();
        else
            play(key);
    }

    /**
     * Stops the sound from the sound library.
     * @param key The key to the mapped sound.
     */
    public static void stop(String key) {
        soundLibrary.get(key).stop();
    }

    /**
     * Loops a sound from the sound library.
     * @param key The key to the mapped sound.
     */
    public static void loop(String key) {
        soundLibrary.get(key).loop();
    }
}
