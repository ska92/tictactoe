package Game.Utils;

import Game.Spielfelder.ISpielfeld;

public interface IObserver {
	
	/**
	 * Informiert den Observer über eine Änderung am Spielfeld.
	 * @param spielfeld - Das neue Spielfeld mit den Änderungen
	 */
	public void update(ISpielfeld spielfeld);
}
