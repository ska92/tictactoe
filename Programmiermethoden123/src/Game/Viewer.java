package Game;

import Game.Utils.IObserver;


public abstract class Viewer implements IObserver{
	
	public abstract void ausgabe(Object ausgabeObjekt);
	
}
