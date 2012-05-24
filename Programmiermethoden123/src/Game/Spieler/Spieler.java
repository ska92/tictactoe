package Game.Spieler;

import Game.Strategien.IStrategie;
import Game.Strategien.StrategieZufall;

public class Spieler {
	
	private String name;
	private int spielstaerke;
	private char gameSymbol;
	private IStrategie strategie;
	
	/**
	 * Erzeugt einen neuen Spieler.
	 * @param name - Der name des Spielers
	 * @param gameSymbol - Das Spielsymbol des Spielers.
	 */
	public Spieler(String name, char gameSymbol){
		this.name = name;
		this.spielstaerke = 10;
		this.gameSymbol = gameSymbol;
		this.strategie = new StrategieZufall();
	}
	
	/**
 	 * @return Die aktuelle Strategie des Spielers
	 */
	public IStrategie getStrategie(){
		return this.strategie;
	}
	
	/**
	 * Setzte die Strategie des Spielers
	 * @param strategie - Die neue Strategie
	 */
	public void setStrategie(IStrategie strategie){
		this.strategie = strategie;
	}
	
	/**
	 * Gibt den Namen des Spielers 
	 * @return - Den namen des Spielers
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Setzte die Spielstärke in Prozent des Spielers. Werte innerhalb 0-100.
	 * @param wert - Die neue Spielstärke
	 */
	public void setSpielstaerke(int wert){
		this.spielstaerke = wert;
	}
	
	/**
	 * 
	 * @return - Die Spielstärke des Spielers in Prozent
	 */
	public int getSpielstaerke(){
		return this.spielstaerke;
	}
	
	/**
	 * 
	 * @return - Das Spielsymbol des Spielers.
	 */
	public char getGameSymbol(){
		return this.gameSymbol;
	}
	
	
	
}
