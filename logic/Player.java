package org.ucm.tp1.logic;

public class Player 
{
	private int numCiclos;
	private int coins = 50;
	private int remainingVampires;
	private int vampiresBoard;
	
	public Player()
	{
		this.numCiclos = numCiclos;
		this.coins = coins;
		this.remainingVampires = remainingVampires;
		this.vampiresBoard = vampiresBoard;
	}
	
	public int numCiclos(int numCiclos)	//Devuelve el número de 
	{
		return numCiclos;
	}

	public void resetMonedas()	//Resetea el número de monedas
    {
        coins = 50;
    }
	
	public int numCoins()	//Devuelve el número de 
	{
		return coins;
	}
	
	public int darMonedas()	//Añade 10 monedas al jugador
	{
		return coins += 10;
	}
	
	public int comprar()	//Resta 50 monedas al jugador
	{
		return coins -= 50;
	}
}
