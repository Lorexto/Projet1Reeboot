package fr.isika.cda22.Projet1Reeboot;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;



public class Noeud3 {

	public final static int TAILLE_MAX_NOM = 20; // en caractères ! pas en octets
	public final static int TAILLE_MAX_PRENOM = 20;
	public final static int TAILLE_MAX_DPT = 4;
	public final static int TAILLE_MAX_ID = 10;
	public final static int TAILLE_MAX_ANNEE = 4;
	public final static int TAILLE_CLE_OCTET = TAILLE_MAX_NOM * 2 + TAILLE_MAX_PRENOM * 2 + TAILLE_MAX_DPT * 2 + TAILLE_MAX_ID * 2
			+ TAILLE_MAX_ANNEE * 2; //116
	public final static int TAILLE_IND_FG = 4;
	public final static int TAILLE_IND_FD = 4;
	public final static int TAILLE_IND_DBL = 4;
	public final static int TAILLE_NUM_NOEUD = 4; // 1 int 4 octets
	public final static int TAILLE_NOEUD = TAILLE_CLE_OCTET + TAILLE_IND_FG + TAILLE_IND_FD + TAILLE_IND_DBL + TAILLE_NUM_NOEUD; // 128

	///////////////////////// attributs///////////////////////////////////////
	private Stagiaire cle;
	private Stagiaire4Liste cleBis;
	private int filsGauche;
	private int filsDroit;
	private int doublon;
	private int numeroNoeud;
	private int compteTours=0;


	///////////////////////// Constructeurs////////////////////////////////

	public Noeud3() {
		super();
	}
	public Noeud3(Stagiaire cle, int filsGauche, int filsDroit, int doublon, int numeroNoeud) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.doublon = doublon;
		this.numeroNoeud = numeroNoeud;
	}

	///////////////////// getters & setters/////////////////////////////////////
	public Stagiaire getCle() {
		return cle;
	}
	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}
	public int getNumeroNoeud() {
		return numeroNoeud;
	}
	public void setNumeroNoeud(int numeroNoeud) {
		this.numeroNoeud = numeroNoeud;
	}
	public int getFilsGauche() {
		return filsGauche;
	}
	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}
	public Stagiaire4Liste getCleBis() {
		return cleBis;
	}
	public void setCleBis(Stagiaire4Liste cleBis) {
		this.cleBis = cleBis;
	}
	public int getFilsDroit() {
		return filsDroit;
	}
	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}
	public int getDoublon() {
		return doublon;
	}
	public void setDoublon(int doublon) {
			this.doublon = doublon;
		}

// /////////////////////TOSTRING/////////////////////////////////////////////
	@Override
	public String toString() {
		return "[cle=" + cle + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + ", doublon=" + doublon
				+ ", numeroNoeud=" + numeroNoeud + "]";
	}
//////////////////////////////////////////////////////////////////////////////////////
	//////// ECRIT LES STAGIAIRES EN FIN DE FICHIER BIN/////////
