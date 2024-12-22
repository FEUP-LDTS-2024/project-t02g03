<h1 align="center"> LDTS_T02G03 - Jump King </h1>

<p align="center">
    <img src="resources/images/Jump King Title Logo.png" alt="logo">
</p>

## GAME DESCRIPTION
<p>Jump King is a 2D platformer game where the main objective is to reach the top of the map in the fewest jumps possible. However, if you make a mistake, you risk falling and losing a significant part of your progress.
</p>

<p>This game was developed for the hardcore players that are patient, 
precise and resilient. It has simple controls, no checkpoints and beautiful pixel art visuals.
</p>
<p>We hope you enjoy this challenging journey! Please try not to rage too much :)</p>

><p align="center">
>This project was developed by : <a href="https://github.com/andre-cotrim">André Cotrim</a> (up202305592@fe.up.pt), <a href="https://github.com/H-Aze2005">Hugo Azevedo</a> (up202305965@fe.edu.up.pt) and <a href="https://github.com/andre-cotrim">Joana Carvalhal</a> (up202306568@fe.up.pt)
></p>

## Table of Contents

- [List of Features](#list-of-features)
- [Notes About Features](#notes-about-features)
- [Game Controls](#game-controls)
- [Game Screenshots and Mockups](#game-screenshots-and-mockups)
- [General Structure](#general-structure)
- [Code Design](#design)
  - [Model-View-Controller](#code-structure)
  - [Game Loop](#game-loop)
  - [State Pattern](#multiple-game-states)
  - [Adapter Pattern](#adapter-pattern)
  - [Builder Pattern](#builder-pattern)
  - [Factory Pattern](#factory-pattern)
- [Code Smells](#known-code-smells)
- [Code Testing](#testing)
- [Self Evaluation](#self-evaluation)


## LIST OF FEATURES
- **Main Menu** - Simple menu that prompts the user to start or quit the game.
- **Pause Menu** - A menu that can be accessed by pressing the `p` key during gameplay. The user can choose to resume the game or exit game.
- **Map Loader** - Reads and loads a map from a TXT file.
- **Sprite Image Loader** - A class that processes PNG files into the game as sprite by drawing the images pixel by pixel using Lanterna. This is used by the Map Loader to load in the background image, as well as for loading the player character.
- **Sound Player** - A class that plays background music for the game.
- **Player Mobility** - The player character can move left, right jump and fall. The player can also jump higher by letting leaving larger intervals between inputs after the jump is primed. The player can also jump left or right by inputting the respective direction while preparing to jump, this will result in movemnt in the form of an arc.
- **Finish Condition** - When the player reaches the `Princess` at the top of the map, the game ends.
- **Credits** - When finishing the game, either by quitting or reaching the `Princess` the user is presented with an ending screen, where he can visualize the number of jumps and the total time of taken. Once done, the user can return to the main menu using the `q` button.

## Notes About Features
Initially, we aimed to expand the player's movement options through the implementation of a rebounding mechanic, where, when the player would collide with a wall he would bounce back and fall down.
However, as we progressed through the development of the game and as time passed, we ended up abandoning this feature in favor of allowing for other inputs to be processed while the `King` is falling including jumping. As a result, this initialially planned feature was discarded and we have no plans of implementing it in the future as we're very satisfied with the result of our efforts.

## GAME CONTROLS
- **Movement** - The player can move left and right using the `←` and `→` keys, respectively.
- **Jump** - The player can jump using the `↑` key. After paressing it once the `King` will prepare to jump, the longer the key is left, the higher the player will jump. The `King`jumps after a second key input is placed, jumping either upwards, towards the left or towards the right.
- **Pause** - The player can pause the game by pressing the `p` key. This will bring up the `pause menu`, where the player can choose to resume the game, or exit the application.
- **Quit** - The player can quit the game at any time by pressing the `q` key. This will bring up the `credits`, which will then lead to the `main menu`, where the player can choose to start a new game or quit the application.

## GAME SCREENSHOTS AND MOCKUPS
<h4 align="center">
  King Character Design
</h4>
<p align="center">
    <img src="resources/mockups/king.gif" alt="starting point">
</p>

<h4 align="center">
  Sprite Sheet
</h4>
<p align="center">
    <img src="resources/mockups/Jump King pixel sheet.png" alt="Sprite Sheet">
</p>

<h4 align="center">
  Start Menu
</h4>
<p align="center">
    <img src="resources/screenshots/main-menu.png" alt="main menu">
</p>

<h4 align="center">
  Credits
</h4>
<p align="center">
    <img src="resources/screenshots/credits.png" alt="main menu">
</p>

<h4 align="center">
  Level and Gameplay Showcase
</h4>
<p align="center">
    <img src="resources/screenshots/level-showcase-1.gif" alt="level showcase 1">
</p>
<p align="center">
    <img src="resources/screenshots/level-showcase-2.gif" alt="level showcase 2">
</p>
<p align="center">
    <img src="resources/screenshots/level-showcase-3.gif" alt="level showcase 3">
</p>

<h4 align="center">
  Map Preview
</h4>
<p align="center">
    <img src="resources/mockups/Redcrown_woods.png" alt="whole map">
</p>

## General Structure
<p align="center">
    <img src="resources/uml/Structure%20UML.png" alt="UML">
</p>

## Design Patterns and Architecure
- **MVC** - The game was developed using the Model-View-Controller design architecture. The Model is responsible for the game logic, the View is responsible for the game visuals, and the Controller is responsible for the game inputs.
    - **Usage**: `Application`, `KingController`, `SceneController`, `GameViewer`, `Scene`, `King` and more.
    - **Features**: Separates the application into three interconnected components. This separation helps manage complex applications by dividing the responsibilities.
    - **Consequences**: Can lead to a more complex code structure. It requires careful planning and understanding of the pattern to implement correctly.

<p align="center">
    <img src="resources/uml/MVC Diagram.png" alt="MVC">
</p>

- **Builder Pattern**:
  - **Usage**: `SceneBuilder` class.
  - **Features**: Provides a way to construct a complex object step by step. It allows for more readable and maintainable code when creating objects with many parameters.
  - **Consequences**: Can increase the complexity of the codebase. If not used properly, it can lead to an over-engineered solution.
- **Factory Pattern**:
  - **Usage**: `LanternaScreenCreator` class.
  - **Features**: Defines an interface for creating an object but lets subclasses alter the type of objects that will be created.
  - **Consequences**: Adds an extra layer of abstraction, which can make the code harder to understand. However, it provides flexibility in terms of object creation.
- **State Pattern**:
  - **Usage**: `State` and its subclasses like `GameState`.
  - **Features**: Allows an object to alter its behavior when its internal state changes. The object will appear to change its class.
  - **Consequences**: Can lead to a large number of classes and increased complexity. However, it makes the code more flexible and easier to maintain.

<p align="center">
    <img src="resources/uml/State Diagram.png" alt="Design Patterns">
</p>

<p>
Here is the state machine for the game:
</p>
<p align="center">
    <img src="resources/uml/Game State Machine.png" alt="State Machine">
</p>

### Problems/Features and Consequences:
- **MVC Pattern**: It helps in organizing the code and separating concerns, making the application easier to manage and scale. However, it can lead to a more complex codebase and requires a good understanding of the pattern to implement effectively.
- **Builder Pattern**: It makes the construction of complex objects more manageable and readable. However, it can add unnecessary complexity if the objects being constructed are simple.
- **Factory Pattern**: It provides flexibility in object creation and decouples the client code from the concrete classes. However, it can make the code harder to follow due to the added layer of abstraction.
- **State Pattern**: It makes the code more flexible and easier to maintain by encapsulating state-specific behavior. However, it can lead to an increase in the number of classes and overall complexity.

## Known Code Smells

## Testing
- **Unit Testing** - We used JUnit to test the individual components of the game, such as the collision logic, player movement, and map loading. This helped ensure that each component worked as expected in isolation.
- **Mock Testing** - We used Mockito to mock dependencies in our tests, such as the Lanterna library. This allowed us to test our code without relying on external dependencies.
- **Test Coverage** - We aimed to achieve a high test coverage to ensure that our code was well-tested and reliable.
- **Mutation Testing** - We used PITest to perform mutation testing on our code. This helped us identify areas of our code that were not adequately tested and improve our test coverage.
  - Test results:
<p>
    <img src="resources/tests/Tests Pass Rate.png" alt="Tests">
</p>
<p>
  <img src="resources/tests/Test Coverage.png" alt="Tests">
</p>

>## Self Evaluation
 >- **André Cortim:** 30%
 >- **Hugo Azevedo:** 40%
 >- **Joana Carvalhal:** 30%
