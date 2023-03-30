package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Flaske;

public class FlaskePane extends GridPane {
    private ListView<Flaske> lvwFlasker;
    private TextField txfSoegning, txfNavn, txfFlaskeNr, txfDatoForTapning, txfAlkoholProcent, txfFlaskeStoerrelse, txfFortyndingsmaengde, txfVandType;
    private TextArea txaBeskrivelse, txaFade;

    public FlaskePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        txfSoegning = new TextField();
        txfSoegning.setPromptText("Søg efter flaske");
        this.add(txfSoegning,0,0);

        lvwFlasker = new ListView<>();
        this.add(lvwFlasker, 0, 1,1,10);
        lvwFlasker.setPrefHeight(200);
        lvwFlasker.setPrefWidth(400);
        lvwFlasker.getItems().setAll(Controller.getFlasker());

        ChangeListener<Flaske> listener = (ov, oldCompny, newCompany) -> this.selectedFlaskeChanged();
        lvwFlasker.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblNavn = new Label("Navn");
        this.add(lblNavn,2,1);

        txfNavn = new TextField();
        txfNavn.setEditable(false);
        this.add(txfNavn,2,2);

        Label lblFlaskeNr = new Label("Flaske nummer");
        this.add(lblFlaskeNr,3,1);

        txfFlaskeNr = new TextField();
        txfFlaskeNr.setEditable(false);
        this.add(txfFlaskeNr,3,2);

        Label lblDatoForTapning = new Label("Dato for tapning");
        this.add(lblDatoForTapning,2,3);

        txfDatoForTapning = new TextField();
        txfDatoForTapning.setEditable(false);
        this.add(txfDatoForTapning,2,4);

        Label lblAlkoholProcent = new Label("Alkoholprocent");
        this.add(lblAlkoholProcent,3,3);

        txfAlkoholProcent = new TextField();
        txfAlkoholProcent.setEditable(false);
        this.add(txfAlkoholProcent,3,4);

        Label lblFlaskeStørrelse = new Label("Flaske størrelse i CL");
        this.add(lblFlaskeStørrelse,2,5);

        txfFlaskeStoerrelse = new TextField();
        txfFlaskeStoerrelse.setEditable(false);
        this.add(txfFlaskeStoerrelse,2,6);

        Label lblFortyndingsMaengde = new Label("Fortyndingsmængde");
        this.add(lblFortyndingsMaengde,3,5);

        txfFortyndingsmaengde = new TextField();
        txfFortyndingsmaengde.setEditable(false);
        this.add(txfFortyndingsmaengde,3,6);

        Label lblVandtype = new Label("Vandtype");
        this.add(lblVandtype,2,7);

        txfVandType = new TextField();
        txfVandType.setEditable(false);
        this.add(txfVandType,2,8);

        Label lblBeskrivelse = new Label("Beskrivelse");
        this.add(lblBeskrivelse,2,9);

        txaBeskrivelse = new TextArea();
        txaBeskrivelse.setEditable(false);
        this.add(txaBeskrivelse,2,10);

        Label lblFade = new Label("Fade");
        this.add(lblFade,3,9);

        txaFade = new TextArea();
        txaFade.setEditable(false);
        this.add(txaFade,3,10);

        Button btnCreateFlaske = new Button("Create flaske");
        this.add(btnCreateFlaske, 0, 11);
        btnCreateFlaske.setOnAction(event -> this.btnCreateAction());
    }

    private void btnCreateAction() {
        CreateFlaskeWindow createFlaskeWindow = new CreateFlaskeWindow("Lav og fyld flaske");
        createFlaskeWindow.showAndWait();
        lvwFlasker.getItems().setAll(Controller.getFlasker());
    }

    private void selectedFlaskeChanged() {
        this.updateControls();
    }

    private void updateControls() {
    }
}
