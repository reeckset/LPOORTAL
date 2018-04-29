# Game Mockups

The game will have three main screens, as presented below.

## Welcome Screen

![Alt text](Mockups/screen0.jpg?raw=true)

## Player Customization Screen
![Alt text](Mockups/screen1.jpg?raw=true)

## Level Screen
![Alt text](Mockups/screen2.jpg?raw=true)

![Alt text](Mockups/screen3.jpg?raw=true)

## Gyroscope Controller Explanation
![Alt text](Mockups/screen4.jpg?raw=true)

# Architecture Design

## Design Patterns

**MVC - Model View Controller** Used to separate the concerns of the state of the elements (Model), their evolution over time (Controller) and their representation to the user (View)

**Singleton** on the LpoortalGame (Main Game Class), GameModel, GameView, GameController and NetworkManager classes, because at every moment, there will only be one instance of them, and therefore, can be accessed more easily.

**Observer** On the controller, the GameController itself will be a listener (ContactListener) for collisions of the physical world (Embedded in the LibGDX library)

**Flyweight** Used in the View Package (Desktop) [ViewFactory class] to keep a cache of views being used, and allow for their reutilization

**State** Both desktop and mobile apps will have a state, that can be changed based on received events (i.e. when the server sends the message to the client so that it changes from PlayerCustomization State to Playing State (Drawing/Moving controller))



Desktop
======

## Packages Diagram
![Alt text](UMLDiagrams/Lpoortal_Server_PackageDiagram.png?raw=true)

## Model Diagram
![Alt text](UMLDiagrams/Lpoortal_Server_ModelDiagram.png?raw=true)

## View Diagram
![Alt text](UMLDiagrams/Lpoortal_Server_ViewDiagram.png?raw=true)

## Controller Diagram
![Alt text](UMLDiagrams/Lpoortal_Server_ControllerDiagram.png?raw=true)

## Network Diagram
![Alt text](UMLDiagrams/Lpoortal_Server_NetworkDiagram.png?raw=true)

Mobile
======

## Class Diagram
![Alt text](UMLDiagrams/Lpoortal_Client_Diagram.png?raw=true)


# Test Design

To test the game, we will focus on the Models, as well as the network packages.

We can test if the stickman moves to the desired position, if he doesn't walk out of the map, and if he collides with the lines/obstacles. We can also test if the level progresses after we crosses the portal

We can also test the drawings, by giving a start and end position, and testing the actual line that is produced.

To test the cursor, we can check that it moves according to the given parameters, and that it stays within bounds.

To test the network package, we can send malformed packets and assure that it deals correctly with them.

