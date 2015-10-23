package utilities;

import commands.AdvanceGameTimeAction;
import commands.Command;
import controller.Game;
import sun.util.calendar.BaseCalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * A timer for the game animation. It accepts abstract actions
 * to be invoked at the given interval in frames per second.
 * @author Robert Wilk
 *         Created on 3/24/2015.
 */
public class GameTimer
extends Timer {

    /**
     * The action invoked by the timer.
     */
    private AdvanceGameTimeAction action;
    /**
     * Creates a {@code Timer} and initializes both the initial delay and
     * between-event delay to {@code delay} milliseconds. If {@code delay}
     * is less than or equal to zero, the timer fires as soon as it
     * is started. If <code>listener</code> is not <code>null</code>,
     * it's registered as an action listener on the timer.
     *
     * @param delay    milliseconds for the initial and between-event delay
     * @param listener an initial listener; can be <code>null</code>
     * @see #addActionListener
     * @see #setInitialDelay
     * @see #setRepeats
     */
    public GameTimer(int delay, ActionListener listener) {
        super(delay, listener);
        action = (AdvanceGameTimeAction)listener;
        action.setElapsedTime(delay);
    }
}
