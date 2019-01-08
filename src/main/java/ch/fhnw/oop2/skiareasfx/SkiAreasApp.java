package ch.fhnw.oop2.skiareasfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.view.ApplicationUI;

public class SkiAreasApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		RootPM rootPM    = new RootPM();
		Parent rootPanel = new ApplicationUI(rootPM);

		Scene scene = new Scene(rootPanel);
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Heebo");

        // Inspector: remove before final push
		ScenicView.show(scene);

		primaryStage.titleProperty().bind(rootPM.applicationTitleProperty());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
