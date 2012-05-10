package pgm.tictactoe.strategie;

import java.util.Random;

import pgm.tictactoe.Player;
import pgm.tictactoe.Position;
import pgm.tictactoe.Rule;

public class WahrsSpielstaerke extends WahrscheinlichkeitSpielzug{

	public WahrsSpielstaerke(IStrategie strategie){
		this.strategie = strategie;
	}
	
	@Override
	public Position generateNextMove(Player spieler, Rule regeln) {
		// TODO Auto-generated method stub
		if(ersetzeSpielzug(spieler.getWahrscheinlichkeitSpielzugZufall()))
		{
			System.out.println("Zufall");
			return new StrategieZufall().generateNextMove(spieler, regeln);
			
		} else {
			return strategie.generateNextMove(spieler, regeln);
		}
			
	}

	@Override
	protected boolean ersetzeSpielzug(int zufallsWert) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int zufall = r.nextInt(100);
		
		if( zufall < zufallsWert)
			return true;
		else 
			return false;
	}

}
