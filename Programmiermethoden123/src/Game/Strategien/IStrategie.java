package Game.Strategien;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public interface IStrategie {
	
	/**
	 * Generiert den n�chsten Spielzug.
	 * 
	 * @param regeln - Die regeln f�r das aktuelle Spiel
	 * @param spielfeld - Das Spielfeld
	 * @param spieler - Den Spieler der aktuell am Zug ist.
	 * @return - Die Position/Koordinates des generierten Spielzuges
	 */
	public Position nextMove(IRegeln regeln, ISpielfeld spielfeld, Spieler spieler);

}
