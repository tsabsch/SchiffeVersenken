import javax.swing.JOptionPane;

public class setShips {
/*
 *Ab hier gehts richtig zur Sache. Hier kˆnnen Spieler und Computer ihre Schiffe setzen!
 */
	private static final int Gondel=1,  			 	//L‰nge der Boote als Konstanten 
							 Schaluppe=2,
							 Brigg=3,
							 Fregatte=4;
	
	public static Field PlayerSet(User_Interface GUI, Field playerfield)
	{
	int shipx = 0,     						 			//Initialisieren von shipx 
		shipy = 0;							 			//Initialisieren von shipy
			
	JOptionPane.showMessageDialog(null, "Bitte setzen Sie zuerst die Fregatte. Sie ist 4 Felder lang.", "Information", JOptionPane.PLAIN_MESSAGE);
	do
	{
		shipx = setShips.setxCoord();		 			//x-Koordinate wird eingegeben
		shipx--;							 			//Schiffe-versenken-Feld geht von 1-10, das Array aber von 0-9
		shipy = setShips.setyCoord();		 			//y-Koordinate wird eingegeben
		shipy--;							     		//Schiffe-versenken-Feld geht von 1-10, das Array aber von 0-9
		
		if (shipx>=10 || shipx<0 || shipy>=10 || shipy<0 || playerfield.area[shipx][shipy].getPossible()==false) //Falls die Koordinaten nicht innerhalb des Feldes sind oder bereits belegt, dann sind sie ung¸ltig 
			JOptionPane.showMessageDialog(null, "Eine Ihrer Angaben ist ung¸ltig. Bitte geben sie die Variablen erneut ein","FEHLER", JOptionPane.ERROR_MESSAGE);
	}
	while (shipx>=10 || shipx<0 || shipy>=10 || shipy<0); 							 		   //wird alles wiederholt, bis die Koords stimmen
	
	playerfield.area[shipx][shipy].setShip();												   //Schiff wird gesetzt
	GUI.changeIcon(false, "Ship", shipx, shipy);											   //An der betroffenen Stelle wird die Grafik ver‰ndert
	playerfield = setShips.setDirectory(GUI, playerfield, shipx, shipy, Fregatte); 			   //Richtung wird des Schiffs wird gesetzt (siehe Methode)
	
	
	JOptionPane.showMessageDialog(null, "Und nun 2 Briggs. Sie sind 3 Felder lang.", "Information", JOptionPane.PLAIN_MESSAGE);
	int i = 0;								 												   //Z‰hler, da es zwei Briggs sind
	do 
	{
		shipx = setShips.setxCoord();
		shipx--;
		shipy = setShips.setyCoord();
		shipy--;
		if (shipx>=10 || shipx<0 || shipy>=10 || shipy<0 || playerfield.area[shipx][shipy].getPossible()==false)
			JOptionPane.showMessageDialog(null, "Eine Ihrer Angaben ist ung¸ltig. Bitte geben sie die Variablen erneut ein","FEHLER", JOptionPane.ERROR_MESSAGE);
		else
		{
			i++; 																			   //Z‰hler darf nur inkrementiert werden, wenn die Koords auch stimmen
			playerfield.area[shipx][shipy].setShip();
			GUI.changeIcon(false, "Ship", shipx, shipy);
			playerfield = setShips.setDirectory(GUI, playerfield, shipx, shipy, Brigg);
		}
	}
	while (i<2);
	
	JOptionPane.showMessageDialog(null, "Und nun 3 Schaluppen. Sie sind 2 Felder lang.", "Information", JOptionPane.PLAIN_MESSAGE);
	i = 0;
	do
	{
		shipx = setShips.setxCoord();
		shipx--;
		shipy = setShips.setyCoord();
		shipy--;
		if (shipx>=10 || shipx<0 || shipy>=10 || shipy<0 || playerfield.area[shipx][shipy].getPossible()==false)
			JOptionPane.showMessageDialog(null, "Eine Ihrer Angaben ist ung¸ltig. Bitte geben sie die Variablen erneut ein","FEHLER", JOptionPane.ERROR_MESSAGE);
		else 
		{
			i++;
			playerfield.area[shipx][shipy].setShip();
			GUI.changeIcon(false, "Ship", shipx, shipy);
			playerfield = setShips.setDirectory(GUI, playerfield, shipx, shipy, Schaluppe);
		}
	}
	while (i<3);
	
	JOptionPane.showMessageDialog(null, "Und nun 4 Gondeln. Sie sind 1 Feld lang.", "Information", JOptionPane.PLAIN_MESSAGE);
	i = 0;
	do
	{
		shipx = setShips.setxCoord();
		shipx--;
		shipy = setShips.setyCoord();
		shipy--;
		if (shipx>=10 || shipx<0 || shipy>=10 || shipy<0 || playerfield.area[shipx][shipy].getPossible()==false)
			JOptionPane.showMessageDialog(null, "Eine Ihrer Angaben ist ung¸ltig. Bitte geben sie die Variablen erneut ein","FEHLER", JOptionPane.ERROR_MESSAGE);
		else 
			{
			i++;
			playerfield.area[shipx][shipy].setShip();
			GUI.changeIcon(false, "Ship", shipx, shipy);
			playerfield=setShipPossibility(playerfield, shipx, shipy); 						   //Bei Gondeln gibt es keine Directory, also hier der direkte Weg.
			}
	}
	while (i<4);
 							 
	return playerfield;
	}
	
