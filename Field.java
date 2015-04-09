
public class Field {
	/*
	 * Aus mehreren One-Fields entsteht dann das Field, ein weiteres, neu erstelltes Objekt
	 */
	public One_Field[][] area; 			   //Initialisierung eines Arrays area vom Datentyp One-Field
	
	public Field() 						   //Neues Objekt...
	{
		int i,j;
		
		area = new One_Field[10][10];  	   //Jedes Feld bekommt die Werte aus One_Field 
		for (i=0; i<area.length; i++)
		{
			for (j=0; j<area[0].length; j++)
			{
				area[i][j]= new One_Field();
			}
		}
	}
}