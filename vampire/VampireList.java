package org.ucm.tp1.vampire;

public class VampireList 
{
	private int cont = 0;
	private Vampires[] list;
	private int numVamp;
	
	public VampireList(int numVamp)	//Crea una lista de vampiros con el tamaño variable segun el nivel
	{
		this.numVamp = numVamp;
	    list = new Vampires[numVamp];
	}
	
	public void vampireDamage(int pos)	//Resta vida a los vampiros de la fila indicada
	{
		for(int i = 0; i < cont; i ++)
		{
			if(list[i].posX() == pos)
			{
				list[i].hurt();
			}
		}
	}
		
	public void eliminar(int i )	//Elimina un vampiro y mueve al resto una posición menos
	{
		 for(int p = i; p< cont - 1; p++)
		 {
		        list[p]= list[p+1];
		 }
		 cont--;
	}
	
	public Vampires[] listilla()	//Devuelve la lista de vampiros
	{
		return list;
	}
	
	public int cont()	//Devuelve el número de vampiros de la lista
	{
		return cont;
	}
	
	
	public Vampires acceder(int i) 	//Devuelve el vampiro seleccionado
	{
		return list[i];
	}
	
	public Vampires[] nuevoElem(Vampires vampire)	//Añade un vampiro a la lista
	{
		list[cont] = vampire;
		cont++;
		return list;
	}
	
		
	public String[][] vampireBoard(Vampires vampire, String[][] board)
	{
		board[vampire.posX()][vampire.posY()] = vampire.toString();
		
		return board;
	}
}
