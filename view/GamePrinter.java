package org.ucm.tp1.view;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.utils.MyStringUtils;
import org.ucm.tp1.logic.Player;

public class GamePrinter 
{
	Game game;
	Player player;
	int numRows; 
	int numCols;
	
	String[][] board;
	final String space = " ";
	
	public GamePrinter (Game game, int cols, int rows) 
	{
		this.game = game;
		this.numRows = rows;
		this.numCols = cols;
		board = new String[numRows][numCols];	
	}
	
	private String[][] iniciaBoard(String[][] board)	//Inicia el tablero con casillas vacías
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				board[i][j] = "";
			}
		}
		return board;
	}
	
	public boolean existeCasilla(int posX, int posY)	//Devuelve true si la casilla indicada está en el tablero
	{
		if(posX >= 0 && posX < numRows && posY >= 0 && posY < numCols)
		{
			return true;
		}
		return false;
	}
	
	public boolean sitioCasilla(int posX, int posY)	//Devuelve true si la casilla está libre
	{	
		if(board[posX][posY] == "")
		{
			return true;
		}
		return false;
	}

	private void encodeGame(Game game) 	//Inicializa el tablero y carga los objetos en él
	{
		iniciaBoard(board);
		game.refrescar(board);
	}
	
	 public String toString() //Dibuja el tablero y la información
	 {
		encodeGame(game);
		
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String intersect = space;
		String vIntersect = space;
		String hIntersect = "-";
		String corner = space;

		String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);

		String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols-1) + cellDelimiter + vIntersect;
		String hEdge =  corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols-1) + cellDelimiter + corner;

		String margin = MyStringUtils.repeat(space, marginSize);
		String lineEdge = String.format("%n%s%s%n", margin, hEdge);
		String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);

		StringBuilder str = new StringBuilder();

		str.append(lineEdge);
		
		System.out.println("Number of cycles: " + game.ciclo());
		System.out.println("Coins: " + game.coins());
		System.out.println("Remaining vampires: " + game.numVampRestantes());
		System.out.println("Vampires on the board: " + game.cont());	
		
		for(int i=0; i<numRows; i++)
		{
		        str.append(margin).append(vDelimiter);
		        for (int j=0; j < numCols; j++)
		            str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
		        if (i != numRows - 1) str.append(lineDelimiter);
		        else str.append(lineEdge);   
		}

		return str.toString();
	    }
}