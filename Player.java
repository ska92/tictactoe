package pgm.tictactoe;

import java.io.Serializable;

import pgm.tictactoe.strategie.IStrategie;
import pgm.tictactoe.strategie.StrategieEingabe;


public class Player implements Serializable{
	
	public static final int LEICHT = 80;
	public static final int MITTEL = 50;
	public static final int SCHWER = 10;
	
	private static int idGenerator = 1;

	private String name;
	private char gameSymbol;
	private IStrategie strategie;
	private int wahrscheinlichkeitSpielzugZufall;
	private int id;
	
	public Player(String name, char gameSymbol){
		this.name = name;
		this.gameSymbol = gameSymbol;
		this.strategie = new StrategieEingabe();
		this.wahrscheinlichkeitSpielzugZufall = 10;
		
		this.id = idGenerator;
		idGenerator++;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getWahrscheinlichkeitSpielzugZufall(){
		return this.wahrscheinlichkeitSpielzugZufall;
	}
	
	public void setWahrscheinlichkeitSpielzugZufall(int wert){
		if(wert >= 0 && wert <= 100)
			this.wahrscheinlichkeitSpielzugZufall = wert;
	}
	
	public String getName(){
		return this.name;
	}
	
	public char getGameSymbol(){
		return this.gameSymbol;
	}
	
	public void setStrategie(IStrategie neueStrategie){
		this.strategie = neueStrategie;
	}
	
	public Position nextMove(Rule regeln){
		return strategie.generateNextMove(this, regeln);
	}
}
