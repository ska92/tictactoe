package TicTacToe4x4;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class TicTacToe4x4Regeln implements IRegeln{

	@Override
	public boolean unentschieden(ISpielfeld sp) {
		int anzahlGesetzterFelder = 0;
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				if(sp.getPlayerOnPosition(x, y) != null)
					anzahlGesetzterFelder++;
			}
		}
		if(anzahlGesetzterFelder == 16)
			return true;
		
		return false;
	}
	

	@Override
	public boolean spielerGewinnt(ISpielfeld spielfeld, Spieler spieler) {
		for (int i = 0; i < 4; i++) {
			if ((spielfeld.getPlayerOnPosition(i, 0) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, 1) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, 2) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, 3) == spieler))
				return true;
		}
		// Vertikal
		for (int i = 0; i < 4; i++) {
			if ((spielfeld.getPlayerOnPosition(0, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(1, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(2, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(3, i) == spieler))
				return true;
		}
		// Diagonal
		// Links-Oben nach Rechts-Unten
		if ((spielfeld.getPlayerOnPosition(0, 0) == spieler)
				&& (spielfeld.getPlayerOnPosition(1, 1) == spieler)
				&& (spielfeld.getPlayerOnPosition(2, 2) == spieler)
				&& (spielfeld.getPlayerOnPosition(3, 3) == spieler))
			return true;

		// Rechts-Oben nach Links-Unten
		if ((spielfeld.getPlayerOnPosition(0, 3) == spieler)
				&& (spielfeld.getPlayerOnPosition(1, 2) == spieler)
				&& (spielfeld.getPlayerOnPosition(2, 1) == spieler)
				&& (spielfeld.getPlayerOnPosition(3, 0) == spieler))
			return true;

		return false;
	}

	@Override
	public boolean spielfeldBelegt(ISpielfeld sp, Position pos) {
		if(sp.getPlayerOnPosition(pos.getX(), pos.getY()) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean positionInGrenze(ISpielfeld sp, Position pos) {
		if(pos.getX() >= 0 && pos.getX() < sp.getMaxX()){
			if(pos.getY() >= 0 && pos.getY() < sp.getMaxY())
				return true;
		}
		return false;
	}

}
