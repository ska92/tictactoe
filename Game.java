package pgm.tictactoe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import pgm.tictactoe.strategie.StrategieEingabe;
import pgm.tictactoe.strategie.StrategieZufall;
import pgm.tictactoe.strategie.StrategieFesterSpielzug;
import pgm.tictactoe.strategie.StrategieMinMax;
import pgm.tictactoe.strategie.WahrsSpielstaerke;
import pgm.tictactoe.utils.GameData;
import pgm.tictactoe.utils.Speicherstand;


public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player spieler1, spieler2;
	private Player letzterSpieler;
	private Spielfeld spielfeld;
	private Rule regeln;
	private GameData gamedata;
	
	private boolean spielende;
	
	public Player getLetzterSpieler(){
		return this.letzterSpieler;
	}
	
	public Player getSpieler1(){
		return this.spieler1;
	}
	
	public Player getSpieler2(){
		return this.spieler2;
	}
	
	public Spielfeld getSpielfeld(){
		return this.spielfeld;
	}
	
	public Game(){
		spielende = false;
		letzterSpieler = null;
		
		spielfeld = new Spielfeld();
		regeln = new Rule(spielfeld);
		
		spieler1 = new Player("Spieler1", 'O');
		spieler2 = new Player("Spieler2", 'X');	
		letzterSpieler = null;
	
		spieler1.setWahrscheinlichkeitSpielzugZufall(Player.LEICHT);
		spieler1.setStrategie(new WahrsSpielstaerke(new StrategieFesterSpielzug()));
		spieler2.setStrategie(new StrategieEingabe());
		
		gamedata = new GameData(null, "spiel2");
	}
	
	private void ladeSpielstand(){
		if(!gamedata.hasSpielstand())
			return;

		Speicherstand temp = null;
		
		try {
			temp = gamedata.ladeSpielstand(spieler1, spieler2);
		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim laden des Spielstands.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(temp == null){
			System.out.println("Kein Speicherstand geladen.");
			System.out.println("Neues Spiel wird gestartet.");
			System.out.println("---------------------------");
			gamedata.leereSpielstand();
		} else {
			this.spielfeld = temp.getSpielfeld();
			this.letzterSpieler = temp.getLetztenPlayer();
			this.regeln = new Rule(this.spielfeld);
			
			if(temp.getSpieler1() != null)
				this.spieler1 = temp.getSpieler1();
			
			if(temp.getSpieler2() != null)
				this.spieler2 = temp.getSpieler2();
			
			System.out.println("Spielstand wurde geladen.");
		}
	}
	
	public void startGame(){
		ladeSpielstand();
		
		System.out.println("Spiel startet");
		System.out.println("Spieler: ");
		System.out.println(spieler1.getName() + "(" + spieler1.getGameSymbol() + ")" );
		System.out.println(spieler2.getName() + "(" + spieler2.getGameSymbol() + ")");
		
		Position spielzug = null;
		
		while(!spielende){
			
			if(letzterSpieler != null){
				spielfeld.ausgabeSpielfeld();
				
				if(regeln.spielerGewinnt(letzterSpieler)){
					spielende = true;
					System.out.println(letzterSpieler.getName() + " hat gewonnen.");
					continue;
				}
				if(regeln.unentschieden()){
					spielende = true;
					System.out.println("Spiel ist zuende. Unentschieden");
					continue;
				}					
			}
				
			if(letzterSpieler != spieler1){
				letzterSpieler = spieler1;
				spielzug = spieler1.nextMove(regeln);
				spielfeld.setzeSpieler(spieler1, spielzug);
			} else {
				letzterSpieler = spieler2;
				spielzug = spieler2.nextMove(regeln);
				spielfeld.setzeSpieler(spieler2, spielzug);
			}
			
			try{
				//Spielzug abspeichern speichern
				gamedata.speicherSpielstandTextdatei(letzterSpieler, spielzug);
				
				//Gesamtes Spiel abspeichern
				gamedata.speichereSpielSerial(this);
			} catch (Exception e){
				System.out.println("Spielstand konnte nicht gespeichert werden.");
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.startGame();
	}
}
