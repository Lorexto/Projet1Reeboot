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



//////////////////////////////////////////////FIN METHODES/////////////////////////////////////////

}


