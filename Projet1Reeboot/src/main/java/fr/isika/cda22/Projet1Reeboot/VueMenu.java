package fr.isika.cda22.Projet1Reeboot;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ArrayChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VueMenu extends Scene {
	
//////////////////////////////////////////
////////ATTRIBUTS///////
//////////////////////////////////////////
	GridPane VueMenu;
	StackPane listeStagiaires;
	Button search;
	Button addButton;
	Button delete;
	Button refactor;
	Button disconnect;
	Button refresh;
	ChangesController Controlleur;
	final Noeud3 ListChangeListener = null;
	static TableView<Stagiaire> table;
    static ArrayList<Stagiaire> ListOrdreAlpha ;
    static ObservableList<Stagiaire> list;
    static FilteredList <Stagiaire> listFilt;
    public static TextField searchBar;
    static Stagiaire stagiaire;
    Button searchButton;
    static ObservableList<Stagiaire>filteredList;
    String searchTxt;
  ///////////////////////////////////////////////////                

	
	public VueMenu()  {
		super(new GridPane(),800,600);
		GridPane root = (GridPane)this.getRoot();
		
////////////////////////////////////////////////////
////////////BOUTONS et FIELDS
///////////////////////////////////////////////////		
		search = new Button ("RECHERCHE");
	    addButton = new Button ("AJOUTER");
		delete= new Button("SUPPRIMER");
		refactor= new Button("MODIFIER");
		disconnect = new Button ("DECONNEXION");
		refresh= new Button ("RAFRAICHIR LA PAGE");
		//HBOX et INSERTON BOUTONS
		HBox ConteneurBoutons= new HBox();
		ConteneurBoutons.getChildren().add(0, search);
		ConteneurBoutons.getChildren().add(1, addButton);
		ConteneurBoutons.getChildren().add(2, delete);
		ConteneurBoutons.getChildren().add(3, refactor);
	//	ConteneurBoutons.getChildren().add(4, disconnect);
		searchBar= new TextField();    
//////////////////////////////////////////////////////////
////// TABLE VIEW SET UP//////////
/////////////////////////////////////////////////////////   
		table = new TableView<>();
        final Label label = new Label("Liste des stagiaires");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
        TableColumn<Stagiaire, String> nomCol = new TableColumn<>("Nom");
        nomCol.setMinWidth(100);
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Stagiaire, String> prenomCol = new TableColumn<>("Prénom");
        prenomCol.setMinWidth(100);
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TableColumn<Stagiaire, String> anneeCol = new TableColumn<>("Année");
        anneeCol.setMinWidth(50);
        anneeCol.setCellValueFactory(new PropertyValueFactory<>("annee"));
        TableColumn<Stagiaire, String> promoCol = new TableColumn<>("Promo");
        promoCol.setMinWidth(50);
        promoCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Stagiaire, String> dptCol = new TableColumn<>("Département");
        dptCol.setMinWidth(50);
        dptCol.setCellValueFactory(new PropertyValueFactory<>("dpt"));
        table.getColumns().addAll(nomCol,prenomCol,anneeCol,promoCol,dptCol);
/////////////////////////////////////////////////////////////////////////////////
/////////////////FILTER SET UP////////////////////////
////////////////////////////////////////////////////////////////////////////////                
        TextField filterField= new TextField();
        list=getContactList();
        listFilt = new FilteredList<Stagiaire>(list,e->true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        	listFilt.setPredicate(stagiaire -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                String lowerCaseFilter = newValue.toLowerCase();
				
				if (stagiaire.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (stagiaire.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match
			});    	
		});
        ///////////////////////////////////////////////////////////////////
        searchBar.setOnKeyReleased(e->{
			
			searchBar.textProperty().addListener((observableValue,oldValue,newValue)->{
				listFilt.setPredicate((Predicate<? super Stagiaire>) stagiaire ->{
					
					if(newValue==null||newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter= newValue.toLowerCase();
					
//					int i=0;
//					while( i<listFilt.size()) {
						if(stagiaire.getNom().toLowerCase().contains(lowerCaseFilter)) {
							return true;
						}
					
						else if(stagiaire.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
							return true;
						}
						else if(stagiaire.getId().toLowerCase().contains(lowerCaseFilter)){
							return true;
						}
					
					//}
					
					return false;
				});
			});
			
		});
        ////////////////////////////
        SortedList<Stagiaire> ResultatsTris= new SortedList<>(listFilt);
		ResultatsTris.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(ResultatsTris);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(20, 20, 20, 20));

       //AJOUT ELEMENTS DANS ROOT
		root.add(ConteneurBoutons,1,2);
		root.add(table, 1, 4);
	    root.add(refresh,8,4);
	    root.add(disconnect, 8, 9);
	    root.add(searchBar, 10, 7);
	    root.add(search, 9, 7);
	}

///////////////////////////////////////////////////////////////////////
/////////////////METHODES/////////////////////////
//////////////////////////////////////////////////////////////////////	

		public static ObservableList<Stagiaire> getContactList() {

			Stagiaire st1 = new Stagiaire("LACROIX","Kim", "CDA", "98", "2012");
			Stagiaire st2 = new Stagiaire("CHAVENEAU","Alex","AL", "98","2012");
			Stagiaire st3 = new Stagiaire("BON","Jean","AL", "38", "2014");
			ObservableList<Stagiaire> list = FXCollections.observableArrayList(st1,st2,st3);

			try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
			for (int i=0; i<(int)raf.length()/132; i++) {
				Noeud3 n = new Noeud3();
				Noeud3 nCourant = Noeud3.lireParentSuivant(i, raf);
				String nom = nCourant.getCle().getNomLong().split("\\*")[0];
				String prenom = nCourant.getCle().getPrenomLong().split("\\*")[0];
				String id = nCourant.getCle().getId().split("\\*")[0];
				String dpt = nCourant.getCle().getDpt().split("\\*")[0];
				String annee = nCourant.getCle().getAnnee().split("\\*")[0];
				Stagiaire newSt = new Stagiaire(nom, prenom, id, dpt, annee);
				list.add(newSt);
			}

		    return list;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		}		
		
		
		
		
		
		private static boolean searchStagiairesOrder(Stagiaire stagiaire, String searchText){
		    return (stagiaire.getNom().toLowerCase().contains(searchText.toLowerCase())) ||
		            (stagiaire.getPrenom().toLowerCase().contains(searchText.toLowerCase()));
		            
		}

		
		public static ObservableList<Stagiaire> filterList(List<Stagiaire> list, String searchText){
		    List<Stagiaire> filteredList = new ArrayList<>();
		    for (Stagiaire stagiaire : list){
		        if(searchStagiairesOrder(stagiaire, searchText)) filteredList.add(stagiaire);
		    }
		    return FXCollections.observableList(filteredList);
		}
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		public static void Search(EventHandler<? super MouseEvent> eventHandler) throws IOException{
//			table.getItems().clear();
//			table.getItems().addAll(searchList(searchBar.getText(),listFilt));
//			
//			
//		}
//		
//			
//		public static List<Stagiaire> searchList(String searchTxt,FilteredList <Stagiaire>listFilt){
//			List<String> searchTxtArray= Arrays.asList(searchTxt.trim().split(" "));
//			
//			return listFilt.stream().filter(input ->{ 
//				
//				return searchTxtArray.stream().allMatch(stagiaire ->
//				 input.toString().toLowerCase().contains(stagiaire.toLowerCase()));
//			}).collect(Collectors.toList());
//					
//}
//////////////////////////////////////////////////////////////////////////////////////////
		
		
			
			
		


//////////////////////////////////////////////////
/////////GETTERS ET SETTERS/////////////		
/////////////////////////////////////////////////
	public GridPane getVueMenu() {
		return VueMenu;
	}



	public void setVueMenu(GridPane vueMenu) {
		VueMenu = vueMenu;
	}



	public StackPane getListeStagiaires() {
		return listeStagiaires;
	}



	public void setListeStagiaires(StackPane listeStagiaires) {
		this.listeStagiaires = listeStagiaires;
	}



	public Button getSearch() {
		return search;
	}



	public void setSearch(Button search) {
		this.search = search;
	}



	public Button getAddButton() {
		return addButton;
	}



	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}



	public Button getDelete() {
		return delete;
	}



	public void setDelete(Button delete) {
		this.delete = delete;
	}



	public Button getRefactor() {
		return refactor;
	}



	public void setRefactor(Button refactor) {
		this.refactor = refactor;
	}



	public Button getDisconnect() {
		return disconnect;
	}



	public void setDisconnect(Button disconnect) {
		this.disconnect = disconnect;
	}


	public TableView<Stagiaire> getTable() {
		return table;
	}


	public void setTable(TableView<Stagiaire> table) {
		this.table = table;
	}

	public Button getRefresh() {
	return refresh;
}

public void setRefresh(Button refresh) {
	this.refresh = refresh;
}

public static ArrayList<Stagiaire> getListOrdreAlpha() {
	return ListOrdreAlpha;
}

public FilteredList<Stagiaire> getListFilt() {
	return listFilt;
}

public void setListFilt(FilteredList<Stagiaire> listFilt) {
	this.listFilt = listFilt;
}

public void setList(ObservableList<Stagiaire> list) {
	this.list = list;
}

public void setListOrdreAlpha(ArrayList<Stagiaire>listOrdreAlpha) {
	ListOrdreAlpha = listOrdreAlpha;
}




		}
