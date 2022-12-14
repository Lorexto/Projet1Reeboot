package fr.isika.cda22.Projet1Reeboot;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
private Object label;
		public static TextField txtNom;
		public static TextField txtPrenom;
		public static TextField txtDepartement;
		public static TextField txtPromo;
		public static TextField txtAnnee;

////////////////////////////////////////////////////////////////////////////////////
///////////////////////AJOUT STAGIAIRE DU FORMULAIRE AU FICHIER BIN//////
////////////////////////////////////////////////////////////////////////////////////		
	public static boolean AddFormulaireToBIN( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
		String nom=VueFormulaire.txtNom.getText();
		String prenom=VueFormulaire.txtPrenom.getText();
		String dpt= VueFormulaire.txtDepartement.getText();
		String id= VueFormulaire.txtPromo.getText();
		String annee=VueFormulaire.txtAnnee.getText();
		
		if(nom!= ""
			&&prenom!= ""
			&&dpt!= ""
			&&id!= ""
			&&annee!= ""
		) {
		RandomAccessFile raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
		 nom=VueFormulaire.txtNom.getText();
		prenom=VueFormulaire.txtPrenom.getText();
				//substring(0, 1).toUpperCase() + prenom.substring(1);
		 dpt= VueFormulaire.txtDepartement.getText();
		id= VueFormulaire.txtPromo.getText();
		annee=VueFormulaire.txtAnnee.getText();
		
		Stagiaire nouveau= new Stagiaire(nom.toUpperCase(), prenom.substring(0, 1).toUpperCase() + prenom.substring(1), dpt, id, annee);

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
	
////////////////////////////////////AJOUT OUVERTURE PAGE FORMULAIRE///////////////////////////
	public static boolean Ajouter( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
		return true;

	}
	
////////////////////////////////////////////////////////////////////////////////////////
	///////////MODIFICATION DONNEES STAGIAIRE////////////////////////
///////////////////////////////////////////////////////////////////////////////	
	public static boolean Modifier( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
		
		return true;

	}
///////////////////////////////////////////////////////////////////////////////
////////////////////////// REFRESH DE LA TABLE VIEW//////////////
//////////////////////////////////////////////////////////////////////////////	
public static boolean Update( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
	VueMenu.table.refresh();
		
		return true;

	}
////////////////////////////////////////////////////////////////////////
////////////////////DECONNEXION ET RETOUR PAGE LOG///////
////////////////////////////////////////////////////////////////////////

public  static boolean Deconnexion( EventHandler<? super MouseEvent> eventHandler) throws IOException{
	System.out.println("Vous allez vous deconnecter.Etes vous sur?");
    Alert msg = new Alert(AlertType.CONFIRMATION);
    msg.setTitle("Deconnexion");
    msg.setContentText("Vous allez vous deconnecter.Etes vous sur?");
    msg.showAndWait();
    Optional<ButtonType> option = msg.showAndWait();
    if (option.get() == ButtonType.OK) {
        return true;
     } else if (option.get() == ButtonType.CANCEL) {
      return false;
     }
  
	return true;

}
///////////////////////////////////////////////////////////////////////////////
////////////////////////////////SUPPRESSION///////////////////////////
///////////////////////////////////////////////////////////////////////////////

public static boolean Delete( EventHandler<? super MouseEvent> eventHandler) throws IOException{
	
	RandomAccessFile raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
	
	Stagiaire selection= VueMenu.table.getSelectionModel().getSelectedItem();
	System.out.println(VueMenu.table.getSelectionModel().getSelectedItem());
	System.out.println(selection.getNom());
	Noeud3 aEffacer =Noeud3.searchInBinFile(raf, selection.getNom().toUpperCase());	
	Noeud3.SupprimerNoeudStagiaireV2(aEffacer, raf) ;	
		
	
	return true;
	
	
}

/////////////////////////////////////////////////////////////////////
/////////////////////// RETOUR//////////////////////
////////////////////////////////////////////////////////////////////
public static boolean Retour( EventHandler<? super MouseEvent> eventHandler) throws IOException{
	
	
	
	return true;
	
}
/////////////////////////////////////////////////////////////////////
/////////////////////// MODIFICATION STAGIAIRES : OVERTURE FENETRE/////////////
////////////////////////////////////////////////////////////////////
public static boolean Modifications( EventHandler<? super MouseEvent> eventHandler) throws IOException{

	Stagiaire selection= VueMenu.table.getSelectionModel().getSelectedItem();
	VueModification.labelNom.setText(String.valueOf( selection.getNom()));     
	VueModification.labelPrenom.setText(String.valueOf( selection.getPrenom()));
	VueModification.labelDepartement.setText(String.valueOf( selection.getDpt()));
	VueModification.labelPromo.setText(String.valueOf( selection.getId()));
	VueModification.labelAnnee.setText(String.valueOf( selection.getAnnee()));	
RandomAccessFile raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");

System.out.println(VueMenu.table.getSelectionModel().getSelectedItem());
System.out.println(selection.getNom());

	return true;
}
/////////////////////////////////////////////////////////////////////
/////////////////////// MODIFICATION STAGIAIRES DANS BIN/////////////
////////////////////////////////////////////////////////////////////
public static boolean SubmitChanges( EventHandler<? super MouseEvent> eventHandler) throws IOException{
	
RandomAccessFile raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");

Stagiaire selection= VueMenu.table.getSelectionModel().getSelectedItem();

String nom = VueModification.getTxtNomR().getText().toUpperCase();
String prenom=VueModification.getTxtPrenomR().getText().substring(0, 1).toUpperCase() + VueModification.getTxtPrenomR().getText().substring(1);
String id=VueModification.getTxtPromoR().getText();
String dpt=VueModification.getTxtDepartementR().getText();
String annee=VueModification.getTxtAnneeR().getText();

if(nom=="") {
	nom= selection.getNom();
}
if(prenom=="") {
	prenom= selection.getPrenom();
}
if(id=="") {
	id= selection.getId();
}
if(dpt=="") {
	dpt= selection.getDpt();
}
if(annee=="") {
	annee= selection.getAnnee();
}


Stagiaire Remplacement= new Stagiaire(nom, prenom, dpt, id, annee);

Noeud3 aEffacer =	Noeud3.searchInBinFile(raf, selection.getNom());
if (aEffacer.getCle()!=Remplacement) {
	
Noeud3 Nremplacement= new Noeud3(Remplacement, -1, -1, -1, (int)raf.length()/132);
Noeud3.ajouterStagiaire(Nremplacement, Noeud3.lireParentSuivant(0, raf), raf);
Noeud3.SupprimerNoeudStagiaireV2(aEffacer, raf);
VueMenu.table.refresh();
return true;
}
else {
	Alert msg = new Alert(AlertType.WARNING);
    msg.setTitle("ATTENTION");
    msg.setContentText("Vous n'avez pas modifié les informations relatives a ce stagiaire");
    msg.showAndWait();
    Optional<ButtonType> option = msg.showAndWait();
	
	return false;
}

}
/////////////////////////////////////////////////////////////////////
/////////////////////// RETOUR AU MENU PRINCIPAL /////////////
////////////////////////////////////////////////////////////////////







/////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////FIN METHODES//////////////////////////////////////////////
}
