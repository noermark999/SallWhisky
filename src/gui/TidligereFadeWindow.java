package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fad;
import model.Flaske;
import model.Paafyldning;

import java.util.ArrayList;
import java.util.HashSet;

public class TidligereFadeWindow extends Stage {

    private ListView<Fad> lvwFade;

    private Fad fad;

    public TidligereFadeWindow(String title, Fad fad) {
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

        HashSet<Fad> fads = new HashSet<>();
        for (Paafyldning p : fad.getPaafyldninger()) {
            fads.addAll(p.getTidligereFade());
        }

        lvwFade = new ListView<>();
        lvwFade.setPrefHeight(200);
        lvwFade.getItems().setAll(fads);
        pane.add(lvwFade,0,0);
    }
}
