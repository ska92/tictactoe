package Game;

import java.util.Observable;

import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;

public abstract class Model extends Observable{

	protected Spieler spieler1, spieler2;
	protected ISpielfeld spielfeld;
	
	public Model(Spieler spieler1, Spieler spieler2, ISpielfeld spielfeld){
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		this.spielfeld = spielfeld;
	}
	
	public Spieler getSpieler1(){
		return this.spieler1;
	}
	
	public Spieler getSpieler2(){
		return this.spieler2;
	}
	
	public ISpielfeld getSpielfeld(){
		return this.spielfeld;
	}
	
	public void dataChanged(){
		setChanged();
		notifyObservers(spielfeld);
	}
	
}
