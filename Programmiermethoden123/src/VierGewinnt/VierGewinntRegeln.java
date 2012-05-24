package VierGewinnt;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;
import Game.Utils.Position;

public class VierGewinntRegeln implements IRegeln{

	@Override
	public boolean unentschieden(ISpielfeld sp) {
		int anzahlGesetzterFelder = 0;
		for(int x=0; x<4; x++){
			for(int y=0; y<4; y++){
				if(sp.getPlayerOnPosition(x, y) != null)
					anzahlGesetzterFelder++;
			}
		}
		if(anzahlGesetzterFelder == 16)
			return true;
		
		return false;
	}
	

	@Override
	public boolean spielerGewinnt(ISpielfeld spielfeld, Spieler spieler) {
		//Hori
		for (int i = 0; i < 6; i++)
		{
			for(int g=0; g < 4;g++)
			{
				if ((spielfeld.getPlayerOnPosition(i, g) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, g+1) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, g+2) == spieler)
					&& (spielfeld.getPlayerOnPosition(i, g+3) == spieler))
				return true;
			}
		}
		// Vertikal
		for (int i = 0; i < 7; i++)
		{
			for(int g=0;g < 3;g++)
			{ 
				if ((spielfeld.getPlayerOnPosition(g, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+1, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+2, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+3, i) == spieler))
				return true;
			}
		}
		// Diagonal
		// Links-Oben nach Rechts-Unten
		for (int i = 0; i < 4; i++)
		{
			for(int g=0;g < 3;g++)
			{ 
				if ((spielfeld.getPlayerOnPosition(g, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+1, i+1) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+2, i+2) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+3, i+3) == spieler))
				return true;
			}
		}
		// Rechts-Oben nach Links-Unten
		for (int i = 6; i >= 3; i--)
		{
			for(int g=0;g < 3;g++)
			{ 
				if ((spielfeld.getPlayerOnPosition(g, i) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+1, i-1) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+2, i-2) == spieler)
					&& (spielfeld.getPlayerOnPosition(g+3, i-3) == spieler))
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean spielfeldBelegt(ISpielfeld sp, Position pos) {
		if((sp.getPlayerOnPosition(pos.getX(), pos.getY()) == null) && (spielZugmoeglich(sp,pos)))
			return false;
		else
			return true;
	}

	@Override
	public boolean positionInGrenze(ISpielfeld sp, Position pos) {
		if(pos.getX() >= 0 && pos.getX() < sp.getMaxX()){
			if(pos.getY() >= 0 && pos.getY() < sp.getMaxY())
				return true;
		}
		return false;
	}
	
	public boolean spielZugmoeglich(ISpielfeld sp, Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		//int x = 0;
		//int y = 5;
		if((sp.getPlayerOnPosition(x, y)==null)&&(x==5))
		{
			return true;
		}
		else if((sp.getPlayerOnPosition(x, y)==null)&&(sp.getPlayerOnPosition(x+1, y)!=null))
		{
			return true;
		}
		return false;
	}

}
