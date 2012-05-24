package TicTacToe4x4;

import Game.Viewer;
import Game.Spielfelder.ISpielfeld;

public class TicTacToe4x4Viewer extends Viewer{

	@Override
	public void ausgabe(Object ausgabeObjekt) {
		System.out.print(ausgabeObjekt.toString());
		System.out.flush();
	}

	@Override
	public void update(ISpielfeld spielfeld) {
		System.out.println("----------------");
		for (int i = 0; i < 4; i++){
			for (int x = 0; x < 4; x++){
				if (spielfeld.getPlayerOnPosition(i,x) == null){
					System.out.print(" ");
				} else {
					System.out.print(spielfeld.getPlayerOnPosition(i, x).getGameSymbol());
				}
				
				if (x <= 2)
					System.out.print("|");
			}
			System.out.println();
		}
		System.out.flush();
	}

}
