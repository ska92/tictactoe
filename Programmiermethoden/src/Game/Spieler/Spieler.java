package Game.Spieler;

import Game.Strategien.IStrategie;
import Game.Strategien.StrategieZufall;

public class Spieler {
	
	private String name;
	private int spielstaerke;
	private char gameSymbol;
	private IStrategie strategie;
	
	
	public Spieler(String name, char gameSymbol){
		this.name = name;
		this.spielstaerke = 10;
		this.gameSymbol = gameSymbol;
		this.strategie = new StrategieZufall();
	}
	
	public IStrategie getStrategie(){
		return this.strategie;
	}
	
	public void setStrategie(IStrategie strategie){
		this.strategie = strategie;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setSpielstaerke(int wert){
		this.spielstaerke = wert;
	}
	
	public int getSpielstaerke(){
		return this.spielstaerke;
	}
	
	public char getGameSymbol(){
		return this.gameSymbol;
	}
	
	
	
}
