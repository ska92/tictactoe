package Game;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Utils.Position;

public abstract class GameController {
	
	protected Model model;
	protected Viewer viewer;
	protected IRegeln regeln;
	
	public GameController(String[] args, IRegeln regeln, Model gameModel, Viewer viewer){
		this.regeln = regeln;
		this.model = gameModel;
		this.viewer = viewer;
		
		model.addObserver(viewer);
	}
	
	public void parameterAuswerten(String args[]){
		Options opts = new Options();
		opts.addOption(new Option("h","help",false,"Ausgabe der Hilfe"));
		opts.addOption(new Option("d","debug", false, "Debug modus setzen"));
		opts.addOption(new Option("f","file",true, "Datei zur ausgabe"));
		
		PosixParser parser = new PosixParser();
		try {
			CommandLine line = parser.parse(opts, args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void startGame(){
		System.out.printf("Neues Spiel: %s %n", this.getClass().getSimpleName());
		System.out.printf("Spieler1: %s (%s) %n", model.getSpieler1().getName(), model.getSpieler1().getGameSymbol());
		System.out.printf("Spieler2: %s (%s) %n", model.getSpieler2().getName(), model.getSpieler2().getGameSymbol());
		
		boolean spielende = false;
		Spieler letzterSpieler = null;
		
		while(!spielende){
			
			if(letzterSpieler != null){
				if(regeln.spielerGewinnt(model.getSpielfeld(), letzterSpieler)){
					viewer.ausgabe(letzterSpieler.getName() + " gewinnt.");
					spielende = true;
					continue;
				}
				
				if(regeln.unentschieden(model.getSpielfeld())){
					viewer.ausgabe("Unentschieden. Spiel ist zuende.");
					spielende = true;
					continue;
				}
			}
			
			if(letzterSpieler != model.getSpieler1())
				letzterSpieler = model.getSpieler1();
			else
				letzterSpieler = model.getSpieler2();
			
			Position spielzug = letzterSpieler.getStrategie().nextMove(regeln, model.getSpielfeld(), letzterSpieler);
			model.getSpielfeld().setPlayerOnPosition(spielzug, letzterSpieler);
			
			model.dataChanged();
		}
	}

}
