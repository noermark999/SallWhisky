package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
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
