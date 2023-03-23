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
import model.Lager;

public class TilknytFadWindow extends Stage {

    private ComboBox<Lager> cbbLager;
    private TextField txfPlads;
    private Fad fad;

    public TilknytFadWindow(String title, Fad fad) {
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

        Label lblLager = new Label("Lager");
        pane.add(lblLager,0,0);

        cbbLager = new ComboBox<>();
        pane.add(cbbLager,0,1);
        cbbLager.getItems().addAll(Controller.getLager());

        Label lblPlads = new Label("Plads");
        pane.add(lblPlads,1,0);

        txfPlads = new TextField();
        pane.add(txfPlads,1,1);

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 2);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 1, 2);
        butCan.setOnAction(actionEvent -> hide());

        this.initControls();
    }

    private void addOkAction() {
        try {
            Lager lager = cbbLager.getValue();
            int plads = Integer.parseInt(txfPlads.getText());
            Controller.addFadToLager(fad,lager,plads);
            hide();
        } catch (NullPointerException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Udfyld alle felter ");
            dialog.showAndWait();
        }
    }

    private void initControls() {
        if (fad.getLager() != null) {
            cbbLager.getSelectionModel().select(fad.getLager());
            txfPlads.setText(String.valueOf(fad.getPlads()));
        } else {
            cbbLager.getSelectionModel().clearSelection();
            txfPlads.clear();
        }
    }


}
