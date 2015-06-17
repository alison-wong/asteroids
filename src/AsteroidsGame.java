import java.awt.BorderLayout;
import java.util.Random;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AsteroidsGame extends Animation implements KeyListener {

	/*
	 * VARIABLES
	 */
	
	//Width of Window
	public static final int FRAME_WIDTH = 1300;
	
	//Height of Window
	public static final int FRAME_HEIGHT = 800;
	
	
	//Random Object for randomizing numbers
	private Random random = new Random();
	
	//Ship, controlled by the player of the game
	private Ship player;	
	
	//Array of Asteroids (the obstacles)
	public static Asteroid[] AsteroidsArray;
	
	//Amount of Asteroids per level
	private int NumberofAsteroids; 
	
	//Array of Bullets
	private Bullet[] BulletArray = new Bullet[1];  //You can only shoot one @ a time.
	
	/*
	 * VARIABLES END
	 */
	
	
	
	/*ASTEROIDS GAME CONSTRUCTOR
	 * 
	 * 	Constructs the class AsteroidsGame	
	 * 
	 * 		1. Sets variables
	 * 		2. Creates the Game objects
	 * 		3. Sets up the Key Listener
	 */
	public AsteroidsGame()
	{
	//Set variables
		NumberofAsteroids = 20; //Level harder = 20 Asteroids
		
	//Create new AsteroidGameShapes
		
		//Make the ship
	player = new Ship (
						/* x = */ FRAME_WIDTH/2,
						/* y = */ FRAME_WIDTH/2,
						/* starting speed = */ 1 /*pixels per 30 seconds*/,
						/* starting rotation = */ 0
						); 
	
	//Initialize Asteroids Array
	AsteroidsArray = new Asteroid[NumberofAsteroids];
	
	//Initialize BulletArray
	BulletArray = new Bullet[100];

	//Fill Asteroids Array
	ThingsThatAreNotShips.fillThingsThatAreNotShipsArray (AsteroidsArray,new Asteroid(1,1,1,1));

	//Key Listener Setup
	this.setFocusable(true);
	this.addKeyListener(this);


	
	}	
	
	
	
/*	VARIOUS HELPER FUNCTIONS FOR DOING THINGS WHILE CHECKING DEADNESS,& MOVING AROUND/PAINTING THINGS IN ARRAYS
 * ===================================================================================================================
 */
	/*
	 * MOVES THINGS THAT ARE NOT SHIPS WHICH ARE IN AN ARRAY
	 * 
	 * @param ThingsThatAreNotShips[] , any array of ThingsThatAreNotShips.
	 */
	private void moveThingsThatAreNotShips(ThingsThatAreNotShips[] ThingsThatAreNotShipsArray)
	{
		int i = 0;
		while (i < ThingsThatAreNotShipsArray.length)
		{
			move(ThingsThatAreNotShipsArray[i]);
			
			i++;
		}
	}
	
	
	/*
	 * PAINT ThingsThatAreNotShips (CHECKS MORTALITY)
	 * 	
	 */
	public void paintThingsThatAreNotShipsDead(ThingsThatAreNotShips[] ThingsThatAreNotShipsArray, Graphics g)
	{
		int i = 0;
		while (i < ThingsThatAreNotShipsArray.length)
		{
			if (ThingsThatAreNotShipsArray[i].dead == false) 
			{
				ThingsThatAreNotShipsArray[i].paint(g);
			}
			else
			{}
			
			i++;
		}
	}
	
	/*
	 * PAINT PLAYER (DETECT DEADNESS)
	 * 
	 * 	Detects whether player is dead.  If not, paints it. If so, does not paint it.
	 * 	>Called in PaintComponent, purpose is so that player does not get painted if dead.
	 * 
	 * @param AsteroidsGameShape - must be the variable player (type Ship).
	 * @param Graphics - the Graphics object used in Paint Component.
	 * 
	 * returns nothing
	 */
	public void paintThing(AsteroidsGameShape a, Graphics g)
	{
		if (a.dead == true)
		{
		
		}
		else 
		{
			a.paint(g);
		}
		
	}
	
	
	/*
	 * MOVE FUNCTION (FORDEADNESS)
	 * 	Detects if an AsteroidsGameShape is dead, & moves it if it still living.
	 * 	Does not move it if it is dead.
	 * 
	 *  >Called in NextFrame
	 * 	
	 * @param AsteroidsGameShape any Asteroids Game Shape can be used, as long as it exists.
	 * 
	 * returns nothing
	 */
	
	public void move(AsteroidsGameShape a)
	{
		if (a.dead == false)
		{
			a.move();
		}
		else
		{}
	}
	//----------------------------------------------------------------------------------


					/*PAINT COMPONENT*/
	//-------------------------------------------
	/*
	 * Creates Graphics of Asteroids Game.
	 * Called from JComponent superclass automatically.
	 * 	1. Makes the black background.
	 * 	2. Paints the ship.
	 * 	3. Paints the Asteroids.
	 * 
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * @param g, the Graphics object.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		
		paintThingsThatAreNotShipsDead(AsteroidsArray,g);
		paintThing(player,g);	
		paintThing(BulletArray[0],g);
		
		
		}
	
	
	
	
	
/*KEY PRESS FUNCTIONS
 * 
 * (functions that respond to the keyboard)
 * 
 * (non-Javadoc)
 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
 */
//----------------------------------------------------------------------
	public void keyPressed(KeyEvent arg0) {
		
	//Increase speed of ship when up arrow key is pressed.
	if (arg0.getKeyCode() == 38)
	{
		player.increaseSpeed();
	}
	
	//Rotate ship left when left arrow key is pressed.
	if (arg0.getKeyCode() == 37)
	{
		player.rotateLeft();
	}

	//Rotate ship right when right arrow key is pressed.
	if (arg0.getKeyCode() == 39)
	{
		player.rotateRight();
	}
	

	//Decrease speed of ship when down arrow key is pressed.
	if (arg0.getKeyCode() == 40)
	{ 
		player.decreaseSpeed();
		}

	
	//Shoot a Bullet when the spacebar is pressed.
		
	if (arg0.getKeyCode() == KeyEvent.VK_SPACE)
	{	
		if (player.dead == true)
		{}
		else{
			//creates a bullet & adds it to the array.
			//creates a bullet & adds it to the vector BulletVector.
			//Convert vector into BulletArray ("updating" BulletArray).  
				//This is so that infinite amount of bullets can be shot, and the bullets can be called.
			BulletArray[0] = ( new Bullet(
												/*x & y is @ tip of ship */	
												/*x = */				player.getGunPoint().getX(),
												/*y = */				player.getGunPoint().getY(),
												/*speed = */ 50,
												/*rotation = ship's rotation*/ player.rotation/Math.PI));
			
		}	
				
	}
	
	
	
	}

	/*No action is initialized when a typed key is released. 
	 * 
	 * Required function for implementing KeyListener.
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */

	public void keyReleased(KeyEvent arg0) {}
	

	/*Required function for implementing KeyListener.
	 * 
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent arg0) {}
	
//------------------------------------------------
	
	

	/*NEXT FRAME
	 * 
	 * (non-Javadoc)
	 * @see Animation#nextFrame()
	 */
	protected void nextFrame() {
		
		//player detects Collisions of Objects
		player.collidesWithArraysOfEnemies(AsteroidsArray);

		//move player, wraparound.
		move(player);
		
		//move asteroids, wraparound
		moveThingsThatAreNotShips(AsteroidsArray);
		
		/* moves BulletArray & detects Collisions
		 * 
		 * Until the ship shoots a bullet, the BulletArray will be empty,
		 * therefore while the BulletArray is empty, try-catch for NullPointerException is necessary.
		 */
		try
		{moveThingsThatAreNotShips(BulletArray);}
		catch(NullPointerException e)
		{}
				
	}
	

	
	
	
	
	/*SET UP FRAME
	 * 
	 */
	public static void setUpFrame()
	{
		/*Create JFrame (The Window)*/
		final JFrame f = new JFrame();
		f.setSize(FRAME_WIDTH,FRAME_HEIGHT); //set size
		
		//Add Asteroids Game to the Window
		AsteroidsGame A = new AsteroidsGame(); 
		f.add(A, BorderLayout.CENTER); 
		
		//set visible
		f.setVisible(true);

		
		//starts the animation of the game
		A.start();
		
	}
	
	/*MAIN FUNCTION
	 * 
	 */
	public static void main(String[] args)
	{
		
		setUpFrame();
		
	}
	
	

	
	

/*
 * CLOSE ENVELOPE
 */
}
