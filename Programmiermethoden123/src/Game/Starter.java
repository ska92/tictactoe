package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Game.Spieler.Spieler;
import TicTacToe.TicTacToeController;
import TicTacToe.TicTacToeModel;
import TicTacToe.TicTacToeViewer;
import TicTacToe.Regeln;
import TicTacToe.Spielfeld;
import TicTacToe4x4.*;
import VierGewinnt.*;
public class Starter {
	
	public Starter(GameController gc){
		if(gc != null)
			gc.startGame();
	}

	
	
	public static void main(String args[]){
		String s = null;
		Spieler spieler1 = new Spieler("Spieler1", 'X');
		Spieler spieler2 = new Spieler("Spieler2", 'O');
		
		System.out.println( "Welches Spiel möchten Sie spielen ? \n 1. TicTacToe \n 2. TicTacToe4*4 \n 3. 4Gewinnt" );
	    try {
	      BufferedReader in = new BufferedReader(
	                          new InputStreamReader( System.in ) );
	      s = in.readLine();
	    } catch( IOException ex ) {
	      System.out.println( ex.getMessage() );}
		
	    if(s.equals("1"))
	    	startnormal(spieler1,spieler2,args);
	    else if(s.equals("2"))
	    	start4x4(spieler1,spieler2,args);
	    else if(s.equals("3"))
	    	start4gewinnt(spieler1,spieler2,args); 
	    else{
	    	System.out.println("Bitte wählen sie einen vorhandenen Menuepunkt aus");
	    }
		
	}
	
	
	public static void startnormal(Spieler spieler1, Spieler spieler2,String args[])
	{
		Spielfeld spielfeld = new Spielfeld(3,3);
	    Model model = new TicTacToeModel(spieler1, spieler2, spielfeld);
		Regeln regeln = new Regeln();
		GameController spiel = new TicTacToeController(args, regeln, model, new TicTacToeViewer());

		new Starter(spiel);
	}

	public static void start4x4(Spieler spieler1, Spieler spieler2,String args[])
	{
		Spielfeld spielfeld = new Spielfeld(4,4);
		Model model = new TicTacToe4x4Model(spieler1, spieler2, spielfeld);
	    TicTacToe4x4Regeln regeln = new TicTacToe4x4Regeln();
	    GameController spiel = new TicTacToe4x4Controller(args, regeln, model, new TicTacToe4x4Viewer());

		new Starter(spiel);
	}
	
	public static void start4gewinnt(Spieler spieler1, Spieler spieler2,String args[])
	{
		VierGewinntSpielfeld spielfeld = new VierGewinntSpielfeld(6,7);
		VierGewinntModel model = new VierGewinntModel(spieler1, spieler2, spielfeld);
		VierGewinntRegeln regeln = new VierGewinntRegeln();
		GameController spiel = new VierGewinntController(args, regeln, model, new VierGewinntViewer());

		new Starter(spiel);
	}
}
