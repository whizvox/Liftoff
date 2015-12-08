## Liftoff Github Repository

Welcome one and all! Feel free to submit bug reports, suggestions, and pull requests to contribute to the project!

This is a Spigot/Bukkit plugin that allows for the creation of "teleportation elevators". Essentially, you press a button and up or down you go.

### Building

To build an elevator system, you need the following structure:

![Liftoff Structure](http://i.imgur.com/qSzdwag.png)

The sign requires for the **first line** to be `[Elevator]`. Anything else can be written on the other lines. And stone buttons must be placed directly above and/or below the sign. Of course, you will need several of these stacked directly on top of each other:

![Stacked](http://i.imgur.com/8lPbltS.png)

Pressing the above button will check for another elevator-marked sign above the button, and pressing the below button will do the same for below it.

### Configuration

There are three changeable configuration options:

* `signRange`: How far elevator-marked signs will be checked.
* `solidGroundRange`: How far below the destination sign solid ground will be checked.
* `signColoring`: If a placed elevator sign will be recolored to show validity.

### Permissions

* `liftoff.info`: Ability to use the /liftoff command.
* `liftoff.place`: Ability to place an elevator sign.
* `liftoff.use`: Ability to use elevators.


### Command

* `/liftoff version`: Gives version information and relevant links.
* `/liftoff help`: Gives a short description and links back to here.
