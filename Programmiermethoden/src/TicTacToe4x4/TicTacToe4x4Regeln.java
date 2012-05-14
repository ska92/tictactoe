package TicTacToe4x4;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class TicTacToe4x4Regeln implements IRegeln{

	@Override
	public boolean unentschieden(ISpielfeld sp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean spielerGewinnt(ISpielfeld sp, Spieler spieler) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean spielfeldBelegt(ISpielfeld sp, Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean positionInGrenze(ISpielfeld sp, Position pos) {
		// TODO Auto-generated method stub
		return false;
	}

}
