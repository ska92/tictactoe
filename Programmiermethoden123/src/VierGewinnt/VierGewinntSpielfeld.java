package VierGewinnt;

import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class VierGewinntSpielfeld implements ISpielfeld{
	
	private Spieler feld[][];
	private int MaxX;
	private int MaxY;
	
	
	public VierGewinntSpielfeld(int x, int y){
			feld = new Spieler[x][y];
			this.MaxX = x;
			this.MaxY = y;
		
	}
	
	@Override
	public Spieler getPlayerOnPosition(int x, int y) {
		return feld[x][y];
	}

	@Override
	public void setPlayerOnPosition(Position pos, Spieler sp) {
		if(pos == null)
			return;
		
		feld[pos.getX()][pos.getY()] = sp;
	}

	@Override
	public int getMaxX() {
		return MaxX;
	}

	@Override
	public int getMaxY() {
		return MaxY;
	}

}
