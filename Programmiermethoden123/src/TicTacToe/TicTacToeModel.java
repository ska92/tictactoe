package TicTacToe;

import Game.Model;
import Game.Spieler.Spieler;
import Game.Spielfelder.ISpielfeld;

public class TicTacToeModel extends Model{

	
	/**Erzeugt ein neues Spielmodel mit dem Spielfeld und den beiden Spielern.
	 * 
	 * @param spieler1 - Spieler1 
	 * @param spieler2 - Spieler2
	 * @param spielfeld - Das Spielfeld für das Spiel
	 */
	public TicTacToeModel(Spieler spieler1, Spieler spieler2,
			ISpielfeld spielfeld) {
		super(spieler1, spieler2, spielfeld);
		// TODO Auto-generated constructor stub
	}

}
