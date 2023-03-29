package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Flaske;

public class FlaskePane extends GridPane {
    private ListView<Flaske> lvwFlasker;
    private TextField txfSoegning, txfNavn, txfFlaskeNr, txfDatoForTapning, txfAlkoholProcent, txfFlaskeStoerrelse, txfFortyndingsmaengde, txfVandType, txfBeskrivelse;

    public FlaskePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        txfSoegning = new TextField();
        txfSoegning.setPromptText("Søg efter flaske");


        lvwFlasker = new ListView<>();
        this.add(lvwFlasker, 0, 1,1,6);
        lvwFlasker.setPrefHeight(200);
        lvwFlasker.setPrefWidth(400);
        lvwFlasker.getItems().setAll(Controller.getFlasker());

        ChangeListener<Flaske> listener = (ov, oldCompny, newCompany) -> this.selectedFlaskeChanged();
        lvwFlasker.getSelectionModel().selectedItemProperty().addListener(listener);

        Button btnCreateFlaske = new Button("Create flaske");
        this.add(btnCreateFlaske, 0, 8);
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
