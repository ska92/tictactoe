package pgm.tictactoe.strategie;

import java.io.Serializable;
import java.util.Random;
import pgm.tictactoe.Player;
import pgm.tictactoe.Position;
import pgm.tictactoe.Rule;


public class StrategieZufall implements IStrategie, Serializable {
	@Override
	public Position generateNextMove(Player spieler, Rule rule) {
		// TODO Auto-generated method stub
		int zufallX = 0, zufallY = 0;
		boolean positionOk = false;
		Random zufall = new Random();
		
		while( !positionOk ){
			zufallX = zufall.nextInt(3) ;
			zufallY = zufall.nextInt(3) ;
			
			if( !rule.positionBelegt(zufallX, zufallY) ){
				positionOk = true;
			}
		}
		
		return new Position(zufallX, zufallY);
	}

}
