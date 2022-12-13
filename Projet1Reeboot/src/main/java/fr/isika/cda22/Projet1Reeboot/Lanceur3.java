package fr.isika.cda22.Projet1Reeboot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Lanceur3 {

	public final static int TAILLE_NOEUD = 132;
	public final static int TAILLE_NOM = 2*20;
	public final static int TAILLE_IND_FG = 2*4;
	public final static int TAILLE_IND_FD = 2*4;
	public final static int TAILLE_IND_DBL = 2*4;

	//@SuppressWarnings("finally") // pour que le finally s'exécute correctement
	public static void main(String[] args) {
		Noeud3 n = new Noeud3();
		Arbre3 b= new Arbre3();

		System.out.println("Lecture du fichier bin");

				try {
					RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
					raf.setLength(0);

					n = readTxt(raf);

					raf.close();


		} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println("");
				System.out.println("");
				System.out.println("");
				//LectureBin.LectureBin();
				System.out.println("HAHAHAHAHAHHAAHHA");
				try {
					RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
			     n.searchInBinFile(raf, "ROIGNANT");
//					raf.seek(0);
//                   LectureBin.LectureBin();
                  // n.SupprimerNoeudStagiaireV2( n.searchInBinFile(raf, "AUGEREAU"), raf);
                  LectureBin.LectureBin();
                  
                  Stagiaire Paul= new Stagiaire("HAHAHA", "Paul", "33", "CDA22", "2008");
                  System.out.println(raf.length());
                  Noeud3 nPaul= new Noeud3(Paul, -1, -1, -1, ((int)raf.length()/TAILLE_NOEUD));
                  Noeud3.ajouterStagiaire(nPaul,(Noeud3.lireParentSuivant(0, raf)) , raf);
                  LectureBin.LectureBin();
                  //LectureBin.LectureBinSansLesSupprimes();
				} catch (IOException e) {
					e.printStackTrace();
				}



				} // fin main// fin main


	///////////////////////////////////////////////
	/// LIRE STAGIAIRES A PARTIR DU FICHIER TEXTE
	//////////////////////////////////////////////
	public static Noeud3 readTxt(RandomAccessFile raf) {
		// FICHIER TOT ;src/main/java/fr/isika/cda22/Projet_1/STAGIAIRES.DON
		//FIchier TXT Test nb reduit : src/main/java/fr/isika/cda22/Projet_1/TestStagiairesdizaine.txt
		// FICHIER TEST: src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin

		try {
			Noeud3 n = null;
			Noeud3 nParent = null;
			Arbre3 a = new Arbre3();
			File file= new File("src/main/java/fr/isika/cda22/Projet1Reeboot/TestStagiairesdizaine.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			int cptNoeud = 0;
			while (br.ready()) {

				String[] data = new String[6]; // tableau de taille 6
				data[0] = br.readLine(); // nom
				data[1] = br.readLine(); // prenom
				data[2] = br.readLine(); //dpt
				data[3] = br.readLine(); //promo
				data[4] = br.readLine(); // année passée en String
				data[5] = br.readLine(); // pour lire le caractère *
				Stagiaire st = new Stagiaire(data[0], data[1], data[2], data[3], data[4]);
				System.out.println(st);
				if (cptNoeud == 0) {
					nParent = new Noeud3(st, -1, -1, -1, cptNoeud); // on part toujours du parent racine
				}
				System.out.println("");
				System.out.println("Lecture .DON de " + data[0]);
				n = new Noeud3(st, -1, -1, -1, cptNoeud); // le nouveau neoud correspond à la ligne lue dans le .DON
				cptNoeud++;
				Noeud3.ajouterStagiaire(n, nParent, raf);
			}//while br
			br.close();
			fr.close();
			return n;
		} catch (IOException e) {
			System.out.println("on est dans l'eception du lanceur");
			e.printStackTrace();
			return null;
		}

	}



} // fin lanceur2