package org.ucm.tp1.vampire;

public class Vampires
{
	private int resistencia;
	private final int daño = 1;
	private int posX;
	private int posY;
	private int contador = 1;
	
	public Vampires(int posX, int posY, int resistencia)
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
	
	public int contador()	//Devuelve el ciclo del vampiro que se usa para el movimiento
	{
		return contador;
	}
	
	public int move()	//Mueve el vampiro una casilla a la izquierda
	{
		return posY--;
	}
	
	public int vampireAttack()	//Devuelve el daño que cause un ataque de vampiro
	{
		return daño;
	}
	
	public int hurt()	//Resta vida al vampiro
	{
		return resistencia--;
	}
	
	public void sumarContador()	//Añade 1 al ciclo del vampiro
	{
		contador++;
	}
	
	public String toString()	//Devuelve el string con la vida
	{
		return("V [" + this.resistencia + "]");
	}
}
