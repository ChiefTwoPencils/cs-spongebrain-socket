package utilities;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * A abstraction of a game sound.
 * @author Robert Wilk
 *         Created on 4/21/2015.
 */
public class GameSound {

    /**
     * The audio clip backing the game sound.
     */
    private AudioClip clip;

    /**
     * Constructs the sound by a file location.
     * @param path The file location.
     */
    public GameSound(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                clip = Applet.newAudioClip(file.toURI().toURL());
            } else {
                throw new RuntimeException("Sound: file not found:" + path);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: malformed URL: " + e);
        }
    }

    /**
     * Plays the game sound.
     */
    public void play() {
        clip.play();
    }

    /**
     * Stops the game sound.
     */
    public void stop() {
        clip.stop();
    }

    /**
     * Loops the game sound.
     */
    public void loop() {
        clip.loop();
    }
}
