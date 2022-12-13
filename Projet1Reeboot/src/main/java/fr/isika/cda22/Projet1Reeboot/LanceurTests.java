package fr.isika.cda22.Projet1Reeboot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LanceurTests {
	public static void main(String[] args) throws IOException {

		Noeud3 test1= new Noeud3();
		System.out.println("Lecture du fichier bin");

		try {

			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
			raf.setLength(0);

			test1 = readTxts(raf);

			//test1.lireParentSuivant(4, raf);

			//Noeud3.searchInBinFile(test1, fichierBIN, raf, "LECLERC");
			//test1.lireParentSuivant(0, raf);
			//test1.successeur(test1.lireParentSuivant(12, raf), raf);

			//Noeud3 test2= new Noeud3();
			//test2.ajouterStagiaire(test2, test1, raf);



			raf.close();

} catch (IOException e) {
			e.printStackTrace();
		}

		//Noeud3.searchInBinFile(test1, raf, "MADAVANE");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		LectureBin.LectureBin();


	}

public static Noeud3 readTxts(RandomAccessFile raf) {

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
					cptNoeud++;
					System.out.println("");
					System.out.println("Lecture .DON de " + data[0]);
				}



				n = new Noeud3(st, -1, -1, -1, cptNoeud); // le nouveau neoud correspond à la ligne lue dans le .DON
				cptNoeud++;


				//File fichierBIN= new File("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin");
				//n.ajouterStagiaire(n, nParent, raf);


				a.ajouter(n, nParent, raf);


			}//while br
			br.close();
			fr.close();
			//LectureBin.LectureBin();
			return n;



		} catch (IOException e) {
			System.out.println("on est dans l'eception du lanceur");
			e.printStackTrace();
			return null;
		}

	}

}
