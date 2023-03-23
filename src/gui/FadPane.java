package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Fad;

public class FadPane extends GridPane {
    private ListView<Fad> lvwFade;
    private TextField txfDatoForPaafyld, txfFadNr, txfFadtype, txfFadSize, txfSpiritNr,
                        txfAlkoholPro, txfMedarbejder, txfPlads, txfLeverandør, txfLager;

    public FadPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lvwFade = new ListView<>();
        this.add(lvwFade, 0, 0,2,10);
        lvwFade.setPrefWidth(200);
        lvwFade.setPrefHeight(200);
        lvwFade.getItems().setAll(Controller.getFad());

        ChangeListener<Fad> listener = (ov, oldCompny, newCompany) -> this.selectedFadChanged();
        lvwFade.getSelectionModel().selectedItemProperty().addListener(listener);

        //Informationer til fade
        Label lblDatoForPaafyld = new Label("Dato for påfyld");
        this.add(lblDatoForPaafyld,3,0);

        txfDatoForPaafyld = new TextField();
        this.add(txfDatoForPaafyld,3,1);
        
        Label lblFadNr = new Label("Fad nummer");
        this.add(lblFadNr,4,0);
        
        txfFadNr = new TextField();
        this.add(txfFadNr,4,1);

    }

    private void selectedFadChanged() {
        this.updateControls();
    }

    public void updateControls() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            txfDatoForPaafyld.setText(fad.getDatoForPaafyldning().toString());
            txfFadNr.setText(String.valueOf(fad.getFadNr()));
        }
    }
}
