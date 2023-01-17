package org.ucm.tp1.slayer;

public class Slayer 
{
	private int coste = 50;
	private int resistencia;
	private int frecuencia;
	private int alcance;
	final private int daño = 1;
	private int posX;
	private int posY;
	
	public Slayer(int posX, int posY, int resistencia)
	{
		this.posX = posX;
		this.posY = posY;
		this.resistencia = resistencia;
	}
	
	
	public int resistencia()	//Devuelve el número de vida
	{
		return resistencia;
	}
	
	
	public int posX()	//Devuelve la posición X
	{
		return posX;
	}
	
	
	public int posY()	//Devuelve la posición Y
	{
		return posY;
	}
	
	
	public String toString()	//Devuelve el string con la vida
	{
		return("S [" + this.resistencia + "]");
	}
	
	
	public int slayerPain(int pain)	//Resta vida al slayer
	{
		return resistencia -= pain;
	}
}
