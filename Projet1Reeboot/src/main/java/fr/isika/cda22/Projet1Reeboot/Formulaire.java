package fr.isika.cda22.Projet1Reeboot;

import javafx.application.Application;
import javafx.stage.Stage;



public class Formulaire extends Application {

	//attributs
	public VueFormulaire vueFormulaire;

	@Override
	public void start(Stage stage) {

		vueFormulaire = new VueFormulaire();

	stage.setTitle("Formulaire d'ajout d'un stagiaire");
	stage.setScene(vueFormulaire);
	stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public VueFormulaire getVueFormulaire() {
		return vueFormulaire;
	}

	public void setVueFormulaire(VueFormulaire vueFormulaire) {
		this.vueFormulaire = vueFormulaire;
	}


}
