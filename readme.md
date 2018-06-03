# LPOORTAL
A 2-player Co-Op based game for desktop with android smartphones as controllers!

Final Project made for the unit course of *Laboratory of Object Oriented Programming* @ FEUP 2018

  - Angelo Teixeira - up201606516 - up201606516@fe.up.pt
  - Henrique Lima - up201606525 - up201606525@fe.up.pt


# Installation Instructions

The game needs two executable files:
  - one **.jar** to run the **desktop** server and display the actual game
  - one **.apk** that should be installed on **two android devices** that have **accelerometer** and **gyroscope** support

After downloading both of the files, you should install the **.apk** on the android devices and move the **.jar** onto the machine you will be using as desktop. You should also have java run-time environment installed on that machine.

# Run and play the game

To run and play the game, please go through the following steps (see screenshots below):
  - Make sure the android devices and the desktop are all on the same LAN (Local Area Network).
  - Launch the **.jar** file on the desktop (using the command java -jar [name_of_the_file]) and a window will appear. There, you will see the instructions for the gameplay itself and a gamecode for you to insert on each of the client devices' installed app.
  - **After both clients have connected**, the game (shown on the desktop window) will move on to a **player customization screen**, where each of the players/clients **can change their color** (beware as the player colors must be different or the game will not move on to the actual gameplay), **their name** and **the skin for their character** respectively.
  - When a player has finished customizing their character, they can now **press the "ready" tile**, so the game knows it can move on to the next stage.
  - When both players are ready, the desktop application generates a level and picks a player for **drawing** and another one for **controlling the stickman on screen**. The stickman will be colored and have the skin according to the choices on the previous stage, so each player can identify their job with ease.
  - The **drawer** can now control the pen by tilting their phone and pointing it directly at the screen (note that it can be calibrated at any point by dragging it against the borders of the window). The pen has a certain **amount of ink** that will decrease proportionally to the length of the lines drawn. To gain ink, the **stickman must pick up ink jars**. You can see how much ink you have left by looking at the bar on the left of the screen (the bar will have the color of the drawer).
  - To pass a level, the players must work together so **the stickman goes through the portal**. Each level will have the ink jars on different positions. To gain a point, the players have to pickup all ink jars before passing onto the next level.
  - The game **will end when the stickman touches the bottom edge of the screen**. If at any point the players get stuck due to the lack of ink, the stickman can always kill itself by jumping down to the bottom of the screen.

# Game screenshots

## Welcome Screen
![Alt text](Screenshots/screen_1.png?raw=true)

## Player Customization Screen
![Alt text](Screenshots/screen_2.png?raw=true)

## Level Transition Screen
![Alt text](Screenshots/screen_3.png?raw=true)

## Level Screen
![Alt text](Screenshots/screen_4.png?raw=true)

### (Next level)
![Alt text](Screenshots/screen_5.png?raw=true)

## Game Over
![Alt text](Screenshots/screen_6.png?raw=true)


# Design Patterns

**MVC - Model View Controller** Used to separate the concerns of the state of the elements (Model), their evolution over time (Controller) and their representation to the user (View)

**Singleton** on the LpoortalGame (Main Game Class), GameModel, GameView, GameController and NetworkManager classes, because at every moment, there will only be one instance of them, and therefore, can be accessed more easily.

**Observer** On the controller, the GameController itself will be a listener (ContactListener) for collisions of the physical world (Embedded in the LibGDX library)

**Flyweight** Used in the View Package (Desktop) [ViewFactory class] to keep a cache of views being used, and allow for their reutilization

**State** Both desktop and mobile apps will have a state, that can be changed based on received events (i.e. when the server sends the message to the client so that it changes from PlayerCustomization State to Playing State (Drawing/Moving controller))


# Editing the code

To edit/view the code, import **"Lpoortal_Game_Server"** folder on Eclipse and open **"Lpoortal_Game_Client"** folder on Android Studio


# Time invested on the project

Estimating the time spent on the project, including classes, weekly work and some extra time in the beggining of the project and near the delivery date, we concluded that about **150 hours were spent in total** (around 75 hours each). A big part of the progress was achieved through pair programming, having the remaining code been developed by both parties upon agreement on the distribution of tasks.

# Major difficulties

During the development period, we found particularly hard to choose the appropriate design patterns, as well as anylise their effectiveness for each task. We also felt challenged when choosing the selected MVC arquitecture, as there exist many different implementations available and we wanted to pick the one that best suited the project. In addition, we spent some time working on networking, as to decrease lag and lattency, having to compromise other intentions on our part to make for a better user experience. Finally, the remaining difficulties were based on libGDX's implementation of certain features and its behaviour.