	public static Field CPUSet (Field enemyfield)
	{
		enemyfield = setShips.setCPUShips(enemyfield, Fregatte);
		
		enemyfield = setShips.setCPUShips(enemyfield, Brigg);
		enemyfield = setShips.setCPUShips(enemyfield, Brigg);
		
		enemyfield = setShips.setCPUShips(enemyfield, Schaluppe);
		enemyfield = setShips.setCPUShips(enemyfield, Schaluppe);
		enemyfield = setShips.setCPUShips(enemyfield, Schaluppe);
		
		enemyfield = setShips.setCPUShips(enemyfield, Gondel);
		enemyfield = setShips.setCPUShips(enemyfield, Gondel);
		enemyfield = setShips.setCPUShips(enemyfield, Gondel);
		enemyfield = setShips.setCPUShips(enemyfield, Gondel);
			
		return enemyfield;	
	}
		
	public static int setxCoord()
	/*
	 * setxCoord setzt die x-Koordinate
	 */
	{
		String inx = Game_Logic.Input("Nennen Sie nun bitte das Anfangsfeld. Zun‰chst die x-Koordinate bitte:", "X-Koordinate", JOptionPane.INFORMATION_MESSAGE);
		int shipx = Integer.parseInt(inx);
		return shipx;
	}
	public static int setyCoord()
	/*
	 * setyCoord setzt, wie unerwartet, die y-Koordinate
	 */
	{
		String iny = Game_Logic.Input("Vielen Dank. Und nun bitte die y-Koordinate:", "Y-Koordinate", JOptionPane.INFORMATION_MESSAGE);
		int shipy = Integer.parseInt(iny);
		return shipy;
	}
	public static Field setDirectory(User_Interface GUI, Field playerfield,int shipx,int shipy, int Shiptype)
	/*
	 * setDirectory setzt nach Angabe einer Richtung das Schiff korrekt hinein. 
	 * Auch wird festgelegt, dass die Felder um das gesetzte Schiff herum nicht neu besetzt werden kˆnnen.
	 */
	{
		char direc = JOptionPane.showInputDialog(null, "Vielen Dank. Nun nennen Sie die Richtung in die das Schiff gesetzt werden soll: \n Norden, Osten, S¸den oder Westen.", "Richtung", JOptionPane.INFORMATION_MESSAGE).charAt(0);
		boolean done = false; 

		do
		{

			switch ( direc ) 																       
			{
			case 'n':
			case 'N': 							 //oben->shipy-i
				if (shipy+1 - Shiptype <0) 														   //Das Schiff kˆnnte ¸pber den Spielfeldrand hinausgehen, JETZT nicht mehr!
					direc = JOptionPane.showInputDialog(null, " Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
				else
				{ 
					for (int i = 1; i < Shiptype; i++) 											   //in dieser Schleife werden jetzt die ¸brigen Felder des Schiffes als IsShip=true belegt
					{
						if (playerfield.area[shipx][shipy-i].getPossible()==false)				   //...auﬂer nat¸rlich, da ist schon eins.
						{
							direc = JOptionPane.showInputDialog(null, " Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
							break;
						}
						else
						{
							playerfield.area[shipx][shipy-i].setShip(); 						   //So, hier werden sie jetzt aber belegt!
							GUI.changeIcon(false, "Ship", shipx, shipy-i);						   //Und auch die Grafik wird wieder ver‰ndert
						}
					}
					for (int i=0; i< Shiptype; i++)
						playerfield = setShips.setShipPossibility(playerfield, shipx, shipy-i);    //Andere Schiffe d¸rfen nicht auf die gew‰hlten Felder.
					done = true;
					JOptionPane.showMessageDialog(null, " Vielen Dank", "Information", JOptionPane.PLAIN_MESSAGE);
				}
				break;
			case 'o':
			case 'O':							 //rechts->shipx+i
				if (shipx + Shiptype >10)
					direc = JOptionPane.showInputDialog(null, "Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
				else 
				{
					for (int i = 1; i < Shiptype; i++)
					{
						if (playerfield.area[shipx+i][shipy].getPossible()==false)
						{
							direc = JOptionPane.showInputDialog(null, "Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
							break;
						}
						else
						{
							playerfield.area[shipx+i][shipy].setShip();
							GUI.changeIcon(false, "Ship", shipx+i, shipy);
						}
					}
					for (int i=0; i< Shiptype; i++)
						playerfield = setShips.setShipPossibility(playerfield, shipx+i, shipy);
					done = true;
					JOptionPane.showMessageDialog(null, "Vielen Dank", "Information", JOptionPane.PLAIN_MESSAGE); 
				}
				break;
			case 's':
			case 'S': 							 //unten->shipy+i
				if (shipy + Shiptype >10)
					direc = JOptionPane.showInputDialog(null, "Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
				else
				{
					for (int i = 1; i < Shiptype; i++)
					{
						if (playerfield.area[shipx][shipy+i].getPossible()==false)
						{	
							direc = JOptionPane.showInputDialog(null, "Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
							break;
						}
						else
						{
							playerfield.area[shipx][shipy+i].setShip();
							GUI.changeIcon(false, "Ship", shipx, shipy+i);
						}
					}
					for (int i=0; i< Shiptype; i++)
						playerfield = setShips.setShipPossibility(playerfield, shipx, shipy+i);
					done = true;
					JOptionPane.showMessageDialog(null, "Vielen Dank", "Information", JOptionPane.PLAIN_MESSAGE);
				}
				break;
			case 'w':
			case 'W': 							 //links-> shipx-i
				if (shipx+1 - Shiptype <0)
					direc = JOptionPane.showInputDialog(null, "Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
				else
				{
					for (int i = 1; i < Shiptype; i++)
					{
						if (playerfield.area[shipx-i][shipy].getPossible()==false)
						{
							direc = JOptionPane.showInputDialog(null, "Die angebene Richtung kann nicht realisiert werden. Bitte wiederholen Sie ihre Eingabe!", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
							break;
						}
						else
						{
							playerfield.area[shipx-i][shipy].setShip();
							GUI.changeIcon(false, "Ship", shipx-i, shipy);
						}
					}
					for (int i=0; i< Shiptype; i++)
						playerfield = setShips.setShipPossibility(playerfield, shipx-i, shipy);
					done = true;
					JOptionPane.showMessageDialog(null, "Vielen Dank", "Information", JOptionPane.PLAIN_MESSAGE);
				}
				break;	
			default:		
			direc = JOptionPane.showInputDialog(null, " Die angegebene Richtung kann nicht realisiert werden.Bitte wiederholen Sie ihre Eingabe", "FEHLER", JOptionPane.ERROR_MESSAGE).charAt(0);
			}
		}
		while (done==false);
		return playerfield;
	}
	public static Field setShipPossibility (Field field,int shipx,int shipy)
	/* 
	 * Die Felder, auf denen bereits Schiffe sind, und die benachbarten kˆnnen nicht mehr besetzt werden. 
	 * Wenn das Schiff am Rand liegt, g‰be es ohne die Unterteilungen einen ERROR, da man kein One-Field
	 * bearbeiten kann, das es gar nicht gibt.
	 */
	{
		if (shipy-1<0)
		{
			if (shipx-1<0)
			{
				field.area[shipx][shipy].noShip();
				field.area[shipx+1][shipy].noShip();
				field.area[shipx][shipy+1].noShip();
				field.area[shipx+1][shipy].noShip();
			}
			else if (shipx+1>=10)
			{	
				field.area[shipx][shipy].noShip();
				field.area[shipx-1][shipy].noShip();
				field.area[shipx][shipy+1].noShip();
				field.area[shipx-1][shipy+1].noShip();
			}
			else
			{
				field.area[shipx][shipy].noShip();
				field.area[shipx-1][shipy].noShip();
				field.area[shipx+1][shipy].noShip();
				field.area[shipx][shipy+1].noShip();
				field.area[shipx-1][shipy+1].noShip();
				field.area[shipx+1][shipy+1].noShip();
			}
		}
		else if (shipy+1>=10)
		{
			if (shipx-1<0)
			{
				field.area[shipx][shipy].noShip();
				field.area[shipx+1][shipy].noShip();
				field.area[shipx][shipy-1].noShip();
				field.area[shipx+1][shipy-1].noShip();
			}
			else if (shipx+1>=10)
			{
				field.area[shipx][shipy].noShip();
				field.area[shipx-1][shipy].noShip();
				field.area[shipx][shipy-1].noShip();
				field.area[shipx-1][shipy-1].noShip();
			}
			else
			{
				field.area[shipx][shipy].noShip();
				field.area[shipx-1][shipy].noShip();
				field.area[shipx+1][shipy].noShip();
				field.area[shipx][shipy-1].noShip();
				field.area[shipx-1][shipy-1].noShip();
				field.area[shipx+1][shipy-1].noShip();
			}
		}
		else if (shipx-1<0)
		{
			field.area[shipx][shipy].noShip();
			field.area[shipx+1][shipy].noShip();
			field.area[shipx][shipy-1].noShip();
			field.area[shipx][shipy+1].noShip();
			field.area[shipx+1][shipy-1].noShip();
			field.area[shipx+1][shipy+1].noShip();
		}
		else if (shipx+1>=10)
		{
			field.area[shipx][shipy].noShip();
			field.area[shipx-1][shipy].noShip();
			field.area[shipx][shipy-1].noShip();
			field.area[shipx][shipy+1].noShip();
			field.area[shipx-1][shipy-1].noShip();
			field.area[shipx-1][shipy+1].noShip();
		}
		else
		{
			field.area[shipx][shipy].noShip();
			field.area[shipx-1][shipy].noShip();
			field.area[shipx+1][shipy].noShip();
			field.area[shipx][shipy-1].noShip();
			field.area[shipx][shipy+1].noShip();
			field.area[shipx-1][shipy-1].noShip();
			field.area[shipx+1][shipy-1].noShip();
			field.area[shipx-1][shipy+1].noShip();
			field.area[shipx+1][shipy+1].noShip();
		}
		return field;
	}
	public static Field setCPUShips(Field enemyfield, int Shiptype)
	{
		double rndx,
		   	   rndy;
		int    shipx,
	     	   shipy;
		
		do 
		{
			rndx = Math.random() *10;        //Zuf‰llige Auswahl einer x-Koordinate
			rndy = Math.random() *10;        //Zuf‰llige Auswhal einer y-Koordinate
			shipx = (int) rndx;              //Math.random() geht nur als double, daher werden sie hier gleichzeitig konvertiert ins Integer
			shipy = (int) rndy;
		}
		while (enemyfield.area[shipx][shipy].getPossible()!=true);

		enemyfield.area[shipx][shipy].setShip();
		if (Shiptype!=Gondel)                //Wie gesagt, Gondeln haben keine Richtung
			enemyfield = setShips.CPUDirectory(enemyfield, shipx, shipy, Shiptype);
		enemyfield = setShips.setShipPossibility(enemyfield, shipx, shipy);
		return enemyfield;
	}
	public static Field CPUDirectory(Field enemyfield, int shipx, int shipy, int Shiptype)
	{
	/*
	 * Auch die Schiffe des Computers brauchen eine Richtung.
	 * Der ganze Vorgang l‰uft ziemlich symmetrisch zu dem von setDirectory, daher keine Kommentare	
	 */
		boolean done = false; 	
		
		do
		{
			double rndd = Math.random() *4;
			int direc   = (int) rndd +1;
			done = false;
			switch ( direc ) 																   
			{
			case 1: 							 //oben->shipy-i
				out: if (shipy+1 - Shiptype >=0) 	//"out" ist eine Marke, mit der ich kennzeichne, dass beim break die ‰uﬂere Schleife verlassen werden soll													  
				{ 
					for (int i = 1; i < Shiptype; i++) 											   
					{
						if (enemyfield.area[shipx][shipy-i].getPossible()==true)				   
						{
							enemyfield.area[shipx][shipy-i].setShip(); 						  
							done = true;
						}
						else break out;;
					}
					for (int i=0; i< Shiptype; i++)
						enemyfield = setShips.setShipPossibility(enemyfield, shipx, shipy-i);
				}
				break;
			case 2:							 //rechts->shipx+i
				out: if (shipx-1 + Shiptype <=9)
				{
					for (int i = 1; i < Shiptype; i++)
					{
						if (enemyfield.area[shipx+i][shipy].getPossible()==true)
						{
							enemyfield.area[shipx+i][shipy].setShip();
							done = true;
						}
						else break out;;
					}
					for (int i=0; i< Shiptype; i++)
						enemyfield = setShips.setShipPossibility(enemyfield, shipx+i, shipy);				
				}
				break;
			case 3: 							 //unten->shipy+i
				out: if (shipy-1 + Shiptype <=9)
				{
					for (int i = 1; i < Shiptype; i++)
					{
						if (enemyfield.area[shipx][shipy+i].getPossible()==true)
						{
							enemyfield.area[shipx][shipy+i].setShip();
							done = true;
						}
						else break out;;
					}
					for (int i=0; i< Shiptype; i++)
						enemyfield = setShips.setShipPossibility(enemyfield, shipx, shipy+i);
				}
				break;
			case 4: 							 //links-> shipx-i
				out:if (shipx+1 - Shiptype >=0)
				{
					for (int i = 1; i < Shiptype; i++)
					{
						if (enemyfield.area[shipx-i][shipy].getPossible()==true)
						{
							enemyfield.area[shipx-i][shipy].setShip();
							done = true;
						}
						else break out;;
					}
					for (int i=0; i< Shiptype; i++)
						enemyfield = setShips.setShipPossibility(enemyfield, shipx-i, shipy);
				}
				break;	
			}
		}
		while (done==false);
		return enemyfield;
	}
}