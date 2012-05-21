package Game.Strategien;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public interface IStrategie {
	
	public Position nextMove(IRegeln regeln, ISpielfeld spielfeld, Spieler spieler);

}
