package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fad;

import java.time.LocalDate;

public class CreateFlaskeWindow extends Stage {

    private TextField txfNavn, txfAlkoholProcent, txfFlaskeStoerrelse, txfFortyndingsmaengde, txfVandtype, txfBeskrivelse, txfWhiskyMaengde;
    private DatePicker datePickerTapning;
    private TextArea beskrivelse;
    private ListView<Fad> lvwFadeSomHarLagretI3Aar;

    public CreateFlaskeWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0, 0);
        txfNavn = new TextField();
        pane.add(txfNavn, 0, 1);

        Label lblAlkohol = new Label("Alkohol procent");
        pane.add(lblAlkohol, 1, 0);
        txfAlkoholProcent = new TextField();
        pane.add(txfAlkoholProcent, 1, 1);

        Label lblFlaskeStoerrelse = new Label("Flaske Størrelse");
        pane.add(lblFlaskeStoerrelse, 2, 0);
        txfFlaskeStoerrelse = new TextField();
        pane.add(txfFlaskeStoerrelse, 2, 1);

        Label lblFortyndingsmaengde = new Label("Fortyndingsmængde");
        pane.add(lblFortyndingsmaengde, 0, 2);
        txfFortyndingsmaengde = new TextField();
        pane.add(txfFortyndingsmaengde, 0, 3);

        Label lblVandtype = new Label("Vandtype");
        pane.add(lblVandtype, 1, 2);
        txfVandtype = new TextField();
        pane.add(txfVandtype, 1, 3);

        Label LblBeskrivelse = new Label("Beskrivelse");
        pane.add(LblBeskrivelse, 2, 2);
        txfBeskrivelse = new TextField();
        pane.add(txfBeskrivelse, 2, 3);

        Label lblWhisky = new Label("Whisky mængde");
        pane.add(lblWhisky, 0, 4);
        txfWhiskyMaengde = new TextField();
        pane.add(txfWhiskyMaengde, 0, 5);

        Label lblDatoForTapning = new Label("Dato for tapning");
        pane.add(lblDatoForTapning, 1, 4);
        datePickerTapning = new DatePicker();
        pane.add(datePickerTapning, 1, 5);

        lvwFadeSomHarLagretI3Aar = new ListView<>();
        pane.add(lvwFadeSomHarLagretI3Aar, 0, 6);
        lvwFadeSomHarLagretI3Aar.setPrefWidth(200);
        lvwFadeSomHarLagretI3Aar.setPrefHeight(200);
        lvwFadeSomHarLagretI3Aar.getItems().setAll(Controller.fadeSomHarLagretI3Aar());

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 8);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 1, 8);
        butCan.setOnAction(actionEvent -> hide());

    }

    private void addOkAction() {
        try {
            String navn = txfNavn.getText();
            double alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText());
            double flaskeStoerrelse = Double.parseDouble(txfFlaskeStoerrelse.getText());
            double fortyndingsMaengde = Double.parseDouble(txfFortyndingsmaengde.getText());
            double whiskyMaengde = Double.parseDouble(txfWhiskyMaengde.getText());
            String vandType = txfVandtype.getText();
            String beskrivelse = txfBeskrivelse.getText();
            LocalDate datoForTapning = datePickerTapning.getValue();
            Fad fad = lvwFadeSomHarLagretI3Aar.getSelectionModel().getSelectedItem();
            Controller.createFlaske(navn, datoForTapning, alkoholProcent, flaskeStoerrelse, fortyndingsMaengde, vandType, beskrivelse, whiskyMaengde, fad);
            hide();
        } catch (NullPointerException | NumberFormatException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Udfyld alle felter ");
            dialog.showAndWait();
        }
    }
}
