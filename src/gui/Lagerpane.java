package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Fad;
import model.Lager;

import java.util.Map;

public class Lagerpane extends GridPane {

    private ListView<Lager> lvwLagre;
    private ListView<Fad> lvwFade;

    public Lagerpane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lvwLagre = new ListView<>();
        this.add(lvwLagre, 0, 0);
        lvwLagre.setPrefWidth(200);
        lvwLagre.setPrefHeight(200);
        lvwLagre.getItems().setAll(Controller.getLager());

        lvwFade = new ListView<>();
        this.add(lvwFade, 1, 0);
        lvwFade.setPrefWidth(200);
        lvwFade.setPrefHeight(200);

        ChangeListener<Lager> listener = (ov, oldCompny, newCompany) -> this.selectedLagerChanged();
        lvwLagre.getSelectionModel().selectedItemProperty().addListener(listener);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        this.add(hBox,0,1);

        Button btnOpretLager = new Button("Opret Lager");
        hBox.getChildren().add(btnOpretLager);
        btnOpretLager.setOnAction(event -> this.OpretLagerAction());

        Button btnUpdateLager = new Button("Opdater Lager");
        hBox.getChildren().add(btnUpdateLager);
        btnUpdateLager.setOnAction(event -> this.updateAction());
    }

    private void OpretLagerAction() {
        CreateLagerWindow window = new CreateLagerWindow("Lav fad");
        window.showAndWait();
        updateControls();
    }

    private void updateAction() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        if (lager != null) {
            CreateLagerWindow window = new CreateLagerWindow("Opdater fad", lager);
            window.showAndWait();
            updateControls();
        }
    }

    private void selectedLagerChanged() {
        this.updateControls();
    }

    public void updateControls() {
        Lager lager = lvwLagre.getSelectionModel().getSelectedItem();
        lvwFade.getItems().clear();
        if (lager != null) {
            for (Map.Entry<Integer, Fad> entry : lager.getFade().entrySet()) {
                lvwFade.getItems().add(entry.getValue());
            }
        }
    }
}
