package Game.Utils;

import Game.Spielfelder.ISpielfeld;

public interface IObserver {
	
	/**
	 * Informiert den Observer �ber eine �nderung am Spielfeld.
	 * @param spielfeld - Das neue Spielfeld mit den �nderungen
	 */
	public void update(ISpielfeld spielfeld);
}
