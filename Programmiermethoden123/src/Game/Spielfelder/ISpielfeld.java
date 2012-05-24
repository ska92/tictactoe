package Game.Spielfelder;

import Game.Spieler.Spieler;
import Game.Utils.Position;

public interface ISpielfeld {
	
	/**
	 * Gibt den Spieler auf den angegebenen Koordinaten zur�ck
	 * @param x - Die X-Koordinate
	 * @param y - Die Y-Koordinate
	 * @return Wenn das Spielfeld von einem Spieler belegt ist den Spieler,
	 *  ist das Spielfeld leer wird null zur�ckgegeben
	 */
	public Spieler getPlayerOnPosition(int x,int y);
	
	/**
	 * Setzte einen Spieler auf die angegebene Position auf das Spielfeld
	 * @param pos - Die Position des Spielfeldes
	 * @param sp - Den zu setzenden Spieler
	 */
	public void setPlayerOnPosition(Position pos, Spieler sp);
	
	/**
	 * Gibt die Maximale L�nge(X) des Spielfeldes zur�ck
	 * @return
	 */
	public int getMaxX();
	
	/**
	 * Gibt die Maximale H�he(Y) des Spielfeldes zur�ck.
	 * @return
	 */
	public int getMaxY();
	
	
	
}
