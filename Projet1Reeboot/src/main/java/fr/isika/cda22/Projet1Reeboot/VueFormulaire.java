
package fr.isika.cda22.Projet1Reeboot;


import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class VueFormulaire extends Scene {
    Button btn;
	static TextField txtNom;
	static TextField txtPrenom;
	static TextField txtDepartement;
	static TextField txtPromo;
	static TextField txtAnnee;
    Button retour;
    ChangesController Controlleur;



	public VueFormulaire()  {
		super(new GridPane(),600,600);

		GridPane root = (GridPane)this.getRoot();// organise les composants de la fenetre
		root.setPadding(new Insets(20)); //
		root.setHgap(25);// espace entre label et champs remplissage (horizontal)
		root.setVgap(15);// espace entre chaque label(vertical)
		this.setRoot(root);// on crée la scene le conteneur principal qui contient tous les
													// composants

		Label labelNom = new Label("Nom :");
		labelNom.setFont(Font.font("Roboto",FontPosture.ITALIC, 12));

		 txtNom = new TextField();
		//txtNom.setPromptText("Nom");

		Label labelPrenom = new Label("Prénom :");
		labelPrenom.setFont(Font.font("Roboto",FontPosture.ITALIC, 12));

		txtPrenom = new TextField();
	//	txtPrenom.setPromptText("Prénom");

		Label labelDepartement = new Label("Département :");
		labelDepartement.setFont(Font.font("Roboto",FontPosture.ITALIC, 12));

		txtDepartement = new TextField();
		//txtDepartement.setPromptText("Exemple : 75");

		Label labelPromo = new Label("Promo :");
		labelPromo.setFont(Font.font("Roboto",FontPosture.ITALIC, 12));

		txtPromo = new TextField();
		//txtPromo.setPromptText("Exemple : CDA 22");


		Label labelAnnee = new Label("Année :");
		labelAnnee.setFont(Font.font("Roboto",FontPosture.ITALIC, 12));

		 txtAnnee = new TextField();
	//	txtAnnee.setPromptText("Exemple : 2022");

		btn = new Button("Valider");
		retour= new Button("Retour au menu Principal");
		root.add(labelNom, 0, 0);
		root.add(txtNom, 1, 0);
		root.add(labelPrenom, 0, 1);
		root.add(txtPrenom, 1, 1);
		root.add(labelDepartement, 0, 2);
		root.add(txtDepartement, 1, 2);
		root.add(labelPromo, 0, 3);
		root.add(txtPromo, 1, 3);
		root.add(labelAnnee, 0, 4);
		root.add(txtAnnee, 1, 4);
		root.add(btn, 1, 5);
		root.add(retour, 3, 5);

	}

	public Button getBtn() {
		return btn;
	}

	public void setBtn(Button btn) {
		this.btn = btn;
	}

// public void Valider (ActionEvent event) {
//		// doit récuperer les champs de la vue formulaire
//		String nom = txtNom.getText();
//		String prenom = txtPrenom.getText();
//		String dpt = txtDepartement.getText();
//		String id = txtPromo.getText();
//		String annee = txtAnnee.getText();
//
//		Stagiaire st = new Stagiaire(nom,prenom,dpt,id,annee);
//
//	}

 public Button getRetour() {
		return retour;
	}

	public void setRetour(Button retour) {
		this.retour = retour;
	}

	public TextField getTxtNom() {
		return txtNom;
	}

	public void setTxtNom(TextField txtNom) {
		VueFormulaire.txtNom = txtNom;
	}

	public TextField getTxtPrenom() {
		return txtPrenom;
	}

	public void setTxtPrenom(TextField txtPrenom) {
		VueFormulaire.txtPrenom = txtPrenom;
	}

	public TextField getTxtDepartement() {
		return txtDepartement;
	}

	public void setTxtDepartement(TextField txtDepartement) {
		VueFormulaire.txtDepartement = txtDepartement;
	}

	public TextField getTxtPromo() {
		return txtPromo;
	}

	public void setTxtPromo(TextField txtPromo) {
		VueFormulaire.txtPromo = txtPromo;
	}

	public TextField getTxtAnnee() {
		return txtAnnee;
	}

	public void setTxtAnnee(TextField txtAnnee) {
		VueFormulaire.txtAnnee = txtAnnee;
	}





}

//
//		Arbre3 a = new Arbre3();
//		// doit récuperer les champs de la vue formulaire
//
//		//Stagiaire st = new  ......
//
//		//cptNoeud = nb de st déjà là + 1
//		int cptNoeud = 9999;
//		n = new Noeud3(st, -1, -1, -1, cptNoeud); // on part toujours du parent racine
//
//		nParent = new Noeud3(st, -1, -1, -1, cptNoeud)
//
//		a.ajouter(n, nParent, raf);
//	}













