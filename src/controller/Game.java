package controller;

import commands.*;
import model.GameWorld;
import utilities.*;
import views.MapView;
import views.ScoreView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import static commands.Command.*;

/**
 * The high-level representation of a game.
 * @author Robert Wilk
 */
public class Game
extends JFrame
implements IGame, MouseListener {

    /**
     * A key for the background sound.
     */
    public static final String GAME_BACKGROUND = "BackgroundSound";
    /**
     * A key for the dying sound.
     */
    public static final String YOU_DIED = "You Died!";
    /**
     * Delay length for the game's frame timer in
     * milliseconds.
     */
    private final int FRAME_DELAY = 20;
    /**
     * The animation timer.
     */
    private GameTimer timer;
    /**
     * Color for background theme.
     */
    private final Color BACKGROUND = Color.DARK_GRAY;
    /**
     * Color of foreground theme.
     */
    private final Color FOREGROUND = Color.WHITE;
    /**
     * The game data model.
     */
    private GameWorld world = null;
    /**
     * The current game state.
     */
    private static GameState state = GameState.PLAYING;
    /**
     * A map of the commands used throughout.
     */
    private Map<GameState, ArrayList<AbstractAction>> commands = new HashMap<>();
    /**
     * A list of the components tied to commands.
     */
    private ArrayList<JComponent> commandComponents = new ArrayList<>();
    /**
     * The audio snippet player.
     */
    private SnippetPlayer player;

    /**
     * Enumerated values for the game states.
     */
    public enum GameState {
        PLAYING, PAUSED
    }

    /**
     * The main entry point into a game.
     */
    public Game() {
        player = new SnippetPlayer();
        world = new GameWorld(getDefaultTrack());
        world.initLayout();
        /*
          The graphical representation of the game world.
         */
        MapView mapView = mapView();
        /*
          The graphical representation of the game state.
         */
        ScoreView scoreView = new ScoreView(world);
        // Register the graphical representations as observers.
        world.addObserver(mapView);
        world.addObserver(scoreView);
        initialize();
        add(mapView, BorderLayout.CENTER);
        add(scoreView, BorderLayout.NORTH);
        add(commandPanel(), BorderLayout.WEST);
        timer = new GameTimer(FRAME_DELAY, AdvanceGameTimeAction.getInstance());
        timer.start();
        play();
    }

    /**
     * Initializes the game GUI to an initial state.
     */
    private void initialize() {
        Collider.addSlickHandler(world.getSlickHandler());
        makeCommandMap();
        setCommandTargets();
        setTitle("Pylon Racer");
        setSize(new Dimension(1000, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(new Point(100, 100));
        setLayout(new BorderLayout());
        setJMenuBar(menuBar());
        setVisible(true);
    }

    /**
     * Sets the targets for the commands
     */
    private void setCommandTargets() {
        SwitchModeAction.getInstance().setTarget(this);
        AccelerateCarAction.getInstance().setTarget(world);
        AddOilSlickAction.getInstance().setTarget(world);
        BrakeCarAction.getInstance().setTarget(world);
        SteerCarLeftAction.getInstance().setTarget(world);
        SteerCarRightAction.getInstance().setTarget(world);
        CollideWithCarAction.getInstance().setTarget(world);
        CollideWithPylonAction.getInstance().setTarget(world);
        CollideWithBirdAction.getInstance().setTarget(world);
        CollideWithFuelCanAction.getInstance().setTarget(world);
        GenerateNewColorsAction.getInstance().setTarget(world);
        AdvanceGameTimeAction.getInstance().setTarget(world);
        ChangeStrategiesAction.getInstance().setTarget(world);
        SetSoundAction.getInstance().setTarget(world);
        DeleteSelectedAction.getInstance().setTargetMethod(world.getDeleteSelectedMethod());
        AddFuelCanAction.getInstance().setTarget(world);
        AddPylonAction.getInstance().setTarget(world);
        MusicStateAction.getInstance().setTarget(world);
        setCommandsEnabled(true);
    }

    /**
     * Builds the map of commands for the game.
     */
    private void makeCommandMap() {
        commands.put(GameState.PLAYING, new ArrayList<>());
        commands.put(GameState.PAUSED, new ArrayList<>());
        new MapBuilder()
          .add(AccelerateCarAction.getInstance())
          .add(AddOilSlickAction.getInstance())
          .add(AdvanceGameTimeAction.getInstance())
          .add(BrakeCarAction.getInstance())
          .add(SteerCarLeftAction.getInstance())
          .add(SteerCarRightAction.getInstance())
          .build(GameState.PLAYING);
        new MapBuilder()
          .add(DeleteSelectedAction.getInstance())
          .add(AddFuelCanAction.getInstance())
          .add(AddPylonAction.getInstance())
          .build(GameState.PAUSED);
        setCommandsEnabled(true);
    }

    /**
     * Enables or disables the game commands depending on the current
     * game state.
     * @param enabled Is the command to be enabled?
     */
    private void setCommandsEnabled(boolean enabled) {
        for (AbstractAction action : commands.get(GameState.PLAYING))
            action.setEnabled(enabled);
        for (AbstractAction action : commands.get(GameState.PAUSED))
            action.setEnabled(!enabled);
        for (JComponent jComponent : commandComponents) {
            jComponent.setEnabled(
              (jComponent instanceof JButton) ? !enabled : enabled
            );
        }
    }

    /**
     * Creates the command panel for the button commands.
     * @return The command panel.
     */
    private JPanel commandPanel() {

        JPanel panel = new JPanel();
        applyTheme(panel);
        TitledBorder commandBorder = new TitledBorder("Commands");
        commandBorder.setTitleColor(FOREGROUND);
        panel.setBorder(commandBorder);
        panel.setLayout(new GridLayout(9, 1));

        // 'Play'/'Pause' button.
        JButton playPause = new JButton(Command.MODE);
        playPause.setAction(SwitchModeAction.getInstance());
        playPause.setText("Pause");
        panel.add(playPause);

        // 'Add Fuel Can' button.
        JButton addFuelCan = new JButton(Command.ADD_FUEL);
        addFuelCan.setAction(AddFuelCanAction.getInstance());
        panel.add(addFuelCan);
        commandComponents.add(addFuelCan);

        // 'Add Pylon' button.
        JButton addPylon = new JButton(Command.ADD_PYLON);
        addPylon.setAction(AddPylonAction.getInstance());
        panel.add(addPylon);
        commandComponents.add(addPylon);

        // 'Delete' button.
        JButton delete = new JButton(Command.DELETE_SELECTED);
        delete.setAction(DeleteSelectedAction.getInstance());
        panel.add(delete);

        // 'Quit' button.
        JButton quit = new JButton(Command.QUIT);
        quit.setAction(QuitAction.getInstance());
        panel.add(quit);

        applyTheme(playPause, addFuelCan, addPylon, quit, delete);
        maskButtonSpaceAction(playPause, addFuelCan, addPylon, quit, delete);

        panel.setVisible(true);
        return panel;
    }

    /**
     * This constructs the menu bar for the game.
     * @return The constructed menu bar.
     */
    private JMenuBar menuBar() {

        // The file menu.
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        applyTheme(fileMenu);

        // The 'New' menu item.
        JMenuItem newItem = new JMenuItem();
        newItem.setAction(DisplayActionMessage.getInstance());
        newItem.setText("New");
        newItem.setMnemonic(KeyEvent.VK_N);
        fileMenu.add(newItem);

        // The 'Save' menu item.
        JMenuItem saveItem = new JMenuItem();
        saveItem.setAction(DisplayActionMessage.getInstance());
        saveItem.setText("Save");
        saveItem.setMnemonic(KeyEvent.VK_S);
        fileMenu.add(saveItem);

        // The 'Music' menu item.
        JMenuItem musicItem = new JCheckBoxMenuItem();
        musicItem.setAction(MusicStateAction.getInstance());
        MusicStateAction.getInstance().setComponenet(musicItem);
        fileMenu.add(musicItem);

        // The 'Sound' menu item.
        JMenuItem soundItem = new JCheckBoxMenuItem();
        soundItem.setAction(SetSoundAction.getInstance());
        SetSoundAction.getInstance().setComponent(soundItem);
        fileMenu.add(soundItem);
        fileMenu.addSeparator();

        // The 'About' menu item.A
        JMenuItem aboutItem = new JMenuItem();
        aboutItem.setAction(AboutAction.getInstance());
        fileMenu.add(aboutItem);

        // The 'Quit' menu item.
        JMenuItem quitItem = new JMenuItem();
        quitItem.setAction(QuitAction.getInstance());
        fileMenu.add(quitItem);

        // The commands menu.
        JMenu commandMenu = new JMenu("Commands");
        commandMenu.setMnemonic(KeyEvent.VK_C);
        applyTheme(commandMenu);
        commandComponents.add(commandMenu);

        // The 'Add Oil Slick' menu item.
        JMenuItem addOilItem = new JMenuItem();
        addOilItem.setAction(AddOilSlickAction.getInstance());
        commandMenu.add(addOilItem);

        // The 'Pick Up Fuel Can' menu item.
        JMenuItem fuelItem = new JMenuItem();
        fuelItem.setAction(CollideWithFuelCanAction.getInstance());
        commandMenu.add(fuelItem);

        // The 'New Colors' menu item.
        JMenuItem colorsItem = new JMenuItem();
        colorsItem.setAction(GenerateNewColorsAction.getInstance());
        commandMenu.add(colorsItem);

        // The menu bar.
        JMenuBar menuBar = new JMenuBar();
        applyTheme(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(commandMenu);
        menuBar.setVisible(true);

        applyTheme(
          fileMenu,
          newItem,
          saveItem,
          soundItem,
          musicItem,
          aboutItem,
          quitItem,
          commandMenu,
          addOilItem,
          fuelItem,
          colorsItem,
          menuBar
        );
        return menuBar;
    }

    /**
     * Applies the common game theme to the components.
     * @param components The variable number of components to
     *                   apply the theme.
     */
    private void applyTheme(Component... components) {
        for (Component component : components) {
            component.setForeground(FOREGROUND);
            component.setBackground(BACKGROUND);
        }
    }

    /**
     * Constructs the game map view and sets the key bindings
     * for the commands.
     * @return The graphical representation of the game world.
     */
    private MapView mapView() {
        MapView mapView = new MapView(world);
        setMapKeyBindings(mapView);
        mapView.addMouseListener(AddFuelCanAction.getInstance());
        mapView.addMouseListener(AddPylonAction.getInstance());
        mapView.addMouseListener(this);
        return mapView;
    }

    /**
     * Binds the game commands to the appropriate keys.
     * @param mapView The game map view
     */
    private void setMapKeyBindings(MapView mapView) {
        // Map view's input map for key binding.
        InputMap inputMap = mapView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // Map view's action map for key commands.
        ActionMap actionMap = mapView.getActionMap();

        // Begin key/space bindings.
        // 'a' key for accelerate command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), ACCELERATE);
        actionMap.put(ACCELERATE, AccelerateCarAction.getInstance());

        // 'b' key for brake command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), BRAKE);
        actionMap.put(BRAKE, BrakeCarAction.getInstance());

        // 'l' key for left turn command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), LEFT_TURN);
        actionMap.put(LEFT_TURN, SteerCarLeftAction.getInstance());

        // 'o' key to add an oil slick.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0), ADD_SLICK);
        actionMap.put(ADD_SLICK, AddOilSlickAction.getInstance());

        // 'q' key for quit command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), QUIT);
        actionMap.put(QUIT, QuitAction.getInstance());

        // 'r' key for right turn command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), RIGHT_TURN);
        actionMap.put(RIGHT_TURN, SteerCarRightAction.getInstance());

        // 't' key for advance game time command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), TICK);
        actionMap.put(TICK, AdvanceGameTimeAction.getInstance());

        // 'SPACE' key for change strategies command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), CHANGE_STRATEGIES);
        actionMap.put(CHANGE_STRATEGIES, ChangeStrategiesAction.getInstance());
        // End key/space bindings.

        // Begin arrow bindings.
        // Bind up arrow to accelerate command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), ACCELERATE);
        // Bind down arrow to brake command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), BRAKE);
        // Bind left arrow to left turn command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT_TURN);
        // Bind right arrow to right turn command.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT_TURN);
        // End arrow bindings.

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE_SELECTED);
        actionMap.put(DELETE_SELECTED, DeleteSelectedAction.getInstance());
    }

    /**
     * Masks the space bar from invoking the action of the in-focus
     * component (JButtons).
     * @param buttons The JButtons to be masked.
     */
    private void maskButtonSpaceAction(JButton... buttons) {
        for (JButton button : buttons) {
            button.getInputMap()
              .put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "none");
        }
    }

    /**
     * Returns a list of locations which serve as a default game track.
     * @return The locations.
     */
    private java.util.List<Location2D> getDefaultTrack() {
        ArrayList<Location2D> locs = new ArrayList<>();
        locs.add(new Location2D(100, 100));
        locs.add(new Location2D(200, 500));
        locs.add(new Location2D(300, 300));
        locs.add(new Location2D(500, 300));
        locs.add(new Location2D(500, 200));
        locs.add(new Location2D(400, 600));
        return locs;
    }

    /**
     * Determines if the game is paused.
     * @return Paused?
     */
    public static boolean isPaused() { return state == GameState.PAUSED; }

    /**
     * Determines if the game is playing.
     * @return Playing?
     */
    public static boolean isPlaying() { return state == GameState.PLAYING; }

    /**
     * Sets the current game state to playing and enables appropriate commands and
     * returns the sound to the previous state.
     */
    @Override
    public void play() {
        state = GameState.PLAYING;
        world.deselectAll();
        setCommandsEnabled(true);
        timer.restart();
        MusicStateAction.getInstance().actionPerformed(null);
    }

    /**
     * Sets the current game state to paused and disables appropriate commands and
     * stops the sound.
     */
    @Override
    public void pause() {
        SnippetPlayer.stop(GAME_BACKGROUND);
        state = GameState.PAUSED;
        timer.stop();
        setCommandsEnabled(false);
    }

    /**
     * A class that builds a map to prevent numerous map.put(...) calls.
     * It maps actions based on game state keys.
     */
    private class MapBuilder {

        /**
         * Buffer for actions while building.
         */
        private  ArrayList<AbstractAction> list;
        MapBuilder() {
            list = new ArrayList<>();
        }

        /**
         * Adds an action to the builder's buffer.
         * @param action The action to be added.
         * @return The MapBuilder.
         */
        MapBuilder add(AbstractAction action) {
            list.add(action);
            return this;
        }

        /**
         * Empties the buffer into the map backing the builder.
         * @param state The key to map by.
         */
        void build(GameState state) {
            commands.get(state).addAll(list);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Tells the game world to handle the location of mouse pressed events
     * if the game state is paused.
     * @param e Not used.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (state == GameState.PAUSED)
            world.handleClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
