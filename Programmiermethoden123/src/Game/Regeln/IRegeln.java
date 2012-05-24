package Game.Regeln;

import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public interface IRegeln {
	
	
	/**
	 * Prüft, ob das Spiel unentschieden ist.
	 * @param sp - Das Spielfeld des Spieles
	 * @return - True wenn unentschieden, sonst false.
	 */
	public boolean unentschieden(ISpielfeld sp);
	
	/**
	 * Prüft, ob der übergebene Spieler das Spiel gewonnen hat.
	 * @param sp - Das Spielfeld des Spielers
	 * @param spieler - Spieler, für den auf Sieg geprüft wird.
	 * @return True wenn der Spieler gewonnen hat, sonst false
	 */
	public boolean spielerGewinnt(ISpielfeld sp, Spieler spieler);
	
	/**
	 * Prüft, ob das Spielfed an den angegebenen Koordinates belegt ist.
	 * @param sp - Das spielfeld des Spieles.
	 * @param pos - Die Position die überprüft werden soll
	 * @return - True wenn daa Spielfeld belegt ist, sonst false
	 */
	public boolean spielfeldBelegt(ISpielfeld sp, Position pos);
	
	/**
	 * Prüft, ob die angebene Koordinate innerhalb des Spielfeldes liegt.
	 * @param sp - Das Spielfeld des Spieles.
	 * @param pos - Die zu überprüfende Position
	 * @return True, wenn die Position auf dem Spielfeld liegt, sonst false.
	 */
	public boolean positionInGrenze(ISpielfeld sp, Position pos);
	
	
}
