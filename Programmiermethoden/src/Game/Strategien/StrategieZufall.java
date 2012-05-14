package Game.Strategien;

import java.util.Random;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class StrategieZufall implements IStrategie{

	@Override
	public Position nextMove(IRegeln regeln, ISpielfeld spielfeld,
			Spieler spieler) {
		// TODO Auto-generated method stub
		int zufallX = 0, zufallY = 0;
		boolean positionOk = false;
		Random zufall = new Random();
		
		while( !positionOk ){
			zufallX = zufall.nextInt(3) ;
			zufallY = zufall.nextInt(3) ;
			
			if( !regeln.spielfeldBelegt(spielfeld, new Position(zufallX, zufallY)) ){
				positionOk = true;
			}
		}
		
		return new Position(zufallX, zufallY);
	}

	
}
