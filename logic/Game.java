package org.ucm.tp1.logic;
import java.util.Random;

import org.ucm.tp1.view.GamePrinter;

public class Game 
{
	Player player = new Player();
	private Long seed;
	private Level level;
	private GamePrinter printer;
	private int ciclo = 0;	//Establece el número de ciclos en 0
	private Random rand;
	
	private GameObjectBoard gameObject;
	
	private int numVampRestantes;	//Número de vampiros que quedan por salir
	private int numAparecidos = 0;	//Número de vampiros que han salido
	private int numVampMuertos = 0;	//Número de vampiros que han muerto por slayers
	
	//Mensajes varios
	public static final String invalidPositionDoesntExistMsg = String.format("Invalid position. It does not exist");
	public static final String invalidPositionWrongColumnMsg = String.format("Invalid position. You cannot place an slayer in the last column");
	public static final String invalidPositionOccupiedMsg = String.format("Invalid position. Already occupied");
	public static final String notEnoughCoindsMsg = String.format("Not enough coins");
	public static final String gameOverMsg = String.format("[Game over] ");
	
	public Game(Long seed, Level level)
	{
		this.seed = seed;
		this.level = level;
		rand = new Random(seed);
		printer = new GamePrinter(this, level.dim_x(), level.dim_y());
		gameObject = new GameObjectBoard(this, rand);
		this.numVampRestantes = level.numberVampires();		
	}	
	
	// FUNCIONES DE LA LOGICA DEL JUEGO.
	
	public boolean isFinished(boolean exit)	//True cuando el juego acaba
	{
		if(exit)	//Si el usuario escribe exit
		{
			System.out.println(gameOverMsg + "Nobody wins...");
			return true;
		}
		else if(gameObject.comprobarPerder() == true)	//Si los vampiros llegan a la ultima columna
		{
			System.out.println(gameOverMsg + "Vampires win!");
			return true;
		}
		else if(gameObject.comprobarGanar() == true)	//Si todos los vampiros mueren antes de llegar al final
		{
			System.out.println(gameOverMsg + "Player wins");
			return true;
		}
		return false;
	}	
	
	public static boolean exit()	//Devuelve true cuando la llaman
	{
		return true;
	}
	
	public void update() 	//Actualiza cada ciclo
	{
        this.ciclo++;	//Aumenta el núero de ciclos
        if (rand.nextFloat() > 0.5) 	//Da monedas al jugador el 50% de las veces
        {
           player.darMonedas();
        }	
        gameObject.moveVampire();	//Mueve a los vampiros
    }
	


	
	
	public void reset()	//Resetea el juego
    {
        gameObject = new GameObjectBoard(this, rand);
        player = new Player();
        ciclo = 0;
    }
	
	
	public boolean sitioCasilla(int posX, int posY)	//Devuelve true si la casilla está vacía
	{
		if (printer.sitioCasilla(posX, posY))
		{
			return true;
		}
		return false;
	}
	
	
	public boolean existeCasilla(int posX, int posY)	//Devuelve true si la casilla existe en el tablero
	{
		if (printer.existeCasilla(posX, posY)) 
		{
			return true;
		}
		return false;
	}
	
	
	public boolean hayDinero()	//Devuelve true si el jugador tiene 50 monedas o más
	{
		if (player.numCoins() >= 50)
		{
			return true;
		}
		return false;
	}
	
	
	// FUBCIOBES PARA ACCEDER A GAMEOBJECT
	
	
	public void remove()	//Elimina los elementos con 0 de vida
	{
		gameObject.remove();
	}	
	
	public void slayerAttack()	//Los slayers atacan a los vampiros de su fila
	{
		gameObject.slayerAttack();
	}
	
	public void vampireAttack()	//Los vampiros atacan a los slayers que tienen enfrente
	{
		gameObject.vampireAttack();
	}	
	
	public void refrescar(String[][] board)	//Refresca el tablero
	{
		gameObject.refrescar(board);
	}
	
	public void addSlayer(int posX, int posY)	//Añade un slayer en la posición
	{
		gameObject.addSlayer(posX, posY);
	}
	
	public void addVampire(int posY)	//Añade un vampiro en la posición
	{
		if(numVampRestantes > 0)	//Si todavía quedan vampiros por salir
		{
	        if (rand.nextDouble() < level.vampFrequency()) 	//Según la frequencia del nivel de dificultad
	        {
		      	gameObject.addVampire(posY);	//Llama a gameObject para posicionar al vampiro en la columna posY
		    }
		}
	}
	
	public int cont()	//Devuelve el contador de la lista de vampiros
	{
		return gameObject.cont();
	}
	
	// FUNCIONES PARA LLAMAR A PLAYER
	
	public int comprar()	//Resta 50 monedas al jugador
	{
		return player.comprar();
	}
	
	public int coins()		//Pide el número de monedas del jugador
	{
		return player.numCoins();
	}
	
	// FUNCIONES PARA SACAR DATOS DE GAME
	
	public int ciclo()	//Devuelve el ciclo actual
	{
		return ciclo;
	}
	
	public int numAparecidos()	//Devuelve el número de vampiros aparecidos
	{
		return numAparecidos;
	}
	
	public int numVampMuertos()	//Devuelve el número de vampiros que han muerto
	{
		return numVampMuertos;
	}
	
	public int numVampRestantes()	//Devuelve el número de vampiros restantes
	{
		return numVampRestantes;
	}
	
	
	// FUNCIONES PARA MODIFICAR DATOS DE GAME
	
	public void muereVampiro()	//Aumenta el numero de vampiros muertos
	{
		numVampMuertos++;
	}
	
	public void sumaNumAp()	//Aumenta el número de vampiros aparecidos y resta al de los vampiros restantes
	{
		numAparecidos++;
		numVampRestantes--;
	}
	

	// FUNCIONES PARA ACCEDER A LEVEL
	
	public int numVampTotales()	//Pide el nñumero de vampiros totales del nivel
	{
		return level.numberVampires();
	}
	
	public int dim_y()	//Pide la longitud y del tablero
	{
		return level.dim_y();
	}
	
	
	public int dim_x()	//Pide la longitud x del tablero
	{
		return level.dim_x();
	}

	// FUNCION PARA EL PRINTER
	
	public String toString()
	{
		return printer.toString();
	}
}
