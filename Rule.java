package pgm.tictactoe;

import java.io.Serializable;

import pgm.tictactoe.utils.Spielstand;

public class Rule implements Serializable{

	private Spielfeld spielfeld;

	public Rule(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}

	public Spielstand getSpielstand() {
		return this.spielfeld.getCurrentSpielstand();
	}

	public Player getPlayerOnPosition(int x, int y) {
		return spielfeld.getPlayerOnPosition(x, y);
	}

	public boolean positionBelegt(int x, int y) {
		if (spielfeld.getPlayerOnPosition(x, y) == null)
			return false;
		else
			return true;
	}

	public boolean positionInRange(int x, int y) {
		if (x >= 0 && x < 3) {
			if (y >= 0 && y < 3) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean spielerGewinnt(Player spieler) {
		// Horizontal
		for (int i = 0; i < 3; i++) {
			if ((spielfeld.getPlayerOnPosition(i, 0) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, 1) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, 2) == spieler))
				return true;
		}
		// Vertikal
		for (int i = 0; i < 3; i++) {
			if ((spielfeld.getPlayerOnPosition(0, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(1, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(2, i) == spieler))
				return true;
		}
		// Diagonal
		// Links-Oben nach Rechts-Unten
		if ((spielfeld.getPlayerOnPosition(0, 0) == spieler)
				&& (spielfeld.getPlayerOnPosition(1, 1) == spieler)
				&& (spielfeld.getPlayerOnPosition(2, 2) == spieler))
			return true;

		// Rechts-Oben nach Links-Unten
		if ((spielfeld.getPlayerOnPosition(0, 2) == spieler)
				&& (spielfeld.getPlayerOnPosition(1, 1) == spieler)
				&& (spielfeld.getPlayerOnPosition(2, 0) == spieler))
			return true;

		return false;
	}

	public boolean unentschieden() {
		int anzahlVollerFelder = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (spielfeld.getPlayerOnPosition(i, j) != null)
					anzahlVollerFelder++;
			}
		}

		if (anzahlVollerFelder == 9)
			return true;
		else
			return false;
	}

	public Player spielerGewinnt(Spielstand spielstand) {
		Player spieler;
		//Horizontal
		for (int x = 0; x < 3; x++) {
			spieler = spielstand.getPlayerOnPosition(x, 0);
			if(spieler == null)
				continue;
			if ((spielstand.getPlayerOnPosition(x, 0) == spieler)
					&& (spielstand.getPlayerOnPosition(x, 1) == spieler)
					&& (spielstand.getPlayerOnPosition(x, 2) == spieler)) {
				return spieler;
			}
		}
		
		//Vertikal
		for(int y = 0; y < 3; y++){
			spieler = spielstand.getPlayerOnPosition(0, y);
			if(spieler == null)
				continue;
			if ((spielstand.getPlayerOnPosition(0, y) == spieler)
					&& (spielstand.getPlayerOnPosition(1, y) == spieler)
					&& (spielstand.getPlayerOnPosition(2, y) == spieler)) {
				return spieler;
			}
		}
		
		//Diagonal
		spieler = spielstand.getPlayerOnPosition(0, 0);
		if(spieler != null){
			if ((spielstand.getPlayerOnPosition(0, 0) == spieler)
					&& (spielstand.getPlayerOnPosition(1, 1) == spieler)
					&& (spielstand.getPlayerOnPosition(2, 2) == spieler)) {
				return spieler;
			}
		}
		
		spieler = spielstand.getPlayerOnPosition(0, 2);
		if(spieler != null){
			if ((spielstand.getPlayerOnPosition(0, 2) == spieler)
					&& (spielstand.getPlayerOnPosition(1, 1) == spieler)
					&& (spielstand.getPlayerOnPosition(2, 0) == spieler)) {
				return spieler;
			}
		}

		return null;
	}

	public boolean unentschieden(Spielstand spielstand){
		int anzahlVollerFelder = 0;
		
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				if(spielstand.getPlayerOnPosition(x, y) != null)
					anzahlVollerFelder++;
			}
		}
		if(anzahlVollerFelder == 9)
			return true;
		else
			return false;
	}
}
