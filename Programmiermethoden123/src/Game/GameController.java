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
import HSSF.Write;

public abstract class GameController {

	protected Model model;
	protected Viewer viewer;
	protected IRegeln regeln;
	
	private GameData gxls;
	private boolean startGame = true;
	private File paramDatei = null;
	
	/**
	 * Gibt an, ob der Debug-Modus aktiv ist.
	 * Wenn true, Debug-Modus aktiv, wenn false Debug-Modus nicht aktiv.
	 */
	public static boolean debug = false;


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
		if(args != null)
			parameterAuswerten(args);
		this.regeln = regeln;
		this.model = gameModel;
		this.viewer = viewer;

		model.addObserver(viewer);
		
		//Neue Tabelle bzw neuen Sheet erstellen
		
		gxls = new GameData();
		gxls.erstelleTabelle("spielo.xls");
		
		//Spielstand laden
		if(paramDatei != null){
			if(debug){
				System.err.println("Spielstand wird geladen aus Datei:  " + paramDatei.getAbsolutePath());
			}
			try {
				GameController gc = this;
				GameData gd = new GameData();
				gc = gd.ladeSpielAusDatei(paramDatei);
			} catch (IOException e) {
				System.err.println("Spiel konnte nicht geladen werden: " + e.getMessage());
			}
		}
	}

	//Wertet die Parameter aus der Kommandozeile aus
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
				paramDatei = new File(filename);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Parameter fehler.");
		}
		
		gxls = new GameData();
	}

	
	/**
	 * Startet das Spiel
	 */
	public void startGame() {
		
		
		if (!startGame)
			return;
		
		if (debug) {
			System.err.println("Debug modus");
			System.err.println("Controller: " + this.getClass().getName());
			System.err.println("Regeln: " + regeln.getClass().getName());
			System.err.println("Model: " + model.getClass().getName());
			System.err.println("Viewer: " + model.getClass().getName());
			System.err.flush();
		}
		
		try{
			GameData gd = new GameData();
			gd.schreibeInfoDatei(new File("info.txt"), this, model, viewer, regeln);
		}catch(IOException e){
			System.err.println("Fehler beim schreiben der InfoDatei " + e.getMessage());
		}
		
		if(debug){
			System.err.println("Starte Spiel");
			System.err.flush();
		}
		
		printInfo();		//Ausgabe der Spieler	

		boolean spielende = false;
		Spieler letzterSpieler = null;
		long time = 0;
		long lasttime = System.currentTimeMillis();

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

			//Spieler wechseln
			if (letzterSpieler != model.getSpieler1())
				letzterSpieler = model.getSpieler1();
			else
				letzterSpieler = model.getSpieler2();
			
			//Nächsten Spielzug generieren.
			time = System.currentTimeMillis()- lasttime;
			lasttime = System.currentTimeMillis();
			Position spielzug = letzterSpieler.getStrategie().nextMove(regeln,
					model.getSpielfeld(), letzterSpieler);
			
			//Spielzug in Tabelle schreiben
			gxls.speichereInTabelle(spielzug, letzterSpieler,time);
			
			
			//Spielzug aufs Spielfeld setzen.
			model.getSpielfeld().setPlayerOnPosition(spielzug, letzterSpieler);

			model.notifyAll(model.getSpielfeld());
			
			
			if(debug){
				System.err.println("Spieler: " + letzterSpieler.getName());
				System.err.println("Spielzug: " + spielzug.getX() + ":" + spielzug.getY());
				System.err.flush();
			}
			
		}
		
		if(debug){
			System.err.println("Ende");
		}
		gxls.schreiben();
	}
	
	
	//Gibt Informationen zum Spiel aus
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
