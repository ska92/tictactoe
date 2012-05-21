package TicTacToe;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class Regeln implements IRegeln{
	

	@Override
	public boolean unentschieden(ISpielfeld sp) {
		// TODO Auto-generated method stub
		int anzahlGesetzterFelder = 0;
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				if(sp.getPlayerOnPosition(x, y) != null)
					anzahlGesetzterFelder++;
			}
		}
		if(anzahlGesetzterFelder == 9)
			return true;
		
		return false;
	}

	@Override
	public boolean spielerGewinnt(ISpielfeld spielfeld, Spieler spieler) {
		// TODO Auto-generated method stub#
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

	@Override
	public boolean spielfeldBelegt(ISpielfeld sp, Position pos) {
		// TODO Auto-generated method stub
		if(sp.getPlayerOnPosition(pos.getX(), pos.getY()) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean positionInGrenze(ISpielfeld sp, Position pos) {
		// TODO Auto-generated method stub
		if(pos.getX() >= 0 && pos.getX() < sp.getMaxX()){
			if(pos.getY() >= 0 && pos.getY() < sp.getMaxY())
				return true;
		}
		return false;
	}

}
