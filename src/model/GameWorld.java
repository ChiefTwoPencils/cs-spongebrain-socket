package model;

import controller.GameOverException;
import controller.LifeLostException;
import utilities.*;
import utilities.ISteerable.Direction;
import utilities.Observable;
import views.MapView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * The model component of the racer game.
 * @author Robert Wilk
 */
public class GameWorld
extends Observable
implements IGameWorld {

    /**
     * Enumerated size values for game objects which have a range
     * of valid sizes.
     * @author Robert Wilk
     */
    private enum Size {

        SMALL(20), MEDIUM(40), LARGE(60);
        /**
         * The arbitrary value of a given size.
         * The units of the value may be implementation
         * specific.
         */
        private final int value;

        /**
         * Private constructor for the respective enumeration
         * values.
         *
         * @param value The value of the enumeration.
         */
        private Size(int value) {
            this.value = value;
        }

        /**
         * @return The enumeration's value.
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * Enumerated speed values for game objects which are
     * movable.
     * @author Robert Wilk
     */
    private enum Speed {

        SLOW(5), NORMAL(20), FAST(45);

        /**
         * The arbitrary value of a given speed.
         * The units of the value may be implementation
         * specific.
         */
        private final int value;

        /**
         * Private constructor for the respective enumeration
         * values.
         * @param value The value of the enumeration.
         */
        private Speed(int value) {
            this.value = value;
        }

        /**
         * Gets the integer value associated with the speed.
         * @return
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * Enumerated strategy values.
     */
    private enum Strategy {
        WIN, DERBY
    }

    /**
     * The x/y limits of the game world.
     */
    // private static final double MAX_XY = 500;
    private static final double MAX_Y = 500;
    private static final double MAX_X = 750;
    /**
     * Number of pylons that make up the track.
     */
    public static int PYLONS;
    /**
     * The default amount of damage done to the player's
     * car due to collision (car).
     */
    private static final int DAMAGE = 5;
    /**
     * The width of the player's car.
     */
    private static final int PLAYER_WIDTH = Size.SMALL.getValue();
    /**
     * The length of the player's car.
     */
    private static final int PLAYER_LENGTH = Size.MEDIUM.getValue();
    /**
     * The player's max speed.
     */
    private static final int PLAYER_MAX_SPEED = 500;
    /**
     * Player reference to avoid having to iterate the collection
     * to find the player.
     */
    private Car player;
    /**
     * Number of lives (game attempts).
     */
    private int lives = 3;
    /**
     * Number of elapsed "ticks" in the game.
     */
    private int currentGameTime = 0;
    /**
     * Counter to determine if a second has passed.
     */
    private int ticks = 0;
    /**
     * Is the sound on.
     */
    private boolean soundOn = true;
    /**
     * Container for all game objects.
     */
    private final GameObjectCollection<AbstractGameObject> gameObjectCollection
        = new GameObjectCollection<>();
    /**
     * List of all the selected game objects at a given time.
     */
    private ArrayList<ISelectable> selected = new ArrayList<>();
    /**
     * A list of locations for the pylons.
     */
    private List<Location2D> location2Ds;

    /**
     * Constructs the game world with a default track.
     * @param track The default track.
     */
    public GameWorld(List<Location2D> track) {
        location2Ds = track;
    }

    /**
     * Method to initialize the entire initial game state.
     */
    public void initLayout() {
        // Add the default track to the game world.
        //addPylons(location2Ds);

        // two oil slick objects with rand location  width and length (from small range)
        //addOilSlicks(2);
        // two fuel cans with rand location and size from small range
        //addFuelCans(2);

        Location2D initial = location2Ds.get(0);
        double x = initial.getX();
        double y = initial.getY();

        // Number of pylons in the game.
        PYLONS = location2Ds.size();
        player = makePlayerCar(initial.clone());
        gameObjectCollection.add(player);

        NPCar npc = makeNPCar(new Location2D(x - 50, y));
        npc.setStrategy(new NPCDerbyStrategy(npc, player));
        gameObjectCollection.add(npc);

        /*npc = makeNPCar(new Location2D(x - 100, y));
        npc.setStrategy(new NPCDerbyStrategy(npc, player));
        gameObjectCollection.add(npc);
        *//*npc.setStrategy(new NPCWinStrategy(npc, pylonLocations()));
        gameObjectCollection.add(npc);*//*

        npc = makeNPCar(new Location2D(x + 50, y));
        npc.setStrategy(new NPCDerbyStrategy(npc, player));
        gameObjectCollection.add(npc);*/
/*        npc.setStrategy(new NPCWinStrategy(npc, pylonLocations()));
        gameObjectCollection.add(npc);*/

        // two bird objects rand location, heading and speed (from small range of speeds)
       /* addBirds(2);
*/
        notifyObservers();
    }

    private Location2D[] pylonLocations() {
        Queue<Pylon> pq = new PriorityQueue<>(pylonComparator);
        IIterable it = gameObjectCollection.getIterator();
        while (it.hasNext()) {
            ICollider c = (ICollider) it.next();
            if (c instanceof Pylon)
                pq.add((Pylon)c);
        }
        Pylon[] pylons = new Pylon[pq.size()];
        pq.toArray(pylons);
        Location2D[] locs = new Location2D[pylons.length];
        for (int i = 0; i < pylons.length; ++i) {
            locs[i] = pylons[i].getLocation();
            locs[i].setX(pylons[i].getLocation().getX());
            locs[i].setY(pylons[i].getLocation().getY());
        }
        return locs;
    }

    /*
        Begin factory methods for the track.
     */
    private Bird makeBird() {
        int size = Size.values()[RandNumGen.getInstance().nextInt() % Size.values().length].value;
        return new Bird(size, getRandomColor(), getRandomLocation(), getRandomSpeed(), getRandomHeading(), DAMAGE / 2);
    }

    /**
     * Makes a car at the given location.
     * @param location The location.
     * @return The car.
     */
    private Car makePlayerCar(Location2D location){
        return new Car(
          PLAYER_WIDTH,
          PLAYER_LENGTH,
          getRandomColor(),
          location,
          0,
          0,
          PLAYER_MAX_SPEED,
          DAMAGE / 2
        );
    }

    /**
     * Makes a fuel can with random size and location.
     * @return The fuel can.
     */
    private FuelCan makeFuelCan() {
        int size = Size.values()[RandNumGen.getInstance().nextInt() % Size.values().length].getValue();
        return makeFuelCan(getRandomLocation(), size);
    }

    /**
     * Makes a fuel can with the given location and size.
     * @param location The location.
     * @param size The size.
     * @return The fuel can.
     */
    private FuelCan makeFuelCan(Location2D location, int size) {
        return new FuelCan(size, Color.RED, location);
    }

    /**
     * Maked and NPC with the given location.
     * @param location The location.
     * @return The NPC
     */
    private NPCar makeNPCar(Location2D location) {
        return new NPCar(
          PLAYER_WIDTH,
          PLAYER_LENGTH,
          getRandomColor(),
          location,
          getRandomSpeed(),
          0,
          PLAYER_MAX_SPEED,
          DAMAGE
        );
    }

    /**
     * Makes an oil slick with the given location.
     * @param location The location.
     * @return The oil slick.
     */
    private OilSlick makeOilSlick(Location2D location) {
        return new OilSlick(getRandomWidth(), getRandomLength(), Color.BLACK, location);
    }

    /**
     * Makes a pylon with the given sequence number and location.
     * @param number The sequence number.
     * @param location The location.
     * @return The pylon.
     */
    private Pylon makePylon(int number, Location2D location) {
        return new Pylon(number, Size.SMALL.value, location, Color.GREEN);
    }
    /*
        End factory methods for the track.
     */

    /**
     * Gets the last pylon reached by the player's car.
     * @return The current progress on the track.
     */
    public int getCurrentPylon() {
        return player().getPylon();
    }

    /**
     * Informs if the game sound is on.
     * @return Is the sound on?
     */
    public boolean isSoundOn() {
        return soundOn;
    }

    /**
     * Sets the sound to be on or off.
     * @param soundOn Is the sound on.
     */
    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
        notifyObservers();
    }

    /**
     * A convenience method to get the player's car with avoiding
     * iterator instantiations.
     * @return The players car.
     */
    private Car player() {
        return player;
    }

    /**
     * Accelerate the player's car.
     */
    public void accelerateCar() {
        player().accelerate();
        notifyObservers();
    }

    /**
     * Changes the strategies of all NPCs in the game world.
     */
    public void changeStrategies() {
        switchStrategies();
    }

    /**
     * Apply the brakes on the player's car.
     */
    public void brakeCar() {
        player().brake();
        notifyObservers();
    }

    /**
     * Sends a steer command to the player's car.
     * @param direction The direction to head.
     */
    public void steerCar(Direction direction) {
        player().steer(direction);
        notifyObservers();
    }

    /**
     * Moves all the movable game objects in the collection.
     * @param elapsedTime The time elapsed since the last move.
     */
    private void moveAll(int elapsedTime) {
        IIterable i = gameObjectCollection.getIterator();
        while (i.hasNext()) {
            AbstractGameObject ago = (AbstractGameObject) i.next();
            if (ago instanceof IMovable)
                ((IMovable) ago).move(elapsedTime);
        }
    }

    /**
     * Checks the play states of the cars in the game world.
     * @throws GameOverException When the game ends.
     * @throws LifeLostException When the player loses a life.
     */
    private void checkCarStates()
    throws GameOverException, LifeLostException {
        checkNPCs();
        checkPlayer();
    }

    /**
     * Checks the player's state.
     * @throws GameOverException
     * @throws LifeLostException
     */
    private void checkPlayer() throws GameOverException, LifeLostException {
        if (player().getPylon() >= PYLONS) // Did they win? If so inform them.
            throw new GameOverException("YOU WON!!");
        // Are they in good condition?
        if (!player().movable()) {
            if (--lives == 0) // Is this their last life?
                throw new GameOverException("YOU LOST!!");
            else { // Reset the game and inform the controller.
                String reason = (player().getFuelLevel() < 0.0) ?
                  "You ran out of gas!" : "You've been damaged to death!";
                resetGame();
                throw new LifeLostException(lives, reason);
            }
        }
    }

    /**
     * Checks the NPCs states.
     * @throws GameOverException
     * @throws LifeLostException
     */
    private void checkNPCs() throws GameOverException, LifeLostException {
        IIterable it = gameObjectCollection.getIterator();
        while (it.hasNext()) {
            AbstractGameObject ago = (AbstractGameObject) it.next();
            if (ago instanceof Car) {
                Car c = (Car) ago;
                if (c.getPylon() == PYLONS) {
                    if (c instanceof NPCar) {
                        if (--lives == 0) // Is this their last life?
                            throw new GameOverException("YOU LOST THE RACE!!");
                        else { // Reset the game and inform the controller.
                            resetGame();
                            throw new LifeLostException(lives, "An opponent won the race!");
                        }
                    }
                }
            }
        }
    }

    /**
     * Handles all the collisions which occurred during the last tick.
     */
    private void handleCollisions() {
        IIterable outer = gameObjectCollection.getIterator();
        IIterable inner;
        ICollider i, o;

        int size = outer.size();
        for (int p = 0; p < size - 1; ++p) {
            o = (ICollider) outer.next();
            inner = gameObjectCollection.getIterator(p + 1);
            while (inner.hasNext()) {
                i = (ICollider) inner.next();
                if (o.collidesWith(i))
                    o.trackCollision(i);
            }
        }
        outer.reset();
        while (outer.hasNext()) {
            o = (ICollider) outer.next();
            o.handleCollisions();
        }
        outer.reset();
        while (outer.hasNext()) {
            ICollider next = (ICollider) outer.next();
            if (next.collided() && next instanceof FuelCan) {
                gameObjectCollection.remove((AbstractGameObject)next);
                addFuelCan();
            }
        }
    }

    /**
     * Adjusts the time for representation and strategy handling.
     */
    private void adjustTime() {
        if (++ticks % 50 == 0) {
            // Tick the clock.
            ++currentGameTime;
        }
        if (ticks % 500 == 0) {
            switchStrategies();
            ticks = 0;
        }
    }

    /**
     * Switches all the NPC's strategies to another randomly selected one.
     */
    private void switchStrategies() {
        IIterable<AbstractGameObject> it = gameObjectCollection.getIterator();
        while (it.hasNext()) {
            AbstractGameObject ago = it.next();
            if (ago instanceof NPCar) {
                ((NPCar) ago).setStrategy(getRandomNPCStrategy((NPCar) ago));
            }
        }
    }

    /**
     * Sets a random npc strategy to an npc.
     * @param npCar The npc to get the strategy.
     * @return The strategy assigned to the npc.
     */
    private NPCStrategy getRandomNPCStrategy(NPCar npCar) {
        switch (Strategy.values()[RandNumGen.getInstance().nextInt() % Strategy.values().length]) {
            case WIN:
                return new NPCWinStrategy(npCar, pylonLocations());
            case DERBY:
                return new NPCDerbyStrategy(npCar, player());
        }
        return null;
    }

    /**
     * Advances the game clock by a single "tick" and updates all game
     * objects.
     * @throws GameOverException
     * @throws LifeLostException
     */
    public void advanceGameTime(int elapsedTime)
    throws GameOverException, LifeLostException {
        moveAll(elapsedTime);
        handleCollisions();
        adjustTime();
        checkCarStates();
        notifyObservers();
    }

    /**
     * Generates new random colors for the objects in the game world.
     */
    public void generateNewColors() {
        IIterable i = gameObjectCollection.getIterator();
        while (i.hasNext()) {
            AbstractGameObject abo = (AbstractGameObject) i.next();
            if (abo instanceof IColorable) {
                ((IColorable) abo).changeColor(getRandomColor());
                setChanged();
            }
        }
        notifyObservers();
    }

    /**
     * Adds pylons to the game world which define the track.
     * @param locations The locations of the pylons.
     */
    private void addPylons(List<Location2D> locations) {
        for (int i = 0; i < locations.size(); ++i)
            gameObjectCollection.add(makePylon(i + 1, locations.get(i)));
        notifyObservers();
    }

    /**
     * Adds a pylon with the given sequence number and location.
     * @param sequenceNumber The pylons number.
     * @param location The pylon's location.
     */
    public void addPylon(int sequenceNumber, Location2D location) {
        gameObjectCollection.add(makePylon(sequenceNumber, location));
        ++PYLONS;
        NPCWinStrategy.resetTrack(pylonLocations());
        notifyObservers();
    }

    /**
     * Adds an oil slick to the game world of a random width, length, and
     * location.
     */
    private void addOilSlicks(int many) {
        for (int i = 0; i < many; ++i) {
            gameObjectCollection.add(
              new OilSlick(getRandomWidth(), getRandomLength(), Color.BLACK, getRandomLocation())
            );
        }
        notifyObservers();
    }

    /**
     * Adds an oil slick with random attributes.
     */
    public void addOilSlick() {
        addOilSlicks(1);
    }

    /**
     * Adds an oil slick with the given location.
     * @param location
     */
    protected void addOilSlick(Location2D location) {
        gameObjectCollection.add(
          makeOilSlick(location)
        );
    }

    /**
     * Adds a fuel can to the game world of a random size and location.
     */
    public void addFuelCan() {
        addFuelCans(1);
    }

    /**
     * Adds a fuel can at the given location and size.
     * @param location The can's location.
     * @param size The can's size.
     */
    public void addFuelCan(Location2D location, int size) {
        gameObjectCollection.add(makeFuelCan(location, size));
        notifyObservers();
    }

    /**
     * Adds 'many' fuel cans with random attributes.
     * @param many The number of fuel cans to add.
     */
    private void addFuelCans(int many) {
        for (int i = 0; i < many; i++) {
            gameObjectCollection.add(makeFuelCan());
        }
        notifyObservers();
    }

    /**
     * Adds 'many' birds with random attributes.
     * @param many The number of birds to add.
     */
    public void addBirds(int many) {
        for (int i = 0; i < many; i++) {
            gameObjectCollection.add(makeBird());
        }
        notifyObservers();
    }

    /**
     * Adds a bird with random attributes.
     */
    private void addBird() {
        addBirds(1);
    }

    /**
     * Handles a mouse click forwarded from the controler.
     * @param me The mouse event.
     */
    public void handleClick(MouseEvent me) {
        IIterable it = gameObjectCollection.getIterator();
        AbstractGameObject ago;
        if (!me.isControlDown())
            deselectAll();
        while (it.hasNext()) {
            ago = (AbstractGameObject) it.next();
            if (ago instanceof ISelectable) {
                ISelectable s = (ISelectable) ago;
                if (s.contains(me.getPoint())) {
                    s.setSelected(true);
                    selected.add(s);
                }
            }
        }
        notifyObservers();
    }

    /**
     * Puts all selected objects in an unselected state.
     */
    public void deselectAll() {
        for (ISelectable s : selected) {
            s.setSelected(false);
        }
        selected.clear();
        notifyObservers();
    }

    /**
     * Gets a reference to the deleteSelected method.
     * @return The reference.
     */
    public Consumer<Void> getDeleteSelectedMethod() { return this::deleteSelected; }

    /**
     * Gets a reference to the addOilSlick method.
     * @return The reference.
     */
    public Consumer<Location2D> getSlickHandler() { return this::addOilSlick; }

    /**
     * Deletes all objects with a seclected state.
     * @param voided Not used.
     */
    private void deleteSelected(Void voided) {
        for (ISelectable s : selected) {
            gameObjectCollection.remove((AbstractGameObject)s);
        }
        NPCWinStrategy.resetTrack(pylonLocations());
        notifyObservers();
    }

    @Override
    public IIterable<IDrawable> getMapViewIterator() {
        return new MapViewIterator(gameObjectCollection.getIterator());
    }

	/*
	 * Begin methods for randomness.
	 * Instead of requiring each game object to have a dependency on the random
	 * number generator I provided methods to get all the random game object
	 * attribute values in the model. 
	 */
    /**
     * Generates a random location for game objects bound by the limits
     * of the game world.
     * @return A randomly generated 2D location.
     */
    private Location2D getRandomLocation() {
        return new Location2D(
          RandNumGen.getInstance().nextInt() % MAX_X,
          RandNumGen.getInstance().nextInt() % MAX_Y
        );
    }

    /**
     * Generates a random speed for movable game objects from a small
     * range.
     * @return A randomly chosen enumerated speed value.
     */
    private int getRandomSpeed() {
        return Speed.values()[RandNumGen.getInstance().nextInt() %
          Speed.values().length].getValue();
    }

    /**
     * Generates a random heading for game objects over a complete
     * circle.
     * @return A random heading.
     */
    private int getRandomHeading() {
        return RandNumGen.getInstance().nextInt() % (359);
    }

    /**
     * Generates a random width for game objects from a small
     * range.
     * @return A randomly chosen enumerated size value.
     */
    private int getRandomWidth() {
        return getRandomSize();
    }

    /**
     * Generates a random length for game objects from a small
     * range.
     * @return A randomly chosen enumerated size value.
     */
    private int getRandomLength() {
        return getRandomSize();
    }

    /**
     * Generates a random size for game objects.
     * @return A randomly chosen enumerated size value.
     */
    private int getRandomSize() {
        return Size.values()[RandNumGen.getInstance().nextInt() %
          Size.values().length].getValue();
    }

    /**
     * Generates a random color for game objects.
     * @return A randomly chosen color.
     */
    private Color getRandomColor() {
        return new Color(RandNumGen.getInstance().nextInt());
    }
    /*
	 * End methods for randomness.
	 */

    /**
     * Resets the game back to the initial state.
     */
    private void resetGame() {
        gameObjectCollection.clear();
        initLayout();
    }

    /**
     * Gets the string representation of the states tracked by the
     * game world.
     * @return The strings.
     */
    public String[] getGameStates() {
        DecimalFormat df = new DecimalFormat("00.00");
        return new String[]{
          "Time: " + currentGameTime,
          "Lives Left: " + lives,
          "Highest Player Pylon: " + player().getPylon(),
          "Player Fuel Remaining: " + df.format(player().getFuelLevel()),
          "Player Damage Level: " + df.format(player().getDamageLevel()),
          "Sound: " + (isSoundOn() ? "On" : "Off")
        };
    }

    /**
     * Comparator to order pylons by sequence number.
     */
    private Comparator<Pylon> pylonComparator = (o1, o2) -> o1.getSequenceNumber() - o2.getSequenceNumber();

    @Override
    public void notifyObservers() {
        Iterator<IObserver> oi = observers();
        while (oi.hasNext()) {
            IObserver o = oi.next();
            if (o instanceof MapView) {
                o.update(new MapViewProxy(this), null);
            } else
                o.update(new ScoreViewProxy(this), null);
        }
    }

    @Override
    public String toString() {
        IIterable i = gameObjectCollection.getIterator();
        StringBuilder sb = new StringBuilder("\n\tGame Map\n");
        while (i.hasNext()) {
            sb.append(i.next());
            sb.append("\n");
        }
        /*gameObjects.forEach(p -> {
            sb.append(p);
            sb.append("\n");
        });*/
        return sb.toString();
    }

    /**
     * A special iterator for the map view that gives only IDrawable objects
     * to protect the game objects the iterator exposes.
     */
    private class MapViewIterator
      implements IIterable<IDrawable> {

        /**
         * The actual iterator from the game collection.
         */
        private IIterable goCollectionIterator;

        /**
         * Constructs the iterator with the actual game collection iterator.
         * @param goCollectionIterator The iterator.
         */
        MapViewIterator(IIterable goCollectionIterator) {
            this.goCollectionIterator = goCollectionIterator;
        }

        @Override
        public boolean hasNext() {
            return goCollectionIterator.hasNext();
        }

        @Override
        public void reset() {
            goCollectionIterator.reset();
        }

        @Override
        public IDrawable next() throws NoSuchElementException {
            return (IDrawable) goCollectionIterator.next();
        }

        @Override
        public int size() {
            return goCollectionIterator.size();
        }

        @Override
        public int position() {
            return goCollectionIterator.position();
        }
    }
}
