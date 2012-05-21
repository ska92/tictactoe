package Game;



import java.util.ArrayList;

import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.IObservable;
import Game.Utils.IObserver;

public abstract class Model implements IObservable{

	private Spieler spieler1, spieler2;
	private ISpielfeld spielfeld;
	
	private ArrayList<IObserver> beobachterListe;
	
	public Model(Spieler spieler1, Spieler spieler2, ISpielfeld spielfeld){
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		this.spielfeld = spielfeld;
		
		beobachterListe = new ArrayList<IObserver>();
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
	

	@Override
	public void addObserver(IObserver obs) {
		// TODO Auto-generated method stub
		beobachterListe.add(obs);
	}

	@Override
	public void removeObserver(IObserver obs) {
		// TODO Auto-generated method stub
		if(beobachterListe.contains(obs))
			beobachterListe.remove(obs);
	}

	@Override
	public void notifyAll(ISpielfeld spielfeld) {
		// TODO Auto-generated method stub
		for(IObserver obs : beobachterListe){
			obs.update(spielfeld);
		}
	}
	
}
