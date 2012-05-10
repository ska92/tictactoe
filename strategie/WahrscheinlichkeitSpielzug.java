package pgm.tictactoe.strategie;

import java.io.Serializable;

public abstract class WahrscheinlichkeitSpielzug implements IStrategie, Serializable {	
	protected IStrategie strategie;
	
	protected abstract boolean ersetzeSpielzug(int zufallsWert);
}
