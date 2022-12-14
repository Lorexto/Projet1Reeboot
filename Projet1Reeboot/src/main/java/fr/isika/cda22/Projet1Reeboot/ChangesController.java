package fr.isika.cda22.Projet1Reeboot;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

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
		 nom=VueFormulaire.txtNom.getText().trim();
		 nom=nom.toUpperCase();
		prenom=VueFormulaire.txtPrenom.getText();
		
		prenom= prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
		 dpt= VueFormulaire.txtDepartement.getText();
		id= VueFormulaire.txtPromo.getText().trim();
		annee=VueFormulaire.txtAnnee.getText().trim();
		
		Stagiaire nouveau= new Stagiaire(nom, prenom, dpt, id, annee);
System.out.println(nouveau.getNom());
		Noeud3 nvo= new Noeud3(nouveau, -1, -1, -1,( (int)raf.length()/132));
		System.out.println(nvo.getFilsGauche());
		System.out.println(nvo.getFilsDroit());
		System.out.println(nvo.getDoublon());
		System.out.println(nvo.getNumeroNoeud());
		Noeud3.ajouterStagiaire(nvo,(Noeud3.lireParentSuivant(0, raf)) , raf);
		
		LectureBin.LectureBin();
		VueMenu.table.refresh();
		return true;}
		return true;
		
//		else {
//			System.out.println("Veuillez entrer des donnees correctes");
//            Alert msg = new Alert(AlertType.WARNING);
//            msg.setTitle("Informations incompletes");
//            msg.setContentText("Veuillez remplir TOUS les champs, ou retournez au menu principal");
//            msg.showAndWait();
//			return false;
//		}
	}
	
////////////////////////////////////AJOUT OUVERTURE PAGE FORMULAIRE///////////////////////////
	public static boolean Ajouter( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		
		return true;

	}
	
////////////////////////////////////////////////////////////////////////////////////////
	///////////MODIFICATION DONNEES STAGIAIRE////////////////////////
