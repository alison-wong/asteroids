import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;


/*Game Shapes that are Not Ships.
 * 
 * 	These are mass-produced and stored in arrays. (a.k.a, there is more than one of them.)
 * 	Versus, Ships, which only have one , and are always controlled by players of the game.
 * 	These are never controlled by the player of the game.
 */
public abstract class ThingsThatAreNotShips extends AsteroidsGameShape {
	
	/*Variables*/
	private static int POLYGON_WIDTH = 10;
	private static  int POLYGON_HEIGHT = 15; 
	private static Random random = new Random();

	/*VariablesEnd*/
	

	public ThingsThatAreNotShips(double x, double y,double thespeed, double therotation)
	{
		super(x,y,thespeed,therotation);

	}

	public void fillAsteroidArray(ThingsThatAreNotShips[] ThingsThatAreNotShipsArray)
	{
	int i = 0;
	while (i < ThingsThatAreNotShipsArray.length)
	{
		ThingsThatAreNotShipsArray[i] = new Asteroid (100,100,1,1);
		i++;
	}
	}
	
	
	public abstract ThingsThatAreNotShips createThingsThatAreNotShips (int x, int y, double thespeed, double therotation);

	public static void fillThingsThatAreNotShipsArray(ThingsThatAreNotShips[] ThingsThatAreNotShipsArray,ThingsThatAreNotShips ThingsThatAreNotShips)
	{
		
	int i = 0;
	while (i < ThingsThatAreNotShipsArray.length)
	{
	ThingsThatAreNotShipsArray[i] = ThingsThatAreNotShips.createThingsThatAreNotShips (		
			/*x = */ random.nextInt(1300),
			/*y = */ random.nextInt(800),
			/*speed = */ random.nextDouble()*5,
			/*rotation = */ random.nextDouble()*2
);
	i++;
	}
	}
	
	
}
