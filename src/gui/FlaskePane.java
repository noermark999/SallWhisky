package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class FlaskePane extends GridPane {

    public FlaskePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
    }
}
