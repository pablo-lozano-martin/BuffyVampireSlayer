package org.ucm.tp1.control;
import java.util.Scanner;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.slayer.Slayer;

public class Controller 
{
	//Mensaje de comando y de ayuda en caso "help"
	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	//Mensajes de error
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	
	private int posX, posY;	//Posición del Slayer que colocas
    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) 
    {
	    this.game = game;
	    this.scanner = scanner;
    }
   
    //Dibuja el tablero
    public void  printGame() 
    {
   	 	System.out.println(game);
    }    

    //Controla el flujo de la ejecución
    public void run() 
    {
        boolean exit = false;
        boolean dibujar = true;

        printGame();
        
        while(!game.isFinished(exit)) //Acaba el while cuando el juego finaliza
        {
	    	 dibujar = false;
	    	
	    	 //El usuario escribe la instrucción por consola
	    	 System.out.println(prompt);	
	    	 
	    	 //Separa la instrucción en segmentos que luego procesa
	         String[] cmdTokens =scanner.nextLine().toLowerCase().split(" ");
	         String cmd =cmdTokens[0];
	         
	         //Mensaje de ejecución
	         System.out.println("[DEBUG] Executing: " + cmd);
	
	         //Switch según las distintas instrucciones
	         switch(cmd) 
	         {
	         	//Caso help, enseña un mensaje con el menú de instrucciones
	            case "h":
	            case "help":
	                System.out.println(helpMsg);
	
	                break;
	                
	            //Caso add, añade un slayer en la casilla indicada
	            case "a":
	            case "add":
	            	
	            	//Guarda la posición en las variables
	                posX =Integer.parseInt(cmdTokens[2]);
	                posY =Integer.parseInt(cmdTokens[1]);
	                
	                
	        		if (!game.existeCasilla(posX, posY))	//Si no existe la casilla
	        		{
	        			System.out.println("[ERROR] " + Game.invalidPositionDoesntExistMsg);
	        		}
	        		else if (!game.sitioCasilla(posX, posY))	//Si la casilla está ocupada
	        		{
	        			System.out.println("[ERROR] " + Game.invalidPositionOccupiedMsg);
	        		}
	        		else if(posY == game.dim_x() - 1)	//Si la casilla está en la última columna
	        		{
	        			System.out.println("[ERROR] " + Game.invalidPositionWrongColumnMsg);
	        		}
	        		else if (!game.hayDinero())	//Si no hay dinero
	        		{
	        			System.out.println("[ERROR] " + Game.notEnoughCoindsMsg);
	        		}
	        		else	//En caso contrario, coloca el slayer donde se indica
	        		{
	        			System.out.println("posX:" + posX + "posY: " + posY);
	        			game.addSlayer(posX, posY);
	        			game.comprar();
	        			dibujar = true;
	        		}              
	
	                break;
	
	            //Caso reset, reinicia el juego
	            case "r":
	            case "reset":
	            	game.reset();
	            	dibujar = true;
	            	
	                break;
	         
	            //Caso exit, sale del juego
	            case "e":
	            case "exit":
	                exit = Game.exit();
	
	                break;
	
	            //Caso none, continúa el juego sin añadir slayer
	            case "n":
	            case "none":
	            case "":
	                dibujar = true;
	
	                break;
	                
	            //Caso distinto, escribe un mensaje de error
	            default:
	            	System.out.println("[ERROR] " + unknownCommandMsg);      	
	            	break;
	         }
	         
	         //Si el juego tiene que avanzar (add o none)
	         if(dibujar)
	         {
	        	 game.update();	//Actualiza las posiciones y movimientos
	             game.slayerAttack();	//Lo slayers atacan
	             game.vampireAttack();	//Los vampiros atacan
	             game.addVampire(game.dim_x() - 1);	//Los vampiros aparecen
	             
	             game.remove();	//Elimina cualquier elemento con 0 de vida
	             printGame();	//Dibuja el tablero
	         }
	         
	         cmd = "";             
        }
    }  
}