///////////////////////////////////////////////////////////////////////////////	
	public static boolean Modifier( EventHandler<? super MouseEvent> eventHandler) throws IOException{
		if(VueMenu.table.getSelectionModel().isEmpty()!=true) {
		return true;}
		else {
			 Alert msg = new Alert(AlertType.ERROR);
	            msg.setTitle("SELECTION VIDE");
	            msg.setContentText("Veuillez selectionner un stagiaire ");
	            msg.showAndWait();
			
		return false;}

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
	if(VueMenu.table.getSelectionModel().isEmpty()!=true){
	Stagiaire selection= VueMenu.table.getSelectionModel().getSelectedItem();
	System.out.println(VueMenu.table.getSelectionModel().getSelectedItem());
	System.out.println(selection.getNom());
	Noeud3 aEffacer =Noeud3.searchInBinFile(raf, selection.getNom().toUpperCase());	
	Noeud3.SupprimerNoeudStagiaireV2(aEffacer, raf) ;	
	LectureBin.LectureBin();
	raf.close();
	return true;}
	
	else {
		 Alert msg = new Alert(AlertType.WARNING);
           msg.setTitle("SELECTION VIDE");
           msg.setContentText("Veuillez selectionner un stagiaire ");
           msg.showAndWait();
           raf.close();
	return false;
	}
	
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
	
	if(VueMenu.table.getSelectionModel().isEmpty()!=true){
	VueModification.labelNom.setText(String.valueOf( selection.getNom()));     
	VueModification.labelPrenom.setText(String.valueOf( selection.getPrenom()));
	VueModification.labelDepartement.setText(String.valueOf( selection.getDpt()));
	VueModification.labelPromo.setText(String.valueOf( selection.getId()));
	VueModification.labelAnnee.setText(String.valueOf( selection.getAnnee()));	
RandomAccessFile raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");

System.out.println(VueMenu.table.getSelectionModel().getSelectedItem());
System.out.println(selection.getNom());	
	return true;}
	
	else {
		Alert msg = new Alert(AlertType.WARNING);
	    msg.setTitle("ATTENTION");
	    msg.setContentText("Vous n'avez pas modifi?? les informations relatives a ce stagiaire");
	    msg.showAndWait();
	    Optional<ButtonType> option = msg.showAndWait();
		
		return false;
	}
}
/////////////////////////////////////////////////////////////////////
/////////////////////// MODIFICATION STAGIAIRES DANS BIN/////////////
////////////////////////////////////////////////////////////////////
public static boolean SubmitChanges( EventHandler<? super MouseEvent> eventHandler) throws IOException{
	
RandomAccessFile raf= new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
// stagiaire selectionne
Stagiaire selection= VueMenu.table.getSelectionModel().getSelectedItem();

// textfields de vue modification
String nom = VueModification.getTxtNomR().getText().toUpperCase();
String prenom=VueModification.getTxtPrenomR().getText();
String dpt=VueModification.getTxtDepartementR().getText();
String id=VueModification.getTxtPromoR().getText();
String annee=VueModification.getTxtAnneeR().getText();

if(nom=="") { // si vide recupere donnees du stagiaire selectionne
	nom= selection.getNom();
}
if(prenom=="") {
	prenom= selection.getPrenom();
}
if(dpt=="") {
	dpt= selection.getDpt();
}
if(id=="") {
	id= selection.getId();
}

if(annee=="") {
	annee= selection.getAnnee();
}
// stagiaire de remplacement
Stagiaire Remplacement= new Stagiaire(nom, prenom, dpt, id, annee);
// on recherche le noeud d'origine a remplacer
Noeud3 aEffacer =Noeud3.searchInBinFile(raf, selection.getNom());

if (aEffacer.getCle()!=Remplacement) {// si les cles sont differentes
	
Noeud3 Nremplacement= new Noeud3(Remplacement, -1, -1, -1, (int)raf.length()/132);// creation du noeud de remplacement
Noeud3.ajouterStagiaire(Nremplacement, Noeud3.lireParentSuivant(0, raf), raf);//on ajoute le stagiaire
Noeud3.SupprimerNoeudStagiaireV2(aEffacer, raf);// on supprime l'ancien noeud
LectureBin.LectureBin();
raf.seek(0);
VueModification.getTxtNomR().clear();
VueModification.getTxtPrenomR().clear();
VueModification.getTxtDepartementR().clear();
VueModification.getTxtPromoR().clear();
VueModification.getTxtAnneeR().clear();
VueMenu.table.refresh();
return true;
}
else {
	Alert msg = new Alert(AlertType.WARNING);
    msg.setTitle("ATTENTION");
    msg.setContentText("Vous n'avez pas modifi?? les informations relatives a ce stagiaire");
    msg.showAndWait();
    Optional<ButtonType> option = msg.showAndWait();
	
	return false;
}

}
/////////////////////////////////////////////////////////////////////
/////////////////////// PRINT TO PDF /////////////
////////////////////////////////////////////////////////////////////

public static void print(Node node) {
	PrinterJob PrintToPDF = PrinterJob.createPrinterJob();	
	 if (PrintToPDF != null && PrintToPDF.showPrintDialog(null)) {
	  PageLayout pageLayout = PrintToPDF.getJobSettings().getPageLayout();
	  double scaleX = 1.0;
	  if (pageLayout.getPrintableWidth() < node.getBoundsInParent().getWidth()) {
	   scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
	  }
	  double scaleY = 1.0;
	  if (pageLayout.getPrintableHeight() < node.getBoundsInParent().getHeight()) {
	   scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
	  }
	  double scaleXY = Double.min(scaleX, scaleY);
	  Scale scale = new Scale(scaleXY, scaleXY);
	  node.getTransforms().add(scale);
	  boolean success = PrintToPDF.printPage(node);
	  node.getTransforms().remove(scale);
	  if (success) {
		  PrintToPDF.endJob();
	  }
	 }
	}






/////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////FIN METHODES//////////////////////////////////////////////
}
