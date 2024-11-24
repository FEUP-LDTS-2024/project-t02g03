<h1 align="center"> LDTS_T02G03 - Jump King </h1>

##Game Description
<p>Jump King is a 2D platformer game where the main objective is to reach the top of the map. However, if you make a mistake, you risk falling and losing a significant part of your progress.
</p>

<p>This game was developed for the hardcore players that are patient, 
precise and resilient. It has simple controls, no checkpoints and beautiful pixel art visuals.
</p>
<p>We hope you enjoy this challenging journey! Please try not to rage too much :)</p>

<p>This project was developed by : <a href="https://github.com/andre-cotrim">André Cotrim</a> (up202305592@fe.up.pt), <a href="https://github.com/H-Aze2005">Hugo Azevedo</a> (up202305965@fe.edu.up.pt) and <a href="https://github.com/andre-cotrim">Joana Carvalhal</a> (up202306568@fe.up.pt) </p>




## List of Features
- **Main Menu** - Simple menu that prompts the user to start the game or exit the game.
- **Pause Menu** - A menu that can be accessed by pressing the `ESC` key during gameplay. The user can choose to resume the game, restart the game, or exit to the main menu.
- **Map Loader** - Loads the map from a text file.
- **Sprite Image Loader** - A class that processes PNG files into the game as sprite by drawing the images pixel by pixel using Lanterna. This is used by the Map Loader to load in the background image, as well as for loading the player character.
- **Sound Player** - A class that plays sound effects in the game.
- **Player Mobility** - The player character can move left and right, as well as jump. The player can also jump higher by holding the jump button for longer. The player can also jump left or right by inputting the respective direction while preparing to jump, this will result in movemnt in the form of an arc.
- **Collision Logic** - The player character can collide with the map, and will stop moving when colliding with a floor, and rebound when hitting a wall.
- **Finish Condition** - When the player reaches the "Princess" at the top of the map, the game ends.
- **Credits** - when finishing the game, the user is presented with an ending screen, where he can visualize the number of jumps and the total time of gameplay. Once done, the user can return to the main menu using the Escape button.

## GAME SCREENSHOTS AND MOCKUPS

## UML CLASS STRUCTURES AND OVERVIEW