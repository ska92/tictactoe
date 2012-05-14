package Game.Regeln;

import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public interface IRegeln {
	
	public boolean unentschieden(ISpielfeld sp);
	public boolean spielerGewinnt(ISpielfeld sp, Spieler spieler);
	public boolean spielfeldBelegt(ISpielfeld sp, Position pos);
	public boolean positionInGrenze(ISpielfeld sp, Position pos);
	
	
}
