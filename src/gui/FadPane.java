package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Fad;
import model.Paafyldning;

import java.util.Collections;

public class FadPane extends GridPane {
    private ListView<Fad> lvwFade;
    private TextField txfSoegning, txfFadNr, txfFadtype, txfFadSize, txfLeverandør, txfLager, txfPlads;
    private Button btnCreate, btnTilknyt;
    private TextArea txaDest;

    public FadPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        txfSoegning = new TextField();
        this.add(txfSoegning,0,0);
        txfSoegning.setPromptText("Søg efter fad nummer");
        txfSoegning.textProperty().addListener(event -> soegningAction());

        lvwFade = new ListView<>();
        this.add(lvwFade, 0, 1,2,6);
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
        txfFadNr.setEditable(false);

        Label lblFadType = new Label("Fadtype");
        this.add(lblFadType,4,0);

        txfFadtype = new TextField();
        this.add(txfFadtype,4,1);
        txfFadtype.setEditable(false);

        Label lblFadSize = new Label("Fadstørrelse");
        this.add(lblFadSize,3,2);

        txfFadSize = new TextField();
        this.add(txfFadSize,3,3);
        txfFadSize.setEditable(false);

        Label lblLeverandør = new Label("Leverandør");
        this.add(lblLeverandør,4,2);

        txfLeverandør = new TextField();
        this.add(txfLeverandør,4,3);
        txfLeverandør.setEditable(false);

        Label lblLager = new Label("Lager");
        this.add(lblLager,3,4);

        txfLager = new TextField();
        this.add(txfLager,3,5);
        txfLager.setEditable(false);

        Label lblPlads = new Label("Plads");
        this.add(lblPlads,4,4);

        txfPlads = new TextField();
        this.add(txfPlads,4,5);
        txfPlads.setEditable(false);

        Label lblDest = new Label("Destilleringer på fadet");
        this.add(lblDest,5,0);

        txaDest = new TextArea();
        this.add(txaDest,5,1,2,6);
        txaDest.setPrefWidth(400);
        txaDest.setPrefHeight(200);
        txaDest.setEditable(false);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        this.add(hBox, 0,8);

        btnCreate = new Button("Lav fad");
        hBox.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> this.createAction());

        Button btnUpdate = new Button("Opdater fad");
        hBox.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateAction());

        btnTilknyt = new Button("Tilknyt lager");
        hBox.getChildren().add(btnTilknyt);
        btnTilknyt.setOnAction(event -> this.tilknytAction());

        Button btnOmhaeld = new Button("Omhæld fad");
        hBox.getChildren().add(btnOmhaeld);
        btnOmhaeld.setOnAction(event -> this.omhaeldAction());

        lvwFade.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Fad selectedItem = lvwFade.getSelectionModel().getSelectedItem();
                if(selectedItem != null) {
                    FlaskeFadWindow window = new FlaskeFadWindow("Flasker tilknyttet: " + lvwFade.getSelectionModel().getSelectedItem() , lvwFade.getSelectionModel().getSelectedItem());
                    window.showAndWait();
                }
            }
        });
    }

    private void soegningAction() {
        try {
            lvwFade.getItems().setAll(Controller.soegningFad(Controller.getFad(), Integer.parseInt(txfSoegning.getText())));
        } catch (NumberFormatException e) {
            lvwFade.getItems().setAll(Controller.getFad());
        }
    }


    private void selectedFadChanged() {
        this.updateControls();
    }

    public void updateControls() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            txfFadNr.setText(String.valueOf(fad.getFadNr()));
            txfPlads.setText(String.valueOf(fad.getPlads()));
            txfLeverandør.setText(fad.getLeverandoer());
            txfFadtype.setText(fad.getFadType());
            txfFadSize.setText(String.valueOf(fad.getFadStoerrelse()));
            if (fad.getLager() != null) {
                txfLager.setText(fad.getLager().toString());
            } else {
                txfLager.setText("Ingen");
            }
            txaDest.clear();
            if (!fad.getPaafyldninger().isEmpty()) {
                for (Paafyldning p : fad.getPaafyldninger()) {
                    txaDest.appendText(p.toString() + "\n");
                }
            }
        } else {
            txfFadNr.clear();
            txfPlads.clear();
            txfLeverandør.clear();
            txfFadtype.clear();
            txfFadSize.clear();
            txfLager.clear();
            txaDest.clear();
        }
    }

    private void createAction() {
        CreateFadWindow window = new CreateFadWindow("Lav fad");
        window.showAndWait();
        lvwFade.getItems().setAll(Controller.getFad());
        updateControls();
    }

    private void updateAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            CreateFadWindow window = new CreateFadWindow("Opdater fad", fad);
            window.showAndWait();
            lvwFade.getItems().setAll(Controller.getFad());
            updateControls();
        }
    }

    private void tilknytAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        if (fad != null) {
            TilknytFadWindow window = new TilknytFadWindow("Tilknyt fad til lager", fad);
            window.showAndWait();
            lvwFade.getItems().setAll(Controller.getFad());
            updateControls();
        }
    }

    private void omhaeldAction() {

    }

}
