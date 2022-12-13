package fr.isika.cda22.Projet1Reeboot;

import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class Liste extends Application {
//    private TableView<Stagiaire4Liste> table = new TableView<>();
//
//	private ObservableList<Stagiaire4Liste> getContactList() {
//
//		Stagiaire4Liste st1 = new Stagiaire4Liste("LACROIX","Kim", "CDA", "98", "2012");
//		Stagiaire4Liste st2 = new Stagiaire4Liste("CHAVENEAU","Alex","AL", "98","2012");
//		Stagiaire4Liste st3 = new Stagiaire4Liste("BON","Jean","AL", "38", "2014");
//		ObservableList<Stagiaire4Liste> list = FXCollections.observableArrayList(st1,st2,st3);
//
//		try {
//		RandomAccessFile raf = new RandomAccessFile("src/main/java/fr/isika/cda22/Projet1Reeboot/fichbinTEST3.bin", "rw");
//		for (int i=0; i<(int)raf.length()/132; i++) {
//			Noeud3 n = new Noeud3();
//			Noeud3 nCourant = Noeud3.lireParentSuivant(i, raf);
//			String nom = nCourant.getCle().getNomLong().split("\\*")[0];
//			String prenom = nCourant.getCle().getPrenomLong().split("\\*")[0];
//			String id = nCourant.getCle().getId().split("\\*")[0];
//			String dpt = nCourant.getCle().getDpt().split("\\*")[0];
//			String annee = nCourant.getCle().getAnnee().split("\\*")[0];
//			Stagiaire4Liste newSt = new Stagiaire4Liste(nom, prenom, id, dpt, annee);
//			list.add(newSt);
//		}
//
//	    return list;
//
//	} catch (IOException e) {
//		e.printStackTrace();
//		return null;
//	}
//
//	  }
//
//	public static void main(String[] args) {
//		launch(args);
//		}
//    @Override
//    public void start(Stage stage) {
//        Scene scene = new Scene(new Group());
//        scene.getRoot().setStyle("-fx-font-family: 'serif'");
//        stage.setTitle("École ISIKA");
//        //stage.setWidth(450);
//        //stage.setHeight(500);
//        final Label label = new Label("Liste des stagiaires");
//        label.setFont(new Font("Arial", 20));
//        table.setEditable(true);
//        TableColumn nomCol = new TableColumn("Nom");
//        nomCol.setMinWidth(100);
//        nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("nom"));
//        TableColumn prenomCol = new TableColumn("Prénom");
//        prenomCol.setMinWidth(100);
//        prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("prenom"));
//        TableColumn anneeCol = new TableColumn("Année");
//        anneeCol.setMinWidth(50);
//        anneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("annee"));
//        TableColumn promoCol = new TableColumn("Promo");
//        promoCol.setMinWidth(50);
//        promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("id"));
//        TableColumn dptCol = new TableColumn("Département");
//        dptCol.setMinWidth(50);
//        dptCol.setCellValueFactory(new PropertyValueFactory<Stagiaire4Liste, String>("dpt"));
//        table.getColumns().addAll(nomCol, prenomCol, dptCol, promoCol, anneeCol);
//        //On rempli la table avec la liste observable
//        table.setItems(getContactList());
//        VBox vbox = new VBox();
//        vbox.setSpacing(5);
//        vbox.setPadding(new Insets(20, 20, 20, 20));
//        vbox.getChildren().addAll(label, table);
//        ((Group) scene.getRoot()).getChildren().addAll(vbox);
//        stage.setScene(scene);
//        stage.show();
//    }
}
