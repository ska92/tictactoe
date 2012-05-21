package Game;

import Game.Spieler.Spieler;
import TicTacToe.TicTacToeController;
import TicTacToe.TicTacToeModel;
import TicTacToe.TicTacToeViewer;
import TicTacToe.Regeln;
import TicTacToe.Spielfeld;

public class Starter {
	
	public Starter(GameController gc){
		if(gc != null)
			gc.startGame();
	}

	
	
	public static void main(String args[]){
		Spieler spieler1 = new Spieler("Spieler1", 'X');
		Spieler spieler2 = new Spieler("Spieler2", 'O');
		Spielfeld spielfeld = new Spielfeld(3,3);
	    Model model = new TicTacToeModel(spieler1, spieler2, spielfeld);
		Regeln regeln = new Regeln();
		GameController spiel = new TicTacToeController(args, regeln, model, new TicTacToeViewer());
		
		
		
		new Starter(spiel);
	}

}
