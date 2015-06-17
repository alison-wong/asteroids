import java.awt.geom.Point2D;


public class Bullet extends ThingsThatAreNotShips{

	/* ASTEROID CONSTRUCTOR
	 * 	constructs a new Asteroid
	 * 
	 * @param x - takes in an double, puts for x-coordinate. Must be a real #.
	 * @param y - takes in a double, puts for y coordinate. Must be a real #.
	 * @param thespeed - takes in a double, puts for speed. Must be a real #.
	 * @param thespeed - takes in a double, puts for rotation.  Must be a real #, 
	 * 	but for practical purposes, makes more sense to use something below 2.
	 * 	Will be multiplied by pi.
	 */
	public Bullet(double x, double y, double thespeed, double therotation) {
		super(x, y, thespeed, therotation);

		//Add points.
		this.addPoint(0, 30);
		this.addPoint(0, 0);
		
	}
	
	
	/*Collision Function
	 * 
	 * Detects collisions with other non-player objects in arrays.  
	 * @param ThingsThatAreNotShipsArray - takes in an array full of ThingsThatAreNotShips
	 * 
	 * returns nothing.
	 */
	public void collidesWithArraysOfEnemies(ThingsThatAreNotShips[] ThingsThatAreNotShipsArray)
	{
		
		int i = 0;
		while (i < ThingsThatAreNotShipsArray.length)
		{
		
			if (this.collidesWith(ThingsThatAreNotShipsArray[i]) == true)
				{
					ThingsThatAreNotShipsArray[i].dead = true;
					dead = true;
				}
			
			i++;
		}
		
	}
	
	/* Overwrite of Move function in Superclass
	 * 
	 * moves the Bullet forward 
	 * does NOT wraparound
	 * checks collision.
	 * 
	 * (non-Javadoc)
	 * @see AsteroidsGameShape#move()
	 */
	protected void move ()
	{
		calculateXY();
		this.collidesWithArraysOfEnemies(AsteroidsGame.AsteroidsArray);
	}


	@Override
	public ThingsThatAreNotShips createThingsThatAreNotShips(int x, int y, double thespeed, double therotation) {
		// TODO Auto-generated method stub
		return new Bullet(x,y,thespeed,therotation);
	}
	
	
	
	
}
