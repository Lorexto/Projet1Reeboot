package fr.isika.cda22.Projet1Reeboot;
public class Stagiaire {
	//attributs

	private String nom;
	private String prenom;
	private String dpt;
	private String id;
	private String annee;


	public final static int TAILLE_MAX_NOM = 20; // en caractères ! pas en octets 20 caract. 40 octets
	public final static int TAILLE_MAX_PRENOM = 20; // 20 caract. 40 octets
	public final static int TAILLE_MAX_DPT = 4; // 4 caract. 8 octets
	public final static int TAILLE_MAX_ID = 10; // 10 caract. 20 octets
	public final static int TAILLE_MAX_ANNEE = 4; // 4 caract. 8 octets

	public final static int TAILLE_CLE = TAILLE_MAX_NOM + TAILLE_MAX_PRENOM + TAILLE_MAX_DPT + TAILLE_MAX_ID
			+ TAILLE_MAX_ANNEE; // 58 caract. 116 octets

	public final static int TAILLE_IND_FG = 4; // 1 int 4 octets
	public final static int TAILLE_IND_FD = 4; // 1 int 4 octets
	public final static int TAILLE_IND_DBL = 4; // 1 int 4 octets
	public final static int TAILLE_NUM_NOEUD = 4; // 1 int 4 octets

	public final static int TAILLE_NOEUD = TAILLE_CLE + TAILLE_IND_FG + TAILLE_IND_FD + TAILLE_IND_DBL + TAILLE_NUM_NOEUD; // 61 caract 128 octets


	//constructeurs

	public Stagiaire(String nom, String prenom, String dpt, String id, String annee) {

		this.nom = nom;
		this.prenom = prenom;
		this.dpt = dpt;
		this.id = id;
		this.annee = annee;

	}


	public String getNomLong() {
		String nomLong = nom;
		if (nomLong.length() > TAILLE_MAX_NOM ) {
			nomLong = nomLong.substring(0, TAILLE_MAX_NOM);
		} else {
			for(int i = nom.length(); i < TAILLE_MAX_NOM; i++) {
				nomLong += "*";
			}
		}
		return nomLong;
	}
	public <nom> String setToNomLong() {
		String nomLong = null;

			for(int i = nom.length(); i < TAILLE_MAX_NOM; i++) {
				nomLong += nom+"*";
			}

		return nomLong;
	}




	public String getPrenomLong() {
		String prenomLong = prenom;
		if (prenomLong.length() > TAILLE_MAX_PRENOM ) {
			prenomLong = prenomLong.substring(0, TAILLE_MAX_PRENOM);
		} else {
			for(int i = prenom.length(); i < TAILLE_MAX_PRENOM; i++) {
				prenomLong += "*";
			}
		}
		return prenomLong;
	}


	public String getDptLong() {
		String dptLong = dpt;
		if (dptLong.length() > TAILLE_MAX_DPT ) {
			dptLong = dptLong.substring(0, TAILLE_MAX_DPT);
		} else {
			for(int i = dpt.length(); i < TAILLE_MAX_DPT; i++) {
				dptLong += "*";
			}
		}
		return dptLong;
	}

	public String getIdLong() {
		String idLong = id;
		if (idLong.length() > TAILLE_MAX_ID ) {
			idLong = idLong.substring(0, TAILLE_MAX_ID);
		} else {
			for(int i = id.length(); i < TAILLE_MAX_ID; i++) {
				idLong += "*";
			}
		}
		return idLong;
	}

	public String getAnneeLong() {
		String anneeLong = annee;
		if (anneeLong.length() > TAILLE_MAX_ANNEE ) {
			anneeLong = anneeLong.substring(0, TAILLE_MAX_ANNEE);
		} else {
			for(int i = prenom.length(); i < TAILLE_MAX_ANNEE; i++) {
				anneeLong += "*";
			}
		}
		return anneeLong;
	}

	//getters & setters

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}




	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDpt() {
		return dpt;
	}
	public void setDpt(String dpt) {
		this.dpt = dpt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}


	//méthodes spécifiques



	@Override
	public String toString() {

		return "(" + nom + " " + prenom + " " + dpt + " " + id + " " + annee+ ")";
	}

//	public int compareTo(StagiaireSS myStagiaire){
//		if (myStagiaire.getNom().compareTo(this.nom) == 0) { // 0 si c'est identique
//			return myStagiaire.getPrenom().compareTo(this.prenom);
//
//
//			// on compare les prénoms
//		} else {
//
//
//			return myStagiaire.getNom().compareTo(this.nom); // sinon on compare les noms
//		}
//	}


	public int compareTo(Stagiaire myStagiaire){
		if (myStagiaire.getNom().compareTo(this.nom) == 0) { // 0 si c'est identique
			return myStagiaire.getPrenom().compareTo(this.prenom);	// on compare les prénoms
		} else {
			return myStagiaire.getNom().compareTo(this.nom); // sinon on compare les noms
		}
	}


	public boolean compareToDoublon(Stagiaire myStagiaire){
		if (myStagiaire.getNom().compareTo(this.getNom()) == 0&&myStagiaire.getPrenom().compareTo(this.getPrenom())==0) { // 0 si c'est identique

				return true;
			}
			else {return false;}

			// on compare les prénoms

	}


	public void setNom(Object writeChars, String nomLong) {


	}
}






