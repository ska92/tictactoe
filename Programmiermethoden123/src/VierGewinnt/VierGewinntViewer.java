package VierGewinnt;

import Game.Viewer;
import Game.Spielfelder.ISpielfeld;

public class VierGewinntViewer extends Viewer{

	@Override
	public void ausgabe(Object ausgabeObjekt) {
		System.out.print(ausgabeObjekt.toString());
		System.out.flush();
	}

	@Override
	public void update(ISpielfeld spielfeld) {
		System.out.println("----------------");
		for (int i = 0; i < spielfeld.getMaxX(); i++){
			for (int x = 0; x < spielfeld.getMaxY(); x++){
				if (spielfeld.getPlayerOnPosition(i,x) == null){
					System.out.print(" ");
				} else {
					System.out.print(spielfeld.getPlayerOnPosition(i, x).getGameSymbol());
				}
				
				if (x <= 5)
					System.out.print("|");
			}
			System.out.println();
		}
		System.out.flush();
	}

}
