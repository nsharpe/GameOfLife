# Cellular Automata Project #

## Introduction ##
This project was started with the intention of being an example of Conway's Game of Life.  It was designed with the following goal in mind.

* Practice development using streams which were introduced in Java 8
* To support games with a variety of dimensions
* To support games with a unique grid layout
* To allow the games to be visualised through OpenCV

It is intended for educational purposes only.

## Requirments ##
* Java 8 installed
* Gradle
* An instance of opencv-3.0 jar

## How to install ##
In build.gradle replace the following with your own instance of Open CV 3.0 jar.  
```
/Users/neilsharpe/OpenCv/opencv-3.0.0/build/bin/opencv-300.jar
```
 
## Definitions ##
* Game
.. * A collection of rules where a given cell is born, lives, and dies based on a set of rules.  The rules are usually specific to a cells given neighbor.
* Cell
.. * An entity within the game that will die of loneliness if there are not enough neighbors, or die from overcrowding.
* Position
.. * A point in the game where a cell is considered to alive.  This distinction is made so that the program will only take into consideration of living cells, which allows for a theoretically infinite map.

## Running the game ##
Games are defined via a [Step Rules](src/main/java/org/neil/game/StepRule.java)

A game is multiple iterations of a given step rule.