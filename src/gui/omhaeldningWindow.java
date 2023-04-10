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
import model.Paafyldning;

import java.time.LocalDate;

public class omhaeldningWindow extends Stage {

    private ListView<Fad> lvwFad;
    private DatePicker datePicker;
    private Fad fad;
    private TextField txfMaengde;

    public omhaeldningWindow(String title, Fad fad) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.fad = fad;

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


        lvwFad = new ListView<>();
        lvwFad.setPrefHeight(200);
        lvwFad.getItems().setAll(Controller.getIkkeFyldteFade());
        pane.add(lvwFad,0,0);

        Label lblDato = new Label("Dato for omhældning");
        pane.add(lblDato,0,1);

        datePicker = new DatePicker();
        pane.add(datePicker,0,2);

        LocalDate minDate =  fad.getOriginalPaafyldningsDato();
        datePicker.setDayCellFactory(d -> new DateCell() {
            @Override public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(minDate));
            }});

        Label lblMaengde = new Label("Mængde der omhældes");
        pane.add(lblMaengde,0,3);

        txfMaengde = new TextField();
        pane.add(txfMaengde,0,4);

        Button btnOk = new Button("Ok");
        pane.add(btnOk,0,5);
        btnOk.setOnAction(event -> okAction());
    }

    private void okAction() {
        Fad fad1 = lvwFad.getSelectionModel().getSelectedItem();
        try {
            for (Paafyldning p : fad.getPaafyldninger()) {
                p.omhaeldTilFad((Double.parseDouble(txfMaengde.getText())/fad.getPaafyldninger().size()), datePicker.getValue(),fad1);
            }
            close();
        } catch (NullPointerException | IllegalArgumentException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Fejl");
            dialog.showAndWait();
        }
    }
}
