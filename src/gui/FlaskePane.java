package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Flaske;

public class FlaskePane extends GridPane {
    private ListView<Flaske> lvwFlasker;

    public FlaskePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lvwFlasker = new ListView<>();
        this.add(lvwFlasker, 0, 0);
        lvwFlasker.setPrefHeight(200);
        lvwFlasker.setPrefWidth(200);
        lvwFlasker.getItems().setAll(Controller.getFlasker());

        ChangeListener<Flaske> listener = (ov, oldCompny, newCompany) -> this.selectedFlaskeChanged();
        lvwFlasker.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private void selectedFlaskeChanged() {
        this.updateControls();
    }

    private void updateControls() {

    }
}
