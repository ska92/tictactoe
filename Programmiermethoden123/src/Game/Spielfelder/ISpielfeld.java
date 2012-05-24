package Game.Spielfelder;

import Game.Spieler.Spieler;
import Game.Utils.Position;

public interface ISpielfeld {
	
	/**
	 * Gibt den Spieler auf den angegebenen Koordinaten zurück
	 * @param x - Die X-Koordinate
	 * @param y - Die Y-Koordinate
	 * @return Wenn das Spielfeld von einem Spieler belegt ist den Spieler,
	 *  ist das Spielfeld leer wird null zurückgegeben
	 */
	public Spieler getPlayerOnPosition(int x,int y);
	
	/**
	 * Setzte einen Spieler auf die angegebene Position auf das Spielfeld
	 * @param pos - Die Position des Spielfeldes
	 * @param sp - Den zu setzenden Spieler
	 */
	public void setPlayerOnPosition(Position pos, Spieler sp);
	
	/**
	 * Gibt die Maximale Länge(X) des Spielfeldes zurück
	 * @return
	 */
	public int getMaxX();
	
	/**
	 * Gibt die Maximale Höhe(Y) des Spielfeldes zurück.
	 * @return
	 */
	public int getMaxY();
	
	
	
}
