import javax.swing.JOptionPane;

public class Playeraction {
	/*
	 * Hier kommt der Spieler zum Zuge
	 */
		private static int coordx,
						   coordy;
		
	public static Field action (User_Interface GUI, Field enemyfield)
	{
		do
		{
			/*
			 * ...ein One-Field wird ausgesucht
			 */
			coordx = Integer.parseInt(JOptionPane.showInputDialog(null, "Bitte wählen Sie die x-Koordinate eines Feldes aus, auf dem Sie ein Schiff vermuten.", null, JOptionPane.PLAIN_MESSAGE));
			coordy = Integer.parseInt(JOptionPane.showInputDialog(null, "Bitte wählen Sie die y-Koordinate eines Feldes aus, auf dem Sie ein Schiff vermuten.", null, JOptionPane.PLAIN_MESSAGE));
			coordx--;
			coordy--;

			if (enemyfield.area[coordx][coordy].getShip()==true&&enemyfield.area[coordx][coordy].getHit()==false)
			{
				Game_Logic.counter(false);
				GUI.changeIcon(true, "ShipHit", coordx, coordy);
				enemyfield.area[coordx][coordy].setHit();
			}
			else
				enemyfield.area[coordx][coordy].setHit();
		}
		while(enemyfield.area[coordx][coordy].getShip()==true && Game_Logic.cpu!=0);
		if (Game_Logic.cpu!=0)
			GUI.changeIcon(true, "waterHit", coordx, coordy);
		return enemyfield;
	}
}