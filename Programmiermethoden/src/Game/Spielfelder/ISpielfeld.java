package Game.Spielfelder;

import Game.Spieler.Spieler;
import Game.Utils.Position;

public interface ISpielfeld {

	public Spieler getPlayerOnPosition(int x,int y);
	public void setPlayerOnPosition(Position pos, Spieler sp);
	public int getMaxX();
	public int getMaxY();
	
}
