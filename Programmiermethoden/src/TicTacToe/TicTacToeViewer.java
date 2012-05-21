package TicTacToe;

import Game.Viewer;
import Game.Spielfelder.ISpielfeld;

public class TicTacToeViewer extends Viewer {

	@Override
	public void ausgabe(Object ausgabeObjekt) {
		// TODO Auto-generated method stub
		System.out.print(ausgabeObjekt.toString());
		System.out.flush();
	}

	@Override
	public void update(ISpielfeld spielfeld) {
		// TODO Auto-generated method stub
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
		System.out.flush();
	}

}
