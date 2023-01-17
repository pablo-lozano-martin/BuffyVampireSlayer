package org.ucm.tp1.slayer;


public class SlayerList 
{
	private int cont = 0;
	private Slayer[] list;
	
	public SlayerList()	//Crea una lista de slayers de tamaño 20
	{
	    list = new Slayer[20];
	}
	
	
	public Slayer[] listilla()	//Devuelve la lista de los slayers
	{
		return list;
	}
	
	
	public void eliminar(int i)	//Elimina un slayer y mueve al resto una posición menos
	{
		 for(int p = i; p < cont - 1; p++)
		 {
		        list[p]= list[p+1];
		 }
		 cont--;
	}
	
	
	public int cont()	//Devuelve el número de slayers de la lista
	{
		return cont;
	}
	
	public boolean comprobarSiHaySlayer(int posX, int posY)	//Devuelve true cuando existe un slayer en la posición indicada
	{
		for(int i = 0; i < cont; i++)
		{
			if(list[i].posX() == posX && (list[i].posY() == posY))
			{
				return true;
			}
		}
		return false;
	}
	
	
	public Slayer acceder(int i)	//Accede a un slayer de la lista
	{
		return list[i];
	}
	
	
	public Slayer[] nuevoElem(Slayer slayer)	//Añade un slayer a la lista
	{
		list[cont] = slayer;
		cont++;
		return list;
	}
	
	public int buscaSlayer(int posX, int posY)	//Devuelve la posición del slayer que buscas
	{
		boolean encontrado = false;
		int i = 0;
		int pos = -1;
		while ( (i < cont) && (!encontrado) )
		{
			if( (list[i].posX() == posX) && (list[i].posY() == posY) )
			{
				encontrado = true;
				pos = i;
			}
			i++;
		}
		return pos;
	}
	
	
	public String[][] slayerBoard(Slayer slayer, String[][] board)
	{
		board[slayer.posX()][slayer.posY()] = slayer.toString();
		
		return board;
	}
}
