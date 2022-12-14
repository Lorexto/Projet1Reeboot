package fr.isika.cda22.Projet1Reeboot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class AppliLogin extends Application {

	public VueLogin Login;
	public LoginController LoginControl;
	public ChangesController controlleur;
public VueMenu vueMenu;
public VueFormulaire Formulaire;
public VueModification VModif;

    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {
/////////////////////////////////////////
///////////////Declaration des pages/
////////////////////////////////////////    	
    	Login= new VueLogin();
       	vueMenu= new VueMenu();
    	Formulaire= new VueFormulaire();
    	VModif= new VueModification();
//////////////////////////////////////////////////
/////////// EVENT LOGIN//////////
///////////////////////////////////////////////////    	
    	Login.getSubmit().setOnAction(eventAction ->{
    	try {

        	  if (LoginController.loginButton(Login.submit.getOnMouseClicked()))
        	         {stage.setScene(vueMenu);}
    		}
    	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});  
////////////////////////////////////////////////////////////////////////
/////////// EVENT AJOUT STAGIAIRE : REDIRECTION PAGE FORMULAIRE//////////
//////////////////////////////////////////////////////////////////////// 
 
    	vueMenu.getAddButton().setOnAction(eventAction ->{
    		try {
				ChangesController.Ajouter(vueMenu.addButton.getOnMouseClicked());
				if(ChangesController.Ajouter(vueMenu.addButton.getOnMouseClicked()))
					stage.setScene(Formulaire);
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
///////////////////////////////////////////////////////////////////
///////////////EVENT AJOUT DANS LISTE ET RETOUR MENU//////
///////////////////////////////////////////////////////////////////    	
    	
    	Formulaire.getBtn().setOnAction(eventAction ->{
    		try {
				if(ChangesController.AddFormulaireToBIN(Formulaire.btn.getOnMouseClicked())) {
					stage.setScene(vueMenu);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	});
//////////////////////////////////////////////////
/////////// EVENT REFRESH//////////
///////////////////////////////////////////////////     	
    	vueMenu.getRefresh().setOnAction(eventAction ->{
    		
    		try {
				if(ChangesController.Update(vueMenu.refresh.getOnMouseClicked())) {
					VueMenu.table.setItems(VueMenu.getContactList());
					VueMenu.table.refresh();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
    	});
///////////////////////////////////////////////////////////
/////////// EVENT DECONNEXION ET RETOUR AU LOGIN//////////
///////////////////////////////////////////////////////////     	
    	
    	vueMenu.getDisconnect().setOnAction(eventAction ->{
    		
    		try {
				if(ChangesController.Deconnexion(vueMenu.disconnect.getOnMouseClicked())==true) {
					stage.setScene(Login);
				}
				else {stage.setScene(vueMenu);}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});

//////////////////////////////////////////////////
/////////// EVENT MODIFICATION STAGIAIRE//////////
///////////////////////////////////////////////////     	

    	vueMenu.getRefactor().setOnAction(eventAction ->{
    		 
				try {
				if(ChangesController.Modifications(vueMenu.refactor.getOnMouseClicked())&&vueMenu.table.getSelectionModel().isEmpty()!=true) {
						stage.setScene(VModif);
				}
				else {
					Alert msg = new Alert(AlertType.WARNING);
				    msg.setTitle("ATTENTION");
				    msg.setContentText("VEUILLEZ SELECTIONNER UN STAGIAIRE");
				   // msg.showAndWait();
				    Optional<ButtonType> option = msg.showAndWait();	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			
    	});

//////////////////////////////////////////////////
/////////// EVENT RETOUR FROM FORM//////////
///////////////////////////////////////////////////   
    	Formulaire.getRetour().setOnAction(eventAction ->{
    		try {
				if(ChangesController.Retour(Formulaire.retour.getOnMouseClicked())==true) {
					stage.setScene(vueMenu);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});    	
//////////////////////////////////////////////////
/////////// EVENT RETOUR FROM MODIF FORM//////////
///////////////////////////////////////////////////   
    	VModif.getRetour2().setOnAction(eventAction ->{
try {
if(ChangesController.Retour(VModif.retour2.getOnMouseClicked())==true) {
stage.setScene(vueMenu);
}
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}); 
/////////////////////////////////////////////////
/////////// EVENT SUPPRESSION//////////
///////////////////////////////////////////////////
    	vueMenu.getDelete().setOnAction(eventAction ->{
    		try {
				
				if(ChangesController.Delete(VueMenu.delete.getOnMouseClicked()))
					stage.setScene(vueMenu);
							VueMenu.table.setItems(VueMenu.getContactList());
							VueMenu.table.refresh();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();		
			}
    	});
    	
/////////////////////////////////////////////////
/////////// EVENT MODIFICATION//////////
///////////////////////////////////////////////////
   VModif.getValiderModifs().setOnAction(eventAction ->{
   
   try {
		
		if(ChangesController.SubmitChanges(VModif.ValiderModifs.getOnMouseClicked())) {
			        
			        stage.setScene(vueMenu);
					VueMenu.table.setItems(VueMenu.getContactList());
					VueMenu.table.refresh();}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}		
});
/////////////////////////////////////////////////
/////////// EVENT TO PRINT//////////
/////////////////////////////////////////////////// 
   vueMenu.getPrint().setOnAction(eventAction ->{

	   ChangesController.print(VueMenu.table);
	
		        stage.setScene(vueMenu);		
	});  
	  
   
   	
/////////////////////////FIN DES EVENTS ///////////////////////    	
    	
    	
        stage.setScene(Login);
        stage.setTitle("École ISIKA");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}