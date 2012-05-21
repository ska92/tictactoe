package Game.Utils;

import Game.Spielfelder.ISpielfeld;

public interface IObservable {
		public void addObserver(IObserver obs);
		public void removeObserver(IObserver obs);
		public void notifyAll(ISpielfeld spielfeld);
}