///////////////////////////////////////////////////////////////////////////////////////
	public static int ecrireNoeudFinBin(Noeud3 n,RandomAccessFile raf) {

		Stagiaire cle = n.getCle();
		try {
			raf.seek(raf.length());
			System.out.println(raf.length());
			raf.writeChars(cle.getNomLong());
			raf.writeChars(cle.getPrenomLong());
			raf.writeChars(cle.getDptLong());
			raf.writeChars(cle.getIdLong());
			raf.writeChars(cle.getAnneeLong());
			raf.writeInt(n.getFilsDroit());
			raf.writeInt(n.getFilsGauche());
			raf.writeInt(n.getDoublon());
			raf.writeInt(n.getNumeroNoeud());
			int curseur = (int)raf.getFilePointer();
			System.out.println(curseur);
			raf.close();
			return curseur;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;}
	}
	//////////////////////////////////////////////////////////////////////////

	public static void ajouterStagiaire(Noeud3 n, Noeud3 nParent,RandomAccessFile raf) throws FileNotFoundException, EOFException {

		File fileBIN= new File ("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin");
		raf = new RandomAccessFile(fileBIN, "rw");
		Stagiaire cle = n.getCle();
		Stagiaire cleParent = nParent.getCle();
		System.out.println("Ajout .bin " + cle);

//////SI NOM ET PRENOM EGAUX. NB= NE FONCTIONNE QUE AVEC LE CONTAINS

		 if ( (cleParent.getNom().contains(cle.getNom())) ) {
			 if(nParent.getDoublon()==-1) {
					ecrireNoeudFinBin(n, raf);
					nParent.setDoublon(n.getNumeroNoeud());
					modifierIndice(nParent.getNumeroNoeud() * TAILLE_NOEUD, n.getNumeroNoeud(),"DBL", raf);
					System.out.println("Ajout de " + n.getCle().getNom() + " doublon de " + nParent.getCle().getNom());
					System.out.println("Modification indice DBL de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
					}
				else {
				ajouterStagiaire(n, Noeud3.lireParentSuivant(nParent.getDoublon(), raf), raf);
				System.out.println("Ajout de " + n.getCle().getNom() + " doublon de " + nParent.getCle().getNom());
				System.out.println("++++++");
				}
		 }
		//if (cle.compareTo(cleParent)>0){// SI Valeur nouveau nom inferieur a valeur nom racine on va a gauche/////
		 else {
			 if(cle.compareTo(cleParent)<0) { // si on va à droite
				if (nParent.getFilsDroit() == -1) {
					ecrireNoeudFinBin(n, raf);
					//int curseur = ecrireNoeudFinBin(n, raf) - TAILLE_NOEUD;
					System.out.println("Ajout de " + n.getCle().getNom() + " fils droit de " + nParent.getCle().getNom());
					System.out.println("Modification indice FD de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
					// on met l'indice du noeud ici
					nParent.setFilsDroit(n.getNumeroNoeud());

					System.out.println("noued du parent " + nParent.getNumeroNoeud());
					//System.out.println("curseur correspondant : " + (nParent.getNumeroNoeud() * TAILLE_NOEUD));
					modifierIndice(nParent.getNumeroNoeud() * TAILLE_NOEUD, n.getNumeroNoeud(),"FD", raf);
					System.out.println(nParent);
			}else {// on passe au FD, via l'indice
				System.out.println("parent de " + n.getCle().getNom() + " : " + nParent.getCle().getNom());
				System.out.println("on veut aller au neu "+ nParent.getFilsDroit());
				ajouterStagiaire(n, Noeud3.lireParentSuivant(nParent.getFilsDroit(), raf), raf);
			}
		 }
//if (cle.compareTo(cleParent)>0){// SI Valeur nouveau nom inferieur a valeur nom racine on va a gauche/////
		 else {
					if (nParent.getFilsGauche() == -1) {
						ecrireNoeudFinBin(n, raf);
						//int curseur = ecrireNoeudFinBin(n, raf) - TAILLE_NOEUD;
						//System.out.println(curseur);​
						System.out.println("Ajout de " + n.getCle().getNom() + " fils gauche de " + nParent.getCle().getNom());
						System.out.println("Modification indice FG de " + nParent.getCle().getNom() + " à " + n.getNumeroNoeud());
						nParent.setFilsGauche(n.getNumeroNoeud());
						System.out.println(nParent.getNumeroNoeud());
						modifierIndice(nParent.getNumeroNoeud() * TAILLE_NOEUD, n.getNumeroNoeud(),"FG", raf);
						System.out.println(nParent);

					}else {// on passe au FD, via l'indice
						//System.out.println("Recherche ajout de " + n.getCle().getNom() + " vers " + nParent.noeudGauche.getCle());
						System.out.println("parent de " + n.getCle().getNom() + " : " + nParent.getCle().getNom());
						ajouterStagiaire(n, Noeud3.lireParentSuivant(nParent.getFilsGauche(), raf), raf);
						}
			}
		 }
		}

///////////////////////////////////////////////////////////////////////////
/////////////////MODIFIE LES INDICES FG FD DOUBLON AU FUR ET A MESURE
///////////////////////////////////////////////////////////////////////////
	public static void modifierIndice(int curseur, int num, String fils,RandomAccessFile raf) {

		try {
		 raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");

		if (fils == "FD") {
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+TAILLE_CLE_OCTET + TAILLE_IND_FG);
			raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_IND_DBL + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());
		}
		else if (fils == "FG"){
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+TAILLE_CLE_OCTET);
			System.out.println("curseur modif FG : "+(curseur + TAILLE_CLE_OCTET));
		    raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_IND_FD + TAILLE_IND_DBL + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());
		}
		else if(fils=="DBL") {
			System.out.println("avant "+(int)raf.getFilePointer());
			raf.seek(curseur+(TAILLE_CLE_OCTET+TAILLE_IND_FG+TAILLE_IND_FD));
			raf.writeInt(num);
			raf.seek((int)raf.getFilePointer() + TAILLE_NUM_NOEUD);
			System.out.println((int)raf.getFilePointer());
		}
		raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//////////////////////////////////////////////
	// LIT LES NOEUDS SELON LE NUMERO CHOISI
