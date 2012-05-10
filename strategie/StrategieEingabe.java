package pgm.tictactoe.strategie;

import java.io.Serializable;
import java.util.Scanner;

import pgm.tictactoe.Player;
import pgm.tictactoe.Position;
import pgm.tictactoe.Rule;


public class StrategieEingabe implements IStrategie, Serializable{
	@Override
	public Position generateNextMove(Player spieler, Rule rule) {
		// TODO Auto-generated method stub
		boolean eingabe = false;
		
		Scanner tastatur = new Scanner(System.in);
		
		int x = 0,y = 0;
		
		while(!eingabe){
			System.out.println("Bitte Koordinaten eingeben:");
			
			x = tastatur.nextInt();
			y = tastatur.nextInt();
			
			if( rule.positionInRange(x, y)){
				if( !rule.positionBelegt(x, y) )
					eingabe = true;
				else
					System.out.println("Die Position ist bereits belegt");
			} else {
				System.out.println("Position auﬂerhalb des Spielfeldes");
			}
		}
		
		return new Position(x,y);
	}

}
