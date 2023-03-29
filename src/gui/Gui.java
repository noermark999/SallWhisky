package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {

    @Override
    public void init() {
        Controller.initContent();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sall Whisky 1.0");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabLager = new Tab("Lagre");
        tabPane.getTabs().add(tabLager);

        Lagerpane lagerpane = new Lagerpane();
        tabLager.setContent(lagerpane);

        Tab tabFad = new Tab("Fade");
        tabPane.getTabs().add(tabFad);

        FadPane fadPane = new FadPane();
        tabFad.setContent(fadPane);

        Tab tabDestillering = new Tab("Destilleringer");
        tabPane.getTabs().add(tabDestillering);

        DestilleringPane destilleringPane = new DestilleringPane();
        tabDestillering.setContent(destilleringPane);

        Tab tabFlaske = new Tab("Flasker");
        tabPane.getTabs().add(tabFlaske);

        FlaskePane flaskePane = new FlaskePane();
        tabFlaske.setContent(flaskePane);
    }
}
