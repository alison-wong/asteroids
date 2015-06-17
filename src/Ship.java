
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.geom.Point2D;

public class Ship extends AsteroidsGameShape{



	static final int POLYGON_HEIGHT = 15;
	private static final int POLYGON_WIDTH = 10;

	//Maximum Speed
	private static double MaxSpeed = 30;	
		
		
	//CONSTRUCTOR
	public Ship(int x, int y,double thespeed, double rotation) {
		
		super(x,y,thespeed,rotation);

		//adding points for the stuffens.
		
		this.addPoint(-5, 10);
		this.addPoint(0,-10);
		this.addPoint(5,10);
		/*
		this.addPoint(0, -POLYGON_HEIGHT);
		this.addPoint(-POLYGON_WIDTH , POLYGON_HEIGHT);
		this.addPoint(POLYGON_WIDTH, POLYGON_HEIGHT);
		*/
		}
	
	/* MOVEMENT FUNCTIONS
	 * -------------------------------------------------------------
	 */
	
	/*INCREASES SHIP SPEED
	 *	takes in no parameters, does not return anything.
	 * Called via KeyEvent in Asteroids Game class
	 */
	public void increaseSpeed()
	{if (speed == MaxSpeed)
	 {speed = MaxSpeed;}
	 else
	 {speed = speed + 1;}}

	
	/*Decreases speed of ship
	 * 
	 *  Takes in no parameters, does not return anything.
	 * Called via KeyEvent in AsteroidsGameClass
	 */
	public void decreaseSpeed()
	{ if (speed == 0)
	  {speed = 0;}
	  else
	 {speed = speed - 1;}
	}
	
	

	/**Rotates Ship Right (Clockwise)
	 * 
	 * 	@param: Takes in no parameters, returns nothing.
	 * 	Called via KeyEvent in AsteroidsGame Class
	 */
	public void rotateRight()
	{rotation = rotation + rotationspeed;}

	/**Rotates Ship Left (CounterClockwise)
	 */
	public void rotateLeft()
	{rotation = rotation - rotationspeed;}
	
	
	//----------------------------------------------
	
	//-------ARRAYCOLLISION
		//Player collides with an ThingsThatAreNotShips inside a array.
		//This is only for the ship.  Because the ship is the player.
		/*If Player collides with any of the enemies inside this array, player is dead.*/
		
		public void collidesWithArraysOfEnemies(ThingsThatAreNotShips[] ThingsThatAreNotShipsArray)
		{
			if (dead == true)
			{}
			else{
			int i = 0;
			while (i < ThingsThatAreNotShipsArray.length)
			{
			
				if (this.collidesWith(ThingsThatAreNotShipsArray[i]) == true)
					{
						dead = true;
					}
				
				i++;
			}
			}
			
			
		}
	/*GET GUN POINT
	 * 
	 */

		public Point2D.Double getGunPoint() 
		{ return TransformCalculator.transform(new Point2D.Double(0, -10), left, top, rotation); }


		
		
	
	
}
