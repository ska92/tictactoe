package VierGewinnt;

import java.util.Observable;

import Game.Viewer;
import Game.Spielfelder.ISpielfeld;

public class VierGewinntViewer extends Viewer{

	@Override
	public void ausgabe(Object ausgabeObjekt) {
		// TODO Auto-generated method stub
		System.out.println(ausgabeObjekt.toString());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1 == null)
			return;
		
		ISpielfeld spielfeld = (ISpielfeld)arg1;
		
		System.out.println("----------------");
		for (int i = 0; i < 3; i++){
			for (int x = 0; x < 3; x++){
				if (spielfeld.getPlayerOnPosition(i,x) == null){
					System.out.print(" ");
				} else {
					System.out.print(spielfeld.getPlayerOnPosition(i, x).getGameSymbol());
				}
				
				if (x <= 1)
					System.out.print("|");
			}
			System.out.println();
		}
	}

}
