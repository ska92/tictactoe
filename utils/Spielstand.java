package pgm.tictactoe.utils;

import pgm.tictactoe.Player;

public class Spielstand {
	
	private Player feld[][];
	
	public Spielstand(Player feld[][]){
		this.feld = feld.clone();
	}
	
	public Player getPlayerOnPosition(int x, int y){
		return feld[x][y];
	}
	
	public Player[][] getField(){
		return feld;
	}
	
	public void setSpielerOnPosition(int x, int y, Player spieler){
		feld[x][y] = spieler;
	}

}
