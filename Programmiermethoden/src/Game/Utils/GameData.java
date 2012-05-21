package Game.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import Game.GameController;
import Game.Model;
import Game.Viewer;
import Game.Regeln.IRegeln;
import Game.Spieler.Spieler;

public class GameData {

	
	/**
	 * Schreibt alle Relevanten Spielinformationen in eine datei.
	 * 
	 * @param ausgabeDatei - Datei in die geschrieben werden soll
	 * @param gc - Der aktuelle GameController
	 * @param m - Das aktuelle Model
	 * @param v - Der aktuelle Viewer
	 * @param regeln - Die verwendeten Spielregeln;
	 * @throws IOException 
	 */
	public void schreibeInfoDatei(File ausgabeDatei, GameController gc,
			Model m, Viewer v, IRegeln regeln) throws IOException {

		if(!ausgabeDatei.exists()){
			ausgabeDatei.createNewFile();
		}
		
		PrintWriter pw = new PrintWriter(ausgabeDatei);
		Formatter fmt = new Formatter(pw);
		
		fmt.format("controller:%s %n", gc.getClass().getName());
		fmt.format("model:%s %n", m.getClass().getName());
		fmt.format("spielfeld:%s %n", m.getSpielfeld().getClass().getName());
		fmt.format("spieler:%s %n", m.getSpieler1().getClass().getName());
		fmt.format("spieler1:%s (%s) %n", m.getSpieler1().getName(), m.getSpieler1().getGameSymbol());
		fmt.format("spieler2:%s (%s) %n", m.getSpieler2().getName(), m.getSpieler2().getGameSymbol());
		fmt.format("regeln:%s %n", regeln.getClass().getName());
		fmt.format("viewer:%s %n", v.getClass().getName());
		
		fmt.close();
	}
	
	/**
	 * Lädt einen GameController aus den angaben einer Datei
	 * 
	 * @param datei - Datei aus der der GameController geladen werden soll
	 * @return - Geladener GameController
	 * @throws IOException
	 */
	public GameController ladeSpielAusDatei(File datei) throws IOException{
		Scanner in = new Scanner(new FileReader(datei));
		
		HashMap<String, String>  infos = new HashMap<String, String>();
		
		while(in.hasNextLine()){
			String line = in.nextLine();
			if(pruefeFormat(line)){
				String[] split = line.split(":");
				infos.put(split[0], split[1]);
			} else {
				throw new IOException("Falsches Format");
			}
		}
		
		GameController gc = null;
		
		return gc;
	}
	
	private boolean pruefeFormat(String line){
		if(Pattern.matches("[a-zA-Z]*:", line))
			return true;
		else
			return false;
	}
	
	
	
	/**
	 * Speichert einen Spielzug in einer Tabelle im Excel-Format
	 * 
	 * @param spielzug - Der Spielzug des Spielers
	 * @param spieler - Der Spieler der den Spielzug vollzogen hat
	 * @throws IOException
	 */
	public void speichereInTabelle(Position spielzug, Spieler spieler) throws IOException {
		
	}

}
