package pgm.tictactoe.strategie;

import java.io.Serializable;

import pgm.tictactoe.Player;
import pgm.tictactoe.Position;
import pgm.tictactoe.Rule;

import pgm.tictactoe.utils.Spielstand;

public class StrategieMinMax implements IStrategie,  Serializable{
	
	private int läufe = 0;
	private Player globalPlayer;
	private Player shadowPlayer;

	@Override
	public Position generateNextMove(Player spieler, Rule regeln) {
		globalPlayer = spieler;
		shadowPlayer = new Player("Shadow",'S');
		// TODO Auto-generated method stub
		Spielstand[] tempSpielstand = successor(kopiereSpielstand(regeln.getSpielstand()), regeln, spieler);
		int[] anzahlSpielstand = new int[tempSpielstand.length];
		int i=0;
		for(Spielstand s: tempSpielstand){
			if(s != null){
				anzahlSpielstand[i] = min(s, spieler, regeln);
				i++;
			} else {
				break;
			}
		}
		
		i = 0;
		int temp = -2;
		int besterSpielzug = 0;
		for(int y : anzahlSpielstand){
			if( y > temp && tempSpielstand[i] != null){
				temp = y;
				besterSpielzug = i;
			}
			if(temp == 1){
				break;
			}
			i++;
		}
		
		Position nextMove = null;
		Player[][] feld = tempSpielstand[besterSpielzug].getField();
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				if(feld[x][y] != regeln.getPlayerOnPosition(x, y)){
					nextMove = new Position(x,y);
					break;
				}
			}
		}
		
		System.out.println("Durchlauf: " + läufe);
		läufe = 0;
		
		return nextMove;
	}
	
	public Spielstand[] successor(Spielstand spielstand, Rule regeln, Player spieler){
		Spielstand[] spielstandArray = new Spielstand[9];
		int zaehler = 0;
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				if(spielstand.getPlayerOnPosition(x, y) == null){
					Spielstand temp = new Spielstand(kopiereSpielstand(spielstand).getField());
					temp.setSpielerOnPosition(x, y, spieler);
					spielstandArray[zaehler] = temp;
					zaehler++;
				}
			}
		}
		return spielstandArray;
	}

	private int max(Spielstand spielstand, Player spieler, Rule regeln){
		läufe++;
		if( regeln.unentschieden(spielstand) ){
			return 0;
		} else if( regeln.spielerGewinnt(spielstand) != null){
			if( regeln.spielerGewinnt(spielstand) == spieler){
				return 1;
			} else {
				return -1;
			}
		}
		
		int wert = -2;
		for(Spielstand s : successor(spielstand, regeln, spieler)){
			if(s != null){
				int minimal = min(s, spieler, regeln);
				if( wert < minimal ){
					wert = minimal;
				}
			} else {
				break;
			}
		}
		
		return wert;
	}
	
	private int min(Spielstand spielstand, Player spieler, Rule regeln){
		läufe++;
		Player tempPlayer;
		if(spieler == globalPlayer){
			tempPlayer = shadowPlayer;
		} else {
			tempPlayer = spieler;
		}
		
		if( regeln.unentschieden(spielstand) ){
			return 0;
		} else if( regeln.spielerGewinnt(spielstand) != null){
			if(regeln.spielerGewinnt(spielstand) == spieler){
				return 1;
			} else {
				return -1;
			}
		}
		
		int wert = 2;
		for(Spielstand s : successor(spielstand, regeln, tempPlayer)){
			if(s != null){
				int maximal = max(s, spieler, regeln);
				if( wert > maximal) {
					wert = maximal;
				}
			} else {
				break;
			}
		}
		
		return wert;
	}
	
	private Spielstand kopiereSpielstand(Spielstand spielstand){
		Player[][] spielfeldKopie = new Player[3][3];
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				spielfeldKopie[x][y] = spielstand.getPlayerOnPosition(x, y);
			}
		}
		return new Spielstand(spielfeldKopie);
	}
}
