package pgm.tictactoe.strategie;

import java.io.Serializable;

import pgm.tictactoe.Player;
import pgm.tictactoe.Position;
import pgm.tictactoe.Rule;

public class StrategieFesterSpielzug implements IStrategie, Serializable {

	@Override
	public Position generateNextMove(Player spieler, Rule rule) {
		// TODO Auto-generated method stub
		
		//Mitte belegt?
		if( !rule.positionBelegt(1, 1) )
			return new Position(1,1);
		
		//Ecken belegen
		if( !rule.positionBelegt(0, 0) )
			return new Position(0,0);
		
		if( !rule.positionBelegt(0, 2) )
			return new Position(0, 2);
		
		if( !rule.positionBelegt(2, 0) )
			return new Position(2, 0);
		
		if( !rule.positionBelegt(2, 2) )
			return new Position(2, 2);
		
		//Sonstige felder
		if( !rule.positionBelegt(0, 1))
			return new Position(0, 1);
		
		if( !rule.positionBelegt(1, 0))
			return new Position(1, 0);
		
		if( !rule.positionBelegt(1, 2))
			return new Position(1, 2);
		
		if( !rule.positionBelegt(2, 1))
			return new Position(2,1);
		
		return null;
	}

}
