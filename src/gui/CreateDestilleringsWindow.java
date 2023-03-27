package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Destillering;

public class CreateDestilleringsWindow extends Stage {
    private Destillering destillering;

    private TextField txfMaltBatch, txfKornSort, txfMedarbejder, txfMaengde, txfAlkoholProcent;


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

        Label lblAlkholProcent = new Label("Alkoholprocent");

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 8);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 3, 8);
        butCan.setOnAction(actionEvent -> hide());


        this.initControls();

    }

    private void initControls() {
        if (destillering != null) {
            txfMaltBatch.setText(destillering.getMaltBatch());
            txfMaengde.setText(String.valueOf(destillering.getMaengde()));
            txfKornSort.setText(destillering.getKornsort());
            txfMedarbejder.setText(destillering.getMedarbejder());
        } else {
            txfMaltBatch.clear();
            txfMaengde.clear();
            txfMedarbejder.clear();
            txfKornSort.clear();
        }
    }


    private void addOkAction() {
        try {
            int fadNr = Integer.parseInt(txfMaltBatch.getText());
            String fadType = txfKornSort.getText();
            String leverandør = txfMaengde.getText();
            int fadStørrelse = Integer.parseInt(txfMedarbejder.getText());
            if (destillering == null) {
                Controller.createFad(fadNr, fadType, leverandør, fadStørrelse);
            } else {
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
