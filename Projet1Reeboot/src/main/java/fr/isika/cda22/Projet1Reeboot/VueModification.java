package fr.isika.cda22.Projet1Reeboot;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class VueModification extends Scene{
//////////////////////////ATTRIBUTS///////////	
	
	    Button ValiderModifs;
		static Label labelNom;
		static Label labelPrenom;
		static Label labelDepartement;
		static Label labelPromo;
		static Label labelAnnee;
		static TextField txtNomR;
		static TextField  txtPrenomR;
		static TextField txtDepartementR;
		static TextField txtPromoR;
		static TextField txtAnneeR;
	    Button retour2;
	    ChangesController Controlleur;
	
	    
	    public VueModification()  {
			super(new GridPane(),800,800);

			GridPane vueModif = (GridPane) this.getRoot();// organise les composants de la fenetre
			vueModif.setPadding(new Insets(20)); //
			vueModif.setHgap(25);// espace entre label et champs remplissage (horizontal)
			vueModif.setVgap(15);// espace entre chaque label(vertical)
			this.setRoot(vueModif);// 
	
//////////////////////////////////////////////////////////////
//////////////////LABELS ET TEXTFIELDS///////////////
////////////////////////////////////////////////////////////////
			
labelNom = new Label("");
txtNomR = new TextField();
labelPrenom = new Label("");
txtPrenomR = new TextField();
labelDepartement = new Label("");
txtDepartementR = new TextField();
labelPromo = new Label("");
txtPromoR = new TextField();
labelAnnee = new Label("");
txtAnneeR = new TextField();
ValiderModifs = new Button("Valider");
retour2= new Button("Retour au menu Principal");

////////////////AJOUTS DANS LES CONTENEURS//////////////////
HBox hbxNOM= new HBox();
hbxNOM.getChildren().add(labelNom);
hbxNOM.getChildren().add(txtNomR);
HBox hbxPRENOM= new HBox();
hbxPRENOM.getChildren().add(labelPrenom);
hbxPRENOM.getChildren().add(txtPrenomR);
HBox hbxDPT= new HBox();
hbxDPT.getChildren().add(labelDepartement);
hbxDPT.getChildren().add(txtDepartementR);
HBox hbxPROMO= new HBox();
hbxPROMO.getChildren().add(labelPromo);
hbxPROMO.getChildren().add(txtPromoR);
HBox hbxANNEE= new HBox();
hbxPRENOM.getChildren().add(labelAnnee);
hbxPRENOM.getChildren().add(txtAnneeR);



vueModif.add(labelNom, 0, 0);
vueModif.add(txtNomR, 1, 0);
vueModif.add(labelPrenom, 0, 1);
vueModif.add(txtPrenomR, 1, 1);
vueModif.add(labelDepartement, 0, 2);
vueModif.add(txtDepartementR, 1, 2);
vueModif.add(labelPromo, 0, 3);
vueModif.add(txtPromoR, 1, 3);
vueModif.add(labelAnnee, 0, 4);
vueModif.add(txtAnneeR, 1, 4);
vueModif.add(ValiderModifs, 1, 5);
vueModif.add(retour2, 3, 5);

}


	    
	    
//////////////////////////////////////////////
///////GETTERS ET SETTERS	
///////////////////////////////////////////////		    
		public Button getValiderModifs() {
			return ValiderModifs;
		}


		public void setValiderModifs(Button validerModifs) {
			ValiderModifs = validerModifs;
		}


		public static Label getLabelNom() {
			return labelNom;
		}


		public static void setLabelNom(Label labelNom) {
			VueModification.labelNom = labelNom;
		}


		public static Label getLabelPrenom() {
			return labelPrenom;
		}


		public static void setLabelPrenom(Label labelPrenom) {
			VueModification.labelPrenom = labelPrenom;
		}


		public static Label getLabelDepartement() {
			return labelDepartement;
		}


		public static void setLabelDepartement(Label labelDepartement) {
			VueModification.labelDepartement = labelDepartement;
		}


		public static Label getLabelPromo() {
			return labelPromo;
		}


		public static void setLabelPromo(Label labelPromo) {
			VueModification.labelPromo = labelPromo;
		}


		public static Label getLabelAnnee() {
			return labelAnnee;
		}


		public static void setLabelAnnee(Label labelAnnee) {
			VueModification.labelAnnee = labelAnnee;
		}


		public Button getRetour2() {
			return retour2;
		}


		public void setRetour2(Button retour2) {
			this.retour2 = retour2;
		}


		public ChangesController getControlleur() {
			return Controlleur;
		}


		public void setControlleur(ChangesController controlleur) {
			Controlleur = controlleur;
		}




		public static TextField getTxtNomR() {
			return txtNomR;
		}




		public static void setTxtNomR(TextField txtNomR) {
			VueModification.txtNomR = txtNomR;
		}




		public static TextField getTxtPrenomR() {
			return txtPrenomR;
		}




		public static void setTxtPrenomR(TextField txtPrenomR) {
			VueModification.txtPrenomR = txtPrenomR;
		}




		public static TextField getTxtDepartementR() {
			return txtDepartementR;
		}




		public static void setTxtDepartementR(TextField txtDepartementR) {
			VueModification.txtDepartementR = txtDepartementR;
		}




		public static TextField getTxtPromoR() {
			return txtPromoR;
		}




		public static void setTxtPromoR(TextField txtPromoR) {
			VueModification.txtPromoR = txtPromoR;
		}




		public static TextField getTxtAnneeR() {
			return txtAnneeR;
		}




		public static void setTxtAnneeR(TextField txtAnneeR) {
			VueModification.txtAnneeR = txtAnneeR;
		}

		
			
//////////////////////////////////////////////////FIN/////////////////////////////////////			
}
