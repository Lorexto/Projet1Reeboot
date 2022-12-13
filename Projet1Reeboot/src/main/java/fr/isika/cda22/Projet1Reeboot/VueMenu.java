package fr.isika.cda22.Projet1Reeboot;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
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
	static TableView<Stagiaire4Liste> table;
	

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
        final Label label = new Label("Liste des stagiaires");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
        
        TableColumn nomCol = new TableColumn("Nom");
        nomCol.setMinWidth(100);
        nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("nom"));
        TableColumn prenomCol = new TableColumn("Prénom");
        prenomCol.setMinWidth(100);
        prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("prenom"));
        TableColumn anneeCol = new TableColumn("Année");
        anneeCol.setMinWidth(50);
        anneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("annee"));
        TableColumn promoCol = new TableColumn("Promo");
        promoCol.setMinWidth(50);
        promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("id"));
        TableColumn dptCol = new TableColumn("Département");
        dptCol.setMinWidth(50);
        dptCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("dpt"));
        table.getColumns().addAll(nomCol, prenomCol, dptCol, promoCol, anneeCol);
        //On rempli la table avec la liste observable
        table.setItems(getContactList());
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(20, 20, 20, 20));

       //AJOUT ELEMENTS DANS ROOT
		root.add(ConteneurBoutons,1,2);
		root.add(table, 1, 4);
	    root.add(refresh,8,4);
	    root.add(disconnect, 8, 8);
	}

//		StackPane listeStagiaires = new StackPane();
//		ScrollPane pane = new ScrollPane();
////		ListView<Stagiaire> list = new ListView<Stagiaire>();
//	    final ListView<Stagiaire> Stagiaires = new ListView<Stagiaire>();
//	    ObservableList<Stagiaire> items = LectureBin.createArrayListFromBIN();
//	    Stagiaires.setItems(items);
//	    Stagiaires.setPrefSize(400, 30);
//	    Stagiaires.setEditable(true);
//	    Stagiaires.setCellFactory(ComboBoxListCell.forListView(items));
//        listeStagiaires.getChildren().add(Stagiaires);



		public static ObservableList<Stagiaire4Liste> getContactList() {
			try {
			RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
			ArrayList<Noeud3> ListOrdreAlpha = new ArrayList<Noeud3>();
			ListOrdreAlpha = Noeud3.ordreAlpha(0, raf, ListOrdreAlpha);
			Noeud3 racine = ListOrdreAlpha.get(0);

			Stagiaire4Liste st1 = new Stagiaire4Liste(racine.getCle().getNom().split("\\*")[0], racine.getCle().getPrenom().split("\\*")[0], racine.getCle().getId().split("\\*")[0], racine.getCle().getDpt().split("\\*")[0], racine.getCle().getAnnee().split("\\*")[0]);
			ObservableList<Stagiaire4Liste> list = FXCollections.observableArrayList(st1);


			String nomCourant;
			for (int i=1; i<((int)raf.length())/132; i++) {
				nomCourant = ListOrdreAlpha.get(i).getCle().getNom().split("\\*")[0];

			boolean go = true;
			int j = 1;
			while (j<(((int)raf.length()))/132 && go == true) {
				
				Noeud3 nCourant = Noeud3.lireParentSuivant(j, raf);
				String nom = nCourant.getCle().getNomLong().split("\\*")[0];
				j++;

				if (nom.compareTo(nomCourant) == 0) {
					String prenom = nCourant.getCle().getPrenomLong().split("\\*")[0];
					String id = nCourant.getCle().getId().split("\\*")[0];
					String dpt = nCourant.getCle().getDpt().split("\\*")[0];
					String annee = nCourant.getCle().getAnnee().split("\\*")[0];
					Stagiaire4Liste newSt = new Stagiaire4Liste(nom, prenom, id, dpt, annee);
					list.add(newSt);
					go = false;
				}
				
			}
			}

		    return list;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		}
//		    System.out.println("HAHAHAHHAHAHAHAHAHA");
//			Noeud3 n = new Noeud3();
//			try {
//				System.out.println("HIHIHI");
//				RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
//				ArrayList<Noeud3> ListOrdreAlpha = new ArrayList<Noeud3>();
//				ListOrdreAlpha = n.ordreAlpha(0, raf, ListOrdreAlpha);
//				System.out.println(ListOrdreAlpha);
//				// Liste pour affichage
//				ObservableList<Stagiaire4Liste> list = FXCollections.observableArrayList();
//				for (int i=1; i<=(int)raf.length()/132; i++) { // on parcourt la ListOrdreAlpha
//					
//					String nom = ListOrdreAlpha.get(i-1).getCle().getNom().split("\\*")[0]; // et on stocke le nom, donc par ordre alpha
//					String prenom = ListOrdreAlpha.get(i-1).getCle().getPrenomLong().split("\\*")[0];
//					String id = ListOrdreAlpha.get(i-1).getCle().getId().split("\\*")[0];
//					String dpt = ListOrdreAlpha.get(i-1).getCle().getDpt().split("\\*")[0];
//					String annee = ListOrdreAlpha.get(i-1).getCle().getAnnee().split("\\*")[0];
//					Stagiaire4Liste newSt = new Stagiaire4Liste(nom, prenom, id, dpt, annee);
//					list.add(newSt);
//				}
//			    return list;
//			} catch (IOException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}





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





	public TableView<Stagiaire4Liste> getTable() {
		return table;
	}





	public void setTable(TableView<Stagiaire4Liste> table) {
		this.table = table;
	}




	public Button getRefresh() {
	return refresh;
}

public void setRefresh(Button refresh) {
	this.refresh = refresh;
}





		}
