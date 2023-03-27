package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.Destillering;
import model.Fad;
import model.Lager;
import model.Paafyldning;

import java.util.Map;

public class DestilleringPane extends GridPane {

    private ListView<Destillering> lvwDest;
    private ListView<Fad> lvwFade;
    private TextField txfStartDato, txfSlutDato, txfMaltBatch, txfKornSort, txfMedarbejder, txfMængde, txfAlkPro;

    public DestilleringPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblDest = new Label("Destilleringer");
        this.add(lblDest,0,0);

        lvwDest = new ListView<>();
        this.add(lvwDest, 0, 1,1,8);
        lvwDest.setPrefWidth(200);
        lvwDest.setPrefHeight(200);
        lvwDest.getItems().setAll(Controller.getDestilleringer());

        Label lblFade = new Label("Fade");
        this.add(lblFade,1,0);

        lvwFade = new ListView<>();
        this.add(lvwFade, 1, 1,1,8);
        lvwFade.setPrefWidth(200);
        lvwFade.setPrefHeight(200);

        ChangeListener<Destillering> listener = (ov, oldCompny, newCompany) -> this.selectedDestChanged();
        lvwDest.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblOverskrift = new Label("Information omkring destillering");
        this.add(lblOverskrift,2,0);
        lblOverskrift.setFont(new Font(14));

        Label lblStartDato = new Label("Startdato");
        this.add(lblStartDato,2,1);

        txfStartDato = new TextField();
        this.add(txfStartDato,2,2);
        txfStartDato.setEditable(false);

        Label lblSlutDato = new Label("Slutdato");
        this.add(lblSlutDato,3,1);

        txfSlutDato = new TextField();
        this.add(txfSlutDato,3,2);
        txfSlutDato.setEditable(false);

        Label lblMaltBatch = new Label("Malt Batch");
        this.add(lblMaltBatch,2,3);

        txfMaltBatch = new TextField();
        this.add(txfMaltBatch,2,4);
        txfMaltBatch.setEditable(false);

        Label lblKornsort = new Label("Kornsort");
        this.add(lblKornsort,3,3);

        txfKornSort = new TextField();
        this.add(txfKornSort,3,4);
        txfKornSort.setEditable(false);

        Label lblMedarbejder = new Label("Medarbejder");
        this.add(lblMedarbejder,2,5);

        txfMedarbejder = new TextField();
        this.add(txfMedarbejder,2,6);
        txfMedarbejder.setEditable(false);

        Label lblMængde = new Label("Mængde");
        this.add(lblMængde,3,5);

        txfMængde = new TextField();
        this.add(txfMængde,3,6);
        txfMængde.setEditable(false);

        Label lblAlkProcent = new Label("Alkohol Procent");
        this.add(lblAlkProcent,2,7);

        txfAlkPro = new TextField();
        this.add(txfAlkPro,2,8);
        txfAlkPro.setEditable(false);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        this.add(hBox,0,9);

        Button btnOpretDest = new Button("Opret Destillering");
        hBox.getChildren().add(btnOpretDest);
        btnOpretDest.setOnAction(event -> this.opretDestAction());

        Button btnUpdateDest = new Button("Ændre Destillering");
        hBox.getChildren().add(btnUpdateDest);
        btnUpdateDest.setOnAction(event -> this.updateAction());

        /*
        Button btnUpdate = new Button("Opdater oversigt");
        hBox.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateSelectionAction());
         */
    }

    private void updateSelectionAction() {
        Destillering destillering = lvwDest.getSelectionModel().getSelectedItem();
        lvwDest.getItems().setAll();
        if (destillering != null) {
            lvwDest.getSelectionModel().select(destillering);
            updateControls();
        }
    }

    private void opretDestAction() {
        CreateDestilleringsWindow window = new CreateDestilleringsWindow("Lav Destillering");
        window.showAndWait();
        lvwDest.getItems().setAll(Controller.getDestilleringer());
    }

    private void updateAction() {
        Destillering destillering = lvwDest.getSelectionModel().getSelectedItem();
        if (destillering != null) {
            CreateDestilleringsWindow window = new CreateDestilleringsWindow("Opdater destillering", destillering);
            window.showAndWait();
            lvwDest.getItems().setAll(Controller.getDestilleringer());
        }
    }

    private void selectedDestChanged() {
        this.updateControls();
    }

    public void updateControls() {
        Destillering destillering = lvwDest.getSelectionModel().getSelectedItem();
        lvwFade.getItems().clear();
        if (destillering != null) {
            for (Paafyldning p : destillering.getPaafyldninger()) {
                lvwFade.getItems().add(p.getFad());
            }
            txfStartDato.setText(destillering.getStartDato().toString());
            txfSlutDato.setText(destillering.getSlutDato().toString());
            txfMaltBatch.setText(destillering.getMaltBatch());
            txfKornSort.setText(destillering.getKornsort());
            txfMedarbejder.setText(destillering.getMedarbejder());
            txfMængde.setText(String.valueOf(destillering.getMaengde()));
            txfAlkPro.setText(String.valueOf(destillering.getAlkoholProcent()));
        }
    }
}
