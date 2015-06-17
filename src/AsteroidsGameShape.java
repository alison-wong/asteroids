import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

	/* Takes in coordinates for the object & places it there.
	 * 
	 * Sets rotation speed.
	 */
	public class AsteroidsGameShape extends Polygon implements Shape{
		
		
		//Speed of object
		protected double speed;
		
		
		//Speed at which game shape will rotate
		protected double rotationspeed;
		
		protected double left; 
		protected double top;
		//Amount game shape will rotate
		protected double rotation;
		
		protected boolean dead; //detects whether has been destroyed or not.

		
		protected Graphics g;
	 	
		/* ASTEROID GAME SHAPE CONSTRUCTOR
		 * 	constructs a new Asteroid Game Shape
		 * 
		 * @param x - takes in an double, puts for x-coordinate. Must be a real #.
		 * @param y - takes in a double, puts for y coordinate. Must be a real #.
		 * @param thespeed - takes in a double, puts for speed. Must be a real #.
		 * @param thespeed - takes in a double, puts for rotation.  Must be a real #, 
		 * 	but for practical purposes, makes more sense to use something below 2.
		 * 	Will be multiplied by pi.
		 */
	public AsteroidsGameShape(double x, double y, double thespeed, double therotation) {
		left = x; //adjust with width in mind l8r.
		top = y;

		rotation = therotation*Math.PI; //rotation * pi
		speed = thespeed;
		//Smoothest rotation.
		rotationspeed = 0.1;
		
		dead = false;
	}

	

	
	/*Visual of Game Shape*/
	/**
	 * Translates and rotates the graphics surface based on arrow keys
	 * by the user.  Draws x & y axes and the polygon centered at (0,0)
	 * @param g the surface we are drawing on 
	 */
	protected void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.WHITE);
		
		g2d.translate (left,top); 
		g2d.rotate(rotation); 
		g2d.draw(this);
		g2d.rotate(-rotation);
		g2d.translate (-left, -top);		
		}

	
	/* Moves the Games Object forward (in the direction it's pointed) */
	/*wraps around the Games Object*/
	protected void move ()
	{
		calculateXY();
		wraparound();
	}
	
	
	/**
	 * Moves the game Object forward in the direction it's pointed in.
	 * 
	 */
	protected void calculateXY() {
	double xOffset = speed * Math.sin(rotation);
	 double yOffset = -speed * Math.cos(rotation);
	top = top + yOffset; left = left + xOffset;}


	
	/**
	* Asteroids Game Shape will continue on the opposite end of the screen
	* once it reaches the end of the screen.
	*
	* -invoked every frame.
	*/
	protected void wraparound()
	{
	//Wraparound for top part of screen.
	if (top <= 0)
	{this.top = AsteroidsGame.FRAME_HEIGHT;}
	
	//If ship meets bottom, Ship reappears at top of screen.	
	if (top > AsteroidsGame.FRAME_HEIGHT)
	{this.top = 0;}

	if (left <= 0)
	{this.left = AsteroidsGame.FRAME_WIDTH;}
	
	if (left > AsteroidsGame.FRAME_WIDTH)
	{this.left = 0;}
	}

	/*
	 * COLLIDES WITH 
	 * 
	 * detects a collision.
	 */

	public boolean collidesWith (AsteroidsGameShape otherShape)
	{
		Rectangle2D bounds = TransformCalculator.calculateRotatedBounds(
				getBounds2D(), left, top, rotation);
				
		Rectangle2D otherBounds = TransformCalculator.calculateRotatedBounds(
						otherShape.getBounds2D(),otherShape.left,otherShape.top,
						otherShape.rotation);
		return otherBounds.intersects(bounds);
	}




	
	
}
