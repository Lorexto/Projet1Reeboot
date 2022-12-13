package fr.isika.cda22.Projet1Reeboot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
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

    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {


    	Login= new VueLogin();
       	vueMenu= new VueMenu();
    	Formulaire= new VueFormulaire();

    	// Si on clique sur Valider
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
    	vueMenu.getAddButton().setOnAction(eventAction ->{
    		try {
				ChangesController.Ajouter(vueMenu.addButton.getOnMouseClicked());
				if(ChangesController.Ajouter(vueMenu.addButton.getOnMouseClicked()))
					stage.setScene(Formulaire);
					System.out.println("ne veut pas s'ouvrir");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});

    	Formulaire.getBtn().setOnAction(eventAction ->{
    		try {
				//ChangesController.AddFormulaireToBIN(Formulaire.btn.getOnMouseClicked());
				if(ChangesController.AddFormulaireToBIN(Formulaire.btn.getOnMouseClicked())) {
					stage.setScene(vueMenu);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	});
    	vueMenu.getRefresh().setOnAction(eventAction ->{
    		
    		try {
				if(ChangesController.Update(vueMenu.refresh.getOnMouseClicked())) {
					VueMenu.table.setItems(VueMenu.getContactList(VueMenu.getListOrdreAlpha()));
					VueMenu.table.refresh();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
    	});
    	
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
    	
    	
    	
    	
    	
    	
    	
    	
    




        stage.setScene(Login);
        stage.setTitle("École ISIKA");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}