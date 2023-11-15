# Rocket Obstacle Course 🚀


### *1. How the game works:*


Objective of the game:
* Rocket has to reach the yellow portal to complete the level
* There are 3 levels in total. If all levels are completed, player wins


Key controls:
* W key moves the rocket in the direction it is facing
* A key rotates the rocket anti-clockwise
* D key rotates the rocket clockwise


Fuel:
* Fuel tank depletes as W key is pressed
* If fuel tank is empty, game ends


Crashing:
* If rocket crashes into the blocks, game ends and player restarts at level 1
* If rocket crashes into the ground, game ends and player restarts at level 1


The top left of the screen indicates the level that the player is currently at.




### *2. Classes:*


Rocket:
* Stores the x and y coordinates of the instance of Rocket class
* Gets the rectangle boundaries of the instance of Rocket class
* Gets the image file of rocket
* Method for moving the rocket based on key presses


Fuel:
* Gets the image file of gas tank
* Decreases width of fuel bar as the W key is pressed
* Method fuelDrained() detects if fuel has run out


Base:
* Gets the image file of yellow portal (the base)


Block:
* Stores x and y coordinates of the instance of Block class
* Represents the white blocks


Handler:
* Handling blocks:
   * Reads the level’s image file to detect where blocks should be placed (described in detail in “Flourishes section)
   * Draws the individual blocks in the level
   * Gets the rectangle boundaries of all the blocks in the level
* Handling the base:
   * Reads the level’s image file to detect where the yellow portal base should be placed (described in detail in “Flourishes” section)
   * Gets the x and y coordinates of the base
* Switching between levels:
   * Gets the level number from the instance of Canvas class
   * Uses this level number to determine which image file to read (level1.png, level2.png or level3.png)
   * This determines where the images of the blocks and the base are loaded


Canvas:
* Canvas for painting every single object and element in the window
* Controls the game flow


App:
* Constructor of app creates a new JFrame and Canvas
* Contains the main method where an instance of App is created




### *3. Flourishes:*


Pixel block design for every level:
* I designed the “bases” for all my levels on Paint.net. (refer to level1.png for an example)
   * White spaces represent the fancy white pixel blocks
   * Blue spaces represent the yellow portal base
* These files are loaded into my Handler class and saved into a LinkedList.
* I looped through all the pixels in each file, and checked if their RGB values were white or blue.
   * If the RGB value is white, a white space is detected, so I save the coordinate to load the image of the fancy white pixel block at that location.
   * If the RGB value is blue, a blue space is detected, so I save the coordinate to load the image of the yellow portal base at that location.
