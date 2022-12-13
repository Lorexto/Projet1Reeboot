package fr.isika.cda22.Projet1Reeboot;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.collections.ArrayChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VueMenu extends Scene {
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
    FilteredList <Stagiaire> listFilt;
                  

	

	public ObservableList<Stagiaire> getList() {
		return list;
	}

	public void setList(FilteredList<Stagiaire> list) {
		this.list = list;
	}

	public VueMenu()  {
		super(new GridPane(),800,600);
		GridPane root = (GridPane)this.getRoot();
		//this.setRoot(root);


		//BOUTONS
		search = new Button ("RECHERCHE");
	    addButton = new Button ("AJOUTER");
		delete= new Button("SUPPRIMER");
		refactor= new Button("MODIFIER");
		disconnect = new Button ("DECONNExION");
		refresh= new Button ("RAFRAICHIR LA PAGE");
		//HBOX et INSERTON BOUTONS
		HBox ConteneurBoutons= new HBox();
		ConteneurBoutons.getChildren().add(0, search);
		ConteneurBoutons.getChildren().add(1, addButton);
		ConteneurBoutons.getChildren().add(2, delete);
		ConteneurBoutons.getChildren().add(3, refactor);
	//	ConteneurBoutons.getChildren().add(4, disconnect);
		//TABLE VIEW
	    table = new TableView<>();
	   // table.setItems(getContactList(ListOrdreAlpha ));
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

        TextField filterField= new TextField();
      //Adding ChoiceBox and TextField here!
        list=getContactList();
        listFilt = new FilteredList<Stagiaire>(list);
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
				return false; // Does not match.
			});
		});
       SortedList<Stagiaire> SortedSt= new SortedList<>(listFilt);
        SortedSt.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(SortedSt);
        
        
        
        
        
        
        
        
        
        
        
        
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(20, 20, 20, 20));

       //AJOUT ELEMENTS DANS ROOT
		root.add(ConteneurBoutons,1,2);
		root.add(table, 1, 4);
	    root.add(refresh,8,4);
	    root.add(disconnect, 8, 8);
	}



		public static ObservableList<Stagiaire> getContactList(ArrayList<Stagiaire> ListOrdreAlpha ) {
//			try {
//			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
//			ArrayList<Noeud3> ListOrdreAlpha = new ArrayList<Noeud3>();
//			ListOrdreAlpha = Noeud3.ordreAlpha(0, raf, ListOrdreAlpha);
//			Noeud3 racine = ListOrdreAlpha.get(0);
//
//			Stagiaire4Liste st1 = new Stagiaire4Liste(racine.getCle().getNom().split("\\*")[0], racine.getCle().getPrenom().split("\\*")[0], racine.getCle().getId().split("\\*")[0], racine.getCle().getDpt().split("\\*")[0], racine.getCle().getAnnee().split("\\*")[0]);
//			ObservableList<Stagiaire4Liste> list = FXCollections.observableArrayList(st1);
//
//
//			String nomCourant;
//			for (int i=1; i<((int)raf.length())/132; i++) {
//				nomCourant = ListOrdreAlpha.get(i).getCle().getNom().split("\\*")[0];
//
//			boolean go = true;
//			int j = 1;
//			while (j<(((int)raf.length()))/132 && go == true) {
//				
//				Noeud3 nCourant = Noeud3.lireParentSuivant(j, raf);
//				String nom = nCourant.getCle().getNomLong().split("\\*")[0];
//				j++;
//
//				if (nom.compareTo(nomCourant) == 0) {
//					String prenom = nCourant.getCle().getPrenomLong().split("\\*")[0];
//					String id = nCourant.getCle().getId().split("\\*")[0];
//					String dpt = nCourant.getCle().getDpt().split("\\*")[0];
//					String annee = nCourant.getCle().getAnnee().split("\\*")[0];
//					Stagiaire4Liste newSt = new Stagiaire4Liste(nom, prenom, id, dpt, annee);
//					list.add(newSt);
//					go = false;
//				}
//				
//			}
//			}
//
//		    return list;
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//		}         
			
			
		    System.out.println("HAHAHAHHAHAHAHAHAHA");
			Noeud3 n = new Noeud3();
			try {
				
				System.out.println("HIHIHI");
				RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
				
				
				ListOrdreAlpha = n.ordreAlpha(0, raf, ListOrdreAlpha);
				ObservableList<Stagiaire> list =  FXCollections.observableArrayList(ListOrdreAlpha);
				int numNoeud;
				for (int i=1;i< raf.length()/132;i++) {
				ListOrdreAlpha = n.ordreAlpha(i, raf, ListOrdreAlpha);
					Stagiaire st=ListOrdreAlpha.iterator().next();
					list.add(st);
				}
				
//				for (int i=1; i<=(int)raf.length()/132; i++) { // on parcourt la ListOrdreAlpha		
//					
//				
//					String nom = ListOrdreAlpha.get(i-1).getNom().split("\\*")[0]; // et on stocke le nom, donc par ordre alpha
//					String prenom = ListOrdreAlpha.get(i-1).getPrenomLong().split("\\*")[0];
//					String id = ListOrdreAlpha.get(i-1).getId().split("\\*")[0];
//					String dpt = ListOrdreAlpha.get(i-1).getDpt().split("\\*")[0];
//					String annee = ListOrdreAlpha.get(i-1).getAnnee().split("\\*")[0];
//					Stagiaire newSt = new Stagiaire(nom, prenom, id, dpt, annee);
//					System.out.println("SDFDGHTYJTHRGEF");
		
					
					System.out.println(list);
					
				
			    return list;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		
		

		private ObservableList<Stagiaire> getContactList() {

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
