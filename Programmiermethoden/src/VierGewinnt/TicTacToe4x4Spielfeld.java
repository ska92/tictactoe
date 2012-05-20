package VierGewinnt;

import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class TicTacToe4x4Spielfeld implements ISpielfeld{
	
	Spieler feld[][];

	public TicTacToe4x4Spielfeld(int x, int y){
		
	}
	
	@Override
	public Spieler getPlayerOnPosition(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerOnPosition(Position pos, Spieler sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
