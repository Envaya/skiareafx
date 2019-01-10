package ch.fhnw.oop2.skiareasfx;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.view.ApplicationUI;

public class SkiAreasApp extends Application {
    private final int PREF_MIN_WIDTH = 500;
    private final int PREF_MIN_HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) {
        RootPM rootPM = new RootPM();
        Parent rootPanel = new ApplicationUI(rootPM);
        HBox hBox = new HBox();
//        try {
//            Scene scene = new Scene(hBox, PREF_MIN_WIDTH, PREF_MIN_HEIGHT);
//
//            primaryStage.setScene(scene);
//            primaryStage.showingProperty().addListener((observable, oldValue, showing) -> {
//                if (showing) {
//                    primaryStage.setMinHeight(primaryStage.getHeight());
//                    primaryStage.setMinWidth(primaryStage.getWidth());
//                    primaryStage.setTitle("My mininal size is: W" + primaryStage.getMinWidth() + " H" + primaryStage.getMinHeight());
//                }
//            });
//
//            primaryStage.show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        Scene scene = new Scene(rootPanel);
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Heebo");

        // Inspector: remove before final push
//        ScenicView.show(scene);

        primaryStage.titleProperty().bind(rootPM.applicationTitleProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
//		primaryStage.setFullScreen(true);
//		primaryStage.set
        primaryStage.setMaxHeight(800);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
