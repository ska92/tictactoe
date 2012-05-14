package TicTacToe;

import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class Spielfeld implements ISpielfeld{

	private Spieler feld[][];
	private int MaxX;
	private int MaxY;
	
	public Spielfeld(int x, int y){
		feld = new Spieler[x][y];
		this.MaxX = x;
		this.MaxY = y;
	}
	
	@Override
	public Spieler getPlayerOnPosition(int x, int y) {
		// TODO Auto-generated method stub
		return feld[x][y];
	}

	@Override
	public void setPlayerOnPosition(Position pos, Spieler sp) {
		// TODO Auto-generated method stub
		if(pos == null)
			return;
		
		feld[pos.getX()][pos.getY()] = sp;
	}

	@Override
	public int getMaxX() {
		// TODO Auto-generated method stub
		return MaxX;
	}

	@Override
	public int getMaxY() {
		// TODO Auto-generated method stub
		return MaxY;
	}

}
