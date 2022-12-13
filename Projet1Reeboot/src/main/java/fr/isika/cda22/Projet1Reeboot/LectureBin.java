package fr.isika.cda22.Projet1Reeboot;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LectureBin {

	public static void LectureBin() {


	System.out.println("Lecture du fichier bin");
try {	
try {
	RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
    raf.seek(0);
    int FileSize = (int)raf.length();
	int NumNoeud;
System.out.println("Lecture du fichier bin");
while(raf.getFilePointer()<FileSize) {
for (int j = 0; j < 58; j++) {
	String contenu = "";
	for(int i = 0; i < 58 ; i++) {
		contenu += raf.readChar();
	}
	for(int i = 0; i < 4 ; i++) {
		contenu += raf.readInt();
	}
	NumNoeud= (int) ((raf.getFilePointer()-132)/132);
	System.out.println("Noeud " + NumNoeud + " : "+contenu +"\n");
}

}

}  catch (EOFException eofe) {
    System.out.println("End of file reached");
   }
}catch (IOException ioe) {
        ioe.printStackTrace();
    }

	}


	public static ObservableList<String> LectureBinpourFX() {

		System.out.println("Lecture du fichier bin apres suppression");
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
		    raf.seek(0);
			int NumNoeud;
		System.out.println("Lecture du fichier bin");
		for (int j = 0; j < 58; j++) {
			String contenu = "";
			for(int i = 0; i < 58 ; i++) {
				contenu += raf.readChar();
			}
			for(int i = 0; i < 4 ; i++) {
			 contenu += raf.readInt();
			}
			NumNoeud= (int) ((raf.getFilePointer()-132)/132);

			System.out.println("Noeud " + NumNoeud + " : "+contenu +"\n");

		}
		raf.close();
		} catch (IOException e) {
		  e.printStackTrace();}
		return null;
	}


	public static ObservableList<String> LireBINFx() {
		try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");








		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;

	}


//	public static ObservableList<Stagiaire> createArrayListFromBIN() {
//
//		try {
//			 RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
//
//			int numNoeudALire= (int) raf.getFilePointer()/132;
//			raf.seek(0);
//		while(numNoeudALire<= raf.length()/132) {
//			raf.seek(numNoeudALire * Noeud3.TAILLE_NOEUD);
//			System.out.println("dans le lire" + (int)raf.getFilePointer());
//				String nom = "";
//				for(int i = 0; i < 20 ; i++) {
//					nom += raf.readChar();
//				}
//				String prenom = "";
//				for(int i = 0; i < 20 ; i++) {
//					prenom += raf.readChar();
//				}
//				String dpt = "";
//				for(int i = 0; i < 4 ; i++) {
//					dpt += raf.readChar();
//				}
//				String id = "";
//				for(int i = 0; i < 10 ; i++) {
//					id += raf.readChar();
//				}
//				String annee = "";
//				for(int i = 0; i < 4 ; i++) {
//					annee += raf.readChar();
//				}
////////////////////// LIRE les indices//////////////////////////////
//				int FG = raf.readInt();
//				int FD = raf.readInt();
//				int DBL = raf.readInt();
//				int NumNoeud = raf.readInt();
//
//				Stagiaire st = new Stagiaire(nom, prenom, dpt, id, annee);
//				ArrayList<Stagiaire>Stagiaires= new ArrayList<>();
//				Stagiaires.add(st);
//				ObservableList<Stagiaire> ListeStagiaires= FXCollections.observableArrayList(Stagiaires);
//				return ListeStagiaires;
//	}
//		raf.close();
//		}catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		return null;
//	}
//		return null;
//
//
//}


}


