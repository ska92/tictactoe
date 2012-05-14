package Game;

import Game.Spieler.Spieler;
import TicTacToe.KonsolenViewer;
import TicTacToe.Regeln;
import TicTacToe.Spielfeld;
import TicTacToe.TicTacToeController;
import TicTacToe.TicTacToeModel;

public class Start {
	
	private GameController gc;
	
	public Start(String args[]){
		setGameTicTacToe(args);
		
		gc.startGame();
	}
	
	private void setGameTicTacToe(String args[]){
		Spieler spieler1 = new Spieler("Spieler1", 'X');
		Spieler spieler2 = new Spieler("Spieler2", 'O');
		Spielfeld spielfeld = new Spielfeld(3,3);
		TicTacToeModel model = new TicTacToeModel(spieler1, spieler2, spielfeld);
		
		Regeln regeln = new Regeln();
		TicTacToeController spiel = new TicTacToeController(args, regeln, model, new KonsolenViewer());
		this.gc = spiel;
	}
	
	
	public static void main(String args[]){
		new Start(args);
	}

}
