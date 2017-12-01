Project 3: Frogger
Brandon Toney
12 November 2013

Description: Allows a user to play the game Frogger. 

Rules:
	A player starts with three lives and must navigate their frog across a busy
	street and a river with various obstacles. If the user is hit by a car or
	falls into the river their frog will die and they will lose a life. On the
	other side of the river are five goal markers. Once a frog reaches the goal
	another frog will spawn at the bottom of the screen and that frog must now
	navigate to another goal marker. A frog will die if the goal marker is
	inhabited by another frog or if a crocodile appears at the goal's location.
	A frog can also die if it is carried offscreen by a moving object, or if the
	current object the turtle is on submerges. The game ends when all five goals
	have been reached or the user loses all three of their lives.

Controls:
	Up Arrow: Moves the frog forward (NORTH)
	Down Arrow: Moves the frog backward (SOUTH)
	Left Arrow: Moves the frog to the left (WEST)
	Right Arrow: Moves the frog to the right (EAST)

	Start: Starts a new game
	Pause: Pause/unpause game
	Quit: Closes the window and exits game

Compile:
	javac *.java

Run:
	java Frogger

Test:
	Everything mentioned in the project handout should work as expected.
		- Frog dies when turtle submerges
		- Frog dies when carried off screen
		- Frog dies if it misses the goal.
		- etc.

	If the current stage seems too difficult, you can enter "God Mode" by pressing the
	'g' key. In this mode you will not be affected by water or obstacles that
	move across the screen, but you will still lose lives if you attempt to
	enter a goal that is already occupied. 
