
public class CPUaction {

	public static Field action (User_Interface GUI, Field playerfield)
	{
		double rndx,
			   rndy;
		int    x   ,
			   y   ;
		
		do
		{
			rndx = Math.random() *10;
			rndy = Math.random() *10;
		    x    = (int) rndx;
		    y    = (int) rndy;
		if (playerfield.area[x][y].getShip()==true&&playerfield.area[x][y].getHit()==false)
		{
			GUI.changeIcon(false, "ShipHit", x, y);
			Game_Logic.counter(true);
			playerfield.area[x][y].setHit();
		}
		else 
		{
			GUI.changeIcon(false, "waterHit", x, y);
			playerfield.area[x][y].setHit();
		}
		}
		while(playerfield.area[x][y].getShip()==true&& Game_Logic.player!=0);
		return playerfield;
	}
}