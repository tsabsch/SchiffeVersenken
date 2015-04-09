/*
 * Hier gehts los! Willkommen bei diesem Rundgang durchs Schiffe versenken-Programm! 
 * Haben Sie Fragen, Wünsche, Anregungen und gar einen BUG entdeckt?
 * Zögern Sie nicht, den Programmierer zu kontaktieren oder, noch besser, den Fehler gleich zu beheben!  
 */
import javax.swing.*;	 //Das Paket Swing wird importiert, weil es nicht standardmäßig 
                         //getan wird, ich es aber für die GUI benötige
public class Game_Logic {

	/**
	 * @param args
	 */
	public static int player = 20,
			   		  cpu    = 20;
	//Das ist die main()-Methode. Hier wird alles zusammengefügt, was sich in den anderen Klassen ergeben hat...				     	
	public static void main(String[] args)  
	{
		//new sound("C://Users/JSabsch/workspace/Informatik_2012/bin/veneziano.wav").start();
		//Initialisierung des grafischen Interfaces sowie der beiden Felder 
		User_Interface GUI = new User_Interface();
		Field  playerfield = new Field(), 										
		       enemyfield  = new Field(); 	
		
		JOptionPane.showMessageDialog(null, "Herzlich Willkommen bei dieser Ausgabe des Spieleklassikers Schiffe versenken.");
		JOptionPane.showMessageDialog(null, "Wir wünschen Ihnen viel Spaß!");
		JOptionPane.showMessageDialog(null, "Bitte beachten Sie, Ihnen gehört das linke Feld, während das rechte Feld dem Computer vorbehalten ist.");
		playerfield = setShips.PlayerSet(GUI, playerfield);          //Der Spieler setzt zunächst seine Schiffe
		enemyfield = setShips.CPUSet(enemyfield);					 //Dann ist der Computer dran
	
		/*
		 * Ab hier wird geraten. Dabei geht jedes Mal, wenn ein Schiff getroffen wird, ein Zähler von
		 * 20 an runter, bis er 0 ist. Dann wird das Spiel unterbrochen.
		 */
		JOptionPane.showMessageDialog(null, "Sie haben nun die Schiffe gesetzt. Im Nachfolgenden haben Sie die Möglichkeit,\njeweils abwechselnd mit dem Computer die Schiffe des Gegners zu erraten. Sie fangen an:");
		do 
		{
			enemyfield = Playeraction.action(GUI,enemyfield);
			if (cpu==0) break;
			playerfield = CPUaction.action(GUI,playerfield);
			if (player==0) break;
		}
		while (player!=0 && cpu!=0);
		if (cpu==0)
			javax.swing.JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch, Sie haben gewonnen!", "SIEG", JOptionPane.PLAIN_MESSAGE);
		if (player==0)
			javax.swing.JOptionPane.showMessageDialog(null, "Tut mir Leid, Sie haben verloren...!", "Niederlage", JOptionPane.PLAIN_MESSAGE);
		
	}
	//Und hier ist die Methode für den Zähler!
	public static void counter (boolean IsPlayer)
	{
		if (IsPlayer)
			player--;
		if (!IsPlayer)
			cpu--;
	}
	public static String Input (String text, String title, int type)
	{
		String input;
		do 
		{
			input = javax.swing.JOptionPane.showInputDialog(null, text, title, type);
		}
		while(input=="");
		return input;
	}
}