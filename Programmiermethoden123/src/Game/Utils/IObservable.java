package Game.Utils;

import Game.Spielfelder.ISpielfeld;

public interface IObservable {
	
		/**
		 * Meldet einen Observer an
		 * @param obs - den anzumeldenden Observer
		 */
		public void addObserver(IObserver obs);
		
		/**
		 * Entfernt einen Observer aus der Liste.
		 * @param obs - den zu entfernenden Observer
		 */
		public void removeObserver(IObserver obs);
		
		/**
		 * Informiert alle angemeldeten Observer das eine �nderung stattgefunden hat.
		 * @param spielfeld - Das Spielfeld das die �nderungen enth�lt.
		 */
		public void notifyAll(ISpielfeld spielfeld);
}
