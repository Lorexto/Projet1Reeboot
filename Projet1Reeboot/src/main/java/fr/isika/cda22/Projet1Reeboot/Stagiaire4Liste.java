package fr.isika.cda22.Projet1Reeboot;

import javafx.beans.property.SimpleStringProperty;

public class Stagiaire4Liste {


	    private final SimpleStringProperty nom;
	    private final SimpleStringProperty prenom;
	    private final SimpleStringProperty id;
	    private final SimpleStringProperty dpt;
	    private final SimpleStringProperty annee;
	    public Stagiaire4Liste(String nom, String prenom, String id, String dpt, String annee) {
	        this.nom = new SimpleStringProperty(nom);
	        this.prenom = new SimpleStringProperty(prenom);
	        this.annee = new SimpleStringProperty(annee);
	        this.id = new SimpleStringProperty(id);
	        this.dpt = new SimpleStringProperty(dpt);
	    }
		public String getNom() {
			return nom.get();
		}
		public String getPrenom() {
			return prenom.get();
		}
		public String getId() {
			return id.get();
		}
		public String getDpt() {
			return dpt.get();
		}
		public String getAnnee() {
			return annee.get();
		}


}
