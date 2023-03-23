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
    private TextField txfFadNr, txfFadtype, txfFadSize, txfLeverandør, txfLager, txfPlads;

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
        Label lblFadNr = new Label("Fad nummer");
        this.add(lblFadNr,3,0);

        txfFadNr = new TextField();
        this.add(txfFadNr,3,1);

        Label lblFadType = new Label("Fadtype");
        this.add(lblFadType,4,0);

        txfFadtype = new TextField();
        this.add(txfFadtype,4,1);

        Label lblFadSize = new Label("Fadstørrelse");
        this.add(lblFadSize,3,2);

        txfFadSize = new TextField();
        this.add(txfFadSize,3,3);

        Label lblLeverandør = new Label("Leverandør");
        this.add(lblLeverandør,4,2);



    }

    private void selectedFadChanged() {
        this.updateControls();
    }

    public void updateControls() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            txfFadNr.setText(String.valueOf(fad.getFadNr()));
        }
    }
}
