package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Destillering;

import java.time.LocalDate;
import java.time.chrono.Chronology;

public class CreateDestilleringsWindow extends Stage {
    private Destillering destillering;
    private TextField txfMaltBatch, txfKornSort, txfMedarbejder, txfMaengde, txfAlkoholProcent;
    private DatePicker datePickerSlutDato, datePickerStartDato;

    public CreateDestilleringsWindow(String title, Destillering destillering) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.destillering = destillering;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public CreateDestilleringsWindow(String title) {
        this(title,null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblMaltBatch = new Label("MaltBatch");
        pane.add(lblMaltBatch,0,0);

        txfMaltBatch = new TextField();
        pane.add(txfMaltBatch,0,1);

        Label lblKornsort = new Label("Kornsort");
        pane.add(lblKornsort,1,0);

        txfKornSort = new TextField();
        pane.add(txfKornSort,1,1);

        Label lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder,0,2);

        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder,0,3);

        Label lblMaengde = new Label("Maengde");
        pane.add(lblMaengde,1,2);

        txfMaengde = new TextField();
        pane.add(txfMaengde,1,3);

        Label lblStartDato = new Label("StartDato");
        pane.add(lblStartDato, 0, 4);

        datePickerStartDato = new DatePicker();
        pane.add(datePickerStartDato, 0, 5);

        Label lblSlutDato = new Label("SlutDato");
        pane.add(lblSlutDato, 1, 4);

        datePickerSlutDato = new DatePicker();
        pane.add(datePickerSlutDato, 1, 5);

        Label lblAlkholProcent = new Label("Alkoholprocent");
        pane.add(lblAlkholProcent, 0, 6);

        txfAlkoholProcent = new TextField();
        pane.add(txfAlkoholProcent, 0, 7);

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 8);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 1, 8);
        butCan.setOnAction(actionEvent -> hide());


        this.initControls();

    }

    private void initControls() {
        if (destillering != null) {
            txfMaltBatch.setText(destillering.getMaltBatch());
            txfMaengde.setText(String.valueOf(destillering.getMaengde()));
            txfKornSort.setText(destillering.getKornsort());
            txfMedarbejder.setText(destillering.getMedarbejder());
            txfAlkoholProcent.setText(String.valueOf(destillering.getAlkoholProcent()));
            datePickerStartDato.setValue(destillering.getStartDato());
            datePickerSlutDato.setValue(destillering.getSlutDato());
        } else {
            txfMaltBatch.clear();
            txfMaengde.clear();
            txfMedarbejder.clear();
            txfKornSort.clear();
            txfAlkoholProcent.clear();
        }
    }


    private void addOkAction() {
        try {
            String maltBatch = txfMaltBatch.getText();
            String kornSort = txfKornSort.getText();
            double maengde = Double.parseDouble(txfMaengde.getText());
            String medarbejder = txfMedarbejder.getText();
            double alkoholProcent = Double.parseDouble(txfAlkoholProcent.getText());
            LocalDate startDato = datePickerStartDato.getValue();
            LocalDate slutDato = datePickerSlutDato.getValue();
            if (slutDato.isBefore(startDato)) {
                throw new NullPointerException("Slut dato er før start dato");
            }
            if (destillering == null) {
                Controller.createDestillering(startDato, slutDato, maltBatch, kornSort, medarbejder, maengde, alkoholProcent);
            } else {
                Controller.updateDestillering(destillering, startDato, slutDato, maltBatch, kornSort, medarbejder, maengde, alkoholProcent);
            }
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
