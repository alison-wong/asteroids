

public class Asteroid extends ThingsThatAreNotShips {
	
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
	public Asteroid(double x, double y,double thespeed, double therotation)
	{
		super(x,y,thespeed,therotation);
		
		//Add Points.
		this.addPoint(0,0);  
		this.addPoint(0, 30);
		this.addPoint(10, 25);
		this.addPoint (20,14);
		this.addPoint(5,0);
		this.addPoint(0,6);
		

	}

	/*Function to return an Asteroid, from the public abstract function in Superclass, so that 
	 * it is possible to use a function in superclass to fill an array generally of anything under 
	 * this class.
	 * 
	 * returns new asteroid.
	 * 
	 * (non-Javadoc)
	 * @see ThingsThatAreNotShips#createThingsThatAreNotShips(int, int, double, double)
	 */
	public ThingsThatAreNotShips createThingsThatAreNotShips (int x, int y, double thespeed, double therotation) 
	{
	    return new Asteroid (x,y,thespeed,therotation);
	    
	}
}
