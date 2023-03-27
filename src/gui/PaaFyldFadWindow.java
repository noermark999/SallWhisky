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
import model.Fad;

import java.time.LocalDate;

public class PaaFyldFadWindow extends Stage {
    private Destillering destillering;
    private ListView<Fad> lvwFade;
    private DatePicker datePickerPaaFyldningsDato;
    private TextField txfMaengde;


    public PaaFyldFadWindow(String title, Destillering destillering) {
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

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);


        lvwFade = new ListView<>();
        pane.add(lvwFade, 0, 0);

        Label lblSlutDato = new Label("Dato for påfyldning");
        pane.add(lblSlutDato, 0, 1);

        datePickerPaaFyldningsDato = new DatePicker();
        pane.add(datePickerPaaFyldningsDato, 0, 2);

        Label lblMaengde = new Label("Maengde");
        pane.add(lblMaengde,0,3);

        txfMaengde = new TextField();
        pane.add(txfMaengde,0,4);

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 8);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 3, 8);
        butCan.setOnAction(actionEvent -> hide());

        this.initControls();
    }

    private void initControls() {
        lvwFade.getItems().setAll(Controller.getFad());
    }

    private void addOkAction() {
        Fad fad = lvwFade.getSelectionModel().getSelectedItem();
        int maengde = Integer.parseInt((txfMaengde.getText()));
        LocalDate datoForPaaFyldning = datePickerPaaFyldningsDato.getValue();
        try {
            Controller.PaaFyldDestillatPaaFad(maengde, datoForPaaFyldning, fad, destillering);
            hide();
        } catch (NullPointerException | IllegalArgumentException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Fejl");
            dialog.showAndWait();
        }
    }
}
