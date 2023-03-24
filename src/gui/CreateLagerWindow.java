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
import model.Lager;

public class CreateLagerWindow extends Stage {
    private Lager lager;
    private TextField txfMaxplads, txfLagerNavn;
    public CreateLagerWindow(String title, Lager lager) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.lager = lager;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

    }

    public CreateLagerWindow(String title) {
        this(title, null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);


        Label lblFadNr = new Label("Max antal pladser");
        pane.add(lblFadNr,0,0);

        txfMaxplads = new TextField();
        pane.add(txfMaxplads,0,1);

        Label lblFadType = new Label("Lager navn");
        pane.add(lblFadType,1,0);

        txfLagerNavn = new TextField();
        pane.add(txfLagerNavn,1,1);

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 8);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 3, 8);
        butCan.setOnAction(actionEvent -> hide());


        this.initControls();

    }

    private void initControls() {
        if (lager != null) {
            txfMaxplads.setText(String.valueOf(lager.getMaxPladser()));
            txfLagerNavn.setText(lager.getLagernavn());
        } else {
            txfMaxplads.clear();
            txfLagerNavn.clear();
        }
    }

    private void addOkAction() {
        try {
            int maxPlads = Integer.parseInt(txfMaxplads.getText());
            String navn = txfLagerNavn.getText();
            if (lager == null) {
                Controller.createLager(maxPlads,navn);
            } else {
                Controller.updateLager(lager,maxPlads,navn);
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
