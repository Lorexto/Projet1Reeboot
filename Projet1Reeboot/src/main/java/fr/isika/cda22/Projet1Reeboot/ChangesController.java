package fr.isika.cda22.Projet1Reeboot;

import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ChangesController {

	public StackPane listeStagiaires;
	public VueFormulaire formulaire;
	 public VueMenu vueMenu;

//	    Button search;
//	     Button addButton;
//	    Button delete;
//	    Button refactor;
//	    Button disconnect;
		
		 Button btn;
		public static TextField txtNom;
		public static TextField txtPrenom;
		public static TextField txtDepartement;
		public static TextField txtPromo;
		public static TextField txtAnnee;


	public static boolean AddFormulaireToBIN( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
		String nom=VueFormulaire.txtNom.getText();
		String prenom=VueFormulaire.txtPrenom.getText();
		String dpt= VueFormulaire.txtDepartement.getText();
		String id= VueFormulaire.txtPromo.getText();
		String annee=VueFormulaire.txtAnnee.getText();
		
		if(nom!= null
			&&prenom!= null
			&&dpt!= null
			&&id!= null
			&&annee!= null
		) {
		RandomAccessFile raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet_1/fichbinTEST3.bin", "rw");
		 nom=VueFormulaire.txtNom.getText();
		prenom=VueFormulaire.txtPrenom.getText();
		 dpt= VueFormulaire.txtDepartement.getText();
		id= VueFormulaire.txtPromo.getText();
		annee=VueFormulaire.txtAnnee.getText();
		
		Stagiaire nouveau= new Stagiaire(nom, prenom, dpt, id, annee);

		Noeud3 nvo= new Noeud3(nouveau, -1, -1, -1,( (int)raf.length()/Noeud3.TAILLE_NOEUD));
		Noeud3.ajouterStagiaire(nvo,(Noeud3.lireParentSuivant(0, raf)) , raf);
		
		LectureBin.LectureBin();
		VueMenu.table.refresh();
		return true;
		}
		else {
			System.out.println("Veuillez entrer des donnees correctes");
            Alert msg = new Alert(AlertType.WARNING);
            msg.setTitle("Informations incompletes");
            msg.setContentText("Veuillez TOUS les champs d'informations pour l'enregistrement du nouveau stagiaire, ou retournez a la page principale");
            msg.showAndWait();
			return false;
		}
		
	}
	

	public static boolean Ajouter( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		return true;

	}

	public static boolean Modifier( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
		return true;

	}
	
public static boolean Update( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
	VueMenu.table.refresh();
		
		return true;

	}


public static boolean Deconnexion( EventHandler<? super MouseEvent> eventHandler) throws IOException{
	System.out.println("Vous allez vous deconnecter.Etes vous sur?");
    Alert msg = new Alert(AlertType.CONFIRMATION);
    msg.setTitle("Deconnexion");
    msg.setContentText("Vous allez vous deconnecter.Etes vous sur?");
    msg.showAndWait();
	return true;

}

public static boolean Search( EventHandler<? super MouseEvent> eventHandler) throws IOException{
	
	
	
	return false;
	
	
	
}



}
