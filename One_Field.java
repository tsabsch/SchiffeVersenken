
public class One_Field {
	/*
	 * Hier fängt der Spaß eigentlich an. Denn zunächst einmal müssen wir definieren, was wir so alles 
	 * zum Programmieren haben wollen: Als da wäre ein One-Field; ein Objekt, in dem wir die Eigenschaften für 
	 * ein einzelnes Feld bestimmen und auch verändern
	 */	
	private boolean IsShip, 			//Variable, ob sich auf dem Feld ein Schiff(~steil) befindet
					IsHit,				//Variable, ob das Feld bereits getroffen wurde
					IsShipPossible; 	//Variable, ob das Feld noch mit einem Schiff belegt werden kann. Am Anfang natürlich JA 
	
	public One_Field() 					//Neues Objekt wird erstellt
	{
		IsShip=false;
		IsHit=false;
		IsShipPossible=true;
	}
	public void setShip () 				//Methode, mit der ein One-Field mit einem Schiff besetzt wird
	{
		IsShip=true;
	}
	public void noShip()				//Es können auf dem jeweiligen Feld keine Schiffe mehr gesetzt werden  
	{
		IsShipPossible=false;
	}
	public void setHit ()
	{
		IsHit=true;
	}
	public boolean getPossible() 		
	{
		return IsShipPossible;
	}
	public boolean getHit()
	{
		return IsHit;
	}
	public boolean getShip()
	{
		return IsShip;
	}
}