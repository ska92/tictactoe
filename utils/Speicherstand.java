package pgm.tictactoe.utils;

import pgm.tictactoe.Player;
import pgm.tictactoe.Spielfeld;

public class Speicherstand {
	
	private Spielfeld spielfeld;
	private Player letzterSpieler;
	private Player spieler1;
	private Player spieler2;
	
	public Speicherstand(Spielfeld spielfeld, Player letzterSpieler, Player spieler1, Player spieler2){
		this.spielfeld = spielfeld;
		this.letzterSpieler = letzterSpieler;
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
	}
	
	public Speicherstand(Spielfeld spielfeld, Player letzterSpieler){
		this.spielfeld = spielfeld;
		this.letzterSpieler = letzterSpieler;
		this.spieler1 = null;
		this.spieler2 = null;
	}
	
	public Spielfeld getSpielfeld(){
		return this.spielfeld;
	}
	
	public Player getLetztenPlayer(){
		return this.letzterSpieler;
	}
	
	public Player getSpieler1(){
		return this.spieler1;
	}
	
	public Player getSpieler2(){
		return this.spieler2;
	}

}
