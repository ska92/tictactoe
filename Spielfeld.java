package pgm.tictactoe;

import java.io.Serializable;

import pgm.tictactoe.utils.Spielstand;

public class Spielfeld implements Serializable{

	private Player[][] feld;

	public Spielfeld() {
		feld = new Player[3][3];
	}

	public P getPlayerOnPosition(int x, int y) {
		return feld[x][y];
	}
	
	public Spielstand getCurrentSpielstand(){
		Player[][] spielfeldKopie = new Player[3][3];
		
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				spielfeldKopie[x][y] = feld[x][y];
			}
		}
		return new Spielstand(spielfeldKopie);
	}

	public void setzeSpieler(Player spieler, Position punkt) {
		if(punkt == null)
			return;
		feld[punkt.getX()][punkt.getY()] = spieler;
	}

	public void ausgabeSpielfeld() {
		System.out.println("----------------");
		for (int i = 0; i < 3; i++) {
			for (int x = 0; x < 3; x++) {
				if (feld[i][x] == null) {
					/*
					 * Leeres Feld
					 */
					System.out.print(" ");
				} else {
					System.out.print(feld[i][x].getGameSymbol());
				}

				if (x <= 1)
					System.out.print("|");
			}
			System.out.println();
		}
	}

}
