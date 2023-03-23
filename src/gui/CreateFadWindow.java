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

public class CreateFadWindow extends Stage {
    private Fad fad;
    private TextField txfFadNr, txfFadtype, txfFadSize, txfLeverandør;
    public CreateFadWindow(String title, Fad fad) {
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

    public CreateFadWindow(String title) {
        this(title, null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        //Informationer til fade
        Label lblFadNr = new Label("Fad nummer");
        pane.add(lblFadNr,0,0);

        txfFadNr = new TextField();
        pane.add(txfFadNr,0,1);

        Label lblFadType = new Label("Fadtype");
        pane.add(lblFadType,1,0);

        txfFadtype = new TextField();
        pane.add(txfFadtype,1,1);

        Label lblFadSize = new Label("Fadstørrelse");
        pane.add(lblFadSize,0,2);

        txfFadSize = new TextField();
        pane.add(txfFadSize,0,3);

        Label lblLeverandør = new Label("Leverandør");
        pane.add(lblLeverandør,1,2);

        txfLeverandør = new TextField();
        pane.add(txfLeverandør,1,3);

        Button butOk = new Button("Ok");
        pane.add(butOk, 0, 8);
        butOk.setOnAction(actionEvent -> this.addOkAction());

        Button butCan = new Button("Cancel");
        pane.add(butCan, 3, 8);
        butCan.setOnAction(actionEvent -> hide());


        this.initControls();

    }

    private void initControls() {
        if (fad != null) {
            txfFadNr.setText(String.valueOf(fad.getFadNr()));
            txfLeverandør.setText(fad.getLeverandoer());
            txfFadtype.setText(fad.getFadType());
            txfFadSize.setText(String.valueOf(fad.getFadStoerrelse()));
        } else {
            txfFadNr.clear();
            txfLeverandør.clear();
            txfFadSize.clear();
            txfFadtype.clear();
        }
    }

    private void addOkAction() {
        try {
            int fadNr = Integer.parseInt(txfFadNr.getText());
            String fadType = txfFadtype.getText();
            String leverandør = txfLeverandør.getText();
            int fadStørrelse = Integer.parseInt(txfFadSize.getText());
            if (fad == null) {
                Controller.createFad(fadNr, fadType, leverandør, fadStørrelse);
            } else {
                Controller.updateFad(fad, fadNr, fadType, leverandør, fadStørrelse);
            }
            hide();
        } catch (NullPointerException e) {
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Error");
            dialog.setContentText(e.getMessage());
            dialog.setHeaderText("Udfyld alle felter ");
            dialog.showAndWait();
        }
    }
}
