package pgm.tictactoe.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import pgm.tictactoe.Game;
import pgm.tictactoe.Player;
import pgm.tictactoe.Position;
import pgm.tictactoe.Spielfeld;

public class GameData implements Serializable {

	private String spielVerzeichnis;
	private String dateiname;
	private String dateinameSerial;

	public GameData(String spielVerzeichnis, String dateiname) {
		if (spielVerzeichnis == null) {
			this.spielVerzeichnis = System.getProperty("user.dir")
					+ System.getProperty("file.separator") + "save"
					+ System.getProperty("file.separator");
		} else {
			this.spielVerzeichnis = spielVerzeichnis;
		}
		
		this.dateiname = this.spielVerzeichnis + dateiname + ".ttt";
		this.dateinameSerial = this.spielVerzeichnis + dateiname + ".sttt";

		/**
		 * Prüfen ob das Verzeichnis des Speichstände vorhanden ist, wenn nein
		 * dann erstellen
		 */
		File speicherVerzeichnis = new File(this.spielVerzeichnis);
		if (!speicherVerzeichnis.exists())
			speicherVerzeichnis.mkdir();

	}
	
	public void leereSpielstand(){
		File speicherdatei = new File(this.dateiname);
		if(speicherdatei.exists()){
			try {
				FileWriter fw = new FileWriter(speicherdatei);
				fw.write("");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Speicherstand ladeSpielstand(Player spieler1, Player spieler2) throws IOException, ClassNotFoundException {
		System.out.println("Spielstände:");
		ArrayList<File> speicherstandListe = new ArrayList<File>();
		File speicherVerzeichnis = new File(spielVerzeichnis);
		if(speicherVerzeichnis.exists()){
			for(File datei : speicherVerzeichnis.listFiles()){
				if(datei.getName().toLowerCase().endsWith(".ttt") || datei.getName().toLowerCase().endsWith(".sttt")){
					speicherstandListe.add(datei);
					System.out.println(speicherstandListe.size() + ". " + datei.getName());
				}
			}
		}
		
		if(speicherstandListe.size() == 0)
			return null;
		
		Scanner tastatur = new Scanner(System.in);
		System.out.print("Welcer Spielstand soll geladen werden?");
		int auswahl = tastatur.nextInt();
		if(auswahl <= 0 || auswahl > speicherstandListe.size())
			return null;
		
		File speicherstandDatei = speicherstandListe.get(auswahl-1);
		if(speicherstandDatei.getName().endsWith(".ttt"))
			return ladeSpielTextdatei(speicherstandDatei, spieler1, spieler2);
		else
			return ladeSpielSerial(speicherstandDatei);
	}
	
	
	private Speicherstand ladeSpielTextdatei(File speicherstand, Player spieler1, Player spieler2) throws FileNotFoundException{
		Spielfeld spielfeld = new Spielfeld();
		Scanner datei = new Scanner(new FileReader(speicherstand));
		
		Player letzterSpieler = null;
		
		while(datei.hasNextLine()){
			String zeile = datei.nextLine();
			
			//Zeile parsen
			String[] teile = zeile.split(":");
			int spielerID = Integer.parseInt(teile[0]);
			int posX = Integer.parseInt(teile[2]);
			int posY = Integer.parseInt(teile[3]);
			
			if(spielerID == spieler1.getId()){
				spielfeld.setzeSpieler(spieler1, new Position(posX, posY));
				letzterSpieler = spieler1;
			} else {
				spielfeld.setzeSpieler(spieler2, new Position(posX, posY));
				letzterSpieler = spieler2;
			}
		}
		
		spielfeld.ausgabeSpielfeld();
		
		return new Speicherstand(spielfeld, letzterSpieler);
	}
	
	private Speicherstand ladeSpielSerial(File speicherstand) throws IOException, ClassNotFoundException {
		InputStream fis = new FileInputStream(dateinameSerial);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object temp = ois.readObject();
		Game geladenesSpiel = (Game)temp;
		return new Speicherstand(geladenesSpiel.getSpielfeld(), geladenesSpiel.getLetzterSpieler(), geladenesSpiel.getSpieler1(), geladenesSpiel.getSpieler2());
	}
	public void speicherSpielstandTextdatei(Player spieler, Position spielzug) throws IOException {
		FileWriter fw = new FileWriter(dateiname, true);
		fw.write(spieler.getId()+ ":" + spieler.getName() + ":" + spielzug.getX() + ":" + spielzug.getY() + System.getProperty("line.separator"));
		fw.close();
	}

	public void speichereSpielSerial(Game spiel) throws IOException {
		OutputStream fos = new FileOutputStream(dateinameSerial);
		ObjectOutputStream oos = new ObjectOutputStream( fos );
		oos.writeObject(spiel);
		oos.close();
	}



	public boolean hasSpielstand() {
		File speicherVerzeichnis = new File(spielVerzeichnis);
		if(speicherVerzeichnis.exists()){
			for(File datei : speicherVerzeichnis.listFiles()){
				if(datei.getName().toLowerCase().endsWith(".ttt") || datei.getName().toLowerCase().endsWith(".sttt"))
					return true;
			}
		}
		return false;
	}

}
