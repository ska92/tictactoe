package Game;

import java.util.Observer;


public abstract class Viewer implements Observer{
	
	public abstract void ausgabe(Object ausgabeObjekt);
	
}
