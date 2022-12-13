package fr.isika.cda22.Projet1Reeboot;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Arbre3 {

	// attribut
	public Noeud3 racine;

// constructeurs

	public Arbre3() {
		super();
		this.racine = null;
	}

	public Arbre3(Noeud3 racine) {
		super();
		this.racine = racine;
	}

	// Getters & Setters

	public Noeud3 getRacine() {
		return racine;
	}

	public void setRacine(Noeud3 racine) {
		this.racine = racine;
	}

	// méthodes spécifiques

	public boolean isEmpty() {
		return (this.racine == null);
	}

	@Override
	public String toString() {

		if (this.isEmpty()) {
			return "Arbre vide";
		} else {
			return this.racine.toString();
		}
	}

	public void ajouter(Noeud3 n, Noeud3 nParent, RandomAccessFile raf) throws FileNotFoundException, EOFException {
		if (isEmpty()) {
			this.racine = new Noeud3(n.getCle(), -1, -1, -1, 0);
			Noeud3.ecrireNoeudFinBin(n, raf);
			System.out.println("Racine : " +  n.getCle());
		} else {
			Noeud3.ajouterStagiaire(n, nParent, raf);
		}
	}
}