//////////////////////////////////////////////
	public static Noeud3 lireParentSuivant(int numNoeudALire, RandomAccessFile raf) throws EOFException {

		try {
			while(numNoeudALire<= raf.length()/132) {
			raf.seek(numNoeudALire * TAILLE_NOEUD);
			//System.out.println("dans le lire" + (int)raf.getFilePointer());
				String nom = "";
				for(int i = 0; i < 20 ; i++) {
					nom += raf.readChar();
				}
				String prenom = "";
				for(int i = 0; i < 20 ; i++) {
					prenom += raf.readChar();
				}
				String dpt = "";
				for(int i = 0; i < 4 ; i++) {
					dpt += raf.readChar();
				}
				String id = "";
				for(int i = 0; i < 10 ; i++) {
					id += raf.readChar();
				}
				String annee = "";
				for(int i = 0; i < 4 ; i++) {
					annee += raf.readChar();
				}
//////////////////// LIRE les indices//////////////////////////////
				int FG = raf.readInt();
				int FD = raf.readInt();
				int DBL = raf.readInt();
				int NumNoeud = raf.readInt();

				Stagiaire st = new Stagiaire(nom, prenom, dpt, id, annee);
				Noeud3 n = new Noeud3(st, FG, FD, DBL, NumNoeud);
				//raf.close();
				//System.out.println("nouveau parent : " + n);
				return n;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;

	}

///////////////////////////////////////////////////////////////////

public String lireNom(int numNoeudALire, RandomAccessFile raf) throws EOFException {
	String nom="";
	try {
		raf.seek(numNoeudALire * TAILLE_NOEUD);
		System.out.println("Position curseur :  " + (int)raf.getFilePointer());
		for(int j = 0; j < 20 ; j++) {
			nom += raf.readChar();}
		System.out.println(nom);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return nom;
}
/////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////

public int lireFD(int numNoeudALire, RandomAccessFile raf) throws EOFException {
int FD=-1;
try {
	System.out.println(numNoeudALire);
raf.seek((numNoeudALire * 132)+TAILLE_CLE_OCTET+TAILLE_IND_FG);
System.out.println("Position curseur :  " + (int)raf.getFilePointer());
FD = raf.readInt();
System.out.println(FD+"---");
System.out.println(FD+"FD+++");

return FD;

}catch (IOException e) {
e.printStackTrace();
}
return FD;
}

/////////////////////////////////////////////////////////////////
public int lireFG(int numNoeudALire, RandomAccessFile raf) throws EOFException {
int FG=-1;

try {
raf.seek((numNoeudALire * 132)+TAILLE_CLE_OCTET);
System.out.println(numNoeudALire);
System.out.println("Position curseur :  " + (int)raf.getFilePointer());
FG = raf.readInt();
System.out.println(FG+"+++");
return FG;
}catch (IOException e) {
e.printStackTrace();
}
return FG;

}
//////////////////////////////////////////////////////////
public int lireDBL(int numNoeudALire, RandomAccessFile raf) throws EOFException {
int DBL=-1;

try {
raf.seek((numNoeudALire * 132)+TAILLE_CLE_OCTET+TAILLE_IND_FG+TAILLE_IND_FD);
System.out.println(numNoeudALire);
System.out.println("Position curseur :  " + (int)raf.getFilePointer());
DBL = raf.readInt();
System.out.println(DBL+"---");
return DBL;
}catch (IOException e) {
e.printStackTrace();
}
return DBL;

}
////////////////////////////////////////////////////////


		//@SuppressWarnings("unused")
public Noeud3 searchInBinFile(RandomAccessFile raf, String nomRech) throws IOException {
	try {
		raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
		int sizeFile= (int) raf.length();
		int maxNoeuds= sizeFile/132;
		System.out.println(sizeFile+"HELLO");
		int j;
		int h;
		raf.getFilePointer();
		System.out.println(raf.getFilePointer());
		// pour limiter la recherche en nombre noeuds
		for(int i = nomRech.length(); i < TAILLE_MAX_NOM; i++) {
			nomRech= nomRech+"*"; // Transformer le nom en recherche comme les noms en fichier BIN pour comparaison
		    }
		System.out.println("RECHERCHE PAR NOM DE :  "+ nomRech);
		// pour limiter la recherche en nombre noeuds

		for(h=0;h<sizeFile;h++) {
		// on compare la lecture des noms du fichierBIN avec le nom recherche
		if(lireNom(h, raf).compareTo(nomRech)==0){
	///////// LECTRURE PRENOMS DPT ID ANNEE FG FD DBL POS///////////
			String prenomBIN = "";
			for( j =0;  j<20 ; j++) {
			prenomBIN += raf.readChar();}
			System.out.println(prenomBIN);
			String dptBIN = "";
			for(j =0;  j<4 ; j++) {
			dptBIN += raf.readChar();}
			System.out.println(dptBIN);
			String idBIN = "";
			for(j =0;  j<10 ; j++) {
			idBIN += raf.readChar();}
			System.out.println(idBIN);
			String anneeBIN = "";
			for( j =0;  j<4 ; j++) {
			anneeBIN += raf.readChar();}
			System.out.println(anneeBIN);
/////////// FG-FD-DBL-NumeroNoeud/////////
			int FG = raf.readInt();
			System.out.println(FG);
			int FD = raf.readInt();
			System.out.println(FD);
			int DBL = raf.readInt();
			System.out.println(DBL);
			int NumNoeud = raf.readInt();
			System.out.println(NumNoeud);
			Stagiaire stTrouve = new Stagiaire(lireNom(h, raf), prenomBIN, dptBIN, idBIN, anneeBIN);
			Noeud3 n2 = new Noeud3(stTrouve, FG, FD, DBL, NumNoeud);
			System.out.println(" VOICI LE STAGIAIRE TROUVE:   "+lireNom(h, raf));
			System.out.println(n2.getCle());
				h=NumNoeud;
	        if(DBL!=-1) {
	        	System.out.println("Autres FD TROUVES");
	        	raf.seek(DBL*132);


	        }

			return n2;	}
		}


}catch (IOException e) {
	e.printStackTrace();
	raf.close();}
	return null;	}

/////////////////////////////////////////////////
///////////////////////////////////////////////////
//	TROUVE ET ECRIT LES SUCCESSEURS DANS LE FICHIER BIN
//////////////////////////////////////////////////
public Noeud3 successeur(Noeud3 n,RandomAccessFile raf) throws IOException {


int PositionARemplacer= ((int) raf.getFilePointer());

n=lireParentSuivant(n.filsDroit, raf);
String nomFinal= n.getCle().getNom();
String prenomFinal=n.getCle().getPrenom();
String dptFinal=n.getCle().getDpt();
String idFinal= n.getCle().getId();
String anneeFinal=n.getCle().getAnnee();
RemplaceString(PositionARemplacer,nomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
System.out.println("le successeur est:  "+n);

// a traduire pour du binaire...  avec la  taiille octets.
while(n.filsGauche!=-1) {
    PositionARemplacer= ((int) raf.getFilePointer());
	n=lireParentSuivant(n.filsGauche, raf);
	nomFinal= n.getCle().getNom();
	prenomFinal=n.getCle().getPrenom();
    dptFinal=n.getCle().getDpt();
	idFinal= n.getCle().getId();
	anneeFinal=n.getCle().getAnnee();
	RemplaceString(PositionARemplacer,nomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
System.out.println("Le successeur final est  est:   "+ n.cle.toString());
}
return n;
}

////////////////////////////////////////////
///////////////////////////////////////////////////
//TROUVE ET ECRIT LES PREDECESSEURS DANS LE FICHIER BIN
//////////////////////////////////////////////////

public Noeud3 predecesseur(Noeud3 n,RandomAccessFile raf) throws IOException {
int PositionARemplacer= ((int) raf.getFilePointer());
n= lireParentSuivant(n.filsGauche, raf);
raf.seek(n.getNumeroNoeud());
//init variables a remplacer
String nomFinal= n.getCle().getNom();
String prenomFinal=n.getCle().getPrenom();
String dptFinal=n.getCle().getDpt();
String idFinal= n.getCle().getId();
String anneeFinal=n.getCle().getAnnee();
// appel methode modif
RemplaceString(PositionARemplacer,nomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
System.out.println(n.getCle());
//
while(n.filsDroit!=-1) {
	PositionARemplacer= ((int) raf.getFilePointer());
	n=lireParentSuivant(n.filsDroit, raf);
	// EXECUTER Le changement en ecriture
	nomFinal= n.getCle().getNom();
	prenomFinal=n.getCle().getPrenom();
    dptFinal=n.getCle().getDpt();
	idFinal= n.getCle().getId();
	anneeFinal=n.getCle().getAnnee();
	RemplaceString(PositionARemplacer,nomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2,prenomFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2,dptFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2,idFinal, raf);
	RemplaceString(PositionARemplacer+TAILLE_MAX_NOM*2+TAILLE_MAX_PRENOM*2+TAILLE_MAX_DPT*2+TAILLE_MAX_ID*2,anneeFinal, raf);
System.out.println("Le predecesseur alphabetique est : "+ n.cle.toString());

}
return n;
}
/////////////////////////////////////////////////
///// METHODE REMPLACER STRINGS DANS BIN
////////////////////////////////////////////////////
private void RemplaceString(int PositionARemplacer, String donneeARemplacer, RandomAccessFile raf) {
try {
raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
raf.seek(PositionARemplacer);
raf.writeChars(donneeARemplacer);
}
catch (IOException e) {
e.printStackTrace();}
}
/////////////////////////////////////////////////////////////////////////////////////
//////// MODIFIER LES INDICES DU PERE POUR LA SUPPRESSION DE FEUILLE////
////////////////////////////////////////////////////////////////////////////////////
public Noeud3 modifFilsPere(Noeud3 n,RandomAccessFile raf) throws IOException {
	try {
		raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
		int sizeFile= (int) raf.length();
		int maxNoeuds= sizeFile/132;
		System.out.println(sizeFile+"CHERCHER PERE");
		int h;
		raf.getFilePointer();
		System.out.println(raf.getFilePointer());
		 for (h=0; h<maxNoeuds;h++) {
				// on compare la lecture des indices du fichierBIN avec le nom recherche
				if(lireFD(h, raf)==n.getNumeroNoeud()){
					System.out.println(raf.getFilePointer()+"HAHIHO");
					System.out.println(n.lireFD(h, raf));
					raf.seek((h*132)+116+TAILLE_IND_FG);
					System.out.println(raf.getFilePointer());
					System.out.println("ECRITURE A -1 du pere pour FD");
					raf.writeInt(-1);
				}
				else if(lireFG(h, raf)==n.getNumeroNoeud()) {
					raf.seek((h*132)+116);
					System.out.println(raf.getFilePointer());
					System.out.println("ECRITURE A -1 du pere pour FG");
					raf.writeInt(-1);
					raf.seek((h*132)+116);
					int FG=raf.readInt();
					System.out.println(FG);
				}
				else if(lireDBL(h, raf)==n.getNumeroNoeud()) {
					raf.seek((h*132)+116+TAILLE_IND_FG+TAILLE_IND_FD);
					System.out.println("ECRITURE A -1 du pere pour DBL");
					raf.writeInt(-1);
				}
		   }
	}catch (IOException e) {
		e.printStackTrace();
		raf.close();}
		return null;	}
//////////////////////////////////////////////////////////////////////////////
//////////////////METHODE POUR SUPPRIMER NOEUD FICHIER BIN/////////////
///////////////////////////////////////////////////////////////////////////
public Noeud3 SupprimerNoeudStagiaireV2(Noeud3 aEffacer,RandomAccessFile raf) {
	try {
		raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
		searchInBinFile(raf, aEffacer.getCle().getNomLong()).getNumeroNoeud();
		aEffacer.getFilsDroit();
		aEffacer.getFilsGauche();
		raf.seek(aEffacer.numeroNoeud*132);
		   if(aEffacer==null) {
			   System.out.println("Element inexistant");
			   return null;}  //

		   if(aEffacer.filsGauche==-1 && aEffacer.filsDroit==-1) { // SUPPRESSION FEUILLE
			   int positionElement=aEffacer.numeroNoeud*132; //position de l'element a supprimer
			   modifFilsPere(aEffacer, raf);//on va modifier l'indice du pere du noeud a supprimer
			   raf.seek(positionElement+116+12);// on va chercher le numero du noeud a supprimer
			   raf.writeInt(-1);//on met le numero du noeud a -1 pour ne plus le lire et le considerer dans la liste
				}
		   else if (aEffacer.getFilsDroit()!=-1) {
					aEffacer.setCle(successeur(aEffacer, raf).cle);
					aEffacer= SupprimerNoeudStagiaireV2(lireParentSuivant(aEffacer.filsDroit, raf), raf);
					}
		   else {
					aEffacer.getFilsGauche();
					aEffacer.setCle(predecesseur(aEffacer, raf).cle);
					aEffacer= SupprimerNoeudStagiaireV2(lireParentSuivant(aEffacer.filsGauche, raf), raf);
				}
	       return aEffacer;
	}
	catch (IOException e) {
		e.printStackTrace();}
	return aEffacer;
}
////////////////////////////////////////////////////////
///////////////Ecrire Noeud dans position BIN choisie
///////////////////////////////////////////////////////
public int ecrireNoeudBin(int numNoeud, Noeud3 n, RandomAccessFile raf) {
	Stagiaire cle = n.getCle();
	try {
		raf.seek(numNoeud * TAILLE_NOEUD);
		raf.writeChars(cle.getNomLong());
		raf.writeChars(cle.getPrenomLong());
		raf.writeChars(cle.getDptLong());
		raf.writeChars(cle.getIdLong());
		raf.writeChars(cle.getAnneeLong());
		raf.writeInt(n.getFilsDroit());
		raf.writeInt(n.getFilsGauche());
		raf.writeInt(n.getDoublon());
		raf.writeInt(n.getNumeroNoeud());
		int curseur = (int) raf.getFilePointer();
		return curseur;
	} catch (IOException e) {
		e.printStackTrace();
		return -1;
	}
}
//////////////////////////////////////////////////////////
///////////////ECRIRE CLE BIN//////////////
////////////////////////////////////////////////////////
public int ecrireCleBin(int numNoeud, Noeud3 n, RandomAccessFile raf) {
	Stagiaire cle = n.getCle();
	try {
		raf.seek(numNoeud * TAILLE_NOEUD);
		raf.writeChars(cle.getNomLong());
		raf.writeChars(cle.getPrenomLong());
		raf.writeChars(cle.getDptLong());
		raf.writeChars(cle.getIdLong());
		raf.writeChars(cle.getAnneeLong());
		int curseur = (int) raf.getFilePointer();
		return curseur;
	} catch (IOException e) {
		e.printStackTrace();
		return -1;
	}
}



//////////////////////////////////////////////////////
////////// MODIFIER STAGIAIRE////////
/////////////////////////////////////////////////////
public void modifierStagiaire(Stagiaire stModif, RandomAccessFile raf, boolean nomModif) throws IOException {
	//Noeud3 noeudModif = searchInBinFile(raf, stModif.getNom());
	//System.out.println(noeudModif.getCle().getNom());
	Stagiaire stModif2 = new Stagiaire("ELACROIX", "arlette", "98", "ATOD 22", "2014");
	Noeud3 noeudModif = new Noeud3(stModif2, -1, -1, -1, 0);
	// si le champ modifié est autre que le nom, on modifie juste les valeurs
	if (!nomModif) {
		ecrireCleBin(noeudModif.getNumeroNoeud(), noeudModif, raf);
		System.out.println("Modif autre que nom du noeud numéro " + noeudModif.getNumeroNoeud());
	} else {
		// si le champ modifié est le nom, il faut le reranger le stagiaire
		Noeud3 racine = lireParentSuivant(0, raf);
		noeudModif.setNumeroNoeud((int)raf.length()/TAILLE_NOEUD);
		ajouterStagiaire(noeudModif, racine, raf);
		System.out.println("Modif du nom du noeud numéro " + noeudModif.getNumeroNoeud());
		System.out.println(noeudModif.getNumeroNoeud());
		LectureBin.LectureBin();
	}
}

////////////////////////////////////////////////////////////////
///////////////////////Methode TRI ALPHABETIQUE/////////////
///////////////////////////////////////////////////////////////
public static ArrayList<Stagiaire> ordreAlpha(int numNoeud, RandomAccessFile raf, ArrayList<Stagiaire> ListOrdreAlpha) throws EOFException {
	
	
	try {
		ListOrdreAlpha=new ArrayList<>();
		System.out.println(raf.length());
		for (int i=1; i<=(int)raf.length()/132; i++) {
		Noeud3 n;
		
		
		n = lireParentSuivant(numNoeud, raf);
		n.getCle().getNom().replaceAll( "[^a-zA-Z0-9]", " ");
		n.getCle().getPrenom().replace("[^a-zA-Z0-9]", " ");
		
		System.out.println(n.getDoublon());
		System.out.println(n.getFilsDroit());
		System.out.println(n.getCle());
if (n.getDoublon() == -1) {
		if (n.getFilsGauche() != -1) { // tant qu'il y a un FG, on continue jusqu'à trouver le plus petit FG
			ordreAlpha(n.getFilsGauche(), raf, ListOrdreAlpha);
		}
		
		
		ListOrdreAlpha.add(n.getCle()); // donc on l'ajoute à la liste
		System.out.println(ListOrdreAlpha.size()+"sdfgh");
		if (n.getFilsDroit() != -1) { // puis on regarde les FD, qui vont être ajoutés au prochain tour s'ils n'ont pas de FG
			ordreAlpha(n.getFilsDroit(), raf, ListOrdreAlpha);
		}
}
else {
		if (n.getFilsGauche() != -1) { // tant qu'il y a un FG, on continue jusqu'à trouver le plus petit FG
			ordreAlpha(n.getFilsGauche(), raf, ListOrdreAlpha);
		}
		ordreAlpha(n.getDoublon(), raf, ListOrdreAlpha);
		ListOrdreAlpha.add(n.getCle()); // donc on l'ajoute à la liste
		if (n.getFilsDroit() != -1) { // puis on regarde les FD, qui vont être ajoutés au prochain tour s'ils n'ont pas de FG
			ordreAlpha(n.getFilsDroit(), raf, ListOrdreAlpha);
		}	
		
}
System.out.println(ListOrdreAlpha.size()+"fsddddddddddddddddddddd");
return ListOrdreAlpha;

		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return ListOrdreAlpha;
	
}



/////////////////////////////////////////////
//public ArrayList<Stagiaire> ordreAlphaV2(int numNoeud, RandomAccessFile raf, ArrayList<Stagiaire> ListOrdreAlphaV2) throws EOFException {
//	
//	
//	try {
//		ListOrdreAlphaV2=new ArrayList<>();
//		System.out.println(raf.length());
//		//for (int i=1; i<=(int)raf.length()/132; i++) {
//		Noeud3 n;
//		
//		
//		n = lireParentSuivant(0, raf);
////		n.getCle().getNom().replaceAll( "[^a-zA-Z0-9]", " ");
////		n.getCle().getPrenom().replace("[^a-zA-Z0-9]", " ");
//		
//		System.out.println(n.getDoublon());
//		System.out.println(n.getFilsDroit());
//		System.out.println(n.getCle());
//while ((int) raf.getFilePointer()<raf.length()) {
//		if (n.getFilsGauche()!=-1) {
//			raf.seek( (n.getNumeroNoeud()*132)+TAILLE_NOEUD);
//			int FG= raf.readInt();
//			System.out.println(FG+"FG");
//			n.ordreAlphaV2(FG,raf , ListOrdreAlphaV2);
//			ListOrdreAlphaV2.add(n.getCle());
//			if(lireParentSuivant(n.getFilsGauche(), raf).getFilsDroit()!=-1) {
//				n.ordreAlphaV2(n.getFilsGauche(), raf, ListOrdreAlphaV2);
//				ListOrdreAlphaV2.add(n.getCle());
//			}
//		}
//		else {
//			ListOrdreAlphaV2.add(n.getCle());
//			if(lireParentSuivant(n.getFilsDroit(), raf).getFilsGauche()!=-1) {
//				ListOrdreAlphaV2.add(n.getCle());
//			n.ordreAlphaV2(n.getFilsDroit(), raf, ListOrdreAlphaV2);
//			if(n.getFilsDroit()==-1) {
//				ListOrdreAlphaV2.add(n.getCle());
//			}
//		}
//			
//		
//		}
//		return ListOrdreAlphaV2;
//		System.out.println();
//	}
//}catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	return ListOrdreAlphaV2;
//	
//}









////////////////////////////////////////////////FIN METHODE/////////////////////////////////////////////




}