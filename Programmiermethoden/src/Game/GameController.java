package Game;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;
import Game.Utils.GameData;
import Game.Utils.Position;

public abstract class GameController {

	protected Model model;
	protected Viewer viewer;
	protected IRegeln regeln;

	private boolean startGame = true;
	private boolean debug = false;
	private File ausgabeDatei = null;


	/**
	 * Erzeugt einen neuen GameController
	 * 
	 * @param args - Kommandozeilenparameter für die Auswertung
	 * @param regeln - Die für das Spiel zu nutzenden Regeln
	 * @param gameModel - Das Model welches das Spielfeld & Spieler für das aktuelle Spiel enthält
	 * @param viewer - Den für die ausgabe zu benutzenden Viewer
	 */
	public GameController(String[] args, IRegeln regeln, Model gameModel,
			Viewer viewer) {
		parameterAuswerten(args);

		this.regeln = regeln;
		this.model = gameModel;
		this.viewer = viewer;

		model.addObserver(viewer);

		if (debug) {
			System.err.println("Debug modus");
			System.err.println("Controller: " + this.getClass().getName());
			System.err.println("Regeln: " + regeln.getClass().getName());
			System.err.println("Model: " + gameModel.getClass().getName());
			System.err.println("Viewer: " + gameModel.getClass().getName());
			System.err.flush();
		}
	}

	private void parameterAuswerten(String args[]) {
		Options opts = new Options();
		opts.addOption(new Option("h", "help", false, "Ausgabe der Hilfe"));
		opts.addOption(new Option("d", "debug", false, "Debug modus setzen"));
		opts.addOption(new Option("f", "file", true, "Datei zur ausgabe"));

		PosixParser parser = new PosixParser();
		try {
			CommandLine line = parser.parse(opts, args);

			if (line.hasOption("help")) {
				HelpFormatter help = new HelpFormatter();
				help.printHelp("Hilfe", opts);
				startGame = false;
			}

			if (line.hasOption("debug")) {
				debug = true;
			}

			if (line.hasOption("file")) {
				String filename = line.getOptionValue("file");
				ausgabeDatei = new File(filename);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Parameter fehler.");
		}
	}

	
	/**
	 * Startet das Spiel
	 */
	public void startGame() {
		if (!startGame)
			return;
		
		if(ausgabeDatei != null){
			if(debug){
				System.err.println("Ausgabe in datei: " + ausgabeDatei.getAbsolutePath());
			}
			
			GameData gd = new GameData();
			try {
				gd.schreibeInfoDatei(ausgabeDatei, this, model, viewer, regeln);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if(debug){
					System.err.println("Fehler beim schreiben der infodatei");
				}
			}
		}
		
		if(debug){
			System.err.println("Starte Spiel");
			System.err.flush();
		}
		
		printInfo();				//Ausgabe der Spieler	

		boolean spielende = false;
		Spieler letzterSpieler = null;

		while (!spielende) {

			if (letzterSpieler != null) {
				if (regeln.spielerGewinnt(model.getSpielfeld(), letzterSpieler)) {
					viewer.ausgabe(letzterSpieler.getName() + " gewinnt. \n");
					spielende = true;			
					continue;
				}

				if (regeln.unentschieden(model.getSpielfeld())) {
					viewer.ausgabe("Unentschieden. Spiel ist zuende.");
					spielende = true;
					continue;
				}
			}

			if (letzterSpieler != model.getSpieler1())
				letzterSpieler = model.getSpieler1();
			else
				letzterSpieler = model.getSpieler2();
			
			
			Position spielzug = letzterSpieler.getStrategie().nextMove(regeln,
					model.getSpielfeld(), letzterSpieler);
			model.getSpielfeld().setPlayerOnPosition(spielzug, letzterSpieler);

			if(debug){
				System.err.println("Spieler: " + letzterSpieler.getName());
				System.err.printf("Spielzug: %d:%d %s %n", spielzug.getX(), spielzug.getY(), letzterSpieler.getStrategie().getClass().getName());
				System.err.flush();
			}
			
			model.notifyAll(model.getSpielfeld());
			
		}
		
		if(debug){
			System.err.println("Ende");
		}
	}
	
	private void printInfo(){
		Formatter fmt = new Formatter();
		fmt.format("Neues Spiel: %s %n", this.getClass().getSimpleName());
		fmt.format("Spieler1: %s (%s) : %s %n", model.getSpieler1().getName(),
				model.getSpieler1().getGameSymbol(), model.getSpieler1()
						.getStrategie().getClass().getSimpleName());
		fmt.format("Spieler2: %s (%s) : %s %n", model.getSpieler2().getName(),
				model.getSpieler2().getGameSymbol(), model.getSpieler1()
						.getStrategie().getClass().getSimpleName());
		viewer.ausgabe(fmt.toString());
	}

}
