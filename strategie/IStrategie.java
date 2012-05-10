package pgm.tictactoe.strategie;

import java.io.Serializable;

import pgm.tictactoe.Player;
import pgm.tictactoe.Position;
import pgm.tictactoe.Rule;

public interface IStrategie {
	public Position generateNextMove(Player spieler, Rule regeln);
}
