Robert Wilk
CSc 133
April 21, 2015
README.txt

Pylon Racer - v0.3

Strategies:
    The requirements ssy the 'Change Strategies' button should be removed and the switching should happen automatically
    but did not specifically mention that the binding to the space bar should be removed. I chose not to remove it.

Pylon additions and removals:
    Adding or removing pylons can break the track if done haphazardly. For example, adding pylon 99 to a track of six
    results in a race which cannot end with a winner.

NPCs:
    To make the game a bit more unpredictable and challenging the NPCs choose random strategies as opposed to switch to
    the one they aren't using on ten second intervals. It is therefore possible that an NPC can receive the same
    strategy.

    NPCs can win the game. If they complete the track before you, a life is lost and the game resets.

Sound:
    At the last moment I realized the sounds weren't as loud as I'd like them to be. I created the sounds on my
    desktop which has studio monitors and they sounded plenty loud. However, when I moved the final sounds into
    the player on my laptop it needed to be turned all the way up for them to be reasonably loud. I suppose the
    loudness will depend on the running machine. There's an additional feature to aid in hearing the different sounds
    required documented below.

    There is an additional 'Music' check box added to the 'File' menu to turn the background music off while still
    having the sound effects on. Otherwise the sound operates per the requirements.

    Each collision combination emits sound; not just ones involving the player. For example, a collision of two NPCs
    emits the same sound as a player/NPC collision; as does an NPC / Bird or FuelCan collision.