package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fad;
import model.Flaske;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreateFlaskeWindow extends Stage {

    private TextField txfNavn, txfAlkoholProcent, txfFlaskeStoerrelse, txfFortyndingsmaengde, txfVandtype, txfBeskrivelse, txfWhiskyMaengde;
    private DatePicker datePickerTapning;
    private TextArea txaBeskrivelse;
    private ListView<Fad> lvwFadeSomHarLagretI3Aar, lvwFadeDerBruges;
    private Label lblFyldt;
    private HashMap<Fad, Double> fadeDerBruges = new HashMap<>();
    private double vand = 0;
    private double fyldt = 0;

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
        pane.add(lblFlaskeStoerrelse, 0, 2);
        txfFlaskeStoerrelse = new TextField();
        pane.add(txfFlaskeStoerrelse, 0, 3);
        txfFlaskeStoerrelse.textProperty().addListener(event -> this.fyldtAendret());

        Label lblFortyndingsmaengde = new Label("Fortyndingsmængde");
        pane.add(lblFortyndingsmaengde, 1, 2);
        txfFortyndingsmaengde = new TextField();
        pane.add(txfFortyndingsmaengde, 1, 3);
        txfFortyndingsmaengde.textProperty().addListener(event -> this.fyldtAendret());

        Label lblVandtype = new Label("Vandtype");
        pane.add(lblVandtype, 0, 4);
        txfVandtype = new TextField();
        pane.add(txfVandtype, 0, 5);

        Label lblDatoForTapning = new Label("Dato for tapning");
        pane.add(lblDatoForTapning, 1, 4);
        datePickerTapning = new DatePicker();
        pane.add(datePickerTapning, 1, 5);

        Label LblBeskrivelse = new Label("Beskrivelse");
        pane.add(LblBeskrivelse, 2, 0);
        //txfBeskrivelse = new TextField();
        //pane.add(txfBeskrivelse, 2, 3);
        txaBeskrivelse = new TextArea();
        pane.add(txaBeskrivelse, 2, 1,1,4);
        txaBeskrivelse.setPrefWidth(300);
        txaBeskrivelse.setPrefHeight(100);

        Label lblFadeLagret3Aar = new Label("Fade der har lagret i 3 år");
        pane.add(lblFadeLagret3Aar,0,6);
        lvwFadeSomHarLagretI3Aar = new ListView<>();
        pane.add(lvwFadeSomHarLagretI3Aar, 0, 7,1,3);
        lvwFadeSomHarLagretI3Aar.setPrefWidth(200);
        lvwFadeSomHarLagretI3Aar.setPrefHeight(200);
        lvwFadeSomHarLagretI3Aar.getItems().setAll(Controller.fadeSomHarLagretI3Aar());

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        pane.add(vBox,1,7);

        Label lblWhisky = new Label("Whisky mængde");
        vBox.getChildren().add(lblWhisky);
        txfWhiskyMaengde = new TextField();
        vBox.getChildren().add(txfWhiskyMaengde);

        Button butWhisky = new Button("Brug whisky");
        vBox.getChildren().add(butWhisky);
        butWhisky.setOnAction(event -> this.addWhiskyAction());

        Button butWhiskyFjern = new Button("Fjern whisky");
        vBox.getChildren().add(butWhiskyFjern);
        butWhiskyFjern.setOnAction(event -> this.fjernWhiskyAction());

        lblFyldt = new Label("Fyldt: " + (vand + fyldt) + "/" + txfFlaskeStoerrelse.getText());
        vBox.getChildren().add(lblFyldt);

        Label lblFadeDerBruges = new Label("Fade der hældes på flasken");
        pane.add(lblFadeDerBruges,2,6);
        lvwFadeDerBruges = new ListView<>();
        pane.add(lvwFadeDerBruges,2,7,1,3);
        lvwFadeDerBruges.setPrefWidth(200);
        lvwFadeDerBruges.setPrefHeight(200);

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 11);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 1, 11);
        butCan.setOnAction(actionEvent -> hide());

    }

    private void fyldtAendret() {
        try {
            fyldt = 0;
            for (Map.Entry<Fad, Double> e : fadeDerBruges.entrySet()) {
                fyldt += e.getValue();
            }
            vand = Double.parseDouble(txfFortyndingsmaengde.getText());
            lblFyldt.setText("Fyldt: " + (vand + fyldt) + "/" + txfFlaskeStoerrelse.getText());
        } catch (NullPointerException | NumberFormatException e) {
            lblFyldt.setText("Fyldt: " + (vand + fyldt) + "/" + txfFlaskeStoerrelse.getText());
        }
    }

    private void addWhiskyAction() {
        Double maengde = Double.parseDouble(txfWhiskyMaengde.getText());
        Fad fad = lvwFadeSomHarLagretI3Aar.getSelectionModel().getSelectedItem();
        try {
            fadeDerBruges.put(fad,maengde);
            lvwFadeDerBruges.getItems().add(fad);
            fyldtAendret();
            txfWhiskyMaengde.clear();
        } catch (NullPointerException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Udfyld alle felter ");
            dialog.showAndWait();
        }
    }
    private void fjernWhiskyAction() {
        Fad fad = lvwFadeDerBruges.getSelectionModel().getSelectedItem();
        try {
            fadeDerBruges.remove(fad);
            lvwFadeDerBruges.getItems().remove(fad);
            fyldtAendret();
        } catch (NullPointerException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Vælg whisky der skal fjernes");
            dialog.showAndWait();
        }
    }

    private void addOkAction() {
        try {
            String navn = txfNavn.getText();
            double alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText());
            double flaskeStoerrelse = Double.parseDouble(txfFlaskeStoerrelse.getText());
            double fortyndingsMaengde = Double.parseDouble(txfFortyndingsmaengde.getText());
            String vandType = txfVandtype.getText();
            String beskrivelse = txaBeskrivelse.getText();
            LocalDate datoForTapning = datePickerTapning.getValue();
            Fad fad = lvwFadeDerBruges.getItems().get(0);
            double whiskyMaengde = fadeDerBruges.get(fad);
            Flaske flaske = Controller.createFlaske(navn, datoForTapning, alkoholProcent, flaskeStoerrelse, fortyndingsMaengde, vandType, beskrivelse, whiskyMaengde, fad);
            if (fadeDerBruges.size() > 1) {
                for (Map.Entry<Fad, Double> e : fadeDerBruges.entrySet()) {
                    Fad fad1 = e.getKey();
                    double maengde = e.getValue();
                    flaske.addFad(maengde,fad1);
                }
            }
            hide();
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Udfyld alle felter ");
            dialog.showAndWait();
        }
    }
}
