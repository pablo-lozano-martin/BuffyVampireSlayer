package org.ucm.tp1.logic;
import java.util.Random;
import org.ucm.tp1.vampire.VampireList;
import org.ucm.tp1.vampire.Vampires;
import org.ucm.tp1.slayer.Slayer;
import org.ucm.tp1.slayer.SlayerList;

public class GameObjectBoard 
{
	private SlayerList listaSlayer;	//Lista de los slayers que existen
	private VampireList listaVampiro;	//Lista de los vampiros que existen
	private Game game;
	private Random rand;
	
	private boolean hasPerdido = false;

	public GameObjectBoard(Game game, Random rand)
	{
		this.rand = rand;
		this.game = game;
		listaSlayer = new SlayerList();
		listaVampiro = new VampireList(game.numVampTotales());
	}
	
	// FUNCIONES DE GAME IS FINISHED
	
	public void perder() //Cuando el jugador pierde
	{
		hasPerdido = true;	
	}
	
	public boolean comprobarPerder()	//Comprueba si el jugador ha perdido
	{
		return hasPerdido;
	}
	
	public boolean comprobarGanar()	//Comprueba si el jugador ha ganado
	{
		if(game.numVampMuertos() == game.numVampTotales())	//Devuelve true cuando han matado tantos vampiros como vampiros totales había en el nivel
		{
			return true;
		}
		return false;
	}
	
	// FUNCIONES DE MOVIMIENTOS Y ATAQUES
	
	public VampireList moveVampire()	//Mueven al vampiro de posición a la izquierda
	{		
		for(int i = 0; i < listaVampiro.cont(); i++)	//Para cada vampiro existente
		{
			if(listaVampiro.acceder(i).contador() % 2 == 0)	//Los mueve un ciclo de cada dos
			{
				if(listaVampiro.acceder(i).posY() == 0)	//Si el vampiro está en la última columna y se va a mover, el jugador pierde
				{
					perder();
				}				
				else if(game.sitioCasilla(listaVampiro.acceder(i).posX(), listaVampiro.acceder(i).posY() - 1))	//Si no está en el final
				{
					listaVampiro.acceder(i).move();
				}				
			}
			listaVampiro.acceder(i).sumarContador();	//Añade un ciclo al vampiro en concreto
		
		}
		return listaVampiro;	//Devuelve la lista actualizada de los vampiros
	}
	
	public void vampireAttack()	//Los vampiros atacan
	{
		for(int i = 0; i < listaVampiro.cont(); i++)	//Para cada vampiro
		{
			//Guarda su posición
			int posX = listaVampiro.acceder(i).posX();
			int posY = listaVampiro.acceder(i).posY();
			
			//Si el vampiro no está en la última columna y a la casilla de su izquierda está ocupada por un slayer
			if (posY != 0 && !game.sitioCasilla(posX, posY - 1) && listaSlayer.comprobarSiHaySlayer(posX, posY - 1))
			{
				//Ataca al slayer que está a su izquierda
				listaSlayer.acceder(listaSlayer.buscaSlayer(posX, posY -1)).slayerPain(listaVampiro.acceder(i).vampireAttack());
			}
		}
	}
	
	public void slayerAttack()	//El slayer ataca a los vampiros
	{
		for(int i = 0; i < listaSlayer.cont(); i++)	//Para cada slayer
		{
			listaVampiro.vampireDamage(listaSlayer.acceder(i).posX());	//Resta vida a los vampiros de su misma fila
			remove();	//Se asegura de que no haya nadie con 0 de vida en el tablero
		}
	}
	
	public int cont()	//Pide el contador de la lista de vampiros
	{
		return listaVampiro.cont();
	}
	
	
	// FUNCIONES DE CREAR ELEMENTOS
	
	public void addSlayer(int posX, int posY)	//Añade un slayer en la posición con 3 de vida
	{
		listaSlayer.nuevoElem(new Slayer(posX, posY, 3));
	}
	
	public void addVampire(int posY)	//Añade un vampiro en la posición
	{
		int posX = rand.nextInt(game.dim_y());	//Crea una fila aleatoria
		
		if(game.existeCasilla(posX, posY) && game.sitioCasilla(posX, posY))	//Se asegura de que no esté ocupada la casilla
		{
			listaVampiro.nuevoElem(new Vampires(posX, posY, 5));	//Crea el vampiro
			game.sumaNumAp();
		}		
	}
	
	
	// FUNCIONES DE ACTUALIZAR LISTAS Y TABLERO
	
	public void remove()	//Se asegura de que no haya nadie con 0 de vida en el tablero
	{
		for(int i = 0; i < listaSlayer.cont(); i++)	//Busca y elimina a slayers muertos
		{
			if(listaSlayer.acceder(i).resistencia() <= 0)
			{
				listaSlayer.eliminar(i);
			}
		}
		
		for(int i = 0; i < listaVampiro.cont(); i++)	//Busca y elimina a vampiros muertos
		{
			if(listaVampiro.acceder(i).resistencia() <= 0)
			{
				listaVampiro.eliminar(i);
				game.muereVampiro();
			}
		}
	}
	
	public String[][] refrescar(String[][] board)	//Refresca la lista de slayers y vampiros en el tablero
	{
		for(int i = 0; i < listaSlayer.cont(); i ++)
		{
			listaSlayer.slayerBoard(listaSlayer.acceder(i), board);
		}
		
		for(int i = 0; i < listaVampiro.cont(); i++)
		{
			listaVampiro.vampireBoard(listaVampiro.acceder(i), board);
		}
		return board;
	}
}
